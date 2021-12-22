package kr.or.ddit.stock.stockvaluation.controller;

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

import kr.or.ddit.stock.stockvaluation.service.InventoryValuationService;
import kr.or.ddit.stock.vo.StoValuVO;
import kr.or.ddit.validate.groups.InsertGroup;
import kr.or.ddit.validate.util.hubWebValidateUtil;
import kr.or.ddit.validate.util.hubWebValidateUtilImpl;

/**
 * 재고평가
 */
@Controller
public class InventoryValuationInsertController {
	@Inject
	private InventoryValuationService service;
	@Inject
	private WebApplicationContext context;
	
	//재고평가 등록
	@ResponseBody
	@RequestMapping(value="/stock/inventoryValuationInsert.do", method=RequestMethod.POST, produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public Map<String, Object> InventoryValuationInsert(
			@Validated(InsertGroup.class) @ModelAttribute("stoValuVO") StoValuVO stoValuVO
			,Errors errors
			) {
		
		hubWebValidateUtil valid = new hubWebValidateUtilImpl(errors, context);
		if(!valid.checkError()) {
			valid.setResultMap(service.createInventoryValuation(stoValuVO));
		}
		return valid.getResultMap();
	}
}
