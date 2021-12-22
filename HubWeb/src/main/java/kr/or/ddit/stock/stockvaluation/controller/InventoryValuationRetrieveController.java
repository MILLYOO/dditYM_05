package kr.or.ddit.stock.stockvaluation.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.inject.Inject;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.or.ddit.common.dao.DocCheckDAO;
import kr.or.ddit.common.vo.HubSearchVO;
import kr.or.ddit.stock.stockvaluation.service.InventoryValuationService;
import kr.or.ddit.stock.vo.StoValuVO;
import kr.or.ddit.stock.vo.StovalItemVO;
import lombok.extern.slf4j.Slf4j;

/**
 * 재고평가
 */
@Slf4j
@Controller
public class InventoryValuationRetrieveController {
	@Inject
	private InventoryValuationService service;
	@Resource(name="docCheckDAO")
	private DocCheckDAO docCheckDAO;
	
	//조회 없이 보여주는 첫 화면
	@RequestMapping(value="/stock/inventoryValuationRetrieve.do", method=RequestMethod.GET)
	public String inventoryValuationRetrieve() {
		int cnt = docCheckDAO.updateChkYN("SU");
		String viewName = "stock/stovalu";
		return viewName;
	}
	
	//재고평가 기간 선택 시 평가 완료한 리스트를 보여주는 로직
	//검색조건을 파라미터로 보내어 리스트를 모달창에 구현한다.
	@ResponseBody
	@RequestMapping(value="/stock/inventoryValuationRetrieveList.do", method=RequestMethod.POST, produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public List<StoValuVO> valuationRetrieveList(
			@ModelAttribute("hubSearch") HubSearchVO hubSearchVO
			) {
		List<StoValuVO> valuationList = service.retrieveInventoryValuationList(hubSearchVO);
		
		return valuationList;
	}
	
	//재고평가 리스트 선택 시 보여주는 화면
	//재고평가번호를 파라미터로 받아 조회한다
	@ResponseBody
	@RequestMapping(value="/stock/inventoryValuationItemRetrieve.do", method=RequestMethod.POST, produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public Map<String, Object> valuationItemRetrieve(
			@ModelAttribute("stovalItemVO") StovalItemVO stovalItemVO
			) {
		log.info("컨트롤러를 탓다:{}",stovalItemVO);
		List<StovalItemVO> valuationList = service.inventoryValuationItemList(stovalItemVO);
		log.info("valuationList:{}",valuationList);
		String stvNum = valuationList.get(0).getStvNum();
		Map<String, Object> map = new HashMap<>();
		map.put("stvNum", stvNum);
		map.put("valuationList", valuationList);
		return map;
	}
}

















