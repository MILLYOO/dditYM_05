package kr.or.ddit.produce.merterialrelease.controller;

import java.util.Map;

import javax.inject.Inject;
import javax.validation.groups.Default;

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
import kr.or.ddit.produce.merterialrelease.service.MaterialReleaseService;
import kr.or.ddit.produce.vo.MreleaseRawsVO;
import kr.or.ddit.produce.vo.MreleaseVO;
import kr.or.ddit.validate.util.hubWebValidateUtil;
import kr.or.ddit.validate.util.hubWebValidateUtilImpl;

/**
 * 자재출고처리 등록 및 수정
 */
@Controller
public class MaterialReleaseInsertController {
	@Inject
	private WebApplicationContext context;	
	
	@Inject
	private MaterialReleaseService service;
	
	// 자재출고처리서 등록 및 수정
	@ResponseBody
	@RequestMapping(value="/produce/materialReleaseInsert.do",method=RequestMethod.POST,produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public Map<String, Object> MaterialReleaseInsert(
			@Validated(Default.class) @RequestBody CommonListVO<MreleaseVO> obj
			,Errors errors
			) {
		hubWebValidateUtil valid = new hubWebValidateUtilImpl(obj, errors, context);
		if(!valid.checkError()) {
			valid.setResultMap(service.createOrUpdateMaterialRelease(obj.getCommonList()));
		}
		return valid.getResultMap();
	}
	
	// 자재출고처리서-원자재 등록 및 수정
	@ResponseBody
	@RequestMapping(value="/produce/materialReleaseRawsInsert.do",method=RequestMethod.POST,produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public Map<String, Object> MaterialReleaseRawsInsert(
			@Validated(Default.class) @RequestBody CommonListVO<MreleaseRawsVO> obj
			,Errors errors
			) {
		hubWebValidateUtil valid = new hubWebValidateUtilImpl(obj, errors, context);
		if(!valid.checkError()) {
			valid.setResultMap(service.createOrUpdateRaws(obj.getCommonList()));
		}
		return valid.getResultMap();
	}
	
}