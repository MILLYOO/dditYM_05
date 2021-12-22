package kr.or.ddit.sales.salesclose.controller;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import kr.or.ddit.sales.salesclose.service.SalesCloseService;

/**
 * 매출마감
 */
@Controller
public class SalesCloseUpdatecontroller {
	@Inject
	private SalesCloseService service;
	
	@RequestMapping("/produce/SalesCloseUpdate.do")
	public String SalesCloseUpdate() {
		
		String viewName = null;
		return viewName;
	}
}
