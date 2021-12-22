package kr.or.ddit.produce.productioninstruction.controller;

import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.WebApplicationContext;

import kr.or.ddit.common.vo.CommonListVO;
import kr.or.ddit.common.vo.HubSearchVO;
import kr.or.ddit.produce.productioninstruction.service.ProductionInstructionService;
import kr.or.ddit.sales.vo.OrderBookProdVO;
import kr.or.ddit.sales.vo.OrderBookVO;
import kr.or.ddit.validate.groups.DetailUpdateGroup;
import kr.or.ddit.validate.util.hubWebValidateUtil;
import kr.or.ddit.validate.util.hubWebValidateUtilImpl;

/**
 * 생산지시서 - 수주서적용
 */
@Controller
public class PIOrderBookApplyController {
	/*
		수주서적용 버튼 클릭 - (모달 팝업) 수주서 목록 및 수주서-제품 목록 조회 - (모달팝업) 체크 후 적용버튼 클릭
		- 생산지시서에 품목코드, 품명, 규격, 단위, 완료예정일, 지시수량, 관리항목, 지시구분, BF여부, 완료여부, 마감여부 적용
		- 제품일 경우 하단그리드에 추가 X 반제품, 원자재 및 반제품일 경우 하단그리드에 품목 추가 - 적용 후 자동 저장
	 */

	@Inject
	private WebApplicationContext context;	
	
	@Inject
	private ProductionInstructionService service;
	
	// 수주서 적용 masterGrid 목록
	@ResponseBody
	@RequestMapping(value="/produce/piOrderBookApply.do",method=RequestMethod.POST,produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public List<OrderBookVO> pIOrderBookApply(@ModelAttribute("hubSearch") HubSearchVO hubSearch) {
		List<OrderBookVO> obListforPI = service.retrieveOrderBookListForPI(hubSearch);
		return obListforPI;
	}
	
	// 수주서 적용 detailGrid 목록
	@ResponseBody
	@RequestMapping(value="/produce/piOrderBookRawApply.do",method=RequestMethod.POST,produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public List<OrderBookProdVO> pIOrderBookRawApply(@RequestParam("orbNum") String orbNum) {
		OrderBookVO obVO = new OrderBookVO();
		obVO.setOrbNum(orbNum);
		List<OrderBookProdVO> oderBookProdList = service.retrieveOrderBookProdListforPI(obVO);
		return oderBookProdList;
	}
	
	// 수주서 적용 
	@ResponseBody
	@RequestMapping(value="/produce/piOrderBookApplyInsert.do",produces=MediaType.APPLICATION_JSON_UTF8_VALUE,method=RequestMethod.POST)
	public Map<String, Object> pIOrderBookApplyInsert(
			@Validated(DetailUpdateGroup.class) @RequestBody CommonListVO<OrderBookVO> obj
			,Errors errors
			) {
		hubWebValidateUtil valid = new hubWebValidateUtilImpl(obj, errors, context);
		if(!valid.checkError()) {
			valid.setResultMap(service.insertInstProdByOb(obj.getCommonList()));
		}
		return valid.getResultMap();
	}
	
}
