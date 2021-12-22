package kr.or.ddit.validate.constraints;

import static java.lang.annotation.ElementType.ANNOTATION_TYPE;
import static java.lang.annotation.ElementType.CONSTRUCTOR;
import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.PARAMETER;
import static java.lang.annotation.ElementType.TYPE_USE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;
import javax.validation.constraints.NotBlank;
/**
 * BlankPattern -> Nullable일때 형식은 확인하기 위한 어노테이션
 */
@Target({FIELD, METHOD})
@Retention(RUNTIME)
@Constraint(validatedBy = {BlankpatternValidation.class})
public @interface BlankPattern{
	String regexp();
	String placeholder();
	String message() default "{kr.or.ddit.validate.constraints.BlankPattern.message}";

	Class<?>[] groups() default { };

	Class<? extends Payload>[] payload() default { };
	
	/**
	 * Defines several {@code @NotBlank} constraints on the same element.
	 *
	 * @see NotBlank
	 */
	@Target({ METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER, TYPE_USE })
	@Retention(RUNTIME)
	@Documented
	public @interface List {
		BlankPattern[] value();
	}
}