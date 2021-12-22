package kr.or.ddit.advice;

import java.util.HashMap;
import java.util.Map;

import org.springframework.core.annotation.Order;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import kr.or.ddit.common.PKNotFoundException;
import lombok.extern.slf4j.Slf4j;

@Slf4j
//RestController가 controller를 포함하고 있어 우선순위 설정 필요함
@Order(1)
@RestControllerAdvice(annotations=RestController.class)	// RestController 대상으로 위빙
public class CommonExceptionRestControllerAdvice {

	@ExceptionHandler(PKNotFoundException.class)
	public Map<String , Object> exceptionHandler(PKNotFoundException e) {	// 자체적으로 response body가지고 있어 다시 마셜링 될 수 있는 구조
		log.error(e.getMessage(),e);
		Map<String , Object> resultMap = new HashMap<>();
		resultMap.put("status", 500);
		resultMap.put("message", e.getMessage());
		return resultMap;
	}
}
