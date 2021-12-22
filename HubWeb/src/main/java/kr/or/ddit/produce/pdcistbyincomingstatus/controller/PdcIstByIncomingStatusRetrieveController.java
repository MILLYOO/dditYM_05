 package kr.or.ddit.produce.pdcistbyincomingstatus.controller;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import kr.or.ddit.produce.pdcistbyincomingstatus.service.PdcIstByIncomingStatusService;
/**
 * 생산지시대비입고현황
 */
@Controller
public class PdcIstByIncomingStatusRetrieveController {
	@Inject
	private PdcIstByIncomingStatusService service;
	
	@RequestMapping("/produce/PdcIstByIncomingStatusRetrieve.do")
	public String PdcIstByIncomingStatusRetrieve() {
		
		String viewName = "produce/statusMreleaseCompProdware";
		return viewName;
	}
}
