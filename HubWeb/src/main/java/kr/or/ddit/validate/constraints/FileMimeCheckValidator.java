package kr.or.ddit.validate.constraints;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.web.multipart.MultipartFile;

public class FileMimeCheckValidator implements ConstraintValidator<FileMimeChecker, MultipartFile>{

	// value : 클라이언트가 입력한 전화번호
	
	private FileMimeChecker constraintAnnotation;
	
	// 정규표현식 하드코딩하지 않기 위해 어노테이션 가져옴
	@Override
	public void initialize(FileMimeChecker constraintAnnotation) {
		this.constraintAnnotation=constraintAnnotation;
	}
	
	@Override
	public boolean isValid(MultipartFile file, ConstraintValidatorContext context) {
		//value 업로드되고있는 파일 하나
		boolean valid = file == null;
		
		if(!valid) {
			String fileMime = file.getContentType();
			String checkMime = constraintAnnotation.mime();
			valid = fileMime.indexOf(checkMime) >= 0;
		}
		return valid;
	}

}
