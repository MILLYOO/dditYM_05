package kr.or.ddit.mvc.annotation.resolvers;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.RetentionPolicy.*;
import static java.lang.annotation.ElementType.*;

@Target(PARAMETER)
@Retention(RUNTIME)
public @interface RequestHeader {
	String value();
	boolean required() default true;
	String defaultValue() default "";
}
