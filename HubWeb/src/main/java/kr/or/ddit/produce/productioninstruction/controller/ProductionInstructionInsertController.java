package kr.or.ddit.produce.productioninstruction.controller;

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
import kr.or.ddit.produce.productioninstruction.service.ProductionInstructionService;
import kr.or.ddit.produce.vo.InstRawsVO;
import kr.or.ddit.produce.vo.InstrucprodVO;
import kr.or.ddit.validate.util.hubWebValidateUtil;
import kr.or.ddit.validate.util.hubWebValidateUtilImpl;

/**
 * 생산지시서 등록 및 수정
 */
@Controller
public class ProductionInstructionInsertController {
	@Inject
	private WebApplicationContext context;	

	@Inject
	private ProductionInstructionService service;
	
	// 생산지시서 등록 및 수정
	@RequestMapping(value="/produce/productionInstructionInsert.do",produces=MediaType.APPLICATION_JSON_UTF8_VALUE,method=RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> ProductionInstructionInsert(
			@Validated(Default.class) @RequestBody CommonListVO<InstrucprodVO> obj
			,Errors errors
			) {
		hubWebValidateUtil valid = new hubWebValidateUtilImpl(obj, errors, context);
		if(!valid.checkError()) {
			valid.setResultMap(service.createOrUpdateProductionInstruction(obj.getCommonList()));
		}
		return valid.getResultMap();
	}
	
	// 생산지시서-원자재 등록 및 수정
	@RequestMapping(value="/produce/productionRawsInsert.do",produces=MediaType.APPLICATION_JSON_UTF8_VALUE,method=RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> ProductionRawsInstructionInsert(
			@Validated(Default.class) @RequestBody CommonListVO<InstRawsVO> obj
			,Errors errors
			) {
		hubWebValidateUtil valid = new hubWebValidateUtilImpl(obj, errors, context);
		if(!valid.checkError()) {
			valid.setResultMap(service.createOrUpdateProduct(obj.getCommonList()));
		}
		return valid.getResultMap();
	}
}
