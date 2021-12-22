package kr.or.ddit.info1.departmentmanage.controller;

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

import kr.or.ddit.info1.departmentmanage.service.DepartmentManageService;
import kr.or.ddit.info1.vo.DepartmentVO;
import kr.or.ddit.validate.util.hubWebValidateUtil;
import kr.or.ddit.validate.util.hubWebValidateUtilImpl;

@Controller
public class DepartmentManageDeleteController {
	@Inject
	private DepartmentManageService service;
	
	@Inject
	private WebApplicationContext context;
	
	@PostMapping(value="/info1/departmentDelete.do", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public Map<String, Object> DepartmentManageDelete(
			@Valid @ModelAttribute("department") DepartmentVO department,
			Errors errors
			) {
		hubWebValidateUtil valid = new hubWebValidateUtilImpl(errors, context);
		if(!valid.checkError()) {
			valid.setResultMap(service.deleteDepartmentManage(department));
		}
		return valid.getResultMap();
	}
}
