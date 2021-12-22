package kr.or.ddit.info1.projectmanage.controller;

import java.util.List;

import javax.inject.Inject;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.or.ddit.common.vo.HubSearchVO;
import kr.or.ddit.info1.projectmanage.service.ProjectManageService;
import kr.or.ddit.info1.vo.ProjectVO;

/**
 * 프로젝트 관리
 */
@Controller
public class ProjectRetrieveController {
	@Inject
	private ProjectManageService service;
	
	@RequestMapping(value="/info1/projectRetrieve.do", method=RequestMethod.GET)
	public String projectRetrieve() {
		
		String viewName = "info/projectRegister";
		return viewName;
	}
	
	@ResponseBody
	@RequestMapping(value="/info1/projectRetrieve.do", method=RequestMethod.POST, produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public List<ProjectVO> projList(
			@ModelAttribute("hubSearch") HubSearchVO hubSearch
			) {
		List<ProjectVO> projList = service.retrieveProjectList(hubSearch);
		return projList;
	}
}


