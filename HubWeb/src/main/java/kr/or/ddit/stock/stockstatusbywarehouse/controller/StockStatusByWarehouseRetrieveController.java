package kr.or.ddit.stock.stockstatusbywarehouse.controller;

import java.util.List;

import javax.inject.Inject;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.or.ddit.common.vo.HubSearchVO;
import kr.or.ddit.stock.stockstatusbywarehouse.service.StockStatusByWarehouseService;
import kr.or.ddit.stock.vo.WarItemVO;
import lombok.extern.slf4j.Slf4j;

/**
 * 창고별재고현황
 */
@Slf4j
@Controller
public class StockStatusByWarehouseRetrieveController {
	@Inject
	private StockStatusByWarehouseService service;
	
	@RequestMapping("/stock/stockStatusByWarehouseRetrieve.do")
	public String stockStatusByWarehouseRetrieve() {
		
		String viewName = "stock/statusPerWarehouseStock";
		return viewName;
	}
	
	@ResponseBody
	@RequestMapping(value="/stock/stockStatusByWarehouseRetrieve.do", method=RequestMethod.POST, produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public List<WarItemVO> stockStatusByWarehouseRetrieve(
			@ModelAttribute("hubSearch") HubSearchVO hubSearchVO
			) {
		List<WarItemVO> warList = service.warList(hubSearchVO);
		return warList;
	}
	
	//창고 선택시 제품이 보여진다.
	@ResponseBody
	@RequestMapping(value="/stock/stockStatusByWarehouseRetrieveItem.do", method=RequestMethod.POST, produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public List<WarItemVO> stockStatusByWarehouseRetrieveItem(
			@ModelAttribute("hubSearch") HubSearchVO hubSearchVO
			) {
		log.info("hubSearchVO:{}",hubSearchVO);
		List<WarItemVO> warItemList = service.warItemList(hubSearchVO);
		return warItemList;
	}
}
