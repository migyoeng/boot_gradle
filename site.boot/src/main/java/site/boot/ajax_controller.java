package site.boot;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import jakarta.servlet.http.HttpServletResponse;

@Controller
public class ajax_controller {
	
	PrintWriter pw = null;
	
	//ECMA - AJAX(POST) - Front-end FormData 전송 형태
	@RequestMapping(value = "/ecma/ajax11.do", method = RequestMethod.POST)
	public String ajax11(@RequestParam("data") String[] data, HttpServletResponse res) throws Exception {
		ArrayList<String> all = new ArrayList<>(Arrays.asList(data));
		System.out.println(all);
		try {
			this.pw = res.getWriter();
			this.pw.print("ok");
		} catch (Exception e) {
			this.pw.print("error");
		} finally {
			this.pw.close();
		}
		return null;
	}
	
	//ECMA - AJAX(POST) - 기본 형태, JSON.stringify
	@RequestMapping(value = "/ecma/ajax10.do", method = RequestMethod.POST)
	//POST 기본으로 name을 적용하여 통신할 경우
	//public String ajax10(@RequestParam("list") String list, HttpServletResponse res) throws Exception {
	//POST 기본으로 JSON.stringify 적용하여 통신할 경우
	public String ajax10(@RequestBody String list, HttpServletResponse res) throws Exception {
		System.out.println(list);
		try {
			this.pw = res.getWriter();
			this.pw.print("ok");
		} catch (Exception e) {
			this.pw.print("error");
		} finally {
			this.pw.close();
		}
		return null;
	}
	
	
	//ECMA - AJAX(GET) - Front-end name 값 전송
	@CrossOrigin(origins="*", allowedHeaders = "*")
	@GetMapping("/ecma/ajax9.do")
	public String ajax9(@RequestParam("email") String email, HttpServletResponse res) throws Exception {
		try {
			this.pw = res.getWriter();
			System.out.println(email);
			this.pw.print("ok");
		} catch (Exception e) {
			this.pw.print("error");
		} finally {
			this.pw.close();
		}
		return null;
	}
	
	
	//JQ - AJAX(POST) - Front-end FormData 전송
	@PostMapping("/ajax/ajax8.do")//value = "name값명", defaultValue="기본값", required=false(값을 하나도 넘기지 않은 경우에도 400 에러를 막는다)
	public String ajax8(@RequestParam(value="fdata", defaultValue = "", required = false) String fdata, HttpServletResponse res) throws Exception {
		try {
			this.pw = res.getWriter();
			if(fdata.equals("")) {
				this.pw.print("error");
			}
			else {
				String[] redata = fdata.split(",");
				System.out.println(redata[0]);
				this.pw.print("ok");
			}
		} catch (Exception e) {
			this.pw.print("error");
		} finally {
			this.pw.close();
		}
		return null;
	}
	
	//JQ - AJAX(POST) - Front-end 배열로 전송
	@CrossOrigin(origins="*", allowedHeaders = "*")	//CORS 해제
	@PostMapping("/ajax/ajax7.do")
	public String ajax7(@RequestBody String all_data , HttpServletResponse res) throws Exception {
		
		try {
			this.pw = res.getWriter();
			
			JSONObject jo = new JSONObject(all_data);
				System.out.println(jo.get("fdata"));
				JSONArray ja = (JSONArray)jo.get("fdata");	//[] 배열 체크
				if(ja.length() == 0) {
					this.pw.print("error");
				}
				else {
					System.out.println(ja.get(0));
					this.pw.print("ok");
				}
				//System.out.println(ja.get(0));
			
			this.pw.print("ok");
			
		} catch (Exception e) {
			this.pw.print("error");
		} finally {
			this.pw.close();
		}
		return null;
	}
	
	//JQ - AJAX(GET + JSON.stringify) - Front-end 배열로 전송
	//POST 방식에만 @RequestBody 사용 - GET 방식엔 @RequestBody 를 사용하지 않는다
	@GetMapping("/ajax/ajax6.do")
	public String ajax6(HttpServletResponse res, @RequestParam("no") String no) {
		try {
			this.pw = res.getWriter();
			JSONArray ja = new JSONArray(no);
			int ea = ja.length();
			if(ea == 0) {
				this.pw.print("error");
			}
			else {
				int w = 0;
				do {
					System.out.println(ja.get(w));
					w++;
				}while(w < ea);
				this.pw.print("ok");
			}
		} catch (Exception e) {
			this.pw.print("error");
		} finally {
			this.pw.close();
		}
		return null;
	}
	
	//JQ - AJAX(GET) - Front-end name 값으로 전송
	@GetMapping("/ajax/ajax5.do")
	public String ajax5(HttpServletResponse res, @RequestParam("no")String no) throws Exception {
		System.out.println(no);
		String fdata[] = no.split(",");
		try {
			this.pw = res.getWriter();
			int w = 0;
			while(w < fdata.length) {
				System.out.println(fdata[w]);
				w++;
			}
			//Database 활용
			this.pw.print("ok");
		} catch (Exception e) {
			this.pw.print("error");
		} finally {
			this.pw.close();
		}
		
		return null;
	}

	
	//(동일 key 값 전송 시 ------WebKitFormBoundary 형태 구조)
	//JS - AJAX(POST) - Front FormData 전송
	@PostMapping("/ajax/ajax4.do")
	@ResponseBody
	public String ajax4(@RequestParam("product") String product, @RequestHeader("Basket") String names, HttpServletResponse res) throws Exception {
		this.pw = res.getWriter();
		try {
			if(!names.equals("data")) {
				this.pw.print("error");
			}
			else {
				System.out.println(product);
				this.pw.print("ok");
			}
		} catch (Exception e) {
			this.pw.print("error");
		} finally {
			this.pw.close();
		}
		return null;
	}
	
	
	//JS - AJAX(POST) - JSON.stringify) - Front JSON.stringify 실제 배열 형태로 전달받는 상황일 경우
	//@RequestBody - 배열 값을 받는 전용 어노테이션
	@PostMapping("/ajax/ajax3.do")
	public String ajax3(@RequestBody String data, HttpServletResponse res) throws Exception {
		//JSON -> String 타입으로 받을 경우 어떻게 변환시킬지....
		//JSONArray : 배열 [] -> Front-end에서 new Array()로 보냈을 경우
		//JSONObject : 배열 {} -> Front-end에서 new Object()로 보냈을 경우
		try {
			JSONObject jo = new JSONObject(data);
			int ea = jo.length();	//배열 개수 파악
			if(ea == 0) {
				this.pw.print("error");
			}
			else {
				int w = 0;
				while(w < ea) {
					//jo.get(""); -> String 타입만 들어갈 수 있기 때문에 
					//int 형인 w를 String으로 형 변환
					System.out.println(jo.get(String.valueOf(w)));
					System.out.println(jo.get(Integer.toString(w)));
					w++;
				}
				this.pw = res.getWriter();
				this.pw.print("ok");
			}
		} catch (Exception e) {
			this.pw.print("error");
		} finally {
			this.pw.close();
		}
		return null;
	}
	
	//JS - AJAX(POST)
	@PostMapping("/ajax/ajax2.do")
	public String ajax2(@RequestParam("product") String product, HttpServletResponse res) throws Exception {
		System.out.println(product);
		//GETMapping과 동일
		this.pw = res.getWriter();
		try {
			
		} catch (Exception e) {
			
		} finally {
			this.pw.close();
		}
		return null;
	}
	
	
	//JS - AJAX(GET)
	@GetMapping("/ajax/ajax1.do")
	public String ajax1(@RequestParam(name="product") String product, HttpServletResponse res) throws Exception {
		//AJAX - CORS 를 해결하기 위해 사용하는 방식
		res.addHeader("Access-Control-Allow-Origin", "*");	//* : 모든 IP 전송 허용
		res.addHeader("Access-Control-Allow-Credentials", "true");
		
		this.pw = res.getWriter();
		String data[] = product.split(",");	//Front-end 빈 배열 형태로 전송, 1이 핸들링
		try {
			if(product.equals("")) {	//Front-end 가 값이 하나도 없을 경우
				this.pw.print("error");
			}
			else {	//체크가 하나라도 되었을 경우
				int w = 0;
				while(w < data.length) {
					System.out.println(data[w]);
					w++;
				}
				this.pw.print("ok");
			}
		} catch (Exception e) {
			this.pw.print("error");
		} finally {
			this.pw.close();
		}
	
		return "ok";
	}
}
