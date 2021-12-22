package kr.or.ddit.buy.purchaseleaderboard.controller;

import java.util.List;

import javax.inject.Inject;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.or.ddit.buy.purchaseleaderboard.service.PurchaseLeaderboardService;
import kr.or.ddit.buy.vo.CloseRawsVO;
import kr.or.ddit.buy.vo.PurchCloseVO;
import kr.or.ddit.common.vo.HubSearchVO;

/**
 * 매입순위표
 */
@Controller
public class PurchaseLeaderboardRetrieveController {
	
	@Inject
	private PurchaseLeaderboardService service;
	
	@RequestMapping("/buy/purchRankList.do")
	public String PurchaseLeaderboardListGet() {
		String viewName = "buy/rankPurch";
		return viewName;
	}

	//거래처별 매입마감서 master
	@ResponseBody
	@RequestMapping(value="/buy/purchRankListPC.do", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public List<PurchCloseVO> rankListPC(
			@ModelAttribute("hubSearchVO") HubSearchVO hubSearch
			){
		List<PurchCloseVO> rankList = service.retrievePurchaseLeaderboardListPC(hubSearch);
		return rankList;
	}
	
	//부서별 매입마감서 master
	@ResponseBody
	@RequestMapping(value="/buy/purchRankListDP.do", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public List<PurchCloseVO> rankListDP(
			@ModelAttribute("hubSearchVO") HubSearchVO hubSearch
			){
		List<PurchCloseVO> rankList = service.retrievePurchaseLeaderboardListDP(hubSearch);
		return rankList;
	}
	
	//사원별 매입마감서 master
	@ResponseBody
	@RequestMapping(value="/buy/purchRankListEMP.do", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public List<PurchCloseVO> rankListEMP(
			@ModelAttribute("hubSearchVO") HubSearchVO hubSearch
			){
		List<PurchCloseVO> rankList = service.retrievePurchaseLeaderboardListEMP(hubSearch);
		return rankList;
	}
	
//==========================================================================================================================================================
	
	//매입마감서 원자재 거래처별 순위
	@ResponseBody
	@RequestMapping(value="/buy/purchRankRawsListPC.do")
	public List<CloseRawsVO> closeRawsListPC(
			@ModelAttribute("purchCloseVO") PurchCloseVO purchCloseVO
			){
		List<CloseRawsVO> closeRawsList = service.retrievePurchaseLeaderboardRawListPC(purchCloseVO);
		
		return closeRawsList;
	}
	
	//매입마감서 원자재 부서별 순위
	@ResponseBody
	@RequestMapping(value="/buy/purchRankRawsListDP.do")
	public List<CloseRawsVO> closeRawsListDP(
			@ModelAttribute("purchCloseVO") PurchCloseVO purchCloseVO
			){
		List<CloseRawsVO> closeRawsList = service.retrievePurchaseLeaderboardRawListDP(purchCloseVO);
		
		return closeRawsList;
	}
	
	//매입마감서 원자재 사원별 순위
	@ResponseBody
	@RequestMapping(value="/buy/purchRankRawsListEMP.do")
	public List<CloseRawsVO> closeRawsListEMP(
			@ModelAttribute("purchCloseVO") PurchCloseVO purchCloseVO
			){
		List<CloseRawsVO> closeRawsList = service.retrievePurchaseLeaderboardRawListEMP(purchCloseVO);
		
		return closeRawsList;
	}
}
