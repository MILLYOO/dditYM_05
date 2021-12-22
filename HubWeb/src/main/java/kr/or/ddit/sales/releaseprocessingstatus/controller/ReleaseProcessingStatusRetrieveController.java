package kr.or.ddit.sales.releaseprocessingstatus.controller;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import kr.or.ddit.sales.releaseprocessingstatus.service.ReleaseProcessingStatusService;

/**
 * 출고현황
 */
@Controller
public class ReleaseProcessingStatusRetrieveController {
	@Inject
	private ReleaseProcessingStatusService service;
	
	@RequestMapping("/sales/ReleaseProcessingStatusRetrieve.do")
	public String ReleaseProcessingStatusRetrieve() {
		
		String viewName = "sales/statusRelease";
		return viewName;
	}
}
