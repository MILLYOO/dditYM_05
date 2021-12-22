package kr.or.ddit.validate.constraints;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class TelNumberValidator implements ConstraintValidator<TelNumber, String>{

	// value : 클라이언트가 입력한 전화번호
	
	private TelNumber constraintAnnotation;
	
	// 정규표현식 하드코딩하지 않기 위해 어노테이션 가져옴
	@Override
	public void initialize(TelNumber constraintAnnotation) {
		this.constraintAnnotation=constraintAnnotation;
	}
	
	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		
		boolean valid = value == null || value.isEmpty ();
		
		if(!valid)
				valid = value.matches(constraintAnnotation.regex());
		
		return valid;
	}

}
