package site.boot.api;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

import org.apache.commons.compress.utils.IOUtils;
import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPClientConfig;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.messaging.handler.annotation.support.MethodArgumentTypeMismatchException;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.client.j2se.MatrixToImageConfig;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;

import jakarta.annotation.Resource;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@CrossOrigin(origins = "*", allowedHeaders = "*")
//RestController : View JSTL, JSP 로드가 힘들다(ModelAndView를 사용하면 가능)
@RestController
public class api_controller {
	//@Patch : 업데이트
	//@Put : 데이터 입력
	//@Delete : 데이터 삭제
	//@Get, @Post
	//@Header
	
	@Resource(name="api_DTO")
	api_DTO dto;
	
	PrintWriter pw = null;
	
	//private api_service api_service;
	@Autowired
	api_service api_service;
	
	//properties의 값을 가져오는 역할을 하는 어노테이션
	@Value("${domain}")
	private String domain;
	
	@GetMapping("/ajax/qrscan.do")	//QR 스캔하여 접속되는 메소드
	public ModelAndView qrscan(@RequestParam(name="scode", required = false)String scode, Model m) {
		//System.out.println(scode);
		ModelAndView mv = new ModelAndView("/ajax/qrscan");
		m.addAttribute("scode", scode);
		//SQL select 쿼리문으로 조회
		m.addAttribute("usernm", "홍길동");
		m.addAttribute("delivery", this.domain);
		
		return mv;
	}
	
	//@ExceptionHandler : @PathVariable에 한하여 오류 발생 시 처리하는 어노테이션
	@ExceptionHandler(MethodArgumentTypeMismatchException.class)
	@PutMapping("/ajax/qrmake/{scode}")	//QR 생성
	public String qr_api(@PathVariable(name="scode")String scode, HttpServletRequest req, HttpServletResponse res) throws Exception {
		//System.out.println(scode);
		String url = "http://" + this.domain + ":8081/ajax/qrscan.do?scode=" + scode;
		//System.out.println(url);
		
		int size = 300;	//QR 이미지 가로 세로
		//QR 객체 생성
		BitMatrix bm = new MultiFormatWriter().encode(url, BarcodeFormat.QR_CODE, size, size);
	/*	CODE_128 : barcode
		BitMatrix bm = new MultiFormatWriter().encode(url, BarcodeFormat.CODE_128, size, hsize);
		int size = 200;	//QR 이미지 가로 세로
		int hsize = 50;
	*/
		//QR 코드 색상 세팅
		//0xFF : 색상 코드 시작, 뒤 6자리 : 색상 코드
		int qrcolor = 0xFFFF8629;	//색상 코드는 문자로 인식하지 않는다
		int qrbgcolor = 0xFFFFFFFF;	//QR 배경 색상
		//해당 QR 생성 시 지정 색상을 적용하여 세팅하는 객체
		MatrixToImageConfig mc = new MatrixToImageConfig(qrcolor, qrbgcolor);
		
		//저장 및 이미지를 바로 출력하기 위한 OutputStream
		ByteArrayOutputStream bs = null;
		try {
			bs = new ByteArrayOutputStream();
			//Byte로 이미지를 생성하는 Stream
			MatrixToImageWriter.writeToStream(bm, "PNG", bs, mc);	//png 형태로 저장
			
			//QR 이미지 저장 경로
			String fileurl = req.getServletContext().getRealPath("/qrcode/");
			//QR 생성 이미지를 해당 경로에 저장
			FileCopyUtils.copy(bs.toByteArray(), new File(fileurl + scode + ".png"));
			bs.flush();
			
			//Front-end에게 전달하여 이미지 및 경로를 출력할 수 있도록 세팅
			this.pw = res.getWriter();
			//this.pw.print(fileurl + scode + ".png");
			this.pw.print("http://"+ this.domain +":8081/qrcode/" + scode + ".png");
			
		} catch (Exception e) {
			this.pw.print("error");
			System.out.println("QR 라이브러리 생성 오류");
		}
		
		return null;
	}
	
	//@ResponseBody : return 결과값을 즉시 실행 형태로 구성
	@GetMapping("/ajax/cdn_fileview/{filenm}")
	public @ResponseBody byte[] cdn_ftp2(@PathVariable(name="filenm") String filenm, ServletResponse res) throws Exception {
		//System.out.println(filenm);
		
		//String imgurl = "http://34.64.70.220/cdn/BACK1.jpg";	//DB에 CDN Server 이미지 경로라는 가정
		String imgurl = null;
		if(filenm.equals("BACK1")) {
			imgurl = "http://34.64.70.220/cdn/BACK1.jpg";
		} else if(filenm.equals("BACK2")) {
			imgurl = "http://34.64.70.220/cdn/BACK2.jpg";
		}
		
		byte[] img = null;	//Front에서 요청한 이미지 파일명 저장
		try {
			URL url = new URL(imgurl);
			//HttpURLConnection : http, HttpsURLConnection : https 
			HttpURLConnection httpcon = (HttpURLConnection)url.openConnection();
			InputStream is = httpcon.getInputStream();	//해당 경로에 있는 파일의 전체 용량을 읽어서 출력하는 역할
			img = IOUtils.toByteArray(is);	//byte[]로 변환하여 출력시킴
			
			is.close();				//IO 종료
			httpcon.disconnect();	//HttpURLConnection 프로토콜 종료
		} catch (Exception e) {
			System.out.println("CDN URL 연결 실패");
		}
		return img;
	}
	
	
	//CDN Server로 파일 전송 - (ftp 접속 정보는 확인 되어야 한다)
	@PostMapping("/ajax/cdn_fileok.do")
	public String cdn_ftp(@RequestParam(name="mfile") MultipartFile mfile) {
		//System.out.println(mfile.getOriginalFilename());
		//34.64.70.220
		//jeong
		//9021
		FTPClient ftp = new FTPClient();	//외부에서 FTP 서버로 접속하는 메소드
		ftp.setControlEncoding("utf-8");	//FTP 전송 언어셋
		FTPClientConfig cf = new FTPClientConfig();	//FTP 환경 설정을 위한 메소드
		try {
			String filenm = mfile.getOriginalFilename();	//사용자가 업로드하는 파일명을 읽어들인다
			String host = "34.64.70.220";
			String user = "jeong";
			String pass = "wjd01245516";
			int port = 9021;
			ftp.configure(cf);
			ftp.connect(host, port);
			if(ftp.login(user, pass)) {
				//FTP.BINARY_FILE_TYPE : 이미지, 동영상, ppt, zip, pdf, ..
				//FTP.ASCII_FILE_TYPE : .html, .css, .js, .txt, .jsp, ..
				ftp.setFileType(FTP.BINARY_FILE_TYPE);
				ftp.enterLocalPassiveMode();	//passive 모드로 세팅되어 있는 경우
				int result = ftp.getReplyCode();	//status == 200 : 정상
				//System.out.println(result);	//230 - 접속이 제대로 된 경우
				/*
				if(result == 200) {
					ftp.makeDirectory("/home/jeong/img");	//새로운 디렉토리 생성
					ftp.removeDirectory("//home/jeong/hack");	//디렉토리 삭제
				}
				*/
				if(result == 200) {
					//FTP 기존 파일 삭제
					//ftp.deleteFile("/home/jeong/cdn/2024.png");	//2024.png 파일을 삭제
					
					boolean ok = ftp.storeFile("/home/jeong/cdn/"+filenm, mfile.getInputStream());	//FTP 접속 경로
					//System.out.println(ok);	//false
					if(ok == true) {
						System.out.println("정상적으로 업로드 되었습니다.");
					} else {
						System.out.println("퍼미션 및 FTP 경로가 잘못 지정되었습니다.");
					}
				}
			}
			
		} catch (Exception e) {
			System.out.println("FTP 접속 정보가 올바르지 않거나 해당 경로가 잘못 되었습니다.");
		} finally {
			try {
				ftp.disconnect();
			} catch (Exception e2) {
				System.out.println("서버 루프가 발생하여 접속 종료가 발생되었습니다.");
			}
		}
		
		return null;
	}
	
	
	
	@PatchMapping("/ajax/event-update/{eidx}")
	public String event_update(HttpServletResponse res, @PathVariable(name="eidx") String eidx) {
		String calldata[] = eidx.split(",");
		//System.out.println(eidx);
		//System.out.println(calldata[0]);
		//System.out.println(calldata[1]);
		try {
			this.pw = res.getWriter();
			dto.setEIDX(Integer.parseInt(calldata[0]));
			dto.setMEMAIL(calldata[1]);
			int result = api_service.api_update(dto);
			if(result == 0) {
				//실패
				this.pw.print("error");
			} else {
				//성공
				this.pw.print("ok");
			}
		} catch (Exception e) {
			this.pw.print("error");
		} finally {
			this.pw.close();
		}
		return null;
	}
	
	@GetMapping("/ajax/event_all")
	public String event_all(HttpServletResponse res, @PathVariable(name="eidx") String eidx) {
		System.out.println("API 연결 확인");
		try {
			this.pw = res.getWriter();
			List<api_DTO> all = api_service.api_alldata();
			//Front-end : event_member 대표키 각 키 별로 데이터 형태의 배열로 api 만들어주라는 의뢰..
			JSONObject jo = new JSONObject();	//대표키
			JSONArray ja = new JSONArray();	//배열
			for(api_DTO data : all) {
				JSONObject jj = new JSONObject();
				jj.put("eidx", data.getEIDX());
				jj.put("mname", data.getMNAME());
				jj.put("tel_no", data.getTEL_NO());
				jj.put("memail", data.getMEMAIL());
				jj.put("event_date", data.getEVENT_DATE().substring(0,10));
				ja.put(jj);
			}
			jo.put("event_member", ja);
			this.pw.print(jo);
			
		} catch (Exception e) {
			this.pw.print("error");
		} finally {
			this.pw.close();
		}
		return null;
	}
	
	//GET 배열 생성 JSONArray, JSONObject
/*
	  ["SK C&C 채용연계 교육생 모집","헤럴드 경제"]
	  ["오픈 AI Chat GPT 먹통","머니 S"]
	  
	  Front-end => 2차 배열 형태 대표키 : "news"
	  {
	  	"news" : [
	  		["SK C&C 채용연계 교육생 모집","헤럴드 경제"]
	  		["오픈 AI Chat GPT 먹통","머니 S"]
	  	]
	  }
*/
	@GetMapping("/api/list")
	public String list(HttpServletResponse res) {
		
		return null;
	}
	
	
}
