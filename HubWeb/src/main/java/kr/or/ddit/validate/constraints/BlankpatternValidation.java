package kr.or.ddit.validate.constraints;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.apache.commons.lang3.ObjectUtils;

public class BlankpatternValidation  implements ConstraintValidator<BlankPattern, Object>{
	
	private BlankPattern constraintAnnotation;
	
	@Override
	public void initialize(BlankPattern constraintAnnotation) {
		this.constraintAnnotation = constraintAnnotation;
	}
	
	@Override
	public boolean isValid(Object value, ConstraintValidatorContext context) {
		boolean valid = ObjectUtils.isEmpty(value);
		String strValue = "";
		if(!valid) {
			strValue = value.toString();
			valid = strValue.matches(constraintAnnotation.regexp());
		}
		return valid;
	}

}