package kr.or.ddit.sales.estimate.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import kr.or.ddit.common.vo.HubSearchVO;
import kr.or.ddit.info1.buyermanage.service.BuyerManageService;
import kr.or.ddit.info1.vo.BuyerVO;
import kr.or.ddit.sales.estimate.service.EstimateService;
import kr.or.ddit.sales.vo.EstimateProdVO;
import kr.or.ddit.sales.vo.EstimateVO;
import lombok.extern.slf4j.Slf4j;

/**
 * EstimatePdfController.java
 * By 이원균_211202(최종수정)
 * 1.요청받은 데이터를 바탕으로 PDF에 삽입하기 위한 데이터를 DB에서 가져옵니다.
 * 2.가져오는 데이터는 견적서 작서에 필요한 데이터로, 거래처 및 품목 정보입니다.
 * 3.데이터를 model에 삽입 후 BeanNameViewResolver를 이용, pdfDownloadView 로 이동합니다. 
 */

@Slf4j
@Controller
public class EstimatePdfController {

	@Resource(name="estimateServiceImpl")
	private EstimateService estimateService;
	@Resource(name="buyerManageServiceImpl")
	private BuyerManageService buyerManageService;
	
	@RequestMapping("/sales/pdfDownload.do")
	public String pdfDownload(
			@ModelAttribute("estimateVO") EstimateVO estimateVO, Model model) {
		//estimateVO에서 견적서 코드를 이용 품목 리스트 가져오기
		List<EstimateProdVO> estimateProdList = estimateService.retrieveEstimateProdList(estimateVO);
		//거래처 코드를 이용하여 거래처 정보 가져오기
		HubSearchVO hubSearchVO = new HubSearchVO();
		hubSearchVO.setBuyerName(estimateVO.getBuyerName());
		List<BuyerVO> buyerVO = buyerManageService.retrieveBuyerList(hubSearchVO);
		//model에 두 객체 담아 보내기
		model.addAttribute("estimateVO", estimateVO);
		model.addAttribute("estimateProdList", estimateProdList);
		model.addAttribute("buyerVO",buyerVO);
		
		return "pdfDownloadView";
	}
}








