package kr.or.ddit.sales.releaseorderstatus.controller;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import kr.or.ddit.sales.releaseorderstatus.service.ReleaseOrderStatusService;

/**
 * 출고지시서현황
 */
@Controller
public class ReleaseOrderStatusRetrieveController {
	@Inject
	private ReleaseOrderStatusService service;
	
	@RequestMapping("/sales/ReleaseOrderStatusRetrieve.do")
	public String ReleaseOrderStatusRetrieve() {
		
		String viewName = "sales/statusRelord";
		return viewName;
	}
}
