package kr.or.ddit.stock.stockpaymentstatus.controller;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import kr.or.ddit.stock.stockpaymentstatus.service.StockPaymentStatusService;

/**
 * 재고수불현황
 */
@Controller
public class StockPaymentStatusRetrieveController {
	@Inject
	private StockPaymentStatusService service;
	
	@RequestMapping("/stock/StockPaymentStatusRetrieve.do")
	public String StockPaymentStatusRetrieve() {
		
		String viewName = "stock/statusStockstatus";
		return viewName;
	}
}
