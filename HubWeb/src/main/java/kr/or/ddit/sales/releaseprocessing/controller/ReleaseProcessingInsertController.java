package kr.or.ddit.sales.releaseprocessing.controller;

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
import kr.or.ddit.sales.releaseprocessing.service.ReleaseProcessingService;
import kr.or.ddit.sales.vo.RelProdVO;
import kr.or.ddit.sales.vo.ReleaseVO;
import kr.or.ddit.validate.groups.UpdateGroup;
import kr.or.ddit.validate.util.hubWebValidateUtil;
import kr.or.ddit.validate.util.hubWebValidateUtilImpl;

/**
 * 출고처리
 */
@Controller
public class ReleaseProcessingInsertController {
	@Inject
	private ReleaseProcessingService service;
	@Inject
	private WebApplicationContext context;
	
	// 출고처리서 등록
	@RequestMapping(value="/sales/ReleaseProcessingInsert.do", method=RequestMethod.POST, produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public Map<String, Object> ReleaseProcessingInsert(
			@RequestBody @Validated(UpdateGroup.class) CommonListVO<ReleaseVO> obj, 
			Errors errors
			) {
		hubWebValidateUtil valid = new hubWebValidateUtilImpl(obj, errors, context); // 검증조건에서 에러가 발생하면
		if (!valid.checkError()) {
			valid.setResultMap(service.createORupdateReleaseProcessing(obj.getCommonList()));
		}
		return valid.getResultMap();
	}
	
	// 출고처리서 상세 품목 등록
	@RequestMapping(value="/sales/ReleaseProcessingProdInsert.do", method=RequestMethod.POST, produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public Map<String, Object> ReleaseProcessingProdInsert(
			@RequestBody @Validated(UpdateGroup.class) CommonListVO<RelProdVO> obj,
			Errors errors
			){
		hubWebValidateUtil valid = new hubWebValidateUtilImpl(obj, errors, context);
		if (!valid.checkError()) {
			valid.setResultMap(service.createRaws(obj.getCommonList()));
		}
		return valid.getResultMap();
	}
}
