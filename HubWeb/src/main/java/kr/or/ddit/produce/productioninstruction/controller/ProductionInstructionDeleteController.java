package kr.or.ddit.produce.productioninstruction.controller;

import java.util.List;

import javax.inject.Inject;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.produce.productioninstruction.service.ProductionInstructionService;
import kr.or.ddit.produce.vo.InstRawsVO;
import kr.or.ddit.produce.vo.InstrucprodVO;

/**
 * 생산지시서 , 생산지시서-원자재 삭제 컨트롤러
 */
@Controller
public class ProductionInstructionDeleteController {
	@Inject
	private ProductionInstructionService service;
	
	// 생산지시서 삭제
	@RequestMapping(value="/produce/productionInstructionDelete.do",produces=MediaType.APPLICATION_JSON_UTF8_VALUE,method=RequestMethod.POST)
	@ResponseBody
	public String ProductionInstructionDelete(
			@RequestBody List<InstrucprodVO> instrucProdVO
			) {
		String resultMessage = null;
		
		ServiceResult result = service.deleteProductionInstruction(instrucProdVO);
		switch(result) {
		case OK:
			resultMessage = "삭제되었습니다.";
			break;
		default:
			resultMessage = "서버오류";
			break;
		}
		return resultMessage;
	}
	
	// 생산지시서-원자재 삭제
	@RequestMapping(value="/produce/productionRawsDelete.do",produces=MediaType.APPLICATION_JSON_UTF8_VALUE,method=RequestMethod.POST)
	@ResponseBody
	public String ProductionRawsInstructionDelete(
			@RequestBody List<InstRawsVO> instRaws
			) {
		String resultMessage = null;
		
		ServiceResult result = service.deleteProduct(instRaws);
		switch(result) {
		case OK:
			resultMessage = "삭제되었습니다.";
			break;
		case PKDUPLICATED:
			resultMessage = "존재하지 않는 원자재 입니다.";
			break;
		default:
			resultMessage = "서버오류";
			break;
		}
		return resultMessage;
	}
	
}
