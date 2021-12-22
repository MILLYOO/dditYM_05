package kr.or.ddit.sales.salesleaderboard.controller;
/**
 * 매출순위표
 */

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.or.ddit.common.vo.HubSearchVO;
import kr.or.ddit.sales.salesleaderboard.service.SalesLeaderboardService;
import kr.or.ddit.sales.vo.SalesRankVO;

@Controller
public class SalesLeaderboardRetrieveController {

	@Inject
	private SalesLeaderboardService service;
	
	@RequestMapping("/sales/salesRankListView.do")
	public String SalesLeaderboardListView() {
		String viewName = "sales/rankSales";
		return viewName;
	}
	// 거래처
	@RequestMapping(value="/sales/salesRankListPC.do", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public List<SalesRankVO> rankListPC(
			@ModelAttribute("hubSearchVO") HubSearchVO hubSearchVO
			){
		// 매출마감서 리스트 조회(검색조건 포함)
		List<SalesRankVO> rankList = new ArrayList<>();
		rankList = service.retrieveSalesLeaderboardListPC(hubSearchVO);
		return rankList;
	}
	// 부서
	@RequestMapping(value="/sales/salesRankListDP.do", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public List<SalesRankVO> rankListDP(
			@ModelAttribute("hubSearchVO") HubSearchVO hubSearchVO
			){
		// 매출마감서 리스트 조회(검색조건 포함)
		List<SalesRankVO> rankList = new ArrayList<>();
		rankList = service.retrieveSalesLeaderboardListDP(hubSearchVO);
		return rankList;
	}
	
	// 사원
	@RequestMapping(value="/sales/salesRankListEMP.do", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public List<SalesRankVO> rankListEMP(
			@ModelAttribute("hubSearchVO") HubSearchVO hubSearchVO
			){
		// 매출마감서 리스트 조회(검색조건 포함)
		List<SalesRankVO> rankList = new ArrayList<>();
		rankList = service.retrieveSalesLeaderboardListEMP(hubSearchVO);
		return rankList;
	}
	
	// 매출마감 순위 하위 조회
	@RequestMapping(value="sales/salesRankListProd.do", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public List<SalesRankVO> rankListProd(
			@ModelAttribute("buyerName") SalesRankVO salesRankVO
			){
			List<SalesRankVO> rankListProd = service.retrieveSalesLeaderboardProdList(salesRankVO);
			return rankListProd;
	}
}
