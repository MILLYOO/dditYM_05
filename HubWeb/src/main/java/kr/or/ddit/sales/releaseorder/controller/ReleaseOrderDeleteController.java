package kr.or.ddit.sales.releaseorder.controller;

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
import kr.or.ddit.sales.releaseorder.service.ReleaseOrderService;
import kr.or.ddit.sales.vo.ReleaseOrderProdVO;
import kr.or.ddit.sales.vo.ReleaseOrderVO;

/**
 * 출고지시서
 */
@Controller
public class ReleaseOrderDeleteController {
	@Inject
	private ReleaseOrderService service;
	
	// 출고지시서 삭제
	@RequestMapping("/sales/ReleaseOrderDelete.do")
	@ResponseBody
	public Map<String, Object> ReleaseOrderDelete(
			@RequestBody List<ReleaseOrderVO> releaseOrderVOList
			) {
		String message = null;
		Map<String, Object> resultMap = new HashMap<>(); // 결과값을 가지고 나갈 맵 생성
		for(ReleaseOrderVO releaseOrderVO : releaseOrderVOList) {
			
		// 출고지시서 제품 목록 조회 하여 리스트에 담아준다.
		List<ReleaseOrderProdVO> releaseOrderProdList =service.ReleaseOrderList(releaseOrderVO); 
		if (releaseOrderProdList.size() != 0) { // 출고지시서 제품 목록 조회하였을때 존재하면!
			ServiceResult result = null; // 결과 널값 세팅
			int count = 0; // 카운트 세팅
//			for(ReleaseOrderProdVO REO : releaseOrderProdList) { // 포문 돌려서 출고지시서 제품 목록을 하나하나 꺼내온다.
				result = service.deleteRaws(releaseOrderProdList); // 출고지시서 제품의 내용을 하나하나 지운다.
				if(result != result.OK) { // 실패했을때
					message = "삭제 실패하였습니다.";
					message = "품목 : " + count + "건 삭제 되었습니다.";
				}else { // 성공
					result = service.deleteReleaseOrder(releaseOrderVO);
					message = "전체 삭제 되었습니다.";
					count++; // 성공 회수 카운트
				}
//			} // 포문 종료 모든 제품 삭제 완료
		}else {
			
		ServiceResult result = service.deleteReleaseOrder(releaseOrderVO);
		
		switch(result) {
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
		}
		resultMap.put("message", message);
		return resultMap;
	}
	
	// 출고지시서 제품 삭제
	@ResponseBody
	@RequestMapping(value="/sales/ReleaseOrderProdDelete.do", method=RequestMethod.POST, produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public Map<String, Object> EstimateDelete(
			@RequestBody List<ReleaseOrderProdVO> releaseOrderProdVO
			) {
		ServiceResult result = service.deleteRaws(releaseOrderProdVO);
		Map<String, Object> resultMap = new HashMap<>();
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
	}
}
