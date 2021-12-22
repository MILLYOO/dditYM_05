package kr.or.ddit.info2.bommanage.controller;

import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.or.ddit.common.vo.HubSearchVO;
import kr.or.ddit.info2.bommanage.service.BomManageService;
import kr.or.ddit.info2.vo.BomRawsVO;
import kr.or.ddit.info2.vo.BomVO;
import kr.or.ddit.info2.vo.ItemVO;
import lombok.extern.slf4j.Slf4j;

/**
 * BOM 관리
 */
@Controller
@Slf4j
public class BomManageRetrieveController {
	@Inject
	private BomManageService service;
	
	@RequestMapping("/info2/bomManageRetrieve.do")
	public String BomManageRetrieve() {
		String viewName = "info/BOMRegister";
		return viewName;
	}
	
	@PostMapping("/info2/bomCodeRetrieve.do")
	@ResponseBody
	public String BomSetCode(
			@ModelAttribute BomVO bom
			) {
		return service.selectBomCode(bom);
	}
	
	@PostMapping("/info2/bomRetrieve.do")
	@ResponseBody
	public BomVO BomRetrieve(
			@ModelAttribute BomVO bom
			) {
		return service.selectBom(bom);
	}
	
	@PostMapping("/info2/bomDetailRetrieve.do")
	@ResponseBody
	public List<BomRawsVO> Bomdetail(
			@ModelAttribute BomVO bom
			) {
		log.info(""+bom.toString());
		List<BomRawsVO> bomRawsList = service.bomRawsList(bom);
		return bomRawsList;
	}
	
	
}
