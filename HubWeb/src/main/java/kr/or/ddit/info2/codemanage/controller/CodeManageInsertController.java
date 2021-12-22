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
public class CodeManageInsertController {
	@Inject
	private CodeManageService service;

	@PostMapping(value = "/info2/codeManageInsert.do", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public String CodeManageInsert(@Valid @ModelAttribute("common") CommonVO common, Errors errors) {
		String message = null;
		if (!errors.hasErrors()) {
			ServiceResult result = service.createCode(common);
			switch (result) {
			case OK:
				message = "저장되었습니다.";
				break;

			case PKDUPLICATED:
				message = "PK중복";
				break;

			default:
				message = "실패";
				break;
			}
		}
		return message;
	}
	
	
	@PostMapping(value = "/info2/divManageInsert.do", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public String DivManageInsert(@Valid @ModelAttribute("division") DivisionVO division, Errors errors) {
		String message = null;
		if(!errors.hasErrors()) {
			ServiceResult result = service.createDiv(division);
			switch (result) {
			case OK:
				message = "저장되었습니다.";
				break;

			case PKDUPLICATED:
				message = "PK중복";
				break;

			default:
				message = "실패";
				break;
			}
		}
		return message;
	}
	
}
