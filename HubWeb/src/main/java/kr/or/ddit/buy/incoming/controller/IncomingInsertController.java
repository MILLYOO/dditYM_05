package kr.or.ddit.buy.incoming.controller;

import java.util.Map;

import javax.inject.Inject;
import javax.validation.groups.Default;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.WebApplicationContext;

import kr.or.ddit.buy.incoming.service.IncomingService;
import kr.or.ddit.buy.vo.IncomingRawsVO;
import kr.or.ddit.buy.vo.IncomingVO;
import kr.or.ddit.common.vo.CommonListVO;
import kr.or.ddit.validate.util.hubWebValidateUtil;
import kr.or.ddit.validate.util.hubWebValidateUtilImpl;
/**
 * 입고처리서 등록
 */
@Controller
public class IncomingInsertController {
	@Inject
	private WebApplicationContext context;
	@Inject
	private IncomingService service;
	
	// 입고처리서 등록 및 수정
	@ResponseBody
	@RequestMapping(value="/buy/incomingInsert.do", produces=MediaType.APPLICATION_JSON_UTF8_VALUE,method=RequestMethod.POST)
	public Map<String, Object> IncomingInsert(
			@Validated(Default.class) @RequestBody CommonListVO<IncomingVO> obj
			,Errors errors
			) {
		
		hubWebValidateUtil valid = new hubWebValidateUtilImpl(obj, errors, context);
		if(!valid.checkError()) {
			valid.setResultMap(service.createOrModifyIncoming(obj.getCommonList()));
		}
		return valid.getResultMap();
	}
	
	// 입고처리서-원자재 등록 및 수정
	@ResponseBody
	@RequestMapping(value="/buy/incomingRawsInsert.do",produces=MediaType.APPLICATION_JSON_UTF8_VALUE,method=RequestMethod.POST)
	public Map<String, Object> IncomingRawsInsert(
			@Validated(Default.class) @RequestBody CommonListVO<IncomingRawsVO> obj
			,BindingResult errors
			) {
		
		hubWebValidateUtil valid = new hubWebValidateUtilImpl(obj, errors, context);
		if(!valid.checkError()) {
			valid.setResultMap( service.createOrModifyRaws(obj.getCommonList()));
		}
		return valid.getResultMap();
	}
	
}
