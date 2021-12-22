package kr.or.ddit.info2.codemanage.controller;

import javax.inject.Inject;
import javax.validation.Valid;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.info2.codemanage.service.CodeManageService;
import kr.or.ddit.info2.vo.CommonVO;
import kr.or.ddit.info2.vo.DivisionVO;

/**
 * 규격/분류/단위/공정 관리
 */
@Controller
public class CodeManageDeleteController {
	@Inject
	private CodeManageService service;
	
	@PostMapping(value="/info2/codeManageDelete.do", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public String CodeManageDelete(
			@Valid @ModelAttribute("common") CommonVO common,
			Errors errors
			) {
		String message = null;
		if(!errors.hasErrors()) {
			ServiceResult result = service.deleteCode(common);
			switch(result) {
			case OK:
				message = "삭제되었습니다.";
				break;
			default:
				message = "서버오류, 잠시 후 다시 시도해주세요.";
				break;
			}
		}
		return message;
	}
	
	@PostMapping(value="/info2/divManageDelete.do", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public String CodeManageDelete(
			@Valid @ModelAttribute("division") DivisionVO division,
			Errors errors
			) {
		String message = null;
		if(!errors.hasErrors()) {
			ServiceResult result = service.deleteDiv(division);
			switch(result) {
			case OK:
				message = "삭제되었습니다.";
				break;
			default:
				message = "서버오류, 잠시 후 다시 시도해주세요.";
				break;
			}
		}
		return message;
	}
}
