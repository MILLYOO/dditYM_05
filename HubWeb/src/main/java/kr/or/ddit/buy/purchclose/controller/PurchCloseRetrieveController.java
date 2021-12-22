package kr.or.ddit.buy.purchclose.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.inject.Inject;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.or.ddit.buy.purchclose.service.PurchCloseService;
import kr.or.ddit.buy.vo.CloseRawsVO;
import kr.or.ddit.buy.vo.PurchCloseVO;
import kr.or.ddit.common.dao.DocCheckDAO;
import kr.or.ddit.common.vo.HubSearchVO;

/**
 * 매입마감
 */
@Controller
public class PurchCloseRetrieveController {
	
	@Inject
	private PurchCloseService service;
	
	@Resource(name="docCheckDAO")
	private DocCheckDAO docCheckDAO;
	
	@RequestMapping(value="/buy/purchCloseList.do")
	public String PurchCloseRetrieve() {
		int cnt = docCheckDAO.updateChkYN("PC");
		String viewName = "buy/purchclose";
		return viewName;
	}
	
	@RequestMapping(value="/buy/purchCloseList.do", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public List<PurchCloseVO> pClose(
			@ModelAttribute("hubSearchVO") HubSearchVO hubSearchVO
			){
		List<PurchCloseVO> pClose = service.retrievePurchCloseList(hubSearchVO);
		return pClose;
	}
	
	@RequestMapping(value="/buy/closeRawsList.do", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public List<CloseRawsVO> pCloseRawsList(
			@RequestParam("purNum") String purNum
			){
		PurchCloseVO purchCloseVO = new PurchCloseVO();
		purchCloseVO.setPurNum(purNum);
		List<CloseRawsVO> closeRawsList = service.retrievePurchCloseRawsList(purchCloseVO);
		return closeRawsList;
	}
}
