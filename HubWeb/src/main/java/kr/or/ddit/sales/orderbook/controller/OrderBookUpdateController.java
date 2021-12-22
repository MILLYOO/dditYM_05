package kr.or.ddit.sales.orderbook.controller;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import kr.or.ddit.sales.orderbook.service.OrderBookService;

/**
 * 수주서
 */
@Controller
public class OrderBookUpdateController {
	@Inject
	private OrderBookService service;
	
	@RequestMapping("/sales/OrderBookUpdate.do")
	public String OrderBookUpdate() {
		
		String viewName = null;
		return viewName;
	}
}
