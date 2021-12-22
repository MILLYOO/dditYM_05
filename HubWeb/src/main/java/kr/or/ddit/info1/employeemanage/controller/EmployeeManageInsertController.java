package kr.or.ddit.info1.employeemanage.controller;

import java.util.Map;

import javax.inject.Inject;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.WebApplicationContext;

import kr.or.ddit.info1.employeemanage.service.EmployeeManageService;
import kr.or.ddit.info1.vo.EmployeeVO;
import kr.or.ddit.validate.groups.InsertGroup;
import kr.or.ddit.validate.util.hubWebValidateUtil;
import kr.or.ddit.validate.util.hubWebValidateUtilImpl;

@Controller
public class EmployeeManageInsertController {
	@Inject
	private EmployeeManageService service;
	@Inject
	private WebApplicationContext context;
	
	@PostMapping(value="/info1/employeeInsert.do", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public Map<String,Object> EmployeeManageInsert(
			@Validated(InsertGroup.class) @ModelAttribute EmployeeVO employee,
			Errors errors
			) {
		hubWebValidateUtil valid = new hubWebValidateUtilImpl(errors, context);
		if(!valid.checkError()) {
			valid.setResultMap(service.createEmployee(employee));
		}
		return valid.getResultMap();
	}
}
