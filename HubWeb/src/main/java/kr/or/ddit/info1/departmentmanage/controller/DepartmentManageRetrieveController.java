package kr.or.ddit.info1.departmentmanage.controller;

import java.util.List;

import javax.inject.Inject;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.or.ddit.common.vo.HubSearchVO;
import kr.or.ddit.info1.departmentmanage.service.DepartmentManageService;
import kr.or.ddit.info1.vo.DepartmentVO;

/**
 * 부서관리
 */
@Controller
public class DepartmentManageRetrieveController {
	@Inject
	private DepartmentManageService service;
	
	@RequestMapping(value="/info1/departmentManageRetrieve.do", method=RequestMethod.GET)
	public String departmentManageRetrieve() {
		
		String viewName = "info/departmentRegister";
		return viewName;
	}
	
	@ResponseBody
	@RequestMapping(value="/info1/departmentManageRetrieve.do", method=RequestMethod.POST, produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public List<DepartmentVO> deptList(
			@ModelAttribute("hubSearch") HubSearchVO hubSearch
			) {
		List<DepartmentVO> deptList = service.retrieveDepartmentManageList(hubSearch);
		return deptList;
	}
}
