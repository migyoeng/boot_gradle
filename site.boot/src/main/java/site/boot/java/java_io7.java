package site.boot.java;

import java.io.FileInputStream;
import java.io.FileOutputStream;

//Stream 활용법(FileInputStream, FileOutputStream) => byte
public class java_io7 {

	public static void main(String[] args) {
		
		try {
			String url = "D:\\boot_gradle\\site.boot\\src\\123.txt";
			/*
			File f = new File(url);
			FileInputStream fs = new FileInputStream(f);
			*/
			FileInputStream fs = new FileInputStream(url);
			//available() : 파일 전체 용량
			System.out.println(fs.available());	//용량 출력
			//byte[] word = new byte[fs.available()];
			byte[] word = new byte[1024*2];	//1024 = 1KB
			fs.read(word);	//byte에 있는 배열의 숫자를 모두 읽어들임
			
			String print = new String(word);	//byte -> String
			System.out.println(print);	//출력
			fs.close();
			
			new fs2().abc();
		} catch (Exception e) {
			
		}
		
	}

}

//FileOutputStream : Writer 쓰기
class fs2{
	public void abc() throws Exception {
		String url = "D:\\boot_gradle\\site.boot\\src\\agree.txt";
		String word = "[개인정보 보호 정책]";
		
		//true: 기존 내용이 날아가지 않음, 이어쓰기 / false : 새로운 데이터로 생성, 덮어쓰기
		//FileOutputStream fo = new FileOutputStream(url, true);
		FileOutputStream fo = new FileOutputStream(url);
		byte[] data = word.getBytes();
		
		fo.write(data);
		fo.close();
	}
}
