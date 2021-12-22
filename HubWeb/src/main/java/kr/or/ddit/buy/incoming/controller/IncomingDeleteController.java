package kr.or.ddit.buy.incoming.controller;

import java.util.List;

import javax.inject.Inject;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.or.ddit.buy.incoming.service.IncomingService;
import kr.or.ddit.buy.vo.IncomingRawsVO;
import kr.or.ddit.buy.vo.IncomingVO;
import kr.or.ddit.enumpkg.ServiceResult;

/**
 * 입고처리서 삭제
 */
@Controller
public class IncomingDeleteController {
	
	@Inject
	private IncomingService service;
	
	@ResponseBody
	@RequestMapping(value="/buy/incomingDelete.do", produces=MediaType.APPLICATION_JSON_UTF8_VALUE,method=RequestMethod.POST)
	public String IncomingDelete(
			@RequestBody List<IncomingVO> incomingVO
			) {
		String resultMessage = null;
		
			ServiceResult result = service.removeIncoming(incomingVO);
			switch(result) {
			case OK:
				resultMessage = "삭제되었습니다.";
				break;
			default:
				resultMessage = "서버오류";
				break;
			}
		return resultMessage;
	}
		
	@ResponseBody
	@RequestMapping(value="/buy/incomingRawsDelete.do",produces=MediaType.APPLICATION_JSON_UTF8_VALUE,method=RequestMethod.POST)
	public String ProductionRawsInstructionDelete(
			@RequestBody List<IncomingRawsVO> incomingRawsVO
			) {
		String resultMessage = null;
		
			ServiceResult result = service.removeRaws(incomingRawsVO);
			switch(result) {
			case OK:
				resultMessage = "삭제되었습니다.";
				break;
			case PKDUPLICATED:
				resultMessage = "존재하지 않는 원자재 입니다.";
				break;
			default:
				resultMessage = "서버오류";
				break;
			}
		return resultMessage;
	}
	
}
