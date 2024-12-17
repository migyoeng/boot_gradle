package site.boot.java;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

//Files 로 해당 내용을 저장하는 방식
//Files (nio) => Web에서 사용하지 않는다, 경로 입력 시 \\
//io(Web)
public class java_io4 {

	public static void main(String[] args) throws Exception {
		
		
		//클래스 배열을 이용하여 전체 라인을 읽어와(readAllLines) 데이터를 저장하는 방식 - 문자 형태
		List<String> data = Files.readAllLines(Paths.get("D:\\boot_gradle\\site.boot\\src\\123.txt"));
		System.out.println(data);

		//해당 파일의 모든 라인을 읽은 후 byte 단위로 저장한다 - 이미지 형태
		byte[] data2 = Files.readAllBytes(Paths.get("D:\\boot_gradle\\site.boot\\src\\123.txt"));
		System.out.println(new String(data2));
		//System.out.println(data2.toString());	//String 형태로 출력되지 않는다
	}

}
