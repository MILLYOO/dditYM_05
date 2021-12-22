package kr.or.ddit.sales.releasestatusbyorderbook.controller;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import kr.or.ddit.sales.releasestatusbyorderbook.service.ReleaseStatusByOrderBookService;

/**
 * 수주대비출고현황
 */
@Controller
public class ReleaseStatusByOrderBookRetrieveController {
	@Inject
	private ReleaseStatusByOrderBookService service;
	
	@RequestMapping("/sales/ReleaseStatusByOrderBookRetrieve.do")
	public String ReleaseStatusByOrderBookRetrieve() {
		
		String viewName = "sales/statusMreleaseCompOrder";
		return viewName;
	}
}
