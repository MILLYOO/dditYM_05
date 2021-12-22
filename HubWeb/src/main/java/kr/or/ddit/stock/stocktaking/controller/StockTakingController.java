package kr.or.ddit.stock.stocktaking.controller;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.inject.Inject;
import javax.validation.groups.Default;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.WebApplicationContext;

import kr.or.ddit.common.vo.CommonListVO;
import kr.or.ddit.common.vo.HubSearchVO;
import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.stock.stockstatusbywarehouse.service.StockStatusByWarehouseService;
import kr.or.ddit.stock.stocktaking.service.StockTakingService;
import kr.or.ddit.stock.vo.AdjustmentVO;
import kr.or.ddit.stock.vo.StockTakingVO;
import kr.or.ddit.stock.vo.WarItemVO;
import kr.or.ddit.validate.groups.DeleteGroup;
import kr.or.ddit.validate.groups.UpdateGroup;
import kr.or.ddit.validate.util.hubWebValidateUtil;
import kr.or.ddit.validate.util.hubWebValidateUtilImpl;

@Controller
public class StockTakingController {
	@Resource(name="stockStatusByWarehouseServiceImpl")
	private StockStatusByWarehouseService service;
	@Resource(name="stockTakingServiceImpl")
	private StockTakingService stockTakingService;
	@Inject
	private WebApplicationContext context;
	
	//1.재고 실사를 위한 페이지로 이동
	@RequestMapping(value="/stock/stockTakingIndex.do",method=RequestMethod.GET)
	public String stockTakingIndex() {
		return "stock/stockTakingIndex";
	}
	
	//요청을 받아 품목, 창고에 대한 데이터를 가공하고 view를 만든다.
	@RequestMapping(value="/stock/stockTaking.do", method=RequestMethod.POST)
	public String stockTakingView(
			Model model, 
			@ModelAttribute HubSearchVO hubSearchVO){
		
		WarItemVO stockTakingwarItem = service.stockTakingWarItem(hubSearchVO);
		model.addAttribute("stockTakingwarItem",stockTakingwarItem);
		return "stock/stockTaking";
	}
	
	//view에서 요청받은 내용을 DB에 저장, Session에 등록한다
	@ResponseBody
	@RequestMapping(value="/stock/stockTakingSave.do", method=RequestMethod.POST, produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public Map<String, Object> stockTakingForm(
			@Validated(UpdateGroup.class) @ModelAttribute("stockTakingVO") StockTakingVO stockTakingVO
			,Errors errors
			) {
		//받아온 정보(창고명, 품목명, 실사수량)를 테이블에 insert 한다
		hubWebValidateUtil valid = new hubWebValidateUtilImpl(errors, context);
		if(!valid.checkError()) {
			valid.setResultMap(stockTakingService.createStockTaking(stockTakingVO));
		}
		return valid.getResultMap();
	}
	
	//요청리스트를 불러온다
	@ResponseBody
	@RequestMapping(value="/stock/stockTakingRetrieve.do", method=RequestMethod.POST, produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public List<StockTakingVO> stockTakingRetrieve() {
		//확인하지 않은 재고실사 내역이 있는지 확인한다. 있으면 세션에 값을 넣는다.
		List<StockTakingVO> stockTakingNList = stockTakingService.retrieveStockTaking();
		return stockTakingNList;
	}	
	
	//요청리스트 확인 시 확인여부를 Y로 변경한다.
	@ResponseBody
	@RequestMapping(value="/stock/stockTakingYNUpdate.do", method=RequestMethod.POST, produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public Map<String, Object> stockTakingYNUpdate(
			@Validated(DeleteGroup.class) @RequestBody CommonListVO<StockTakingVO> obj
			,Errors errors
			) {
		
		hubWebValidateUtil valid = new hubWebValidateUtilImpl(obj, errors, context);
		if(!valid.checkError()) {//에러가 존재하지 않을 시(객체 검증 통과 시)
			valid.setResultMap(stockTakingService.updateStockTaking(obj.getCommonList()));
		}
		return valid.getResultMap();
		
	}
}
