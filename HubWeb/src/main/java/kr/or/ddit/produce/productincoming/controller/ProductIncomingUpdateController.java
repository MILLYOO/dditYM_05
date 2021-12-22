package kr.or.ddit.produce.productincoming.controller;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import kr.or.ddit.produce.productincoming.service.ProductIncomingService;

/**
 * 생산품입고처리
 */
@Controller
public class ProductIncomingUpdateController {
	@Inject
	private ProductIncomingService service;
	
	@RequestMapping("/produce/ProductIncomingUpdate.do")
	public String ProductIncomingUpdate() {
		
		String viewName = null;
		return viewName;
	}
}
