package kr.or.ddit.buy.purchclose.controller;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


/**
 * 매입마감
 */
@Controller
public class PurchCloseUpdateController {
	
	@RequestMapping("/buy/purchCloseUpdate")
	public String PurchCloseUpdate() {
		
		String viewName = null;
		return viewName;
	}
}
