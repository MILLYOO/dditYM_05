package kr.or.ddit.stock.stockstatusbyproject.controller;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import kr.or.ddit.stock.stockstatusbyproject.service.StockStatusByProjectService;

/**
 * 프로젝트별재고현황
 */
@Controller
public class StockStatusByProjectRetrieveController {
	@Inject
	private StockStatusByProjectService service;
	
	@RequestMapping("/stock/StockStatusByProjectRetrieve.do")
	public String StockStatusByProjectRetrieve() {
		
		String viewName = "stock/statusPerProjectStock";
		return viewName;
	}
}
