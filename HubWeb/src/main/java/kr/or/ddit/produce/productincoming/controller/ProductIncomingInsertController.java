package kr.or.ddit.produce.productincoming.controller;

import java.util.Map;

import javax.inject.Inject;
import javax.validation.groups.Default;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.WebApplicationContext;

import kr.or.ddit.common.vo.CommonListVO;
import kr.or.ddit.produce.productincoming.service.ProductIncomingService;
import kr.or.ddit.produce.vo.ProdWareVO;
import kr.or.ddit.produce.vo.PrwaProdVO;
import kr.or.ddit.validate.util.hubWebValidateUtil;
import kr.or.ddit.validate.util.hubWebValidateUtilImpl;

/**
 * 생산품입고처리
 */
@Controller
public class ProductIncomingInsertController {
	@Inject
	private WebApplicationContext context;	
	
	@Inject
	private ProductIncomingService service;
	
	// 생산품입고처리 등록 및 수정
	@ResponseBody
	@RequestMapping(value="/produce/productIncomingInsert.do",method=RequestMethod.POST,produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public Map<String, Object> productIncomingInsert(
			@Validated(Default.class) @RequestBody CommonListVO<ProdWareVO> obj
			,Errors errors
			) {
		hubWebValidateUtil valid = new hubWebValidateUtilImpl(obj, errors, context);
		if(!valid.checkError()) {
			valid.setResultMap(service.createOrUpdateProductIncoming(obj.getCommonList()));
		}
		return valid.getResultMap();
	}
	
	// 생산품입고처리-제품 등록 및 수정
	@ResponseBody
	@RequestMapping(value="/produce/productIncomingRawsInsert.do",method=RequestMethod.POST,produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public Map<String, Object> productIncomingRawsInsert(
			@Validated(Default.class) @RequestBody CommonListVO<PrwaProdVO> obj
			,Errors errors
			) {
		hubWebValidateUtil valid = new hubWebValidateUtilImpl(obj, errors, context);
		if(!valid.checkError()) {
			valid.setResultMap(service.createOrUpdateIncomingProduct(obj.getCommonList()));
		}
		return valid.getResultMap();
	}
}
