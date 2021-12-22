package kr.or.ddit.stock.inoutstatusbyperiod.controller;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import kr.or.ddit.stock.inoutstatusbyperiod.service.InOutStatusByPeriodService;

/**
 * 기간별입출고현황
 */
@Controller
public class InOutStatusByPeriodRetrieveController {
	@Inject
	private InOutStatusByPeriodService service;
	
	@RequestMapping("/stock/InOutStatusByPeriodRetrieve.do")
	public String InOutStatusByPeriodRetrieve() {
		
		String viewName = "stock/statusPerPeriodStoredRelease";
		return viewName;
	}

}
