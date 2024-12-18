package site.boot.java;

import java.io.FileInputStream;
import java.io.FileOutputStream;

//이미지 복사(Stream)만 사용
public class java_io9 {

	public static void main(String[] args) throws Exception {
		/*
		String img = "D:\\boot_gradle\\site.boot\\src\\image.jpg";
		FileInputStream fs = new FileInputStream(img);
		byte by[] = new byte[fs.available()];
		fs.read(by);	//전체 byte만큼 모두 읽어들임
		
		String cpimg = "D:\\boot_gradle\\site.boot\\src\\mcopy.jpg";
		FileOutputStream os = new FileOutputStream(cpimg);
		os.write(by);
		os.close();
		fs.close();
		*/
		
		String img = "D:\\boot_gradle\\site.boot\\src\\image.jpg";
		FileInputStream fs = new FileInputStream(img);
		byte by[] = new byte[fs.available()/100];
		fs.read(by);
		
		String cpimg = "D:\\boot_gradle\\site.boot\\src\\mcopy.jpg";
		FileOutputStream os = new FileOutputStream(cpimg);
		
		//반복문으로 progress를 구현
		int i=0;
		int check=0;
		while(true) {
			i = fs.read(by);
			if(i == -1) {
				break;
			} else {
				os.write(by,0,i);
			}
			check++;
			if(check % 2 == 0) {
				System.out.println(check + "%");
			}
		}
		os.close();
		fs.close();

	}

}
