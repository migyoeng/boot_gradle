package site.boot.java;

import java.io.FileReader;
import java.util.Scanner;

//JAVA I/O 종류에 따른 핸들링
/*
I/O : Reader, Writer, Stream, StreamReader
Writer(쓰기, 저장) => Stream(OutputStream) 변경 가능
Reader(읽기) => Stream(InputStream)

Writer, Reader => ASCII
Stream => 이미지, 동영상, 음악


*/
public class java_io {

	public static void main(String[] args) {
		//파일 입출력 관련 사용 시 무조건
		//try~catch 문 또는 throws Exception 사용
		try {
			//FileReader : 기본 단어를 한 개씩 읽음
			FileReader fr = new FileReader("D:\\boot_gradle\\site.boot\\src\\123.txt");
			Scanner sc = new Scanner(fr);	//한 라인씩 핸들링
			
			while(sc.hasNext()) {
				String word = sc.nextLine();
				System.out.println(word);
				/*
				int m = fr.read();
				System.out.println((char)m);
				if(m == -1) break;
				*/
			}
			sc.close();
			fr.close();
		} catch (Exception e) {
			System.out.println("해당 파일이 없습니다.");
		}
		
		
	}

}
