package site.boot.socket;

//남이 쓴 채팅 내가 보기
//socket 통신 시 송/수신 역할을 하는 클래스
public class Greeting {

	private String content;
	
	//생성자에서 초기화
	public Greeting(String content) {
		this.content = content;
	}
	
	public String getContent() {
		return this.content;
	}
}
