package kr.or.ddit.info1.employeemanage.controller;

import java.util.List;

import javax.inject.Inject;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.or.ddit.common.vo.HubSearchVO;
import kr.or.ddit.info1.employeemanage.service.EmployeeManageService;
import kr.or.ddit.info1.vo.EmployeeVO;

@Controller
public class EmployeeManageRetrieveController {
	@Inject
	private EmployeeManageService service;
	
	@RequestMapping(value="/info1/employeeManageRetrieve.do", method=RequestMethod.GET)
	public String employeeManageRetrieve() {
		
		String viewName = "info/employeeRegister";
		return viewName;
	}
	
	@ResponseBody
	@RequestMapping(value="/info1/employeeManageRetrieve.do", method=RequestMethod.POST, produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public List<EmployeeVO> empList(
			@ModelAttribute("hubSearch") HubSearchVO hubSearch
			) {
		List<EmployeeVO> empList = service.retrieveEmployeeList(hubSearch);
		return empList;
	}
}
