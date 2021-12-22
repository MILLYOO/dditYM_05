package kr.or.ddit.info1.projectmanage.controller;

import java.util.Map;

import javax.inject.Inject;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.WebApplicationContext;

import kr.or.ddit.info1.projectmanage.service.ProjectManageService;
import kr.or.ddit.info1.vo.ProjectVO;
import kr.or.ddit.validate.groups.InsertGroup;
import kr.or.ddit.validate.util.hubWebValidateUtil;
import kr.or.ddit.validate.util.hubWebValidateUtilImpl;

/**
 * 프로젝트 관리
 */
@Controller
public class ProjectInsertController {
	@Inject
	private ProjectManageService service;
	@Inject
	private WebApplicationContext context;
	
	@RequestMapping(value="/info1/projectInsert.do", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public Map<String, Object> ProjectInsert(			
			@Validated(InsertGroup.class) @ModelAttribute ProjectVO project,
			Errors errors
			) {
		hubWebValidateUtil valid = new hubWebValidateUtilImpl(errors, context);
		if(!valid.checkError()) {
			valid.setResultMap(service.createProject(project));
		}
		return valid.getResultMap();
	}
}
