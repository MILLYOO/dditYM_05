package kr.or.ddit.sales.releaseprocessing.controller;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import kr.or.ddit.sales.releaseprocessing.service.ReleaseProcessingService;

/**
 * 출고처리
 */
@Controller
public class ReleaseProcessingUpdateController {
	@Inject
	private ReleaseProcessingService service;
	
	@RequestMapping("/sales/ReleaseProcessingUpdate.do")
	public String ReleaseProcessingUpdate() {
		
		String viewName = null;
		return viewName;
	}
}
