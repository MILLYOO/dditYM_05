package kr.or.ddit.produce.productincoming.controller;

import java.util.List;

import javax.inject.Inject;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.produce.productincoming.service.ProductIncomingService;
import kr.or.ddit.produce.vo.ProdWareVO;
import kr.or.ddit.produce.vo.PrwaProdVO;

/**
 * 생산품입고처리, 품목 삭제 컨트롤러
 */
@Controller
public class ProductIncomingDeleteController {
	@Inject
	private ProductIncomingService service;

	
	// 생산품입고처리 삭제
	@ResponseBody
	@RequestMapping(value="/produce/productIncomingDelete.do",method=RequestMethod.POST,produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public String productIncomingDelete(
			@RequestBody List<ProdWareVO> prodWareVO
			) {
		String resultMessage = null;
		
		ServiceResult result = service.deleteProductIncoming(prodWareVO);
		switch(result) {
		case OK:
			resultMessage = "성공";
			break;
		default:
			resultMessage = "서버오류";
			break;
		}
		return resultMessage;
	}
	
	
	// 생산품입고처리-품목 삭제
	@ResponseBody
	@RequestMapping(value="/produce/productIncomingRawsDelete.do",method=RequestMethod.POST,produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public String productIncomingRawsDelete(
			@RequestBody List<PrwaProdVO> prwaProdVO
			) {
		String resultMessage = null;
		
		ServiceResult result = service.deleteIncomingProduct(prwaProdVO);
		switch(result) {
		case OK:
			resultMessage = "성공";
			break;
		default:
			resultMessage = "서버오류";
			break;
		}
		return resultMessage;
	}
	
}
