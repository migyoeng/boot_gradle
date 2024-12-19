package site.boot.java;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.apache.commons.io.input.ReaderInputStream;

//InputStreamReader, OutputStreamWriter - 변환을 주로 하는 클래스
//InputStream => InputStreamReader 를 거친 다음 => BufferedReader 로 변환
//BufferedReader => ReaderInputStream 를 거친 다음 => InputStream 로 변환

//FileOutputStream => OutputStreamWriter 를 거친 다음 => BufferedWriter
public class java_io11 {

	public static void main(String[] args) throws Exception {
		String url = "D:\\boot_gradle\\site.boot\\src\\agree.data";
		
		//Stream => Reader
		InputStream is = new FileInputStream(url);	//Stream으로 Byte 인식
		System.out.println(is.available());
		//Buffered의 성격을 가지고 있으므로 read() 사용하면 데이터는 loss가 발생하기 시작
		InputStreamReader isr = new InputStreamReader(is);	//Stream -> Reader로 변환
		//System.out.println(isr.read());
		BufferedReader br = new BufferedReader(isr);
		System.out.println(br.readLine());
		br.close();
		isr.close();
		is.close();
		
		//Reader[String] => Stream[Byte]
		FileReader fr = new FileReader(url);
		BufferedReader br2 = new BufferedReader(fr);
		InputStream is2 = new ReaderInputStream(br2);
		System.out.println(is2.read());
		is2.close();
		br2.close();
		fr.close();
		
	}

}
