package kr.or.ddit.annotation.stereotype;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
//
import static java.lang.annotation.RetentionPolicy.*;
// static import ElementType의 static 타입들을 전부 다 사용할 수 있음
import static java.lang.annotation.ElementType.*;
//
import java.lang.annotation.Target;

@Target(TYPE)
@Retention(RUNTIME)
public @interface MultiValueAnnotation {
	String value(); // 필수 속성
	int number() default 12; // 옵션 속성
	float floatNumber() default 2.0f; // 옵션 속성
}
