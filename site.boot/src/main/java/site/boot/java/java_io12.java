package site.boot.java;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.OutputStream;
import java.io.OutputStreamWriter;

import org.apache.commons.io.output.WriterOutputStream;

//OutputStream => Writer
//Writer => OutputStream
public class java_io12 {

	public static void main(String[] args) throws Exception {
		String url = "D:\\boot_gradle\\site.boot\\src\\agree.data";
		
		/*
		String msg = "IO 어렵당";
		
		OutputStream os = new FileOutputStream(url);
		OutputStreamWriter osw = new OutputStreamWriter(os);
		BufferedWriter bw = new BufferedWriter(osw);
		bw.append(msg);
		bw.flush();	//Buffered 메모리를 비우는 작업
		bw.close();
		osw.close();
		os.close();
		*/
		String msg = "북극곰은 사람을 찢어";
		FileWriter fw = new FileWriter(url);
		BufferedWriter bw = new BufferedWriter(fw);
		OutputStream os = new WriterOutputStream(bw);
		os.write(msg.getBytes());
		bw.flush();
		os.close();
		bw.close();
		fw.close();
	}

}
