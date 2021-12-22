package kr.or.ddit.sales.salesclose.controller;

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
import kr.or.ddit.sales.salesclose.service.SalesCloseService;
import kr.or.ddit.sales.vo.SalProdVO;
import kr.or.ddit.sales.vo.SalesCloseVO;

/**
 * 매출마감
 */
@Controller
public class SalesCloseDeleteController {
	@Inject
	private SalesCloseService service;
	
	// 매출마감 삭제
	@RequestMapping("/sales/SalesCloseDelete.do")
	@ResponseBody
	public Map<String, Object> SalesCloseDelete(
			@RequestBody List<SalesCloseVO> salesCloseList
			) {
		String message = null;
		// 결과값을 담을 맵
		Map<String, Object> resultMap = new HashMap<>();
		ServiceResult result = null;
		for (SalesCloseVO salesCloseVO : salesCloseList) { // 데이터로 받은 매출마감서 리스트를 한건씩 꺼내 데이터
			List<SalProdVO> salProdList = service.retrieveSalesCloseProdList(salesCloseVO); // 매출마감 제품 내역 가져온다.
				// 조건문 실행
				if (salesCloseList.size() != 0) {// 데이터로 받은 매출마감 리스트가 존재하면
					int count = 0; // 제품 삭제 건수
					if (salProdList.size() != 0) { // 매출마감 제품 존재하면
						
					result = service.deleteRaws(salProdList); // 매출마감 제품 리스트 보내 삭제 쿼리 실행
						if (result != result.OK) {
							message = "삭제 실패";
						}else { // 매출마감 제품을 삭제 성공했으면
							result = service.deleteSalesClose(salesCloseVO); // 매출마감서 삭제
							message = "전체 삭제 되었습니다.";
						}
						
					}else { // 조건문 끝
					// 매출마감 제품이 없을 경우 바로 매출마감서 삭제
					result = service.deleteSalesClose(salesCloseVO); // 매출마감서 삭제
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
	// 매출마감 제품 삭제
	@ResponseBody
	@RequestMapping(value="/sales/SalesCloseProdDelete.do", method=RequestMethod.POST, produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public Map<String, Object> SalesCloseProdDelete(
			@RequestBody List<SalProdVO> salProdVO
			){
		// 제품 삭제
		ServiceResult result = service.deleteRaws(salProdVO);
		// 결과값 맵
		Map<String, Object> resultMap = new HashMap<>();
		// 메세지
		String message = null;
		
		switch (result) {
		case OK:
			message = "삭제되었습니다";
			break;
		case PKDUPLICATED:
			message = "존재하지않습니다";
			break;
		default:
			message = "오류";
			break;
		} // 스위치문 끝
		resultMap.put("message", message);
		return resultMap;
	} // 제품 삭제 끝
}
