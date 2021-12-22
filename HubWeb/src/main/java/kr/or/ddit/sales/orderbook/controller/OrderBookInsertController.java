package kr.or.ddit.sales.orderbook.controller;

import java.util.Map;

import javax.inject.Inject;

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
import kr.or.ddit.sales.orderbook.service.OrderBookService;
import kr.or.ddit.sales.vo.OrderBookProdVO;
import kr.or.ddit.sales.vo.OrderBookVO;
import kr.or.ddit.validate.groups.UpdateGroup;
import kr.or.ddit.validate.util.hubWebValidateUtil;
import kr.or.ddit.validate.util.hubWebValidateUtilImpl;

/**
 * 수주서
 */
@Controller
public class OrderBookInsertController {
	@Inject
	private OrderBookService service;
	@Inject
	private WebApplicationContext context;
	
	// 수주서 등록
	@ResponseBody
	@RequestMapping(value="/sales/OrderbookInsert.do", method=RequestMethod.POST, produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public Map<String, Object> OrderbookInsert(
			@RequestBody @Validated(UpdateGroup.class) CommonListVO<OrderBookVO> obj
			,Errors errors
			) {
		// 검증 시행 조건문
		hubWebValidateUtil valid = new hubWebValidateUtilImpl(obj, errors, context); // 검증조건에서 에러가 발생하면
		if (!valid.checkError()) {
			valid.setResultMap(service.createORupdateOrderBook(obj.getCommonList()));
		}
		return valid.getResultMap();
	}
	// 수주서 제품 등록
	@RequestMapping(value="/sales/OrderbookProdInsert.do", method=RequestMethod.POST, produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public Map<String, Object> OrderbookProdInsert(
			@RequestBody @Validated(UpdateGroup.class) CommonListVO<OrderBookProdVO> obj,
			Errors errors
			) {
		hubWebValidateUtil valid = new hubWebValidateUtilImpl(obj, errors, context);
		if (!valid.checkError()) {
			valid.setResultMap(service.createRaws(obj.getCommonList()));
		}
		return valid.getResultMap();
	}
}
