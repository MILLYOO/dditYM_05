package kr.or.ddit.info2.itemmanage.controller;

import java.util.List;

import javax.inject.Inject;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.or.ddit.common.vo.HubSearchVO;
import kr.or.ddit.info2.itemmanage.service.ItemManageService;
import kr.or.ddit.info2.vo.ItemVO;
import lombok.extern.slf4j.Slf4j;

/**
 * 품목 관리
 */
@Slf4j
@Controller
public class ItemManageRetrieveController {
	@Inject
	private ItemManageService service;
	
	@RequestMapping(value="/info2/itemManageRetrieve.do", method=RequestMethod.GET)
	public String ItemManageRetrieve() {
		String viewName = "info/itemRegister";
		return viewName;
	}
	
	@ResponseBody
	@RequestMapping(value="/info2/itemManageRetrieve.do", method=RequestMethod.POST, produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public List<ItemVO> itemList(
			@ModelAttribute("hubSearch") HubSearchVO hubSearch
			) {
		List<ItemVO> itemList = service.retrieveItemList(hubSearch);
		return itemList;
	}
}
