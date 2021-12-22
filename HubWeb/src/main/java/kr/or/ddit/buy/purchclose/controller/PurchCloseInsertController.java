package kr.or.ddit.buy.purchclose.controller;

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

import kr.or.ddit.buy.purchclose.service.PurchCloseService;
import kr.or.ddit.buy.vo.CloseRawsVO;
import kr.or.ddit.buy.vo.PurchCloseVO;
import kr.or.ddit.common.vo.CommonListVO;
import kr.or.ddit.validate.util.hubWebValidateUtil;
import kr.or.ddit.validate.util.hubWebValidateUtilImpl;

/**
 * 매입마감
 */
@Controller
public class PurchCloseInsertController {
	@Inject
	private WebApplicationContext context;
	@Inject
	private PurchCloseService service;
	
	@RequestMapping(value="/buy/pCloseInsert.do", produces=MediaType.APPLICATION_JSON_UTF8_VALUE,method=RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> PurchCloseInsert(
			@Validated(Default.class) @RequestBody CommonListVO<PurchCloseVO> obj
			,Errors errors
			) {
		
		hubWebValidateUtil valid = new hubWebValidateUtilImpl(obj, errors, context);
		if(!valid.checkError()) {
			valid.setResultMap(service.createOrModifyPurchClose(obj.getCommonList()));
		}
		return valid.getResultMap();
	}
	
	@RequestMapping(value="/buy/pCloseRawsInsert.do", produces=MediaType.APPLICATION_JSON_UTF8_VALUE, method=RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> PurchCloseRawsInsert(
			@Validated(Default.class) @RequestBody CommonListVO<CloseRawsVO> obj
			,BindingResult errors
			) {
		
		hubWebValidateUtil valid = new hubWebValidateUtilImpl(obj, errors, context);
		if(!valid.checkError()) {
			valid.setResultMap(service.createOrUpdateRaws(obj.getCommonList()));
		}
		return valid.getResultMap();
	}

}
