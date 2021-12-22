package kr.or.ddit.sample.advice;


import java.util.Arrays;import org.aopalliance.intercept.Joinpoint;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Aspect
public class LoggingAdvice {
	@Pointcut("execution(* kr.or.ddit.sample.service.*.*(..))")
	public void dummy() {}
	@Around("dummy()")
	public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
		Object target = joinPoint.getTarget();
		Object[] args = joinPoint.getArgs();
		Signature signature = joinPoint.getSignature();
		String methodName = signature.getName();
		String targetName = target.getClass().getSimpleName();
		log.info("{}.{}({})타겟이 실행되기 전에 weaving", targetName, methodName, Arrays.toString(args));
		long start = System.currentTimeMillis();
		Object retValue = joinPoint.proceed(args); // 타겟을 직접 호출하여 around 구조를 만듬. 
		long end = System.currentTimeMillis();
		log.info("{}.{} 호출 후 소요시간 : {}ms, 반환 값 : {}", targetName, methodName, (end-start),retValue);
		return retValue;
	}
	
	@AfterReturning(pointcut="dummy()", returning="retValue")
	public void afterReturning(JoinPoint joinPoint, Object retValue) {
		Object target = joinPoint.getTarget();
		Object[] args = joinPoint.getArgs();
		Signature signature = joinPoint.getSignature();
		
		String methodName = signature.getName();
		String targetName = target.getClass().getSimpleName();
		log.info("{}.{} 호출 후 반환 값 : {}", targetName, methodName, retValue);
	}

	@Before("dummy()")
	public void beforeAdvice(JoinPoint joinPoint) {
		Object target = joinPoint.getTarget();
		Object[] args = joinPoint.getArgs();
		Signature signature = joinPoint.getSignature();
		
		String methodName = signature.getName();
		String targetName = target.getClass().getSimpleName();
		log.info("{}.{}({})타겟이 실행되기 전에 weaving", targetName, methodName, Arrays.toString(args));
	}
}
