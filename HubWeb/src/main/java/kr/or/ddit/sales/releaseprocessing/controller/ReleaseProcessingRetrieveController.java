package kr.or.ddit.sales.releaseprocessing.controller;

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
import kr.or.ddit.sales.releaseprocessing.service.ReleaseProcessingService;
import kr.or.ddit.sales.vo.RelProdVO;
import kr.or.ddit.sales.vo.ReleaseVO;

/**
 * 출고처리
 */
@Controller
public class ReleaseProcessingRetrieveController {
	@Inject
	private ReleaseProcessingService service;
	
	@Resource(name="docCheckDAO")
	private DocCheckDAO docCheckDAO;
	
	// 초기화면
	@RequestMapping("/sales/ReleaseProcessingRetrieveView.do")
	public String ReleaseProcessingRetrieveView() {
		int cnt = docCheckDAO.updateChkYN("RA");
		String viewName = "sales/release";
		return viewName;
	}
	
	// 출고처리서 조회
	@RequestMapping(value="/sales/ReleaseProcessingRetrieve.do", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public List<ReleaseVO> ReleaseProcessingRetrieve(
			@ModelAttribute("hubSearchVO") HubSearchVO hubSearchVO
			){
			//출고처리서 리스트(검색조건 포함)
			List<ReleaseVO> releaseList = null;
			releaseList = service.retrieveReleaseProcessingList(hubSearchVO);
			return releaseList;
	}
	
	// 출고처리서 제품 조회
	@RequestMapping(value="sales/ReleaseProcessingProdRetrieve.do", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public List<RelProdVO> ReleaseProcessingProdRetrieve(
			@ModelAttribute("relCode") ReleaseVO relCode
			){
			List<RelProdVO> releaseProdList = service.releaseProcessing(relCode);
			return releaseProdList;
	}
	
}
