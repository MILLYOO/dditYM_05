package kr.or.ddit.sales.salesclosestatus.controller;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import kr.or.ddit.sales.salesclosestatus.service.SalesCloseStatusService;

/**
 * 매출마감현황
 */
@Controller
public class SalesCloseStatusRetrieveController {
	@Inject
	private SalesCloseStatusService service;
	
	@RequestMapping("/produce/SalesCloseStatusRetrieve.do")
	public String SalesCloseStatusRetrieve() {
		
		String viewName = "sales/statussalesclose";
		return viewName;
	}
}
