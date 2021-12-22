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
import kr.or.ddit.produce.merterialrelease.service.MaterialReleaseService;
import kr.or.ddit.produce.vo.MreleaseRawsVO;
import kr.or.ddit.produce.vo.MreleaseVO;

/**
 * 자재출고처리, 자재출고처리-원자재 삭제 컨트롤러
 */
@Controller
public class MaterialReleaseDeleteController {
	@Inject
	private MaterialReleaseService service;
	
	// 자재출고처리서 삭제
	@RequestMapping(value="/produce/materialReleaseDelete.do",produces=MediaType.APPLICATION_JSON_UTF8_VALUE,method=RequestMethod.POST)
	@ResponseBody
	public String MaterialReleaseDelete(
			@RequestBody List<MreleaseVO> mreleaseVO
			) {
		String resultMessage = null;
		
		ServiceResult result = service.deleteMaterialRelease(mreleaseVO);
		switch(result) {
		case OK:
			resultMessage = "성공";
			break;
		default:
			resultMessage = "서버오류";
			break;
		}
		return resultMessage;
	}
	
	// 자재출고처리서-원자재 삭제
	@RequestMapping(value="/produce/materialReleaseRawsDelete.do",produces=MediaType.APPLICATION_JSON_UTF8_VALUE,method=RequestMethod.POST)
	@ResponseBody
	public String MaterialReleaseRawsDelete(
			@RequestBody List<MreleaseRawsVO> mreleaseRawsVO
			) {
		String resultMessage = null;
		
		ServiceResult result = service.deleteRaws(mreleaseRawsVO);
		switch(result) {
		case OK:
			resultMessage = "성공";
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
