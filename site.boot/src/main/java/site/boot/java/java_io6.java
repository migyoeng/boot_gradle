package site.boot.java;

import java.io.RandomAccessFile;
import java.nio.channels.FileChannel;

//파일 복사
public class java_io6 {

	public static void main(String[] args) {
		
		try {
			String file1 = "D:\\boot_gradle\\site.boot\\src\\123.txt";	//원본
			String file2 = "D:\\boot_gradle\\site.boot\\src\\agree.txt";//사본
			/*
			File f1 = new File(file1);	//파일 경로를 확인 및 가져옴
			File f2 = new File(file2);
			
			Files.copy(f1.toPath(), f2.toPath(), StandardCopyOption.REPLACE_EXISTING);
			Files.copy(new File(file1).toPath(), new File(file2).toPath(), StandardCopyOption.REPLACE_EXISTING);
			
			System.out.println(f1);	//경로 출력 확인
			*/
			
			//JAVA11 Byte 형태로 파일을 복사하아ㅕ 사용하는 기준
			//r : 읽기 , w : 쓰기 , x : 실행
			RandomAccessFile rf = new RandomAccessFile(file1, "r");	//퍼미션 부여
			RandomAccessFile rf2 = new RandomAccessFile(file2, "rw");
			
			//byte 클래스 배열
			FileChannel fc = rf.getChannel();
			System.out.println(fc.size());
			FileChannel fc2 = rf2.getChannel();
			
			//transferTo : 해당 파일 복사
			//transferFrom : 반대로 파일 복사
			fc.transferTo(0, fc.size(), fc2);
			
		} catch (Exception e) {
			
		}

	}

}
