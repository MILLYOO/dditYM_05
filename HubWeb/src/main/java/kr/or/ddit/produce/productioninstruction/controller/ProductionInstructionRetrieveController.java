package kr.or.ddit.produce.productioninstruction.controller;

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
import kr.or.ddit.produce.productioninstruction.service.ProductionInstructionService;
import kr.or.ddit.produce.vo.InstRawsVO;
import kr.or.ddit.produce.vo.InstrucprodVO;

/**
 * 생산지시서 목록 조회 - 품목 목록 조회
 */
@Controller
public class ProductionInstructionRetrieveController {
	
	@Inject
	private ProductionInstructionService service;
	
	@Resource(name="docCheckDAO")
	private DocCheckDAO docCheckDAO;
	
	@RequestMapping(value="/produce/productionInstructionList.do",method=RequestMethod.GET)
	public String productionInstructionListGet() {
		int cnt = docCheckDAO.updateChkYN("ID");
		String viewName = "produce/instrucItem";
		return viewName;
	}
	
	@ResponseBody
	@RequestMapping(value="/produce/productionInstructionList.do",produces=MediaType.APPLICATION_JSON_UTF8_VALUE,method=RequestMethod.POST)
	public List<InstrucprodVO> productionInstructionList(
			@ModelAttribute("hubSearch") HubSearchVO hubSearch
			) {
		List<InstrucprodVO> productionInstList = service.retrieveProductionInstructionList(hubSearch);
		return productionInstList;
	}
	
	
	@ResponseBody
	@RequestMapping(value="/produce/instRawsList.do",produces=MediaType.APPLICATION_JSON_UTF8_VALUE,method=RequestMethod.POST)
	public List<InstRawsVO> instRawsList(
			@RequestParam("instNum") String instNum
			) {
		InstrucprodVO instrucProdVO = new InstrucprodVO();
		instrucProdVO.setInstNum(instNum);
		List<InstRawsVO> instRawsList = service.retrieveInstRawsList(instrucProdVO);
		
		return instRawsList;
	}
}
