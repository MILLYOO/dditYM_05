package kr.or.ddit.produce.productioninstructionstatus.controller;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import kr.or.ddit.produce.productioninstructionstatus.service.ProductionInstructionStatusService;

/**
 * 생산지시현황
 */
@Controller
public class ProductionInstructionStatusRetrieveController {
	@Inject
	private ProductionInstructionStatusService service;
	
	@RequestMapping("/produce/ProductionInstructionStatusRetrieve.do")
	public String ProductionInstructionStatusRetrieve() {
		
		String viewName = "produce/statusInstrucprod";
		return viewName;
	}
}
