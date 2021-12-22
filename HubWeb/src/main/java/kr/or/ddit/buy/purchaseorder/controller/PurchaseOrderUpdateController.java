package kr.or.ddit.buy.purchaseorder.controller;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import kr.or.ddit.buy.purchaseorder.service.PurchaseOrderService;

/**
 * 발주서
 */
@Controller
public class PurchaseOrderUpdateController {
	@Inject
	private PurchaseOrderService service;
	
	@RequestMapping("/buy/purchaseOrderUpdate.do")
	public String PurchaseOrderUpdate() {
		
		String viewName = null;
		return viewName;
	}
}
