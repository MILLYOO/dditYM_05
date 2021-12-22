package kr.or.ddit.buy.purchaseorder.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.inject.Inject;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.or.ddit.buy.purchaseorder.service.PurchaseOrderService;
import kr.or.ddit.buy.vo.OrderRawsVO;
import kr.or.ddit.buy.vo.OrderVO;
import kr.or.ddit.common.dao.DocCheckDAO;
import kr.or.ddit.common.vo.HubSearchVO;

/**
 * 발주서
 */
@Controller
public class PurchaseOrderRetrieveController {
	
	@Inject
	private PurchaseOrderService service;
	
	@Resource(name="docCheckDAO")
	private DocCheckDAO docCheckDAO;
	
	@RequestMapping(value="/buy/orderList.do")
	public String PurchaseOrderListGet() {
		int cnt = docCheckDAO.updateChkYN("OD");
		String viewName = "buy/order";
		return viewName;
	}
	
	@ResponseBody
	@RequestMapping(value="/buy/orderList.do", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public List<OrderVO> orderList(
			 @ModelAttribute("hubSearchVO") HubSearchVO hubSearchVO
			){
		List<OrderVO> orderList = service.retrieveOrderList(hubSearchVO);
		return orderList;
	}
	
	@ResponseBody
	@RequestMapping(value="/buy/orderRawsList.do", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public List<OrderRawsVO> orderRawsList(
			 @RequestParam("ordNum") String ordNum
			){
		OrderVO orderVO = new OrderVO();
		orderVO.setOrdNum(ordNum);
		List<OrderRawsVO> orderRawsList = service.retrieveOrderRawsList(orderVO);
		
		return orderRawsList;
	}
}
