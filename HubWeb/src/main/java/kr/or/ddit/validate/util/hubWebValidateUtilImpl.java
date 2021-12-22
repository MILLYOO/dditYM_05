package kr.or.ddit.validate.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;
import org.springframework.web.context.WebApplicationContext;

import kr.or.ddit.common.vo.CommonListVO;
import kr.or.ddit.enumpkg.ServiceResult;
import lombok.extern.slf4j.Slf4j;

/*
 * hubWebValidateUtilImpl.java
 * 클래스 제작 : By유밀
 * 주석 작성 : By이원균_211210
 * Controller 에서  hibernate-validator 로 객체 검증시 에러 여부 및 에러메시지 출력을 담당하는 Util Class 입니다
 */
  
@Slf4j
public class hubWebValidateUtilImpl implements hubWebValidateUtil {

	private Errors errors;
	private Map<String, Object> resultMap = new HashMap<>();
	private WebApplicationContext context;
	private CommonListVO<?> targetList;
	
	//단일 객체 검증 시 사용하는 생성자
	public hubWebValidateUtilImpl(Errors errors, WebApplicationContext context) {
		super();
		this.errors = errors;
		this.context = context;
	}
	
	//List 내부의 다중 객체 검증 시 사용하는 생성자
	public hubWebValidateUtilImpl(CommonListVO<?> targetList, Errors errors, WebApplicationContext context) {
		super();
		this.errors = errors;
		this.context = context;
		this.targetList= targetList;
	}
	
	//Custom Message를 이용하기 위하여, VO의 SimpleName을 가지고온다. (coc적용X)
	private String parseNestedObjectName() {
		String nestedName = "";
		if(targetList!=null && targetList.getCommonList().size()>0) {
			nestedName = targetList.getCommonList().get(0).getClass().getSimpleName();
		}
		return nestedName;
	}

	//service 로직을 탄 경우와 타지 않은 경우 모두 해당 메서드가 실행된다
	//단, error 발생의 경우(객체 검증 불통과) 로직을 타지 않고 if문이 실행되어 메세지를 세팅한다
	@Override
	public Map<String, Object> getResultMap() {
		List<String> message = new ArrayList<>();
		if(errors.hasErrors()) {
			List<FieldError> errList = errors.getFieldErrors();
			for(FieldError fieldError : errList) {
				String msgTemplate = context.getMessage(fieldError, LocaleContextHolder.getLocale());
				String printMsg = msgTemplate;
				if(msgTemplate.contains("commonList")) {
					String objName = parseNestedObjectName();//Message properties 에서 오류가 발생한 객체의 변수명을 가져온다
					int lastIdx = fieldError.getField().lastIndexOf(".");
					String nestedFldName = fieldError.getField().substring(lastIdx + 1); 
					String argumentMsg = context.getMessage(objName+"."+nestedFldName, null, null);  //"유형"
					printMsg = printMsg.replaceAll("commonList\\[\\d+\\]\\.\\w+", argumentMsg); 
				}
				message.add(printMsg);
			}
			resultMap.put("reuslt", ServiceResult.VALIDFAIL);
			resultMap.put("message", message);
		}
		return resultMap;
	}
	
	//로직 결과에 따른 ServiceResult 및 message Setting 
	//로직을 탄 경우(객체 검증을 통과한 경우)에 실행된다.
	@Override
	public void setResultMap(ServiceResult result) {
		switch(result) {
		case OK:
			resultMap.put("result", ServiceResult.OK);
			resultMap.put("message", "저장 성공");
			break;
		case FAILED:
			resultMap.put("result", ServiceResult.FAILED);
			resultMap.put("message", "서버 오류, 잠시후 재시도해주세요.");
			break;
		case CHECKANOTHERTABLE:
			resultMap.put("result", ServiceResult.CHECKANOTHERTABLE);
			resultMap.put("message", "다른 메뉴에서 사용중입니다.");
			break;
		case PKDUPLICATED:
			resultMap.put("result", ServiceResult.PKDUPLICATED);
			resultMap.put("message", "Code 중복");
			break;
		}
	}

	//Error 체크 메서드
	@Override
	public boolean checkError() {
		return errors.hasErrors();
	}

	
	
}
