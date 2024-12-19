package site.boot.socket;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.util.HtmlUtils;

@Controller
public class ChatController {

	@CrossOrigin(origins = "*", allowedHeaders = "*")
	@MessageMapping("/conchat")	//메세지를 받는 API, @MessageMapping : socket 전용 mapping
	@SendTo("/room/geeting")	//socket 통일을 하기 위한 메소드
	public Greeting geeting(ChatDTO dto) throws Exception {
		Thread.sleep(1000);	//1000ms
		String mid = HtmlUtils.htmlEscape(dto.getName());
		String msg = HtmlUtils.htmlEscape(dto.getMsg());
		
		System.out.println(dto.getName());
		System.out.println(dto.getMsg());
		
		//해당 소켓 room에 접속하는 모든 사용자에게 메세지를 전송하는 상태
		return new Greeting(mid + ":" + msg);
	}
}
