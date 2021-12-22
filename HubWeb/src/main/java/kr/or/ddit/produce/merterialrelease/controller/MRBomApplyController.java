package kr.or.ddit.produce.merterialrelease.controller;

import java.util.List;

import javax.inject.Inject;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.info2.vo.BomRawsVO;
import kr.or.ddit.produce.merterialrelease.service.MaterialReleaseService;

/**
 * 자재출고처리-bom전개
 */
@Controller
public class MRBomApplyController {
	@Inject
	private MaterialReleaseService service;
	
	@ResponseBody
	@RequestMapping(value="/produce/mrBomApply.do",method=RequestMethod.POST,produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public String MRBomApply(
			@RequestBody List<BomRawsVO> bomRawsVO
			) {
		String resultMessage = null;
		ServiceResult result = service.createBomApplyMrelease(bomRawsVO);
		switch(result) {
			case OK:
				resultMessage = "저장되었습니다.";
				break;
			case PKDUPLICATED:
				resultMessage = "존재하지 않는 제품코드입니다.";
				break;
			default:
				resultMessage = "서버오류";
				break;
		}
		return resultMessage;
	}
}
