package kr.or.ddit.stock.stockassetpaymentstatus.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import kr.or.ddit.common.vo.HubSearchVO;
import kr.or.ddit.stock.vo.StockAssetVO;

/**
 * 재고자산사불부현황
 */
@Repository
public interface StockAssetPaymentStatusDAO {

	public List<StockAssetVO> selectStockAssest(HubSearchVO hubSearchVO);
}
