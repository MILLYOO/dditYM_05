package kr.or.ddit.mvc.annotation.stereotype;

import java.lang.annotation.Target;
import static java.lang.annotation.ElementType.*;

import java.lang.annotation.Retention;
import static java.lang.annotation.RetentionPolicy.*;

import kr.or.ddit.mvc.annotation.RequestMethod;

@Target(METHOD)
@Retention(RUNTIME)
public @interface RequestMapping {
	String value();
	RequestMethod method() default RequestMethod.GET;
}
