package site.boot.java;

import java.io.BufferedReader;
import java.io.FileReader;

//WEB I/O => Buffer => Memory 할당
//Reader/Writer => BufferedReader/BufferedWriter
public class java_io3 {
	//Buffered : 캐시 메모리 형태, 조건문에 해당 readLine()을 사용할 경우 한 줄이 할당되어 할당된 데이터는 더이상 재실행 되지 않는다
	//loss가 발생하는 상황이 올 수 있다
	
	//Buffered : 메모리를 이용하여 빠르게 해당 데이터를 입출력할 수 있다
	public static void main(String[] args) throws Exception {
		FileReader fr = new FileReader("D:\\boot_gradle\\site.boot\\src\\123.txt");
		//Buffer 사용할 경우 -> Reader, Writer 사용 후 Buffered Reader/Writer 사용
		BufferedReader bf = new BufferedReader(fr);	//메모리 할당
		//System.out.println(bf.readLine() + "test");
		
		String line = "";	//Buffered에 있는 데이터 값을 문자로 받는 변수
		while((line = bf.readLine()) != null) {
			System.out.println(line);
		}
		
		fr.close();

	}

}
