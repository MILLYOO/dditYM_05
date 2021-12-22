package kr.or.ddit.stock.defectiveitemstockstatus.controller;

import java.util.List;

import javax.inject.Inject;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.or.ddit.common.vo.HubSearchVO;
import kr.or.ddit.stock.defectiveitemstockstatus.service.DefectiveItemStockStatusService;
import kr.or.ddit.stock.vo.DefectiveStockVO;

/**
 * 불량품재고현황
 */
@Controller
public class DefectiveItemStockStatusRetrieveController {
	@Inject
	private DefectiveItemStockStatusService service;
	
	@RequestMapping("/stock/defectiveItemStockStatusRetrieve.do")
	public String defectiveItemStockStatusRetrieve() {
		
		String viewName = "stock/statusPoorStock";
		return viewName;
	}
	
	@RequestMapping(value="/stock/defectiveItemStockStatusRetrieve.do", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public List<DefectiveStockVO> defectiveItemStockStatusRetrieve(
			@ModelAttribute("hubSearch") HubSearchVO hubSearchVO
			){
		List<DefectiveStockVO> defectiveStockList = service.selectDefectiveStock(hubSearchVO);	
		return defectiveStockList;
	}
}
