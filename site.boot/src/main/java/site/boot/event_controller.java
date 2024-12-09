package site.boot;

import java.io.PrintWriter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import jakarta.annotation.Resource;
import jakarta.servlet.ServletResponse;
import site.DTO.event_DTO;
import site.Service.event_service;

@Controller
@ComponentScan(basePackages = {"site.*"})
public class event_controller {
	
	@Resource(name="event_DTO")
	event_DTO dto;

	@Autowired
	event_service os;
	
	PrintWriter pw = null;
	
	@GetMapping("/event/event_list.do")
	public String event_list() {
		
		return null;
	}
	
	@PostMapping("/event/eventok.do")
	public String event_join(@ModelAttribute event_DTO dto, ServletResponse res) {
		res.setContentType("text/html;charset=utf-8");
		try {
			this.pw = res.getWriter();
			Integer result = os.event_join(dto);
			if(result > 0) {
				this.pw.print("<script>"
						+ "alert('정상적으로 이벤트에 참여되었습니다.');"
						+ "location.href='./event_list.do';"
						+ "</script>");
			}
			else {
				this.pw.print("<script>"
						+ "alert('서비스 오류로 인하여 잠시 후 다시 참여해주시길 바랍니다.');"
						+ "location.href='./event_list.do';"
						+ "</script>");
			}
		} catch (Exception e) {
			System.out.println(e);
		} finally {
			this.pw.close();
		}
		return null;
	}
	
}
