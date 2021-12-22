package kr.or.ddit.sales.salesclose.controller;

import java.util.Map;

import javax.inject.Inject;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.WebApplicationContext;

import kr.or.ddit.common.vo.CommonListVO;
import kr.or.ddit.sales.salesclose.service.SalesCloseService;
import kr.or.ddit.sales.vo.SalProdVO;
import kr.or.ddit.sales.vo.SalesCloseVO;
import kr.or.ddit.validate.groups.UpdateGroup;
import kr.or.ddit.validate.util.hubWebValidateUtil;
import kr.or.ddit.validate.util.hubWebValidateUtilImpl;

/**
 * 매출마감
 */
@Controller
public class SalesCloseInsertController {
	@Inject
	private WebApplicationContext context;
	@Inject
	private SalesCloseService service;
	
	// 매출마감 등록
	@RequestMapping(value="/sales/SalesCloseInsert.do", method=RequestMethod.POST, produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public Map<String, Object> SalesCloseInsert(
			@RequestBody @Validated(UpdateGroup.class) CommonListVO<SalesCloseVO> obj,
			Errors errors
			) { 
			// 검증 시행 조건문
			hubWebValidateUtil valid = new hubWebValidateUtilImpl(obj, errors, context); // 검증조건에서 에러가 발생하면
			if (!valid.checkError()) {
				valid.setResultMap(service.createORupdateSalesClose(obj.getCommonList()));
			}
			return valid.getResultMap();
	} // 매출마감 등록 끝
	
	// 매출마감 제품 등록
	@ResponseBody
	@RequestMapping(value="/sales/SalesCloseProdInsert.do", method=RequestMethod.POST, produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public Map<String, Object> SalesCloseProdInsert(
			@RequestBody @Validated(UpdateGroup.class) CommonListVO<SalProdVO> obj,
			Errors errors
			){
		
			hubWebValidateUtil valid = new hubWebValidateUtilImpl(obj, errors, context);
			if (!valid.checkError()) {
				valid.setResultMap(service.createRaws(obj.getCommonList()));
			}
			return valid.getResultMap();
	} // 매출마감 제품 등록 끝
}
