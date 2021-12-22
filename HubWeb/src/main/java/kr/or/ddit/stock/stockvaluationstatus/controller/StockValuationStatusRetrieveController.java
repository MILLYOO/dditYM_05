package kr.or.ddit.stock.stockvaluationstatus.controller;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import kr.or.ddit.stock.stockvaluationstatus.service.StockValuationStatusService;

/**
 * 재고평가현황
 */
@Controller
public class StockValuationStatusRetrieveController {
	@Inject
	private StockValuationStatusService service;
	
	@RequestMapping("/stock/StockValuationStatusRetrieve.do")
	public String StockValuationStatusRetrieve() {
		
		String viewName = "stock/statusStockEstimate";
		return viewName;
	}
}
