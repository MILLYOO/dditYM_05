package kr.or.ddit.stock.stockmove.controller;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import kr.or.ddit.stock.stockmove.service.StockMoveService;

/**
 * 재고이동
 */
@Controller
public class StockMoveUpdateController {
	@Inject
	private StockMoveService service;
	
	@RequestMapping("/stock/StockMoveUpdate.do")
	public String StockMoveUpdate() {
		
		String viewName = null;
		return viewName;
	}
}
