package kr.or.ddit.produce.productincomingstatus.controller;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import kr.or.ddit.produce.productincomingstatus.service.ProductIncomingStatusService;

/**
 * 생산입고현황
 */
@Controller
public class ProductIncomingStatusRetrieveController {
	@Inject
	private ProductIncomingStatusService service;
	
	@RequestMapping("/produce/ProductIncomingStatusRetrieve.do")
	public String ProductIncomingStatusRetrieve() {
		
		String viewName = "produce/statusProdware";
		return viewName;
	}
}
