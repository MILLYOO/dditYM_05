package kr.or.ddit.stock.stockvaluation.controller;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import kr.or.ddit.stock.stockvaluation.service.InventoryValuationService;

/**
 * 재고평가
 */
@Controller
public class InventoryValuationUpdateController {
	@Inject
	private InventoryValuationService service;
	
	@RequestMapping("/stock/InventoryValuationUpdate.do")
	public String InventoryValuationUpdate() {
		
		String viewName = null;
		return viewName;
	}
}
