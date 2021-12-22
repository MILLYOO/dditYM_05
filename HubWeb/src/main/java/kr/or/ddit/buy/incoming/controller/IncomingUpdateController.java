package kr.or.ddit.buy.incoming.controller;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import kr.or.ddit.buy.incoming.service.IncomingService;

/**
 * 입고처리서
 */
@Controller
public class IncomingUpdateController {
	@Inject
	private IncomingService service;
	
	@RequestMapping("/buy/incomingUpdate.do")
	public String IncomingUpdate() {
		
		String viewName = null;
		return viewName;
	}
	
}
