package kr.or.ddit.sales.estimate.controller;

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
import kr.or.ddit.sales.estimate.service.EstimateService;
import kr.or.ddit.sales.vo.EstimateProdVO;
import kr.or.ddit.sales.vo.EstimateVO;
import kr.or.ddit.validate.groups.UpdateGroup;
import kr.or.ddit.validate.util.hubWebValidateUtil;
import kr.or.ddit.validate.util.hubWebValidateUtilImpl;

/**
 * 견적서
 */
@Controller
public class EstimateInsertController {
	@Inject
	private EstimateService service;
	@Inject
	private WebApplicationContext context;
	
	
	// 견적서 등록
	@ResponseBody
	@RequestMapping(value="/sales/EstimateInsert.do", method=RequestMethod.POST, produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public Map<String, Object> EstimateInsert(
			@RequestBody @Validated(UpdateGroup.class) CommonListVO<EstimateVO> obj
			,Errors errors
			) {
		// 검증 시행 조건문
		hubWebValidateUtil valid = new hubWebValidateUtilImpl(obj, errors, context);
		if (!valid.checkError()) {
			valid.setResultMap(service.createORupdateEstimate(obj.getCommonList()));
		}
		return valid.getResultMap();
	} // 견적서 끝
	
	// 견적서 제품 등록
	@ResponseBody
	@RequestMapping(value="/sales/EstimateProdInsert.do", method=RequestMethod.POST, produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public Map<String, Object> EstimateProdInsert(
			@RequestBody @Validated(UpdateGroup.class) CommonListVO<EstimateProdVO> obj,
			Errors errors
			) {
			// 제품 등록
		hubWebValidateUtil valid = new hubWebValidateUtilImpl(obj, errors, context);
		if (!valid.checkError()) {
			valid.setResultMap(service.createRaws(obj.getCommonList()));
		}
		return valid.getResultMap();
	} // 견적서 제품 등록 끝
}
