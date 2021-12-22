package kr.or.ddit.buy.incoming.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.inject.Inject;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.or.ddit.buy.incoming.service.IncomingService;
import kr.or.ddit.buy.vo.IncomingRawsVO;
import kr.or.ddit.buy.vo.IncomingVO;
import kr.or.ddit.common.dao.DocCheckDAO;
import kr.or.ddit.common.vo.HubSearchVO;

/**
 * 입고처리서
 */
@Controller
public class IncomingRetrieveController {
	
	@Inject
	private IncomingService service;
	
	@Resource(name="docCheckDAO")
	private DocCheckDAO docCheckDAO;
	
	@RequestMapping(value="/buy/incomingList.do")
	public String IncomingListGet(){
		String viewName = "buy/incoming";
		int cnt = docCheckDAO.updateChkYN("IC");
		return viewName;
	}
	
	@ResponseBody
	@RequestMapping(value="/buy/incomingList.do", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public List<IncomingVO> incomingList(
			 @ModelAttribute("hubSearchVO") HubSearchVO hubSearchVO
			){
		List<IncomingVO> incomingList = service.retrieveIncomingList(hubSearchVO);
		return incomingList;
	}
	
	@RequestMapping(value="/buy/incomingRawsList.do", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public List<IncomingRawsVO> incomingRawsList(
			@RequestParam("incNum") String incNum
			){
		IncomingVO incomingVO = new IncomingVO();
		incomingVO.setIncNum(incNum);
		List<IncomingRawsVO> incomingRawsList = service.retrieveincomingRawsList(incomingVO);
		return incomingRawsList;
	}
	
	
	
}
