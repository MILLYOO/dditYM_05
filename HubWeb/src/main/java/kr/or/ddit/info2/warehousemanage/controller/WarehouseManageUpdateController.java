package kr.or.ddit.info2.warehousemanage.controller;

import java.util.Map;

import javax.inject.Inject;
import javax.validation.Valid;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.WebApplicationContext;

import kr.or.ddit.info2.vo.WarehouseVO;
import kr.or.ddit.info2.warehousemanage.service.WarehouseManageService;
import kr.or.ddit.validate.util.hubWebValidateUtil;
import kr.or.ddit.validate.util.hubWebValidateUtilImpl;

/**
 * 창고 관리
 */
@Controller
public class WarehouseManageUpdateController {
	@Inject
	private WarehouseManageService service;
	@Inject
	private WebApplicationContext context;
	
	@RequestMapping(value="/info2/warehouseUpdate.do", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public Map<String,Object> warehouseUpdate(
			@Valid @ModelAttribute("warehouse") WarehouseVO warehouse,
			Errors errors
			) {
		hubWebValidateUtil valid = new hubWebValidateUtilImpl(errors, context);
		if (!valid.checkError()) {
			valid.setResultMap(service.updateWarehouse(warehouse));
		}
		return valid.getResultMap();
	}
	
	@RequestMapping(value="/info2/warehouseDetailUpdate.do", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public Map<String, Object> warehouseDetailUpdate(
			@Valid @ModelAttribute("warehouse") WarehouseVO warehouse,
			Errors errors
			) {
		hubWebValidateUtil valid = new hubWebValidateUtilImpl(errors, context);
		if (!valid.checkError()) {
			valid.setResultMap(service.updateWarehouseDetail(warehouse));
		}
		return valid.getResultMap();
	}
}
