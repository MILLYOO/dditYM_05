package kr.or.ddit.sales.orderbook.controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.inject.Inject;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.or.ddit.common.dao.DocCheckDAO;
import kr.or.ddit.common.vo.HubSearchVO;
import kr.or.ddit.sales.orderbook.service.OrderBookService;
import kr.or.ddit.sales.vo.OrderBookProdVO;
import kr.or.ddit.sales.vo.OrderBookVO;

/**
 * 수주서
 */
@Controller
public class OrderBookRetrieveController {
	@Inject
	private OrderBookService service;
	@Resource(name="docCheckDAO")
	private DocCheckDAO docCheckDAO;
	
	// 초기화면
	@RequestMapping("/sales/OrderbookRetrieveView.do")
	public String OrderBookRetrieve() {
		int cnt = docCheckDAO.updateChkYN("OR");
		String viewName = "sales/orderbook";
		return viewName;
	}
	
	// 수주서 조회
	@RequestMapping(value="/sales/OrderbookRetrieve.do", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public List<OrderBookVO> OrderbookRetrieve(
			@ModelAttribute("hubSearchVO") HubSearchVO hubSearchVO
			) {
		// 수주서 리스트(검색조건 포함)
		List<OrderBookVO> orderbookList = new ArrayList<>();
		orderbookList = service.retrieveOrderBookList(hubSearchVO); 
		return orderbookList;
	}
	
	// 수주서 제품 조회
	@RequestMapping(value="/sales/OrderbookProdRetrieve.do", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public List<OrderBookProdVO> OrderbookProdRetrieve(
			@ModelAttribute("orbCode") OrderBookVO orbCode
			) {
		List<OrderBookProdVO> orderbookProdList = service.retrieveOrderBookProdList(orbCode);
		return orderbookProdList;
	}
	
	
	
}
