package kr.or.ddit.info2.itemdeadlinetransfer.controller;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import kr.or.ddit.info2.itemdeadlinetransfer.service.ItemDeadlineTransferService;

/**
 * 품목 마감 후 이월
 */
@Controller
public class ItemDeadlineTransferDeleteController {
	@Inject
	private ItemDeadlineTransferService service;
	
	@RequestMapping("/info2/ItemDeadlineTransferDelete.do")
	public String ItemDeadlineTransferDelete() {
		
		String viewName = null;
		return viewName;
	}
}
