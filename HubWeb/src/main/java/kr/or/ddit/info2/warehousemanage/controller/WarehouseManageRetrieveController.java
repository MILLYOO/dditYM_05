package kr.or.ddit.info2.warehousemanage.controller;

import java.util.List;

import javax.inject.Inject;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.or.ddit.common.vo.HubSearchVO;
import kr.or.ddit.info2.vo.WarehouseVO;
import kr.or.ddit.info2.warehousemanage.service.WarehouseManageService;

/**
 * 창고 관리
 */
@Controller
public class WarehouseManageRetrieveController {
	@Inject
	private WarehouseManageService service;
	
	@RequestMapping(value="/info2/warehouseRetrieve.do", method=RequestMethod.GET)
	public String WarehouseManageRetrieve() {
		
		String viewName = "info/warehouseRegister";
		return viewName;
	}
	
	@ResponseBody
	@RequestMapping(value="/info2/warehouseRetrieve.do", method=RequestMethod.POST, produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public List<WarehouseVO> warList(
			@ModelAttribute("hubSearch") HubSearchVO hubSearch
			) {
		List<WarehouseVO> warList = service.retrieveWarehouseList(hubSearch);
		return warList;
	}
}


