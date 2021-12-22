package kr.or.ddit.stock.stockmove.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.inject.Inject;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.or.ddit.common.dao.DocCheckDAO;
import kr.or.ddit.common.vo.HubSearchVO;
import kr.or.ddit.stock.stockmove.service.StockMoveService;
import kr.or.ddit.stock.vo.StockMoveVO;
import kr.or.ddit.stock.vo.StomovItemVO;

/**
 * 재고이동
 */
@Controller
public class StockMoveRetrieveController {
	@Inject
	private StockMoveService service;
	@Resource(name="docCheckDAO")
	private DocCheckDAO docCheckDAO;
	
	@RequestMapping(value="/stock/stockMoveRetrieve.do", method=RequestMethod.GET)
	public String StockMoveRetrieve() {
		int cnt = docCheckDAO.updateChkYN("SE");
		String viewName = "stock/stockmove";
		return viewName;
	}
	
	@ResponseBody
	@RequestMapping(value="/stock/stockMoveRetrieve.do", method=RequestMethod.POST, produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public List<StockMoveVO> stockMoveRetrieveList(
			@ModelAttribute("hubSearch") HubSearchVO hubSearch
			){
		List<StockMoveVO> stockMoveList = service.retrieveStockMoveList(hubSearch);
		return stockMoveList;
	}
	
	@ResponseBody
	@RequestMapping(value="/stock/stockMoveRetrieveItem.do",  method=RequestMethod.POST, produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public List<StomovItemVO> stockMoveRetrieveItemList(
			@RequestParam("stmNum") String stmNum
			) {
		StomovItemVO stomovItemVO = new StomovItemVO();
		stomovItemVO.setStmNum(stmNum);
		List<StomovItemVO> inOutItemList = service.retrieveStockMoveItemList(stomovItemVO);
		
		return inOutItemList;
	}
}














