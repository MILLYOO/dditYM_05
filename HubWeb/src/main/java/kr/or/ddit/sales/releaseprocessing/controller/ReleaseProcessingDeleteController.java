package kr.or.ddit.sales.releaseprocessing.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.sales.releaseprocessing.service.ReleaseProcessingService;
import kr.or.ddit.sales.vo.RelProdVO;
import kr.or.ddit.sales.vo.ReleaseVO;

/**
 * 출고처리
 */
@Controller
public class ReleaseProcessingDeleteController {
	@Inject
	private ReleaseProcessingService service;
	
	// 출고처리서 삭제
	@RequestMapping("/sales/ReleaseProcessingDelete.do")
	@ResponseBody
	public Map<String, Object> ReleaseProcessingDelete(
			@RequestBody List<ReleaseVO> releaseVOList
			) {
		String message = null;
		Map<String, Object> resultMap = new HashMap<>();
		for(ReleaseVO releaseVO : releaseVOList) {
			List<RelProdVO> relProdList = service.releaseProcessing(releaseVO);
				if (relProdList.size() != 0) {
					ServiceResult result = null;
					result = service.deleteRaws(relProdList);
						if (result != result.OK) {
							message = "삭제 실패하였습니다.";
						}else {
							result = service.deleteReleaseProcessing(releaseVO);
							message = "전체 삭제 되었습니다.";
						}
				}else {
					ServiceResult result = service.deleteReleaseProcessing(releaseVO);
					
					switch (result) {
					case OK:
						message = "삭제되었습니다.";
						break;
					case PKDUPLICATED:
						message = "존재하지않습니다.";
						break;
					default:
						message = "오류.";
						break;
					}
				} // else문
		} // for문 끝
		resultMap.put("message", message);
		return resultMap;
	}
	
	// 출고처리서 제품 삭제
	@ResponseBody
	@RequestMapping(value="/sales/ReleaseProcessingProdDelete.do", method=RequestMethod.POST, produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public Map<String, Object> ReleaseProcessingProdDelete(
			@RequestBody List<RelProdVO> releaseProdVO
			){
		ServiceResult result = service.deleteRaws(releaseProdVO);
		Map<String, Object> resultMap = new HashMap<>();
		String message = null;
		
		switch (result) {
		case OK:
			message = "삭제되었습니다.";
			break;
		case PKDUPLICATED:
			message = "존재하지 않습니다.";
			break;
		default:
			message = "오류.";
			break;
		}
		resultMap.put("message", message);
		return resultMap;
	}
}
