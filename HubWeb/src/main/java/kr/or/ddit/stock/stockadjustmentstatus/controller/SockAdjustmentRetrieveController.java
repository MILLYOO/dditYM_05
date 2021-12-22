package kr.or.ddit.stock.stockadjustmentstatus.controller;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import kr.or.ddit.stock.stockadjustmentstatus.service.SockAdjustmentService;

/**
 * 재고조정현황
 */
@Controller
public class SockAdjustmentRetrieveController {
	@Inject
	private SockAdjustmentService service;
	
	@RequestMapping("/stock/SockAdjustmentRetrieve.do")
	public String SockAdjustmentRetrieve() {
		
		String viewName = "stock/statusStockadjust";
		return viewName;
	}
}
