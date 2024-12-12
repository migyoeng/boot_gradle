package site.boot.api;

import java.io.PrintWriter;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletResponse;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
public class api_controller {
	
	@Resource(name="api_DTO")
	api_DTO dto;
	
	PrintWriter pw = null;
	
	//private api_service api_service;
	@Autowired
	api_service api_service;
	
	//@Patch : 업데이트
	//@Put : 데이터 입력
	//@Delete : 데이터 삭제
	//@Get, @Post
	//@Header : 
	
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
