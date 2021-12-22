package kr.or.ddit.sales.estimate.controller;

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
import kr.or.ddit.sales.estimate.service.EstimateService;
import kr.or.ddit.sales.vo.EstimateProdVO;
import kr.or.ddit.sales.vo.EstimateVO;

/**
 * 견적서
 */
@Controller
public class EstimateDeleteController {
	@Inject
	private EstimateService service;
	
	// 견적서 삭제
	@RequestMapping(value="/sales/EstimateDelete.do", method=RequestMethod.POST, produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public Map<String, Object> EstimateDelete(
			@RequestBody List<EstimateVO> estimateList
			) {
		ServiceResult result = null;
		String message = null;
		// 결과 담을 맵
		Map<String, Object> resultMap = new HashMap<>();
		for (EstimateVO estimateVO : estimateList) { // 데이터로 받은 견적서 리스트를 한건씩 꺼내 온 데이터
			List<EstimateProdVO> estimateProdList =service.retrieveEstimateProdList(estimateVO); // 견적서 제품 내역 가져온다.
			// 조건문 실행
				if (estimateList.size() != 0) { // 데이터로 받은 견적서 리스트가 존재하면
					if (estimateProdList.size() != 0) {// 견적서 제품 존재하면
						result = service.deleteRaws(estimateProdList); // 견적서 제품 리스트 보내 삭제 쿼리 실행
						if (result != result.OK) {
							message = "삭제 실패";
						}else {
							result = service.deleteEstimate(estimateVO); // 견적서 삭제
							message = "전체 삭제 되었습니다.";
						}
					}else {
						// 견적서 제품이 없을 경우 바로 삭제
						result = service.deleteEstimate(estimateVO);
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
					} // 조건문 끝
				}
		} // 반복문 끝
		resultMap.put("message", message);
		return resultMap;
	}
	// 견적서 제품 삭제
	@ResponseBody
	@RequestMapping(value="/sales/EstimateProdDelete.do", method=RequestMethod.POST, produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public Map<String, Object> EstimateProdDelete(
			@RequestBody List<EstimateProdVO> estimateProdVO
			) {
		// 제품 삭제
		ServiceResult result = service.deleteRaws(estimateProdVO);
		// 결과값 맵
		Map<String, Object> resultMap = new HashMap<>();
		// 메시지
		String message = null;
		
		switch(result) {
		case OK:
			message = "삭제되었습니다.";
			break;
		
		case PKDUPLICATED:
			message = "존재하지 않습니다.";
			break;
			
		default:
			message = "오류";
			break;
		}
		resultMap.put("message", message);
		return resultMap;
	} // 제품 삭제 끝
	
}
