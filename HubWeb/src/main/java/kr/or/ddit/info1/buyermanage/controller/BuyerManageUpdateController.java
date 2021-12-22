package kr.or.ddit.info1.buyermanage.controller;
/**
 * 거래처 등록
 */

import java.util.Map;

import javax.inject.Inject;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.WebApplicationContext;

import kr.or.ddit.info1.buyermanage.service.BuyerManageService;
import kr.or.ddit.info1.vo.BuyerVO;
import kr.or.ddit.validate.groups.DetailUpdateGroup;
import kr.or.ddit.validate.groups.UpdateGroup;
import kr.or.ddit.validate.util.hubWebValidateUtil;
import kr.or.ddit.validate.util.hubWebValidateUtilImpl;
import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class BuyerManageUpdateController {
	@Inject
	private WebApplicationContext context;
	
	@Inject
	private BuyerManageService service;
	
	@PostMapping(value="/info1/buyerUpdate.do", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public Map<String, Object> buyerUpdate(
			@Validated(UpdateGroup.class) @ModelAttribute BuyerVO buyer,
			Errors errors
			) {
		hubWebValidateUtil valid = new hubWebValidateUtilImpl(errors, context);
		if(!valid.checkError()) {
			valid.setResultMap(service.updateBuyer(buyer));
		}
		return valid.getResultMap();
	}
	
	// 유틸 참고용 메소드
	@PostMapping(value="/info1/buyerDetailUpdate.do", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public Map<String, Object> buyerDetailUpdate(
			@Validated(DetailUpdateGroup.class) @ModelAttribute BuyerVO buyer,
			BindingResult errors
			) {
		hubWebValidateUtil valid = new hubWebValidateUtilImpl(errors, context);
		if(!valid.checkError()) {
			valid.setResultMap(service.updateBuyerDetail(buyer));
		}
		return valid.getResultMap();
	}
	
	
}
