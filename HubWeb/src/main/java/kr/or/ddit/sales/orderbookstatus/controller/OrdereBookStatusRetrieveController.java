package kr.or.ddit.sales.orderbookstatus.controller;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import kr.or.ddit.sales.orderbookstatus.service.OrdereBookStatusService;

/**
 * 수주현황
 */
@Controller
public class OrdereBookStatusRetrieveController {
	@Inject
	private OrdereBookStatusService service;
	
	@RequestMapping("/sales/OrdereBookStatusRetrieve.do")
	public String OrdereBookStatusRetrieve() {
		
		String viewName = "sales/statusOrderbook";
		return viewName;
	}
}
