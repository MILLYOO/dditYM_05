package kr.or.ddit.listener;

import java.util.HashSet;

import javax.servlet.ServletContext;

import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;

import lombok.extern.slf4j.Slf4j;

@Component	// 상위 컨테이너에 등록
@Slf4j
public class CustomCotextEventListener {
	
	//이벤트 처리 : 1 이벤트타겟, 2 종류, 3 리스너 만듦, 4 타겟에 리스너 갖다 붙여
	
	
	@EventListener(ContextRefreshedEvent.class)
	public void contextRefreshedEventHandler(ContextRefreshedEvent event) {
		WebApplicationContext context = (WebApplicationContext) event.getApplicationContext();
		ServletContext application = context.getServletContext();
		application.setAttribute("cPath", application.getContextPath());
		application.setAttribute("userList", new HashSet<>());
		log.info("{} 컨텍스트 시작 : {}", application.getContextPath(), context);
	}
}
