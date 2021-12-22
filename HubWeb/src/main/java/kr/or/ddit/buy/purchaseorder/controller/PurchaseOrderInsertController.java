package kr.or.ddit.buy.purchaseorder.controller;

import java.util.Map;

import javax.inject.Inject;
import javax.validation.groups.Default;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.WebApplicationContext;

import kr.or.ddit.buy.purchaseorder.service.PurchaseOrderService;
import kr.or.ddit.buy.vo.OrderRawsVO;
import kr.or.ddit.buy.vo.OrderVO;
import kr.or.ddit.common.vo.CommonListVO;
import kr.or.ddit.validate.util.hubWebValidateUtil;
import kr.or.ddit.validate.util.hubWebValidateUtilImpl;

/**
 * 발주서 등록
 */
@Controller
public class PurchaseOrderInsertController {
	@Inject
	private WebApplicationContext context;
	@Inject
	private PurchaseOrderService service;
	
	@ResponseBody
	@RequestMapping(value="/buy/orderInsert.do",produces=MediaType.APPLICATION_JSON_UTF8_VALUE,method=RequestMethod.POST)
	public Map<String, Object> PurchaseOrderInsert(
			@Validated(Default.class) @RequestBody CommonListVO<OrderVO> obj
			,Errors errors
			) {
		
		hubWebValidateUtil valid = new hubWebValidateUtilImpl(obj, errors, context);
		if(!valid.checkError()) {
			valid.setResultMap(service.createOrModifyOrder(obj.getCommonList()));
		}
		return valid.getResultMap();
	}
	
	@ResponseBody	
	@RequestMapping(value="/buy/orderRawsInsert.do",produces=MediaType.APPLICATION_JSON_UTF8_VALUE,method=RequestMethod.POST)
	public Map<String, Object> PurchaseOrderRawsInsert(
			@Validated(Default.class) @RequestBody CommonListVO<OrderRawsVO> obj
			,BindingResult errors
			) {
		
		hubWebValidateUtil valid = new hubWebValidateUtilImpl(obj,errors, context);
		if(!valid.checkError()) {
			valid.setResultMap(service.createOrModifyRaws(obj.getCommonList()));
		}
		return valid.getResultMap();
	}
}
