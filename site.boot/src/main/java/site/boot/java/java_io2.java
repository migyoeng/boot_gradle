package site.boot.java;

import java.io.FileWriter;

//FileWriter
public class java_io2 {

	public static void main(String[] args) {

		try {
			String[] user = {"hong", "park", "kim"};
			FileWriter fw = new FileWriter("D:\\boot_gradle\\site.boot\\src\\agree.data");
			
			for(int i=0; i<user.length; i++) {
				fw.write(user[i] + "\n");
			}
			fw.close();
			
		} catch (Exception e) {
			System.out.println();
		}
		

	}

}
