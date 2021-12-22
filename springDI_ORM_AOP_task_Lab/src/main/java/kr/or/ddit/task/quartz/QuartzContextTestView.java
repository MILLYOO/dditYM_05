package kr.or.ddit.task.quartz;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class QuartzContextTestView {
	public static void main(String[] args) {
		ConfigurableApplicationContext context = new ClassPathXmlApplicationContext("kr/or/ddit/task/quartz-context.xml");
		context.registerShutdownHook();
	}
}
