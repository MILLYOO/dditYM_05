package kr.or.ddit.buy.incoming.controller;

import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.WebApplicationContext;

import kr.or.ddit.buy.incoming.service.IncomingService;
import kr.or.ddit.buy.vo.OrderRawsVO;
import kr.or.ddit.buy.vo.OrderVO;
import kr.or.ddit.common.vo.CommonListVO;
import kr.or.ddit.common.vo.HubSearchVO;
import kr.or.ddit.validate.groups.DetailUpdateGroup;
import kr.or.ddit.validate.groups.UpdateGroup;
import kr.or.ddit.validate.util.hubWebValidateUtil;
import kr.or.ddit.validate.util.hubWebValidateUtilImpl;
import lombok.extern.slf4j.Slf4j;

/**
 * 입고처리서에 발주서 적용
 */
@Controller
@Slf4j
public class IncOrderApplyController {
	@Inject
	private WebApplicationContext context;	
	
	@Inject
	private IncomingService service;
	
	//발주서 적용 masterGrid 
	@ResponseBody
	@RequestMapping(value="/buy/incOrderApply.do",produces=MediaType.APPLICATION_JSON_UTF8_VALUE,method=RequestMethod.POST)
	public List<OrderVO> incOrderApply(@ModelAttribute("hubSearchVO") HubSearchVO hubSearchVO){
		List<OrderVO> ioListForInc = service.retrieveOrderListForInc(hubSearchVO);
		return ioListForInc;
	}
	
	//발주서 적용 detailGrid
	@ResponseBody
	@RequestMapping(value="/buy/incOrderRawsApply.do",method=RequestMethod.POST,produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public List<OrderRawsVO> incOrderRawsApply(@RequestParam("ordNum") String ordNum) {
		OrderVO orderVO = new OrderVO();
		orderVO.setOrdNum(ordNum);
		List<OrderRawsVO> orderRawsList = service.retrieveOrderRawsListForInc(orderVO);
		return orderRawsList;
	}
	

	//발주서 적용
	@ResponseBody
	@RequestMapping(value="/buy/incOrderApplyInsert.do",method=RequestMethod.POST,produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public Map<String, Object> IncOrderApplyInsert(
			@Validated(DetailUpdateGroup.class) @RequestBody CommonListVO<OrderVO> orderVO
			,Errors errors
			) {
		hubWebValidateUtil valid = new hubWebValidateUtilImpl(orderVO,errors, context);
		if(!valid.checkError()) {
			valid.setResultMap(service.insertIncByOrd(orderVO.getCommonList()));
		}
		return valid.getResultMap();
	}

}
