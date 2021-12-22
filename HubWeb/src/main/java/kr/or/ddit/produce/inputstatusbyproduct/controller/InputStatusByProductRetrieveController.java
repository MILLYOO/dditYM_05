package kr.or.ddit.produce.inputstatusbyproduct.controller;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import kr.or.ddit.produce.inputstatusbyproduct.service.InputStatusByProductService;

/**
 * 제품별자재투입현황
 */
@Controller
public class InputStatusByProductRetrieveController {
	@Inject
	private InputStatusByProductService service;
	
	@RequestMapping("/produce/InputStatusByProductRetrieve.do")
	public String InputStatusByProductRetrieve() {
		
		String viewName = "produce/statusPerProdIncomStock";
		return viewName;
	}
}
