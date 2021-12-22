package kr.or.ddit.buy.purchaseorder.controller;

import java.util.List;

import javax.inject.Inject;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.or.ddit.buy.purchaseorder.service.PurchaseOrderService;
import kr.or.ddit.buy.vo.OrderRawsVO;
import kr.or.ddit.buy.vo.OrderVO;
import kr.or.ddit.enumpkg.ServiceResult;
import lombok.extern.slf4j.Slf4j;

/**
 * 발주서 삭제
 */
@Controller
@Slf4j
public class PurchaseOrderDeleteController {

	@Inject
	private PurchaseOrderService service;
	
	//발주서 삭제
	@ResponseBody
	@RequestMapping(value="/buy/orderDelete.do",produces=MediaType.APPLICATION_JSON_UTF8_VALUE,method=RequestMethod.POST)
	public String PurchaseOrderDelete(
			@RequestBody List<OrderVO> orderVO
			) {
		String resultMessage = null;
		
		ServiceResult result = service.removeOrder(orderVO);
		switch(result) {
		case OK:
			resultMessage = "삭제되었습니다.";
			break;
		default:
			resultMessage = "서버오류";
			break;
		}
		return resultMessage;
	}
	
	//발주서 - 원자재 삭제
	@ResponseBody
	@RequestMapping(value="/buy/orderRawsDelete.do",produces=MediaType.APPLICATION_JSON_UTF8_VALUE,method=RequestMethod.POST)
	public String PurchaseOrderRawsDelete(
			@RequestBody List<OrderRawsVO> orderRawsVO
			) {
		String resultMessage = null;
		
		ServiceResult result = service.removeRaws(orderRawsVO);
		switch(result) {
		case OK:
			resultMessage = "삭제되었습니다.";
			break;
		default:
			resultMessage = "서버오류";
			break;
		}
	return resultMessage;
	}
}