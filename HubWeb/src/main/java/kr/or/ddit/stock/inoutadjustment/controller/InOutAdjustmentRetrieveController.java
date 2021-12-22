package kr.or.ddit.stock.inoutadjustment.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.or.ddit.common.dao.DocCheckDAO;
import kr.or.ddit.common.vo.HubSearchVO;
import kr.or.ddit.stock.inoutadjustment.service.InOutAdjustmentService;
import kr.or.ddit.stock.stocktaking.service.StockTakingService;
import kr.or.ddit.stock.vo.AdjItemVO;
import kr.or.ddit.stock.vo.AdjustmentVO;
import kr.or.ddit.stock.vo.StockTakingVO;
import lombok.extern.slf4j.Slf4j;

/**
 * 입출고조정
 */
@Slf4j
@Controller
public class InOutAdjustmentRetrieveController {
	@Resource(name="inOutAdjustmentServiceImpl")
	private InOutAdjustmentService service;
	@Resource(name="stockTakingServiceImpl")
	private StockTakingService stockTakingService;
	@Resource(name="docCheckDAO")
	private DocCheckDAO docCheckDAO;
	
	@RequestMapping(value="/stock/inOutAdjustmentRetrieve.do", method=RequestMethod.GET)
	public String InOutAdjustmentRetrieve(Model model) {
		//확인하지 않은 재고실사 내역이 있는지 확인한다. 있으면 세션에 값을 넣는다.
		List<StockTakingVO> stockTakingNList = stockTakingService.retrieveStockTaking();
		if(stockTakingNList != null) {
			model.addAttribute("stockTakingNList",stockTakingNList);
		}
		int cnt = docCheckDAO.updateChkYN("AT");
		String viewName = "stock/adjustment";
		return viewName;
	}

	@ResponseBody
	@RequestMapping(value="/stock/inOutAdjustmentRetrieve.do", method=RequestMethod.POST
					, produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public List<AdjustmentVO> inOutAdjustmentList(
			@ModelAttribute("hubSearch") HubSearchVO hubSearch
			) {
		List<AdjustmentVO> inOutList = service.retrieveInOutAdjustmentList(hubSearch);
		return inOutList;
	}
	
	
	@ResponseBody
	@RequestMapping(value="/stock/inOutItemList.do", method=RequestMethod.POST, produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public List<AdjItemVO> inOutItemList(
			@RequestParam("adjNum") String adjNum
			) {
		AdjItemVO adjItemVO = new AdjItemVO();
		adjItemVO.setAdjNum(adjNum);
		List<AdjItemVO> inOutItemList = service.inOutAdjustmentItemList(adjItemVO);
//		for(AdjItemVO adjItemVO1 : inOutItemList) {
//			//VO에 adjSort의 값이 입고이면 -> gcommName : gcommInname / ucommName : ucommInname
//			if(adjItemVO1.getAdjSort().equals("입고")) {
//				adjItemVO1.setGcommName(adjItemVO.getGcommInname());
//				adjItemVO1.setUcommName(adjItemVO.getUcommInname());
//			}else {
//				//VO에 adjSort의 값이 출고이면 -> gcommName : gcommOutname / ucommName : ucommOutname
//				adjItemVO1.setGcommName(adjItemVO.getGcommOutname());
//				adjItemVO1.setUcommName(adjItemVO.getUcommOutname());
//			}
//		}
		return inOutItemList;
	}
}
