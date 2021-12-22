package kr.or.ddit.sales.profitmarginstatus.controller;
/**
 * 매출이익현황
 */

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.or.ddit.common.vo.HubSearchVO;
import kr.or.ddit.sales.profitmarginstatus.service.ProfitMarginStatusService;
import kr.or.ddit.sales.vo.ProfitVO;

@Controller
public class ProfitMarginStatusRetrieveController {
	
	@Inject
	private ProfitMarginStatusService service;

	@RequestMapping(value="/sales/profitMarginStatusRetrieve.do", method=RequestMethod.GET)
	public String ProfitMarginStatusListView() {
		String viewName = "sales/profitMarginStatus";
		return viewName;
	}
	
	// 거래처
	@RequestMapping(value="/sales/profitMarginStatusListPC.do", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public List<ProfitVO> ProfitMarginStatusListPC(
			@ModelAttribute("hubSearchVO")HubSearchVO hubSearchVO
			){
			// 매출이익현황 조회(검색 포함)
			List<ProfitVO>	profitList = new ArrayList<>();
			profitList = service.retrieveProfitListPC(hubSearchVO);
			return profitList;
			
	}
	// 부서
	@RequestMapping(value="/sales/profitMarginStatusListDP.do", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public List<ProfitVO> ProfitMarginStatusListDP(
			@ModelAttribute("hubSearchVO")HubSearchVO hubSearchVO
			){
		// 매출이익현황 조회(검색 포함)
		List<ProfitVO>	profitList = new ArrayList<>();
		profitList = service.retrieveProfitListDP(hubSearchVO);
		return profitList;
		
	}
	// 사원
	@RequestMapping(value="/sales/profitMarginStatusListEMP.do", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public List<ProfitVO> ProfitMarginStatusListEMP(
			@ModelAttribute("hubSearchVO")HubSearchVO hubSearchVO
			){
		// 매출이익현황 조회(검색 포함)
		List<ProfitVO>	profitList = new ArrayList<>();
		profitList = service.retrieveProfitListEMP(hubSearchVO);
		return profitList;
		
	}
	
	
	
	
	
	
}
