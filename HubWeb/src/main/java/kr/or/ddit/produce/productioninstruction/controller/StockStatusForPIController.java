package kr.or.ddit.produce.productioninstruction.controller;

import java.util.List;

import javax.inject.Inject;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.or.ddit.common.vo.HubSearchVO;
import kr.or.ddit.info2.vo.ItemVO;
import kr.or.ddit.produce.productioninstruction.service.ProductionInstructionService;

/**
 * 생산지시서 - 현재고
 */
@Controller
public class StockStatusForPIController {
	
	@Inject
	private ProductionInstructionService service;
	

	@ResponseBody
	@RequestMapping(value="/produce/selectStockStatusListForPi.do",produces=MediaType.APPLICATION_JSON_UTF8_VALUE,method=RequestMethod.POST)
	public List<ItemVO> selectStockStatusListForPI(
			@ModelAttribute("hubSearch") HubSearchVO hubSearch
			) {
		List<ItemVO> stockStatusList = service.retrieveStockStatus(hubSearch);
		return stockStatusList;
	}
	
	
}
