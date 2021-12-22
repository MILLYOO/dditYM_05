package kr.or.ddit.various;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import kr.or.ddit.vo.CollectionDIVO;

public class CollectionDIView {
	public static void main(String[] args) {
		ConfigurableApplicationContext context = new ClassPathXmlApplicationContext("kr/or/ddit/various/conf/CollectionDI.xml");
		context.registerShutdownHook();
		
		CollectionDIVO cVO1 = context.getBean("cVO1", CollectionDIVO.class);
		System.out.println(cVO1);
		CollectionDIVO cVO2 = context.getBean("cVO2", CollectionDIVO.class);
		System.out.println(cVO2);
		CollectionDIVO cVO3 = context.getBean("cVO3", CollectionDIVO.class);
		System.out.println(cVO3);
	}
}
