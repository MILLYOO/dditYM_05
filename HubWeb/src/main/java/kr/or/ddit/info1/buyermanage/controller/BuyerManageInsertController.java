package kr.or.ddit.info1.buyermanage.controller;

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

import kr.or.ddit.info1.buyermanage.service.BuyerManageService;
import kr.or.ddit.info1.vo.BuyerVO;
import kr.or.ddit.validate.groups.InsertGroup;
import kr.or.ddit.validate.util.hubWebValidateUtil;
import kr.or.ddit.validate.util.hubWebValidateUtilImpl;

/**
 * 거래처 등록
 */
@Controller
public class BuyerManageInsertController {
	@Inject
	private BuyerManageService service;
	
	@Inject
	private WebApplicationContext context;
	
	@RequestMapping(value="/info1/buyerInsert.do", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public Map<String, Object> buyerInsert(	
			@Validated(InsertGroup.class) @ModelAttribute BuyerVO buyer,
			Errors errors
			) {
		hubWebValidateUtil valid = new hubWebValidateUtilImpl(errors, context);
		if(!valid.checkError()) {
			valid.setResultMap(service.createBuyer(buyer));
		}
		return valid.getResultMap();
	}
}
