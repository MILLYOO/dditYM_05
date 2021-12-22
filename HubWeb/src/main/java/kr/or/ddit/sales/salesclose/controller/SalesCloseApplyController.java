package kr.or.ddit.sales.salesclose.controller;

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
import kr.or.ddit.sales.salesclose.service.SalesCloseService;
import kr.or.ddit.sales.vo.RelProdVO;
import kr.or.ddit.sales.vo.ReleaseVO;
import kr.or.ddit.sales.vo.SalProdVO;
import kr.or.ddit.sales.vo.SalesCloseVO;
import lombok.extern.slf4j.Slf4j;

/**
 * 매출마감 - 출고처리서 적용
 * @author pc14
 *
 */
@Slf4j
@Controller
public class SalesCloseApplyController {

	@Inject
	private SalesCloseService service;
	
	// 매출마감 적용 masterGrid 목록
	@ResponseBody
	@RequestMapping(value="/sales/piSalesCloseApply.do", method=RequestMethod.POST, produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public List<ReleaseVO> piSalesCloseApply(@ModelAttribute("hubSearch") HubSearchVO hubSearchVO){
		// 매출마감 적용을 위한 출고처리서 조회
		hubSearchVO.setDateStart(hubSearchVO.getObdateStart());
		hubSearchVO.setDateEnd(hubSearchVO.getObdateEnd());
		List<ReleaseVO> salesCloseListForPI = service.retreiveSalesCloseListForPI(hubSearchVO);
		return salesCloseListForPI;
	}
	// 매출마감 적용 detailGrid 목록
	@ResponseBody
	@RequestMapping(value="sales/piSalesCloseProdApply.do", method=RequestMethod.POST, produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public List<RelProdVO> piSalesCloseProdApply(@RequestParam("relNum") String relNum){
		// 출고처리 객체 선언
		ReleaseVO scVO = new ReleaseVO();
		// 출고처리번호 
		scVO.setRelNum(relNum);
		// 출고처리 리스트
		List<RelProdVO> salProdList = service.retrieveSalesCloseProdListForPI(scVO);
		return salProdList;
	}
	// 매출마감 적용
	@ResponseBody
	@RequestMapping(value="/sales/piSalesCloseApplyInsert.do", method=RequestMethod.POST, produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public Map<String, Object> piSalesCloseApplyInsert(
			@RequestBody ReleaseVO releaseVO, Errors errors
			) {
		List<ReleaseVO> releaseList = service.insertSalesCloseTotalListForPI(releaseVO);
		List<SalesCloseVO> salesCloseList = new ArrayList<>();
		ServiceResult result = null;
		for(int i = 0; i <releaseList.size(); i++) {
			List<SalProdVO> sPVOList = new ArrayList<>();
			SalesCloseVO sVO = new SalesCloseVO();
			BeanUtils.copyProperties(releaseList.get(i), sVO);
			sVO.setSalcDate(releaseList.get(i).getRelDate());
			sVO.setSalcVat(releaseList.get(i).getRelVat());
			List<RelProdVO> relProdList = releaseList.get(i).getDataProdList();
			
		// 매출마감 제품 반복문으로 데이터 브이오 객체에 넣어준다.
		for (int j = 0; j < relProdList.size(); j++) {
			SalProdVO sPVO = new SalProdVO();
			BeanUtils.copyProperties(relProdList.get(j), sPVO);
			sPVO.setRelNum(relProdList.get(j).getRelNum());
			sPVO.setScpDate(relProdList.get(j).getRpDate());
			sPVO.setScpQty(relProdList.get(j).getRpQty());
			sPVO.setScpSp(relProdList.get(j).getRpSp());
			sPVO.setScpSumcost(relProdList.get(j).getRpSumcost());
			sPVO.setScpUprice(relProdList.get(j).getRpUprice());
			sPVO.setScpVat(relProdList.get(j).getRpVat());
			sPVOList.add(j, sPVO);
			
			
		} // for문 끝
		sVO.setDataProdList(sPVOList); // 출고처리서 제품 내역을 매출마감으로 변환한 리스트를 매출마감서에 담아준다.
		salesCloseList.add(i,sVO);
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
		result = service.createORupdateSalesClose(salesCloseList);
		// 맵에서 result 키값으로 해당 값 꺼내온다.
		
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
