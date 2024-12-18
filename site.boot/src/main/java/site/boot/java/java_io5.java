package site.boot.java;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

//FileReader + BufferedReader => ArrayList
public class java_io5 {

	public static void main(String[] args) {
		String url = "D:\\boot_gradle\\site.boot\\src\\123.txt";
		try {
			FileReader fr = new FileReader(url);
			BufferedReader br = new BufferedReader(fr);
			ArrayList<String> data = new ArrayList<>();
			String word = "";	//BufferedReader에서 라인 별로 읽은 값을 ArrayList에 전달하기 위한 변수
			while((word = br.readLine()) != null) {
				data.add(word + "\n");
			}
			System.out.println(data);
			
			br.close();
			fr.close();
			
			class2 cl2 = new class2();
			cl2.abc();
		} catch (Exception e) {
			System.out.println("해당 파일 경로를 확인할 수 없습니다.");
		}
		
	}

}

//외부 클래스
class class2{
	
	public void abc() {
		String user = "홍길동";
		byte data[] = user.getBytes();	//String -> byte 형태로 변환
		System.out.println(data);
		
		String username = new String(data);	//byte -> String 변환
		System.out.println(username);
	}
}

