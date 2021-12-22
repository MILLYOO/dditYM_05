package kr.or.ddit.sales.releaseprocessing.controller;

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
import kr.or.ddit.sales.releaseprocessing.service.ReleaseProcessingService;
import kr.or.ddit.sales.vo.RelProdVO;
import kr.or.ddit.sales.vo.ReleaseListVO;
import kr.or.ddit.sales.vo.ReleaseOrderProdVO;
import kr.or.ddit.sales.vo.ReleaseOrderVO;
import kr.or.ddit.sales.vo.ReleaseVO;
import lombok.extern.slf4j.Slf4j;

/**
 * 출고처리 - 출고지시서적용
 */
@Slf4j
@Controller
public class RPReleaseOrderApplyController {
	@Inject
	private ReleaseProcessingService service;
	
	// 출고처리서 적용 masterGird 목록
	@ResponseBody
	@RequestMapping(value="/sales/piReleaseApply.do", method=RequestMethod.POST, produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public List<ReleaseOrderVO> piReleaseApply(@ModelAttribute("hubSearch") HubSearchVO hubSearchVO){
		hubSearchVO.setDateStart(hubSearchVO.getObdateStart());
		hubSearchVO.setDateEnd(hubSearchVO.getObdateEnd());
		List<ReleaseOrderVO> releaseListForPI = service.retreiveReleaseOrderListForPI(hubSearchVO);
		return releaseListForPI;
	}
	
	// 출고처리서 적용 detailGrid 목록
	@RequestMapping(value="/sales/piReleaseProdApply.do", method=RequestMethod.POST, produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public List<ReleaseOrderProdVO> piReleaseProdApply(@RequestParam("reoNum") String reoNum) {
		ReleaseOrderVO roVO = new ReleaseOrderVO();
		roVO.setReoNum(reoNum);
		List<ReleaseOrderProdVO> releaseOrderProdList = service.retrieveReleaseOrderProdListForPI(roVO);
		return releaseOrderProdList;
	}
	
	// 출고처리서 적용
	@RequestMapping(value="/sales/piReleaseApplyInsert.do", produces=MediaType.APPLICATION_JSON_UTF8_VALUE, method=RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> piReleaseApplyInsert(
			@RequestBody ReleaseOrderVO releaseOrderVO,
			Errors errors
			){
			// data와 dataProdList를 가지고 해당 서비스로 이동
			List<ReleaseOrderVO> releaseOrderList = service.retrieveReleaseOrderTotalListForPI(releaseOrderVO);
			List<ReleaseVO> releaseList = new ArrayList<>();
			ServiceResult result = null;
			ReleaseListVO tVO = new ReleaseListVO();
			
			for (int i = 0; i < releaseOrderList.size(); i++) {
				List<RelProdVO> relProdList  = new ArrayList<>();
				ReleaseVO rVO = new ReleaseVO();
				rVO.setBuyerCode(releaseOrderList.get(i).getBuyerCode());
				rVO.setBuyerName(releaseOrderList.get(i).getBuyerName());
				rVO.setDeptName(releaseOrderList.get(i).getDeptName());
				rVO.setEmpName(releaseOrderList.get(i).getEmpName());
				rVO.setRelDate(releaseOrderList.get(i).getReoDate());
				rVO.setRelFinish(releaseOrderList.get(i).getReoFinish());
				rVO.setRelResult(releaseOrderList.get(i).getReoResult());
				rVO.setRelVat(releaseOrderList.get(i).getReoVat());
				List<ReleaseOrderProdVO> releaseOrderProdList = releaseOrderList.get(i).getDataProdList();
				for (int j = 0; j < releaseOrderProdList.size(); j++) {
					RelProdVO rPVO = new RelProdVO();
					BeanUtils.copyProperties(releaseOrderProdList.get(j), rPVO);
					rPVO.setProjName(releaseOrderProdList.get(j).getProjName());
					rPVO.setRpDate(releaseOrderProdList.get(j).getRopDate());
					rPVO.setRpQty(releaseOrderProdList.get(j).getRopQty());
					rPVO.setRpSp(releaseOrderProdList.get(j).getRopSp());
					rPVO.setRpSumcost(releaseOrderProdList.get(j).getRopSumcost());
					rPVO.setRpUprice(releaseOrderProdList.get(j).getRopUprice());
					rPVO.setRpVat(releaseOrderProdList.get(j).getRopVat());
					rPVO.setReoName(releaseOrderProdList.get(j).getReoNum());
					relProdList.add(j, rPVO);
				} // for문 끝
				rVO.setDataProdList(relProdList);
//				tVO.setDataProdList(relProdList);
				releaseList.add(i,rVO);
//				tVO.setDataList(releaseList);
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
							result = service.createORupdateReleaseProcessing(releaseList);
						
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
					}
}
