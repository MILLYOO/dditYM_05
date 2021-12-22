package kr.or.ddit.sales.salesclose.controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.inject.Inject;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.or.ddit.common.dao.DocCheckDAO;
import kr.or.ddit.common.vo.HubSearchVO;
import kr.or.ddit.sales.salesclose.service.SalesCloseService;
import kr.or.ddit.sales.vo.SalProdVO;
import kr.or.ddit.sales.vo.SalesCloseVO;

/**
 * 매출마감
 */
@Controller
public class SalesCloseRetrieveController {
	@Inject
	private SalesCloseService service;
	
	@Resource(name="docCheckDAO")
	private DocCheckDAO docCheckDAO;
	
	// 초기화면
	@RequestMapping("/sales/SalesCloseRetrieveView.do")
	public String SalesCloseRetrieve() {
		int cnt = docCheckDAO.updateChkYN("SA");	
		String viewName = "sales/salesclose";
		return viewName;
	}
	
	// 매출마감 조회
	@RequestMapping(value="/sales/SalesCloseRetrieve.do", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public List<SalesCloseVO> SalesCloseRetrieve(
			@ModelAttribute("hubSearchVO") HubSearchVO hubSearchVO
			){
		// 매출마감 리스트(검색조건 포함)
		List<SalesCloseVO> salesCloseList = new ArrayList<>();
		salesCloseList = service.retrieveSalesCloseList(hubSearchVO);
		return salesCloseList;
	}
	
	// 매출마감 제품 조회
	@RequestMapping(value="sales/SalesCloseProdRetrieve.do", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public List<SalProdVO> SalesCloseProdRetrieve(
			@ModelAttribute("salcNum") SalesCloseVO slacNum
			){
			List<SalProdVO> salesCloseProdList = service.retrieveSalesCloseProdList(slacNum);
			return salesCloseProdList;
	}
}
