package kr.or.ddit.produce.bomenrollmentstatus.controller;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import kr.or.ddit.produce.bomenrollmentstatus.service.BomEnrollmentStatusService;


/**
 * BOM 등록 현황
 */
@Controller
public class BomEnrollmentStatusRetrieveController {
	@Inject
	private BomEnrollmentStatusService service;
	
	@RequestMapping("/produce/BomEnrollmentStatusRetrieve.do")
	public String BomEnrollmentStatusRetrieve() {
		
		String viewName = "produce/statusBOM";
		return viewName;
	}
}
