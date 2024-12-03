package site.boot;

import java.io.PrintWriter;

import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.servlet.http.HttpServletResponse;

@Controller
public class ajax_controller {
	
	PrintWriter pw = null;

	
	//JS - AJAX(POST) - Front FormData 전송 (동일 key 값 전송 시 ------WebKitFormBoundary 형태 구조)
	@PostMapping("/ajax/ajax4.do")
	public String ajax4(@RequestBody String product, HttpServletResponse res) throws Exception {
		this.pw = res.getWriter();
		try {
			System.out.println(product);
			this.pw.print("ok");
			
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
