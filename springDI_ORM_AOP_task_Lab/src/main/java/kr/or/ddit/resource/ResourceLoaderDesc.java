package kr.or.ddit.resource;

import java.io.IOException;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

public class ResourceLoaderDesc {
	public static void main(String[] args) throws IOException {
		// Resource 모든 자원의 상위 개념
//		Resource res;
//		ResourceLoaderDesc.class.getResource("/log4j2.xml"); -->
		ClassPathResource res = new ClassPathResource("/log4j2.xml"); // ClassPath는 이미 잡혀 있기 때문에 이후 경로만 넣으면 된다.
//		System.out.println(res.exists());
		ConfigurableApplicationContext context = new ClassPathXmlApplicationContext();
		
//		prefix형태를 이용하여 classpath를 찾음
//		Context는 ResourceLoader의 여활을 함
		Resource cpRes = context.getResource("classpath:/log4j2.xml");
		System.out.println(cpRes.exists());
		
		Resource fsRes = context.getResource("file:D:/contents/cat1.jpg");
		System.out.println(fsRes.getFile().getCanonicalPath());
		
		Resource webRes = context.getResource("https://www.google.com/images/branding/googlelogo/2x/googlelogo_color_272x92dp.png");
		System.out.println(webRes.contentLength());
		
		
		context.close();
	}
}
