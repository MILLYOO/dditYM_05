package kr.or.ddit.produce.productincoming.controller;

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
import kr.or.ddit.produce.productincoming.service.ProductIncomingService;
import kr.or.ddit.produce.vo.InstrucprodVO;

/**
 * 생산품입고처리 - 생산지시서적용
 */
@Controller
public class PICProductionInstructionApplyController {
	@Inject
	private ProductIncomingService service;
	
	
	// 지시서 적용 masterGrid 목록
	@ResponseBody
	@RequestMapping(value="/produce/pwProductionInstructionApplyList.do",method=RequestMethod.POST,produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public List<InstrucprodVO> mrInstApplyList(@ModelAttribute("hubSearch") HubSearchVO hubSearch) {
		List<InstrucprodVO> piListforPW = service.retrievePiApplyList(hubSearch);
		return piListforPW;
	}
	
	@ResponseBody
	@RequestMapping(value="/produce/picProductionInstructionApply.do",method=RequestMethod.POST,produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public String incomingStatusByOrderRetrieve(
			@RequestBody List<InstrucprodVO> instProdList
			) {
		String resultMessage = null;
		ServiceResult result = service.createPiApplyProdIncoming(instProdList);
		switch (result) {
			case OK:
				resultMessage = "성공";
				break;
			case PKDUPLICATED:
				resultMessage = "존재하지 않는 생산품입고처리서입니다.";
				break;
			default:
				resultMessage = "서버오류";
				break;
		}
		return resultMessage;
	}
}
