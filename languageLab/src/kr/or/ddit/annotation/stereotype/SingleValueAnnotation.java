package kr.or.ddit.annotation.stereotype;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface SingleValueAnnotation { // 어노테이션을 대상으로 tracing을 할려면 무조건 Runtime을 해야한다.
											// 런타임일때 벨류를 꺼내오기 때문이다.
	String value(); // 필수속성
}
