package kr.or.ddit.sales.releaseorder.controller;

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
import kr.or.ddit.sales.releaseorder.service.ReleaseOrderService;
import kr.or.ddit.sales.vo.ReleaseOrderProdVO;
import kr.or.ddit.sales.vo.ReleaseOrderVO;

/**
 * 출고지시서
 */
@Controller
public class ReleaseOrderRetrieveController {
	@Inject
	private ReleaseOrderService service;
	
	@Resource(name="docCheckDAO")
	private DocCheckDAO docCheckDAO;
	
	//초기화면
	@RequestMapping("/sales/ReleaseOrderRetrieveView.do")
	public String ReleaseOrderRetrieve() {
		int cnt = docCheckDAO.updateChkYN("RE");
		String viewName = "sales/relord";
		return viewName;
	}
	
		// 출고지시서 데이터 보내기
		@RequestMapping(value="/sales/ReleaseOrderRetrieve.do", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
		@ResponseBody
		public List<ReleaseOrderVO> ReleaseOrderRetrieve(
				@ModelAttribute("hubSearchVO") HubSearchVO hubSearchVO
				) {
			// 출고지시서 리스트(검색조건 포함)
			List<ReleaseOrderVO> releaseOrderList = null;
			releaseOrderList = service.retrieveReleaseOrderList(hubSearchVO);
			return releaseOrderList;
		}
	
		// 출고지시서 제품 데이터 보내기
		@RequestMapping(value="/sales/ReleaseOrderProdRetrieve.do", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
		@ResponseBody
		public List<ReleaseOrderProdVO> ReleaseOrderProdRetrieve(
				@ModelAttribute("reoCode") ReleaseOrderVO reoCode
				) {
			List<ReleaseOrderProdVO> releaseOrderProdList = service.ReleaseOrderList(reoCode);
			return releaseOrderProdList;
		}
}
