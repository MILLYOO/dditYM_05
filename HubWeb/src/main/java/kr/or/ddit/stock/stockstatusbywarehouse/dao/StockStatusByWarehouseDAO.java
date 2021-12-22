package kr.or.ddit.stock.stockstatusbywarehouse.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import kr.or.ddit.common.vo.HubSearchVO;
import kr.or.ddit.stock.vo.WarItemVO;

/**
 * 창고별재고현황
 */
@Repository
public interface StockStatusByWarehouseDAO {

	public List<WarItemVO> selectWarList(HubSearchVO hubSearchVO);

	public List<WarItemVO> selectWarItemList(HubSearchVO hubSearchVO);
	
	public WarItemVO selectStockTakingWarItem(HubSearchVO hubSearchVO);
}
