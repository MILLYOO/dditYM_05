package kr.or.ddit.stock.stockstatusbystandard.controller;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import kr.or.ddit.stock.stockstatusbystandard.service.StockStatusByStandardService;

/**
 * 규격군별재고현황
 */
@Controller
public class StockStatusByStandardRetrieveController {
	@Inject
	private StockStatusByStandardService service;
	
	@RequestMapping("/stock/StockStatusByStandardRetrieve.do")
	public String StockStatusByStandardRetrieve() {
		
		String viewName = "stock/statusPerStandardStock";
		return viewName;
	}
}
