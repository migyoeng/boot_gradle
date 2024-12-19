package site.boot.java;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.Scanner;

//응용 문제
/*
Scanner 를 이용하여 고객 이메일을 입력 시킬 경우 member.txt로 입력된 값이 저장 되도록 하시오
*/
public class java_io13 {

	public static void main(String[] args) throws Exception {
		Scanner sc = new Scanner(System.in);
		System.out.print("이메일을 입력해주세요 : ");
		String email = sc.next();
		sc.close();
		
		String url = "D:\\boot_gradle\\site.boot\\src\\main\\resources\\member.txt";
		/*
		OutputStream fos = new FileOutputStream(f);
		byte[] data = email.getBytes();
		fos.write(data);
		fos.flush();
		fos.close();
		*/
		PrintWriter pw = new PrintWriter(url);
		pw.write(email);
		pw.close();
		
	}

}
