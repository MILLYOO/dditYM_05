package kr.or.ddit.common.vo;

import java.util.List;

import javax.validation.Valid;

import lombok.Data;

/*
 * By.이원균_211211
 * List 형식의 객체를 검증하는 경우 사용하는 Class
 * 파라미터가 List 타입인 경우, validation 이 되지 않으므로 VO에 List를 담아 사용합니다
 */
@Data
public class CommonListVO<T> {
	
	@Valid
	List<T> commonList;
}

