package kr.or.ddit.sales.orderbook.controller;

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
import kr.or.ddit.sales.orderbook.service.OrderBookService;
import kr.or.ddit.sales.vo.OrderBookProdVO;
import kr.or.ddit.sales.vo.OrderBookVO;

/**
 * 수주서
 */
@Controller
public class OrderBookDeleteController {
	@Inject
	private OrderBookService service;
	
	// 수주서 삭제
	@RequestMapping(value="/sales/OrderbookDelete.do", method=RequestMethod.POST, produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public Map<String, Object> EstimateDelete(
			@RequestBody List<OrderBookVO> orderBookList
			) {
		String message = null;
		Map<String, Object> resultMap = new HashMap<>();
		ServiceResult result = null;
		for (OrderBookVO orderBookVO : orderBookList) {
			List<OrderBookProdVO> orderBookProdList = service.retrieveOrderBookProdList(orderBookVO);
					if (orderBookList.size() != 0) {// 데이터로 받은 매출마감 리스트가 존재하면
						if (orderBookProdList.size() != 0) { // 매출마감 제품 존재하면
						result = service.deleteRaws(orderBookProdList); // 매출마감 제품 리스트 보내 삭제 쿼리 실행
							if (result != result.OK) {
								message = "삭제 실패";
							}else { // 매출마감 제품을 삭제 성공했으면
								result = service.deleteOrderBook(orderBookVO); // 매출마감서 삭제
								message = "전체 삭제 되었습니다.";
							}
							
						}else { // 조건문 끝
						// 매출마감 제품이 없을 경우 바로 매출마감서 삭제
						result = service.deleteOrderBook(orderBookVO); // 매출마감서 삭제
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
		
	// 수주서 제품 삭제
	@ResponseBody
	@RequestMapping(value="/sales/OrderbookProdDelete.do", method=RequestMethod.POST, produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public Map<String, Object> OrderbookProdDelete(
			@RequestBody List<OrderBookProdVO> orderBookProdVO
			) {
		ServiceResult result = service.deleteRaws(orderBookProdVO);
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
