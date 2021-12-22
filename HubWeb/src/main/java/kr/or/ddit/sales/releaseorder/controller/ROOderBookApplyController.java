package kr.or.ddit.sales.releaseorder.controller;

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
import kr.or.ddit.sales.releaseorder.service.ReleaseOrderService;
import kr.or.ddit.sales.vo.OrderBookProdVO;
import kr.or.ddit.sales.vo.OrderBookVO;
import kr.or.ddit.sales.vo.ReleaseOrderProdVO;
import kr.or.ddit.sales.vo.ReleaseOrderVO;

/**
 * 출고지시서 - 수주서적용
 */
@Controller
public class ROOderBookApplyController {
	@Inject
	private ReleaseOrderService service;
	
		// 출고지시서 적용 masterGrid 목록
		@ResponseBody
		@RequestMapping(value="/sales/piOrderBookApply.do",method=RequestMethod.POST,produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
		public List<OrderBookVO> piOrderBookApply(@ModelAttribute("hubSearch") HubSearchVO hubSearchVO) {
			hubSearchVO.setDateStart(hubSearchVO.getObdateStart());
			hubSearchVO.setDateEnd(hubSearchVO.getObdateEnd());
			List<OrderBookVO> obListforPI = service.retrieveOrderbookListForPI(hubSearchVO);
			return obListforPI;
		}
		// 출고지시서 적용 detailGrid 목록
		@ResponseBody
		@RequestMapping(value="/sales/piOrderBookProdApply.do",method=RequestMethod.POST,produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
		public List<OrderBookProdVO> piOrderBookProdApply(@RequestParam("orbNum") String orbNum) {
			OrderBookVO obVO = new OrderBookVO(); // 수주 적용할 수주서 브이오 객체 생성
			obVO.setOrbNum(orbNum); // 브이오에 수주번호를 담아준다.
			List<OrderBookProdVO> orderBookProdList = service.retrieveOrderbookProdListForPI(obVO); // 해당 수주번호를 담은 브이오 객체를 넘겨 같은 수주번호의 제품목록을 찾아온다.
			return orderBookProdList; // 수주번호 - 제품 목록을 리턴
		}
		// 출고지시서 적용 
		@ResponseBody
		@RequestMapping(value="/sales/piOrderBookApplyInsert.do",produces=MediaType.APPLICATION_JSON_UTF8_VALUE,method=RequestMethod.POST)
		public Map<String, Object> piOrderBookApplyInsert(
				@RequestBody OrderBookVO orderBookVO,
				Errors errors
 				) {
			List<OrderBookVO> orderBookList = service.insertOrderBookTotalListForPI(orderBookVO);
			List<ReleaseOrderVO> releaseOrderList = new ArrayList<>();
			ServiceResult result = null;
			
			for (int i = 0; i < orderBookList.size(); i++) {
				List<ReleaseOrderProdVO> releaseOrderProdList = new ArrayList<>();
				ReleaseOrderVO relVO = new ReleaseOrderVO();
				relVO.setBuyerCode(orderBookList.get(i).getBuyerCode());
				relVO.setBuyerName(orderBookList.get(i).getBuyerName());
				relVO.setDeptName(orderBookList.get(i).getDeptName());
				relVO.setEmpName(orderBookList.get(i).getEmpName());
				relVO.setReoFinish(orderBookList.get(i).getOrbFinish());
				relVO.setReoResult(orderBookList.get(i).getOrbResult());
				relVO.setReoDate(orderBookList.get(i).getOrbDate());
				relVO.setReoVat(orderBookList.get(i).getOrbVat());
				
				List<OrderBookProdVO> orderBookProdList = orderBookList.get(i).getDataProdList();
					
				if (orderBookProdList != null) {
				for(int j = 0; j < orderBookProdList.size(); j++) {
					ReleaseOrderProdVO releaseOrderProdVO = new ReleaseOrderProdVO();
					BeanUtils.copyProperties(orderBookProdList.get(j), releaseOrderProdVO);
					releaseOrderProdVO.setProjName(orderBookProdList.get(j).getProjName());
					releaseOrderProdVO.setRopDate(orderBookProdList.get(j).getOpDate());
					releaseOrderProdVO.setRopQty(orderBookProdList.get(j).getOpQty());
					releaseOrderProdVO.setRopSp(orderBookProdList.get(j).getOpSp());
					releaseOrderProdVO.setRopVat(orderBookProdList.get(j).getOpVat());
					releaseOrderProdVO.setRopSumcost(orderBookProdList.get(j).getOpSumcost());
					releaseOrderProdVO.setRopUprice(orderBookProdList.get(j).getOpUprice());
					releaseOrderProdVO.setOrbNum(orderBookProdList.get(j).getOrbNum());
					releaseOrderProdList.add(j, releaseOrderProdVO);
				}
				relVO.setDataProdList(releaseOrderProdList);
				}
				releaseOrderList.add(i, relVO);
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
							result = service.createORupdateReleaseOrder(releaseOrderList);
						
						switch (result) { // 매출마감 결과 값에 따른 스위치문
						case OK: // 매출마감이 성공했을때
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
			
			
			
//			// 수주서 - 제품의 목록을 받아온다.
//			List<OrderBookProdVO> orderBookProdList = service.retrieveOrderbookProdListForPI(orderBookVO);
//			// 수주서 목록을 받아온다.
//			List<OrderBookVO> orderBookList = service.retrieveOrderbookListForPI(hubSearchVO);
//			// 가져온 데이터를 담아줄 출고지시서 - 제품 브이오 객체 생성
//			ReleaseOrderProdVO roprodVO = new ReleaseOrderProdVO();
//			// 새로운 맵
//			Map<String, Object> vo = new HashMap<>();
//			// 포문 수주서 목록 
//			for(int i = 0; i < orderBookList.size(); i++) {
//				ReleaseOrderVO roVO = new ReleaseOrderVO();
//				// 거래처코드
//				roVO.setBuyerCode(orderBookList.get(i).getBuyerCode());
//				// 거래처이름
//				roVO.setBuyerName(orderBookList.get(i).getBuyerName());
//				// 부서이름
//				roVO.setDeptName(orderBookList.get(i).getDeptName());
//				// 사원이름
//				roVO.setEmpName(orderBookList.get(i).getEmpName());
//				// 날짜
//				roVO.setReoDate(orderBookList.get(i).getOrbDate());
//				// 수주번호
//				String maxId = service.retrieveMaxId(roVO);
//				roVO.setReoNum(maxId);
//				// vat 여부
//				roVO.setReoVat(orderBookList.get(i).getOrbVat());
//				// 완료 여부
//				roVO.setReoFinish(orderBookList.get(i).getOrbFinish());
//				// 마감 여부
//				roVO.setReoResult(orderBookList.get(i).getOrbResult());
//			}
//			for (int i = 0; i < orderBookProdList.size(); i++) {
////						roprodVO.setGcommName(orderBookProdList.get(i).getGcommName());
////						roprodVO.setIcodeName(orderBookProdList.get(i).getIcodeName());
////						roprodVO.setProdCode(orderBookProdList.get(i).getProdCode());
////						roprodVO.setProdName(orderBookProdList.get(i).getProdName());
////						roprodVO.setProjName(orderBookProdList.get(i).getProdName());
////						roprodVO.setUcommName(orderBookProdList.get(i).getUcommName());
//				// 이름이 같은 컬럼 한번에 넣기
//				BeanUtils.copyProperties(orderBookProdList.get(i), roprodVO);
//				roprodVO.setRopDate(orderBookProdList.get(i).getOpDate());
//				roprodVO.setRopQty(orderBookProdList.get(i).getOpQty());
//				roprodVO.setRopSp(orderBookProdList.get(i).getOpSp());
//				roprodVO.setRopSumcost(orderBookProdList.get(i).getOpSumcost());
//				roprodVO.setRopUprice(orderBookProdList.get(i).getOpUprice());
//				roprodVO.setRopVat(orderBookProdList.get(i).getOpVat());
//			}
//			return vo;	
		}
			}
