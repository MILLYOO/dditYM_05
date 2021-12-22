package kr.or.ddit.info1.projectmanage.controller;

import java.util.Map;

import javax.inject.Inject;
import javax.validation.Valid;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.WebApplicationContext;

import kr.or.ddit.info1.projectmanage.service.ProjectManageService;
import kr.or.ddit.info1.vo.ProjectVO;
import kr.or.ddit.validate.util.hubWebValidateUtil;
import kr.or.ddit.validate.util.hubWebValidateUtilImpl;

/**
 * 프로젝트 관리
 */
@Controller
public class ProjectDeleteController {
	@Inject
	private ProjectManageService service;
	
	@Inject
	private WebApplicationContext context;
	
	@PostMapping(value="/info1/projectDelete.do", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public Map<String, Object> projectDelete(
			@Valid @ModelAttribute ProjectVO project,
			Errors errors
			) {
		hubWebValidateUtil valid = new hubWebValidateUtilImpl(errors, context);
		if(!valid.checkError()) {
			valid.setResultMap(service.deleteProject(project));
		}
		return valid.getResultMap();
	}
}
