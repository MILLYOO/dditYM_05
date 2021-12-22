package kr.or.ddit.stock.inoutadjustment.controller;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import kr.or.ddit.stock.inoutadjustment.service.InOutAdjustmentService;

/**
 * 입출고조정
 */
@Controller
public class InOutAdjustmentUpdateController {
	@Inject
	private InOutAdjustmentService service;
	
	@RequestMapping("/stock/InOutAdjustmentUpdate.do")
	public String InOutAdjustmentUpdate() {
		
		String viewName = null;
		return viewName;
	}
}
