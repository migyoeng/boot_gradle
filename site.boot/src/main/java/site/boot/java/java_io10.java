package site.boot.java;

import java.io.File;
import java.util.Arrays;

//파일 리스트 및 속성
public class java_io10 {

	public static void main(String[] args) {
		String url = "D:\\boot_gradle\\site.boot\\src";
		File f = new File(url);
		File all_list[] = f.listFiles();	//해당 경로의 모든 파일을 가져온다
		System.out.println(Arrays.asList(all_list));	//가져온 파일 배열을 리스트로 변환
		
		String filename = all_list[0].getName();	//0번째 리스트의 파일명 가져온다
		System.out.println(filename);
		int n = filename.lastIndexOf(".");	//마지막 "." 의 위치 파악
		String att = filename.substring(n);	//. 기준 문자열 분리 -> 확장자 저장
		System.out.println(att);

	}

}
