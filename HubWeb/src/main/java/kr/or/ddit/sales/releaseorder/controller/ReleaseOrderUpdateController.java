package kr.or.ddit.sales.releaseorder.controller;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import kr.or.ddit.sales.releaseorder.service.ReleaseOrderService;

/**
 * 출고지시서
 */
@Controller
public class ReleaseOrderUpdateController {
	@Inject
	private ReleaseOrderService service;
	
	@RequestMapping("/sales/ReleaseOrderUpdate.do")
	public String ReleaseOrderUpdate() {
		
		String viewName = null;
		return viewName;
	}
}
