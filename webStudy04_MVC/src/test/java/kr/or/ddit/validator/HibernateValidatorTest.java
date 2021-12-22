package kr.or.ddit.validator;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Path;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import kr.or.ddit.vo.MemberVO;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class HibernateValidatorTest {
	private MemberVO dummy;
	
	private static Validator validator;
	
	@BeforeClass
	public static void setUpclass() {
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		validator = factory.getValidator();
	}
	
	@Before
	public void setUp() {
		dummy = new MemberVO();
		dummy.setMemId("a001");
		dummy.setMemMail("qwdwqd@qw.com");
		dummy.setMemRegno1("123456");
		dummy.setMemRegno2("1234567");
		dummy.setMemBir("1999-01-01");
	}
	
	
	@Test
	public void testValidate() {
		Set<ConstraintViolation<MemberVO>> violations =  validator.validate(dummy); // 검증 통과 Size : 0, 그외에는 실패.
		boolean valid = violations.size() == 0;
		log.info("valid :  {}", valid);
		if(!valid) {
			for(ConstraintViolation<MemberVO> violation : violations) {
				log.info("violation : {}", violation);
				Path propertyPath = violation.getPropertyPath();
				String key = propertyPath.toString();
				String value = violation.getMessage();
				log.info("key : {}, value : {}", key, value);
			}
		}
	}
}
