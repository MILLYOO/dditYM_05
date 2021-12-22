package kr.or.ddit.sales.releasestatusbyreleaseorder.controller;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import kr.or.ddit.sales.releasestatusbyreleaseorder.service.ReleaseStatusByReleaseOrderService;

/**
 * 출고지시서대비출고현황
 */
@Controller
public class ReleaseStatusByReleaseOrderRetrieveController {
	@Inject
	private ReleaseStatusByReleaseOrderService service;
	
	@RequestMapping("/sales/ReleaseStatusByReleaseOrderRetrieve.do")
	public String ReleaseStatusByReleaseOrderRetrieve() {
		
		String viewName = "sales/statusMreleaseCompRelord";
		return viewName;
	}
}
