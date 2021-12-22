package kr.or.ddit.produce.merterialrelease.controller;

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
import kr.or.ddit.produce.merterialrelease.service.MaterialReleaseService;
import kr.or.ddit.produce.vo.MreleaseRawsVO;
import kr.or.ddit.produce.vo.MreleaseVO;

/**
 * 자재출고처리 목록 조회 - 품목 목록 조회
 */
@Controller
public class MaterialReleaseRetrieveController {
	@Inject
	private MaterialReleaseService service;
	
	@Resource(name="docCheckDAO")
	private DocCheckDAO docCheckDAO;
	
	@RequestMapping(value="/produce/materialReleaseList.do",method=RequestMethod.GET)
	public String materialReleaseListGet() {
		int cnt = docCheckDAO.updateChkYN("ME");
		return "produce/mrelease";
	}
	
	
	// 자재출고처리 목록 (검색)
	@ResponseBody
	@RequestMapping(value="/produce/materialReleaseList.do",produces=MediaType.APPLICATION_JSON_UTF8_VALUE,method=RequestMethod.POST)
	public List<MreleaseVO> materialReleaseList(
			@ModelAttribute("hubSearch") HubSearchVO hubSearch
			) {
		return service.retrieveMaterialReleaseList(hubSearch);
	}
	
	// 자재출고처리-원자재 목록
	@ResponseBody
	@RequestMapping(value="/produce/materialReleaseRawsList.do",produces=MediaType.APPLICATION_JSON_UTF8_VALUE,method=RequestMethod.POST)
	public List<MreleaseRawsVO> instRawsList(
			@RequestParam("mreNum") String mreNum
			) {
		MreleaseRawsVO mreleaseRaws = new MreleaseRawsVO();
		mreleaseRaws.setMreNum(mreNum);
		List<MreleaseRawsVO> marRawsList = service.retrieveMaterialReleaseRawsList(mreleaseRaws);
		return marRawsList;
	}
	
}
