package kr.or.ddit.info1.authmanage.controller;

import java.util.Map;

import javax.inject.Inject;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.WebApplicationContext;

import kr.or.ddit.info1.authmanage.service.AuthManageService;
import kr.or.ddit.info1.vo.MemAuthVO;
import kr.or.ddit.validate.groups.UpdateGroup;
import kr.or.ddit.validate.util.hubWebValidateUtil;
import kr.or.ddit.validate.util.hubWebValidateUtilImpl;
import lombok.extern.slf4j.Slf4j;

/**
 * 권한설정
 */
@Controller
@Slf4j
public class AuthManageUpdateController {
	@Inject
	private AuthManageService service;
	@Inject
	private WebApplicationContext context;
	
	@RequestMapping(value = "/info1/authManageUpdate.do", method=RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public Map<String, Object> AuthManageList(
			@Validated(UpdateGroup.class) @ModelAttribute MemAuthVO memAuth,
			Errors errors
			) {
		hubWebValidateUtil valid = new hubWebValidateUtilImpl(errors, context);
		if(!valid.checkError()) {
			valid.setResultMap(service.updateAuth(memAuth));
		}
		
		return valid.getResultMap();
	}
}
