package kr.or.ddit.sales.estimate.controller;

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
import kr.or.ddit.sales.estimate.service.EstimateService;
import kr.or.ddit.sales.vo.EstimateProdVO;
import kr.or.ddit.sales.vo.EstimateVO;

/**
 * 견적서
 */
@Controller
public class EstimateRetrieveController {
	@Inject
	private EstimateService service;
	
	@Resource(name="docCheckDAO")
	private DocCheckDAO docCheckDAO;
	
	@RequestMapping("/sales/EstimateRetrieveView.do")
	public String EstimateRetrieveView(
			) {
		int cnt = docCheckDAO.updateChkYN("ES");
		String viewName = "sales/estimate";
		return viewName;
	}
	
	// 견적서 조회
	@RequestMapping(value="/sales/EstimateRetrieve.do", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public List<EstimateVO> EstimateRetrieve(
			@ModelAttribute("hubSearchVO") HubSearchVO hubSearchVO
			) {
		
		List<EstimateVO> estimateList = service.retrieveEstimateList(hubSearchVO);
		return estimateList;
	}
	
	// 견적서 제품 조회
	@RequestMapping(value="/sales/EstimateProdRetrieve.do", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public List<EstimateProdVO> EstimateProdRetrieve(
			@ModelAttribute("estCode") EstimateVO estCode
			) {
		List<EstimateProdVO> estimateProdList = service.retrieveEstimateProdList(estCode);
		
		return estimateProdList;
	}
}
