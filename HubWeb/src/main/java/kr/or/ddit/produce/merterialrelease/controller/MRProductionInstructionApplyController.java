package kr.or.ddit.produce.merterialrelease.controller;

import java.util.List;

import javax.inject.Inject;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.or.ddit.common.vo.HubSearchVO;
import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.produce.merterialrelease.service.MaterialReleaseService;
import kr.or.ddit.produce.vo.InstrucprodVO;

/**
 * 자재출고처리-생산지시서적용
 */
@Controller
public class MRProductionInstructionApplyController {
	@Inject
	private MaterialReleaseService service;
	
	// 지시서 적용 masterGrid 목록
	@ResponseBody
	@RequestMapping(value="/produce/mrProductionInstructionAppList.do",method=RequestMethod.POST,produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public List<InstrucprodVO> mrInstApplyList(@ModelAttribute("hubSearch") HubSearchVO hubSearch) {
		List<InstrucprodVO> piListforMR = service.retrieveInstAppList(hubSearch);
		return piListforMR;
	}
	
	@ResponseBody
	@RequestMapping(value="/produce/mrProductionInstructionApply.do",produces=MediaType.APPLICATION_JSON_UTF8_VALUE,method=RequestMethod.POST)
	public String MRProductionInstructionApply(
			@RequestBody List<InstrucprodVO> instProdVO
			) {
		String resultMessage = null;
		ServiceResult result = service.createPiApplyMrelease(instProdVO);
		switch (result) {
			case OK:
				resultMessage = "성공";
				break;
			default:
				resultMessage = "실패";
				break;
		}
		return resultMessage;
	}
}
