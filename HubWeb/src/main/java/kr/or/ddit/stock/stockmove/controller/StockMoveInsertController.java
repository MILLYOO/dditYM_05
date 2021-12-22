package kr.or.ddit.stock.stockmove.controller;

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
import kr.or.ddit.stock.stockmove.service.StockMoveService;
import kr.or.ddit.stock.vo.AdjItemVO;
import kr.or.ddit.stock.vo.StockMoveVO;
import kr.or.ddit.stock.vo.StomovItemVO;
import kr.or.ddit.validate.util.hubWebValidateUtil;
import kr.or.ddit.validate.util.hubWebValidateUtilImpl;
import lombok.extern.slf4j.Slf4j;

/**
 * 재고이동
 */
@Slf4j
@Controller
public class StockMoveInsertController {
	@Inject
	private StockMoveService service;
	@Inject
	private WebApplicationContext context;
	
	@ResponseBody
	@RequestMapping(value="/stock/stockMoveInsert.do", produces=MediaType.APPLICATION_JSON_UTF8_VALUE,method=RequestMethod.POST)
	public Map<String, Object> stockMoveInsert(
			@Validated(Default.class) @RequestBody CommonListVO<StockMoveVO> obj
			,Errors errors
			) {
		
		hubWebValidateUtil valid = new hubWebValidateUtilImpl(obj, errors, context);
		if(!valid.checkError()) {
			valid.setResultMap( service.createStockMove(obj.getCommonList()));
		}
		return valid.getResultMap();
	}
	
	
	@ResponseBody
	@RequestMapping(value="/stock/stockMoveItemInsert.do",produces=MediaType.APPLICATION_JSON_UTF8_VALUE,method=RequestMethod.POST)
	public Map<String, Object> stockMoveItemInsert(
			@Validated(Default.class) @RequestBody CommonListVO<StomovItemVO> obj
			,BindingResult errors
			) {

		hubWebValidateUtil valid = new hubWebValidateUtilImpl(obj, errors, context);
		if(!valid.checkError()) {
			for(StomovItemVO stomovItemVO : obj.getCommonList()) {
				//VO에 icodeName의 값이 제품이면 -> itemCode 값을 prodCode에 넣어준다
				if(stomovItemVO.getIcodeName().equals("제품")) {
					stomovItemVO.setProdCode(stomovItemVO.getItemCode());
				}else if(stomovItemVO.getIcodeName().equals("상품")){
					stomovItemVO.setProdCode(stomovItemVO.getItemCode());
				}else {
					//VO에 icodeName의 값이 그 외 이면 -> itemCode 값을 rawsCode에 넣어준다
					stomovItemVO.setRawsCode(stomovItemVO.getItemCode());
				}
			}
			valid.setResultMap( service.createStockMoveItem(obj.getCommonList()));
		}
		return valid.getResultMap();
	}
	
}














