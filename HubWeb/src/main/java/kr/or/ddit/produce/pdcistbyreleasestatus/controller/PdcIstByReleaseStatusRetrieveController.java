package kr.or.ddit.produce.pdcistbyreleasestatus.controller;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import kr.or.ddit.produce.pdcistbyreleasestatus.service.PdcIstByReleaseStatusService;

/**
 * 생산지시대비출고현황
 */
@Controller
public class PdcIstByReleaseStatusRetrieveController {
	@Inject
	private PdcIstByReleaseStatusService service;
	
	@RequestMapping("/produce/PdcIstByReleaseStatusRetrieve.do")
	public String IncomingStatusByOrderRetrieve() {
		
		String viewName = "produce/statusMreleaseCompInstrucItem";
		return viewName;
	}
}
