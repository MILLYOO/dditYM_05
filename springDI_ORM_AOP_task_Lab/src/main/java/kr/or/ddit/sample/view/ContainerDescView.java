package kr.or.ddit.sample.view;

import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

import kr.or.ddit.sample.service.SampleService;

public class ContainerDescView {
	public static void main(String[] args) {
		ConfigurableApplicationContext context = new GenericXmlApplicationContext("classpath:kr/or/ddit/sample/conf/DI-Desc.xml");
		// classpath 스프링 내부에서(리소스 로더에 의해) kr를 클래스패스 아래에서 찾음
		// 컨테이너가 생성되는 순간에 bean에 등록된 모든 객체가 생성이 됨.
		SampleService service1 = context.getBean("sampleService1", SampleService.class);
		SampleService service1_1 = context.getBean("sampleService1", SampleService.class);
		SampleService service1_2 = context.getBean("sampleService1", SampleService.class);
		System.out.println(service1_1  == service1_2);
		System.out.println(service1 == service1_2);
		SampleService service2 = context.getBean("sampleService2", SampleService.class);
		SampleService service2_1 = context.getBean("sampleService2", SampleService.class);
		System.out.println(service2 == service2_1);
		
		System.out.println(service1 == service2);
//		String pk = "a001";
//		System.out.println(service1.retrieveSampleData(pk));
//		System.out.println(service2.retrieveSampleData(pk));
		context.close();
	}
}
