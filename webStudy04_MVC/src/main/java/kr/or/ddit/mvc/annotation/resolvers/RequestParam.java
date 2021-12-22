package kr.or.ddit.mvc.annotation.resolvers;

import java.lang.annotation.Target;
import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.*;

import java.lang.annotation.Retention;


@Target(PARAMETER)
@Retention(RUNTIME)
public @interface RequestParam {
	String value(); // 필수 속성
	boolean required() default true; // 필수파라미터인지 옵셔널인지 설정
	String defaultValue() default "";
	
}
