package kr.or.ddit.stock.itemenrollmentstatus.controller;

import java.util.List;

import javax.inject.Inject;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.or.ddit.common.vo.HubSearchVO;
import kr.or.ddit.info2.itemmanage.service.ItemManageService;
import kr.or.ddit.info2.vo.ItemVO;
import lombok.extern.slf4j.Slf4j;

/**
 * 품목등록현황
 */
@Controller
@Slf4j
public class ItemEnrollmentStatusRetrieveController {
	@Inject
	private ItemManageService service;
	
	//요청을 받아 페이지로 접속
	@RequestMapping("/stock/itemEnrollmentStatusRetrieve.do")
	public String ItemEnrollmentStatusRetrieve() {
		//넘겨줄 값은 없다.
		
		String viewName = "stock/statusStockRegister";
		return viewName;
	}
	
	@RequestMapping(value="/stock/itemEnrollmentStatusRetrieve.do", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public List<ItemVO> ItemEnrollmentStatusRetrieveForData(
			@ModelAttribute("hubSearch") HubSearchVO hubSearch
			) {
		//ajax 요청을 받아 해당 값으로 로직을 탄다
		//파라미터 hubSearchVO에 세팅
		
		List<ItemVO> items = service.retrieveItemList(hubSearch);
		log.info("items : {}",items);
		//결과값을 반환한다.
		return items;
	}
	
}
