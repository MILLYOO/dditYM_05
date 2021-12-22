package kr.or.ddit.advice;

import java.util.Arrays;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Aspect
public class LoggingAdvice {
	
	// 중복 적용 할 pointcut
	@Pointcut("execution(* kr.or.ddit..service.*.*(..))")
	public void dummy() {}
	
	// before+after
	// advice + pointcut = aspect
	@Around("dummy()")
	public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
		Object target = joinPoint.getTarget();	// 타겟 타입 고려 X 
		Signature signature = joinPoint.getSignature();	
		String methodName = signature.getName();	// 호출 되고 있는 메서드명
		String targetName = target.getClass().getSimpleName();
		Object[] args = joinPoint.getArgs();	// 파라미터 가로챌 수 있음
		
		log.info("{}.{}({})이 실행되기 전에 weaving",targetName,methodName,Arrays.toString(args));
		long start = System.currentTimeMillis();
		Object retValue = joinPoint.proceed(args);
		long end = System.currentTimeMillis();
		log.info("{}.{}  호출 후 소요시간, {}ms, 반환 값  {}",targetName,methodName,(end-start),retValue);
		return retValue;
	}
	
}
