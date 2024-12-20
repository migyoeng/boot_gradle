package site.boot.thymeleaf;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import jakarta.servlet.http.HttpServletRequest;

@Controller
public class ThymeleafController {
	
	String copyright = "Copyright @ 2024 Maker By abc.co.kr All Reserved.";
	
	@GetMapping("/leaf.do")
	public String leaf() {
		
		return "/leaf.html";
	}
	
	//실제 메인화면, 로그인 페이지 - GetMapping을 활용한 복합 페이지
	@GetMapping(value = {"/mainpage.do","/login.do"})
    public String mainpage(Model m, HttpServletRequest req) {
       //System.out.println(req.getServletPath());
       m.addAttribute("copyright",this.copyright);
       
       Map<String, String> mdlist = new HashMap<>();
       mdlist.put("productnm", "삼성 냉장고");
       mdlist.put("money", "680,000");         
       m.addAttribute("mdlist",mdlist);
       
       List<String> board = new ArrayList<>();
       board.add("공지사항1");
       board.add("공지사항2");
       board.add("공지사항3");
       board.add("공지사항4");
       
       m.addAttribute("board",board);
       
       //해당 URL의 .do 속성을 삭제 후 적용하는 코드
       String webpage = req.getServletPath().replace(".do", "");
       return "/" + webpage + ".html";	//.html 사용 시 thymeleaf 가동
    }
	
	@GetMapping("/subpage.do")
	public String subpage(Model m) {
		m.addAttribute("copyright", this.copyright);
		return "/subpage.html";
	}

	@GetMapping("/layout.do")
    public String layout(Model m) {
       
       m.addAttribute("copyright",this.copyright);
       
       Map<String, String> mdlist = new HashMap<>();
       mdlist.put("productnm", "삼성 냉장고");
       mdlist.put("money", "680,000");         
       m.addAttribute("mdlist",mdlist);
       
       List<String> board = new ArrayList<>();
       board.add("공지사항1");
       board.add("공지사항2");
       board.add("공지사항3");
       board.add("공지사항4");
       
       m.addAttribute("board",board);
       
       
       return "/layout.html";
    }

}
