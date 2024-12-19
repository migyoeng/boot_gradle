package site.boot.socket;

import lombok.Data;

//내가 쓴 채팅 내가 보기
@Data
public class ChatDTO {

	public String name;	//이름
	public String msg;	//메세지
	
	public ChatDTO() {
		
	}
	public ChatDTO(String name, String msg) {
		this.name = name;
		this.msg = msg;
	}	
}
