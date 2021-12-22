package kr.or.ddit.advice;

import java.util.Calendar;

import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;

import kr.or.ddit.common.PKNotFoundException;
import lombok.extern.slf4j.Slf4j;

// 괄호 안 값(pointcut) 넣지 않으면 모든 컨트롤러 대상으로 weaving
// 하위컨테이너등록
@ControllerAdvice(annotations=Controller.class) 
@Slf4j
@Order(2)
public class CommonExceptionControllerAdvice {

//	 모든 컨트롤러에서 현재 시각을 뷰로 전달해야 한다.
//	 핵심 : 모든 컨트롤러
//	 횡단 : 현재시각을 뷰로 전달
	
//	 모델에 집어 넣고 위빙만 된다면 타겟 건드리지 않고 문제 해결
//	 모든 컨트롤러에서 now 사용 가능
	@ModelAttribute("now")
	public Calendar getCalendar() {
		return Calendar.getInstance();
	}
	
//	 모든 컨트롤러에서 발생하는 PKNE에 대한 공통 처리
//	 핵심 : 모든 컨트롤러
//	 횡단 : PKNE에 대한 공통 처리
	@ExceptionHandler(PKNotFoundException.class)	// 예외 종류 국한
	public String exceptionHandler(PKNotFoundException e) {	// catch블럭의 역할
		log.error(e.getMessage(), e);
		return "errors/pkNotFound";		// 논리적인 view name ->handler adapter
	}
	
	
}








