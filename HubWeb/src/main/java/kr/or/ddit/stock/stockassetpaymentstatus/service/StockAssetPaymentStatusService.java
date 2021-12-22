package kr.or.ddit.stock.stockassetpaymentstatus.service;

import java.util.List;

import kr.or.ddit.common.vo.HubSearchVO;
import kr.or.ddit.stock.vo.StockAssetVO;

/**
 * 재고자산사불부현황
 */
public interface StockAssetPaymentStatusService {

	//현황 리스트 가져오기
	public List<StockAssetVO> retrieveStockAssetList(HubSearchVO hubSearchVO);
	
}
