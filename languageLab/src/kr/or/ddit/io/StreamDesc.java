package kr.or.ddit.io;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLDecoder;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * stream : 데이터의 흐름이면서 단방향 이동 통로. 
 * 스트림을 이용한 I/O 단계
 * 1. 데이터의 출처(media)를 객체화(File, Socket, byte[]...)
 * 2. media에 맞는 단방향 스트림 생성
 * 		(FileInput[Output]Stream, SocketInput[Output]Stream, ByteArrayInput[Output]Stream) 읽어 들이는 크기가 1Byte인 작은 빨대.
 * 3. EOF(EOS)까지 반복적인 read/write
 * 4. close를 통해 media release.
 * 
 * 스트림의 종류
 * 1. 전송 데이터 크기
 * 		1) byte Stream = 빨대중 가장 얇은 빨대 1Byte씩 빨아들인다. (~~Stream)
 * 			- FileInput[Output]Stream
 * 		2) character Stream = 중간빨대 2Byte씩 빨아들인다.	(~~Reader/~~Writer)
 *			- FileReader/Writer
 * 2. 스트림 생성 방법
 * 		1) 1차 스트림 : 생성자의 파라미터로 media 객체를 받아 생성.
 * 				new FileInputStream(new File("path"))
 * 		2) 2차 스트림(연결형 스트림) : 다른 스트림 객체를 기반으로 생성되는 스트림(생성자의 파라미터로 스트림을 받음)
 *				new BufferedInputStream(new FileInputStream(new File("path"))) 매체에 대한 정보가 없으면 그것은 보통 2차 스트림이다.
 * 3. 필터링 스트림(전처리 구조, 2차 스트림)
 * 		DataInputStream(new FileInputStream(new File("path")))  데이터의 원래의 타입 그대로 가지고 놀 수 있다.
 * 		ObjectInputStream(new FileInputStream(new File("path"))) 객체를 직렬화 시켜 객체 그대로 읽어들일때 사용
 *
 */
public class StreamDesc {
	public static void main(String[] args) throws IOException{
//		simpleIOExample();
//		String cpRelativePath = "/kr/or/ddit/io/오래된 노래.txt"; // MS949
//		String cpRelativePath = "/kr/or/ddit/io/오래된 노래_utf8.txt"; // UTF-8
//		String charset = "MS949";
//		String charset = "UTF-8";
//		simpleIOWithCharsetExample(cpRelativePath, charset);
		
		String webPath = "http://www.google.com/images/branding/googlelogo/2x/googlelogo_color_272x92dp.png";
		URL url = new URL(webPath);
		Path path = Paths.get(url.getPath()); // 나중에 공부하기 Paths
		String filename = path.getFileName().toString();
//		File downloadFile = new File("d:/contents", filename);
		Path downloadPath = Paths.get("d:/contents", filename);
//		System.out.println(downloadFile.getCanonicalPath());
		try(
			InputStream is = url.openStream();
		){
			Files.copy(is, downloadPath);
		}
		// stream copy
//		System.out.println(is.getClass().getName());
//		System.out.println(is.available());
		
	}
	public static void simpleIOWithCharsetExample(String cpRelativePath, String charset) throws IOException{
		URL fileURL = StreamDesc.class.getResource(cpRelativePath);
		String filePath = fileURL.getFile();
		//		1. media 생성
		filePath = URLDecoder.decode(filePath, "UTF-8");
		System.out.println(filePath);
		File file = new File(filePath);
		System.out.println(file.getCanonicalPath());
		String tmp = null;
		// try with resouce 구문 형태(since 1.7) close() 메소드를 가지고 있는 객체를 자동으로 실행시켜준다.
		try(
				//	2. stream 생성 / 4. media release
				FileInputStream fis = new FileInputStream(file);
				InputStreamReader isr = new InputStreamReader(fis, charset);
				BufferedReader reader = new BufferedReader(isr);
				) {
			//	3. EOS 까지 반복
			while((tmp = reader.readLine()) != null) {
				System.out.println(tmp);
			}
		}
	}
	
	public static void simpleIOExample() throws IOException{
//		String cpRelativePath = "/kr/or/ddit/io/another day.txt";
		String cpRelativePath = "/kr/or/ddit/io/오래된 노래_utf8.txt";
		URL fileURL = StreamDesc.class.getResource(cpRelativePath);
		String filePath = fileURL.getFile();
		//		1. media 생성
		filePath = URLDecoder.decode(filePath, "UTF-8");
		System.out.println(filePath);
		File file = new File(filePath);
		System.out.println(file.getCanonicalPath());
		String tmp = null;
		// try with resouce 구문 형태(since 1.7) close() 메소드를 가지고 있는 객체를 자동으로 실행시켜준다.
		try(
				//	2. stream 생성 / 4. media release
				FileReader fr = new FileReader(file);
				BufferedReader reader = new BufferedReader(fr);
				) {
			//	3. EOS 까지 반복
			while((tmp = reader.readLine()) != null) {
				System.out.println(tmp);
			}
		}
	}
	
	
}
