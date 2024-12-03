package site.boot;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class main_controller {

	@GetMapping("/test.do")
	public String test() {
		System.out.println("test page");
		return null;
	}
}
