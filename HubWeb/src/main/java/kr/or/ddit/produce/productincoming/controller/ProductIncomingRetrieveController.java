package kr.or.ddit.produce.productincoming.controller;

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
import kr.or.ddit.produce.productincoming.service.ProductIncomingService;
import kr.or.ddit.produce.vo.ProdWareVO;
import kr.or.ddit.produce.vo.PrwaProdVO;

/**
 * 생산품입고처리 목록 조회 , 품목 목록 조회
 */
@Controller
public class ProductIncomingRetrieveController {
	@Inject
	private ProductIncomingService service;
	
	@Resource(name="docCheckDAO")
	private DocCheckDAO docCheckDAO;
	
	@RequestMapping(value="/produce/productIncomingList.do",method=RequestMethod.GET)
	public String productIncomingRetrieve() {
		int cnt = docCheckDAO.updateChkYN("PR");
		return "produce/itemware";
	}
	
	
	// 생산품입고처리서 목록(검색)
	@ResponseBody
	@RequestMapping(value="/produce/productIncomingList.do",produces=MediaType.APPLICATION_JSON_UTF8_VALUE,method=RequestMethod.POST)
	public List<ProdWareVO> productIncomingList(
			@ModelAttribute("hubSearch") HubSearchVO hubSearch
			){
		List<ProdWareVO> prodWareList = service.retrieveProductIncomingList(hubSearch);
		return prodWareList;
	}
	
	// 생산품입고처리-품목 목록
	@ResponseBody
	@RequestMapping(value="/produce/productIncomingRawsList.do",produces=MediaType.APPLICATION_JSON_UTF8_VALUE,method=RequestMethod.POST)
	public List<PrwaProdVO> productIncomingRawsList(
			@RequestParam("arrNum") String arrNum
			){
		ProdWareVO prodWareVO = new ProdWareVO();
		prodWareVO.setArrNum(arrNum);
		List<PrwaProdVO> prodWareRawsList = service.retrieveProductIncomingProductList(prodWareVO);
		return prodWareRawsList;
	}
}
