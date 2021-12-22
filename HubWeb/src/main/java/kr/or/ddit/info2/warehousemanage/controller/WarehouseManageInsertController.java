package kr.or.ddit.info2.warehousemanage.controller;

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

import kr.or.ddit.info2.vo.WarehouseVO;
import kr.or.ddit.info2.warehousemanage.service.WarehouseManageService;
import kr.or.ddit.validate.groups.InsertGroup;
import kr.or.ddit.validate.util.hubWebValidateUtil;
import kr.or.ddit.validate.util.hubWebValidateUtilImpl;

/**
 * 창고 관리
 */
@Controller
public class WarehouseManageInsertController {
	@Inject
	private WarehouseManageService service;
	@Inject
	private WebApplicationContext context;
	
	@RequestMapping(value="/info2/warehouseInsert.do", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public Map<String,Object> WarehouseManageInsert(		
			@Validated(InsertGroup.class)@ModelAttribute WarehouseVO warehouse,
			Errors errors
			) {
		hubWebValidateUtil valid = new hubWebValidateUtilImpl(errors, context);
		if (!valid.checkError()) {
			valid.setResultMap(service.createWarehouse(warehouse));
		}
		return valid.getResultMap();
	}
}
