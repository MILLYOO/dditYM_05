package kr.or.ddit.sample.view;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import kr.or.ddit.sample.conf.JavaConfiguration;
import kr.or.ddit.sample.service.SampleService;

public class javaConfigurationView {
	public static void main(String[] args) {
		ConfigurableApplicationContext context = new AnnotationConfigApplicationContext(JavaConfiguration.class);
		
		/*       컨테이너 종료관련       */
//		context.close(); // 어플리케이션의 종료시점을 정확히 알때 사용함
		SampleService service2 = context.getBean("sampleService2", SampleService.class);
		SampleService service2_1 = context.getBean("sampleService2", SampleService.class);
		System.out.println(service2 == service2_1);
		context.registerShutdownHook(); // 예측이 안될때 종료를 예약함.
		
	}
}
