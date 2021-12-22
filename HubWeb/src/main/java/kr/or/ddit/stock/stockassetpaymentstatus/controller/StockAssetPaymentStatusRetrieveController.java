package kr.or.ddit.stock.stockassetpaymentstatus.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.or.ddit.common.vo.HubSearchVO;
import kr.or.ddit.info2.itemmanage.service.ItemManageService;
import kr.or.ddit.info2.vo.ItemVO;
import kr.or.ddit.stock.stockassetpaymentstatus.service.StockAssetPaymentStatusService;
import kr.or.ddit.stock.vo.StockAssetVO;

/**
 * 재고자산사불부현황
 */
@Controller
public class StockAssetPaymentStatusRetrieveController {
	@Resource(name="stockAssetPaymentStatusServiceImpl")
	private StockAssetPaymentStatusService serviceAsset;
	@Resource(name="itemManageServiceImpl")
	private ItemManageService serviceItem;
	
	@RequestMapping(value="/stock/stockAssetPaymentStatusRetrieve.do", method=RequestMethod.GET)
	public String stockAssetPaymentStatusRetrieve() {
		
		String viewName = "stock/statusStockAsset";
		return viewName;
	}
	
	@ResponseBody
	@RequestMapping(value="/stock/stockAssetPaymentStatusRetrieve.do", method=RequestMethod.POST, produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public List<ItemVO> stockAssetPaymentStatusRetrieve(
			@ModelAttribute("hubSearch") HubSearchVO hubSearchVO
			) {
		List<ItemVO> itemList = serviceItem.retrieveItemList(hubSearchVO);
		return itemList;
	}
	
	@ResponseBody
	@RequestMapping(value="/stock/stockAssetList.do", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public List<StockAssetVO> stockAssetList(
			@ModelAttribute("hubSearch") HubSearchVO hubSearchVO
			) {
		List<StockAssetVO> stockAssetList = serviceAsset.retrieveStockAssetList(hubSearchVO);
		return stockAssetList;
	}
}








