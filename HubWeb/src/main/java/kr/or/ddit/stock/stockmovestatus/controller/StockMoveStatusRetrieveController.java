package kr.or.ddit.stock.stockmovestatus.controller;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import kr.or.ddit.stock.stockmovestatus.service.StockMoveStatusService;

/**
 * 재고이동현황
 */
@Controller
public class StockMoveStatusRetrieveController {
	@Inject
	private StockMoveStatusService service;
	
	@RequestMapping("/stock/StockMoveStatusRetrieve.do")
	public String StockMoveStatusRetrieve() {
		
		String viewName = "stock/statusStockmove";
		return viewName;
	}
}
