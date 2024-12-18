package site.boot.java;

import java.io.File;
import java.io.FileOutputStream;

public class java_io8 {

	public static void main(String[] args) {

		try {
			String url = "D:\\boot_gradle\\site.boot\\src\\data.txt";
			File f = new File(url);
			f.createNewFile();	//파일을 생성하는 역할
			//파일 저장 형태 설정 : true, false
			FileOutputStream fs = new FileOutputStream(f, true);	//이어쓰기
			
			int w = 1;
			while(w <= 9) {
				int sum = 7 * w;
				byte[] total = String.valueOf(sum + "\n").getBytes();	//int -> String -> byte
				fs.write(total);	
				w++;
			}
			fs.close();
			
		} catch (Exception e) {
			System.out.println("파일 로드 실패");
		}

	}

}
