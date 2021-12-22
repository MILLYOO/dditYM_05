package kr.or.ddit.info2.bommanage.controller;

import java.util.Map;

import javax.inject.Inject;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.WebApplicationContext;

import kr.or.ddit.common.vo.CommonListVO;
import kr.or.ddit.info2.bommanage.service.BomManageService;
import kr.or.ddit.info2.vo.BomRawsVO;
import kr.or.ddit.info2.vo.BomVO;
import kr.or.ddit.validate.groups.DeleteGroup;
import kr.or.ddit.validate.util.hubWebValidateUtil;
import kr.or.ddit.validate.util.hubWebValidateUtilImpl;

/**
 * BOM 관리
 */
@Controller
public class BomManageDeleteController {
	@Inject
	private BomManageService service;
	@Inject
	private WebApplicationContext context;
	
	@PostMapping(value="/info2/bomDelete.do", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public Map<String, Object> BomManageDelete(
			@Validated(DeleteGroup.class) @ModelAttribute BomVO bom,
			Errors errors
			) {
		hubWebValidateUtil valid = new hubWebValidateUtilImpl(errors, context);
		if(!valid.checkError()) {
			valid.setResultMap(service.deleteBom(bom));
		}
		return valid.getResultMap();
	}
	
	
	@PostMapping(value="/info2/bomRawDelete.do", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public Map<String, Object> BomRawDelete(
			@Validated(DeleteGroup.class) @RequestBody CommonListVO<BomRawsVO> bomRaws,
			Errors errors
			){
		hubWebValidateUtil valid = new hubWebValidateUtilImpl(bomRaws, errors, context);
		if(!valid.checkError())
			valid.setResultMap(service.deleteRaws(bomRaws));
		return valid.getResultMap();
	}
}
