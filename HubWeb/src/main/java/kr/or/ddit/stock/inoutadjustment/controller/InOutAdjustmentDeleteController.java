package kr.or.ddit.stock.inoutadjustment.controller;

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

import kr.or.ddit.common.vo.CommonListVO;
import kr.or.ddit.stock.inoutadjustment.service.InOutAdjustmentService;
import kr.or.ddit.stock.vo.AdjItemVO;
import kr.or.ddit.stock.vo.AdjustmentVO;
import kr.or.ddit.validate.util.hubWebValidateUtil;
import kr.or.ddit.validate.util.hubWebValidateUtilImpl;
import lombok.extern.slf4j.Slf4j;

/**
 * 입출고조정
 */
@Slf4j
@Controller
public class InOutAdjustmentDeleteController {
	@Inject
	private WebApplicationContext context;
	@Inject
	private InOutAdjustmentService service;
	
	//입출고조정 삭제
	@RequestMapping(value="/stock/inOutAdjustmentDelete.do",produces=MediaType.APPLICATION_JSON_UTF8_VALUE,method=RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> inOutAdjustmentDelete(
			@Validated(Default.class) @RequestBody CommonListVO<AdjustmentVO> obj
			,Errors errors
			) {
		
		hubWebValidateUtil valid = new hubWebValidateUtilImpl(obj, errors, context);
		if(!valid.checkError()) {
			valid.setResultMap(service.deleteInOutAdjustment(obj.getCommonList()));
		}
		return valid.getResultMap();
	}
	
	//입출고조정-품목 삭제
	@RequestMapping(value="/stock/inOutItemDelete.do",produces=MediaType.APPLICATION_JSON_UTF8_VALUE,method=RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> inOutItemDelete(
			@Validated(Default.class) @RequestBody CommonListVO<AdjItemVO> obj
			,BindingResult errors
			) {
		
		hubWebValidateUtil valid = new hubWebValidateUtilImpl(obj, errors, context);
		if(!valid.checkError()) {
			valid.setResultMap(service.deleteItem(obj.getCommonList()));
		}
		return valid.getResultMap();
	}
}
