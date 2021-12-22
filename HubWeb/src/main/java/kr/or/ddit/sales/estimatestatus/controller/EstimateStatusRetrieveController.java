package kr.or.ddit.sales.estimatestatus.controller;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import kr.or.ddit.sales.estimatestatus.service.EstimateStatusService;

/**
 * 견적현황
 */
@Controller
public class EstimateStatusRetrieveController {
	@Inject
	private EstimateStatusService service;
	
	@RequestMapping("/sales/EstimateStatusRetrieve.do")
	public String EstimateStatusRetrieve() {
		
		String viewName = "sales/statusEstimate";
		return viewName;
	}
}
