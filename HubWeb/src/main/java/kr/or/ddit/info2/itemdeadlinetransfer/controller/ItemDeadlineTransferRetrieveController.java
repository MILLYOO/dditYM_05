package kr.or.ddit.info2.itemdeadlinetransfer.controller;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import kr.or.ddit.info2.itemdeadlinetransfer.service.ItemDeadlineTransferService;

/**
 * 품목 마감 후 이월
 */
@Controller
public class ItemDeadlineTransferRetrieveController {
	@Inject
	private ItemDeadlineTransferService service;
	
	@RequestMapping("/info2/ItemDeadlineTransferRetrieve.do")
	public String ItemDeadlineTransferRetrieve() {
		
		String viewName = "info/prodCloseForward";
		return viewName;
	}
}
