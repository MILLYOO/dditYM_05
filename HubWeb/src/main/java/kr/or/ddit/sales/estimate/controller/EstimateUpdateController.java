package kr.or.ddit.sales.estimate.controller;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import kr.or.ddit.sales.estimate.service.EstimateService;

/**
 * 견적서
 */
@Controller
public class EstimateUpdateController {
	@Inject
	private EstimateService service;
	
	@RequestMapping("/sales/EstimateUpdate.do")
	public String EstimateUpdate() {
		
		String viewName = null;
		return viewName;
	}
}
