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
@Controller
public class InOutAdjustmentInsertController {
	@Inject
	private WebApplicationContext context;
	@Inject
	private InOutAdjustmentService service;
	
	@RequestMapping(value="/stock/inOutAdjustmentInsert.do"
					,produces=MediaType.APPLICATION_JSON_UTF8_VALUE,method=RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> inOutInsert(
			@Validated(Default.class) @RequestBody CommonListVO<AdjustmentVO> obj
			,Errors errors
			) {
		hubWebValidateUtil valid = new hubWebValidateUtilImpl(obj, errors, context);
		if(!valid.checkError()) {//에러가 존재하지 않을 시(객체 검증 통과 시)
			valid.setResultMap(service.createOrUpdateInOutAdjustment(obj.getCommonList()));
		}
		return valid.getResultMap();
	}
	
	@RequestMapping(value="/stock/inOutItemInsert.do",produces=MediaType.APPLICATION_JSON_UTF8_VALUE
				,method=RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> ProductionRawsInstructionInsert(
			@Validated(Default.class) @RequestBody CommonListVO<AdjItemVO> obj
			,BindingResult errors
			) {
		hubWebValidateUtil valid = new hubWebValidateUtilImpl(obj,errors, context);
		if(!valid.checkError()) {
			for(AdjItemVO adjItemVO : obj.getCommonList()) {
				//VO에 icodeName의 값이 제품이면 -> itemCode 값을 prodCode에 넣어준다
				if(adjItemVO.getIcodeName().equals("제품")) {
					adjItemVO.setProdCode(adjItemVO.getItemCode());
				}else if(adjItemVO.getIcodeName().equals("상품")){
					adjItemVO.setProdCode(adjItemVO.getItemCode());
				}else {
					//VO에 icodeName의 값이 그 외 이면 -> itemCode 값을 rawsCode에 넣어준다
					adjItemVO.setRawsCode(adjItemVO.getItemCode());
				}
				
				//VO에 adjSort의 값이 입고이면 -> gcommName : gcommInname / ucommName : ucommInname
				if(adjItemVO.getAdjSort().equals("입고")) {
					adjItemVO.setGcommInname(adjItemVO.getGcommName());
					adjItemVO.setUcommInname(adjItemVO.getUcommName());
				}else {
					//VO에 adjSort의 값이 출고이면 -> gcommName : gcommOutname / ucommName : ucommOutname
					adjItemVO.setGcommOutname(adjItemVO.getGcommName());
					adjItemVO.setUcommOutname(adjItemVO.getUcommName());
				}
			}
			valid.setResultMap(service.createOrUpdateItem(obj.getCommonList()));
		}
		return valid.getResultMap();
	}
}
