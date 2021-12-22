package kr.or.ddit.sales.orderbook.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.springframework.beans.BeanUtils;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.or.ddit.common.vo.HubSearchVO;
import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.sales.orderbook.service.OrderBookService;
import kr.or.ddit.sales.vo.EstimateProdVO;
import kr.or.ddit.sales.vo.EstimateVO;
import kr.or.ddit.sales.vo.OrderBookProdVO;
import kr.or.ddit.sales.vo.OrderBookVO;

/**
 * 수주서 - 견적서적용
 */
@Controller
public class OBEstimateApplyController {
	@Inject
	private OrderBookService service;
	
	// 견적서 적용 masterGrid 목록
	@ResponseBody
	@RequestMapping(value="/sales/piEstimateApply.do",method=RequestMethod.POST,produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public List<EstimateVO> piEstimateApply(@ModelAttribute("hubSearch") HubSearchVO hubSearchVO) {
		hubSearchVO.setDateStart(hubSearchVO.getObdateStart());
		hubSearchVO.setDateEnd(hubSearchVO.getObdateEnd());
		List<EstimateVO> estimateListforPI = service.retrieveEstimateListForPI(hubSearchVO);
		return estimateListforPI;
	}
	
	// 견적서 적용 detailGrid 목록
	@ResponseBody
	@RequestMapping(value="/sales/piEstimateProdApply.do",method=RequestMethod.POST,produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public List<EstimateProdVO> pIEstimateProdApply(@RequestParam("estCode") String estCode) {
		EstimateVO esVO = new EstimateVO();
		esVO.setEstCode(estCode);
		List<EstimateProdVO> estimateProdList = service.retrieveEstimateProdListForPI(esVO);
		return estimateProdList;
	}
	
	// 견적서 적용 
		@ResponseBody
		@RequestMapping(value="/sales/piEstimateApplyInsert.do",produces=MediaType.APPLICATION_JSON_UTF8_VALUE,method=RequestMethod.POST)
		public Map<String, Object> pIOrderBookApplyInsert(
				@RequestBody EstimateVO estimateVO,
				Errors errors
 				) {
			List<EstimateVO> estimateList = service.insertEstimateTotalListForPI(estimateVO);
			List<OrderBookVO> orderBookList = new ArrayList<>();
			ServiceResult result = null;
			
			for (int i = 0; i < estimateList.size(); i++) {
				List<OrderBookProdVO> orderBookProdList = new ArrayList<>();
				OrderBookVO orbVO = new OrderBookVO();
				orbVO.setBuyerCode(estimateList.get(i).getBuyerCode());
				orbVO.setBuyerName(estimateList.get(i).getBuyerName());
				orbVO.setDeptName(estimateList.get(i).getDeptName());
				orbVO.setEmpName(estimateList.get(i).getEmpName());
				orbVO.setOrbFinish(estimateList.get(i).getEstFinish());
				orbVO.setOrbResult(estimateList.get(i).getEstResult());
				orbVO.setOrbDate(estimateList.get(i).getEstDate());
				orbVO.setOrbVat(estimateList.get(i).getEstVat());
				List<EstimateProdVO> estimateProdList = estimateList.get(i).getDataProdList();
				for(int j = 0; j < estimateProdList.size(); j++) {
					OrderBookProdVO orbProdVO = new OrderBookProdVO();
					BeanUtils.copyProperties(estimateProdList.get(j), orbProdVO);
					orbProdVO.setOpDate(estimateProdList.get(j).getEpDate());
					orbProdVO.setOpQty(estimateProdList.get(j).getEpQty());
					orbProdVO.setOpSp(estimateProdList.get(j).getEpSp());
					orbProdVO.setOpVat(estimateProdList.get(j).getEpVat());
					orbProdVO.setOpSumcost(estimateProdList.get(j).getEpScost());
					orbProdVO.setOpUprice(estimateProdList.get(j).getEpUprice());
					orbProdVO.setEstCode(estimateProdList.get(j).getEstCode());
					orderBookProdList.add(j, orbProdVO);
				}
				orbVO.setDataProdList(orderBookProdList);
				orderBookList.add(i, orbVO);
			}
			
			
			
			// 바로 인서트
			// 결과값 맵
			Map<String, Object> resultMap = new HashMap<>();
			// 메시지 선언
			String message = null;
			// 검증 시행 조건문
			if (errors.hasErrors()) {
				// 에러가 발생하면
				message = "다시 확인해주세요";
				resultMap.put("message", message);
				return resultMap;
			} // 조건문 끝

			// 등록 후 결과값 저장
				result = service.createORupdateOrderBook(orderBookList);
			
			switch (result) { // 수주서 결과 값에 따른 스위치문
			case OK: // 수주서 등록이 성공했을때
				message = "등록완료";
				break;
			case PKDUPLICATED:
				message = "중복";
				break;
			default:
				message = "오류";
				break;
			} // 스위치문 끝

			resultMap.put("message", message);
			return resultMap;
		}
}
