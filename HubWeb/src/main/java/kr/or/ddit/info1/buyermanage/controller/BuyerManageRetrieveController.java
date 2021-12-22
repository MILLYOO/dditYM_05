package kr.or.ddit.info1.buyermanage.controller;

import java.util.List;

import javax.inject.Inject;
import javax.validation.Valid;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.or.ddit.common.vo.HubSearchVO;
import kr.or.ddit.info1.buyermanage.service.BuyerManageService;
import kr.or.ddit.info1.vo.BuyerVO;

/**
 * 거래처등록
 */
@Controller
public class BuyerManageRetrieveController {
	@Inject
	private BuyerManageService service;
	
	@RequestMapping(value="/info1/buyerRetrieve.do", method=RequestMethod.GET)
	public String buyerManageRetrieve() {
		
		String viewName = "info/buyerRegister";
		return viewName;
	}
	
	@ResponseBody
	@RequestMapping(value="/info1/buyerRetrieve.do", method=RequestMethod.POST, produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public List<BuyerVO> buyerList(
			@Valid @ModelAttribute("hubSearch") HubSearchVO hubSearch,
			Errors errors
			) {
		List<BuyerVO> buyerList = service.retrieveBuyerList(hubSearch);
		return buyerList;
	}
}















