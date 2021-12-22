package kr.or.ddit.stock.stockoverallstatus.controller;

import java.util.List;

import javax.inject.Inject;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.or.ddit.common.vo.HubSearchVO;
import kr.or.ddit.info2.itemmanage.service.ItemManageService;
import kr.or.ddit.info2.vo.ItemVO;

/**
 * 현재고총괄현황
 */
@Controller
public class StockOverallStatusRetrieveController {
	@Inject
	private ItemManageService service;
	
	@RequestMapping("/stock/stockOverallStatusRetrieve.do")
	public String StockOverallStatusRetrieve() {
		
		String viewName = "stock/statusCurrentTotalStock";
		return viewName;
	}
	
	@RequestMapping(value="/stock/stockOverallStatusRetrieve.do", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public List<ItemVO> StockOverallStatusRetrieveForData(
			@ModelAttribute("hubSearch") HubSearchVO hubSearch
			){
		List<ItemVO> items = service.retrieveStockAllStatus(hubSearch);
		return items;
	}
	
}
