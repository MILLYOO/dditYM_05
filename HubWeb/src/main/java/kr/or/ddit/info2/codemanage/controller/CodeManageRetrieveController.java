package kr.or.ddit.info2.codemanage.controller;

import java.util.List;

import javax.inject.Inject;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.or.ddit.info2.codemanage.service.CodeManageService;
import kr.or.ddit.info2.vo.CommonVO;
import kr.or.ddit.info2.vo.DivisionVO;
import lombok.extern.slf4j.Slf4j;

/**
 * 규격/분류/단위/공정 관리
 * standard/
 */
@Controller
@Slf4j
public class CodeManageRetrieveController {
	
	@Inject
	private CodeManageService service;
	
	// 폼으로 이동
	@RequestMapping("/info2/codeManageRetrieve.do")
	public String CodeManageRetrieve() {
		
		String viewName = "info/codeRegister";
		return viewName;
	}
	
	@GetMapping(value="/info2/common/{common}.do", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public List<CommonVO> codeList(@PathVariable(required=true) CommonVO common) {
		List<CommonVO> standardList = service.retrieveCodeList(common);
		return standardList;
	}
	
	@GetMapping(value="/info2/div/divList.do", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public List<DivisionVO> divList(@ModelAttribute(value="division") DivisionVO division) {
		List<DivisionVO> divList = service.retrieveDivList(division);
		return divList;
	}
	
	@PostMapping(value="/info2/div/divList.do", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public List<DivisionVO> selectDivList(@ModelAttribute(value="division") DivisionVO division) {
		List<DivisionVO> divList = service.retrieveDivList(division);
		return divList;
	}
	
}
