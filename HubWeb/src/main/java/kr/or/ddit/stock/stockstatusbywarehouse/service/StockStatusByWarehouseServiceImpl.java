package kr.or.ddit.stock.stockstatusbywarehouse.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import kr.or.ddit.common.vo.HubSearchVO;
import kr.or.ddit.stock.stockstatusbywarehouse.dao.StockStatusByWarehouseDAO;
import kr.or.ddit.stock.vo.WarItemVO;

@Service
public class StockStatusByWarehouseServiceImpl implements StockStatusByWarehouseService {

	@Inject
	private StockStatusByWarehouseDAO dao;
	
	@Override
	public List<WarItemVO> warList(HubSearchVO hubSearchVO) {
		List<WarItemVO> warList = dao.selectWarList(hubSearchVO);
		return warList;
	}

	@Override
	public List<WarItemVO> warItemList(HubSearchVO hubSearchVO) {
		List<WarItemVO> warItemList = dao.selectWarItemList(hubSearchVO);
		return warItemList;
	}

	@Override
	public WarItemVO stockTakingWarItem(HubSearchVO hubSearchVO) {
		WarItemVO stockTakingWarItem = dao.selectStockTakingWarItem(hubSearchVO);
		return stockTakingWarItem;
	}

}
