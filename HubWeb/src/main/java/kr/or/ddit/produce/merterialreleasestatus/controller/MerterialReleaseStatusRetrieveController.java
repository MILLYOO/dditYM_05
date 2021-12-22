package kr.or.ddit.produce.merterialreleasestatus.controller;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import kr.or.ddit.produce.merterialreleasestatus.service.MerterialReleaseStatusService;

/**
 * 자재출고현황
 */
@Controller
public class MerterialReleaseStatusRetrieveController {
	@Inject
	private MerterialReleaseStatusService service;
	
	@RequestMapping("/produce/MerterialReleaseStatusRetrieve.do")
	public String MerterialReleaseStatusRetrieve() {
		
		String viewName = "produce/statusMrelease";
		return viewName;
	}
}
