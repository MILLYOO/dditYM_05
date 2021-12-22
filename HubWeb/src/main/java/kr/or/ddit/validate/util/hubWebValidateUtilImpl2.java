//package kr.or.ddit.validate.util;
//
//import java.io.Serializable;
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
//import org.apache.commons.collections.map.HashedMap;
//import org.springframework.ui.Model;
//import org.springframework.validation.BindingResult;
//import org.springframework.validation.Errors;
//import org.springframework.validation.FieldError;
//import org.springframework.web.servlet.support.BindStatus;
//import org.springframework.web.servlet.support.RequestContext;
//
//import kr.or.ddit.enumpkg.ServiceResult;
//import lombok.extern.slf4j.Slf4j;
//@Slf4j
//public class hubWebValidateUtilImpl2 implements hubWebValidateUtil, Serializable{
//
//	private Errors errors;
//	private RequestContext requestContext;
//	private Map<String, Object> resultMap = new HashMap<>();
//
//	public hubWebValidateUtilImpl2(Errors errors) {
//		super();
//		this.errors = errors;
//	}
//
//	public hubWebValidateUtilImpl2(RequestContext requestContext) {
//		super();
//		this.requestContext = requestContext;
//	}
//
//	@Override
//	public Map<String, Object> getResultMap(Map<String, Object> target, Map<String, Object> model) {
//		String objectName = (String) model.get("objectName");
//		Errors errors = (Errors) model.get(BindingResult.class.getName() + "." + objectName);
//		log.info("errors.hasErrors = {}",errors.hasErrors());
//		if (errors.hasErrors()) {
//			List<String> message = new ArrayList<>();
//
//			for (FieldError fe : errors.getFieldErrors()) {
//				String path = objectName + "." + fe.getField();
//				BindStatus status = requestContext.getBindStatus(path);
////			BindStatus status = new BindStatus(requestContext, path, true);
//				String errorMsg = status.getErrorMessage();
//				message.add(errorMsg);
//			}
//			resultMap.put("reuslt", ServiceResult.VALIDFAIL);
//			resultMap.put("message", message);
//		}
////		List<String> message = new ArrayList<>();
////		if(errors.hasErrors()) {
////			List<FieldError> errList = errors.getFieldErrors();
////			for(FieldError fieldError : errList) {
////				message.add(fieldError.getDefaultMessage());
////			}
////			resultMap.put("reuslt", ServiceResult.VALIDFAIL);
////			resultMap.put("message", message);
////		}
//		return resultMap;
//	}
//
//	@Override
//	public void setResultMap(ServiceResult result) {
//		switch (result) {
//		case OK:
//			resultMap.put("result", ServiceResult.OK);
//			resultMap.put("message", "저장 성공");
//			break;
//		case FAILED:
//			resultMap.put("result", ServiceResult.FAILED);
//			resultMap.put("message", "서버 오류, 잠시후 재시도해주세요.");
//			break;
//		case CHECKANOTHERTABLE:
//			resultMap.put("result", ServiceResult.CHECKANOTHERTABLE);
//			resultMap.put("message", "다른 메뉴에서 사용중입니다.");
//			break;
//		case PKDUPLICATED:
//			resultMap.put("result", ServiceResult.PKDUPLICATED);
//			resultMap.put("message", "Code 중복");
//			break;
//		}
//	}
//
//	@Override
//	public boolean checkError() {
//		return errors.hasErrors();
//	}
//
//}
