package kr.or.ddit.stock.stocktaking.service;

import java.util.List;

import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.stock.vo.StockTakingVO;

public interface StockTakingService {
	//재고실사 조회
	public List<StockTakingVO> retrieveStockTaking();
	
	//재고실사 등록
	public ServiceResult createStockTaking(StockTakingVO stockTakingVO);
	
	//재고실사 확인여부 Y 로 업데이트
	public ServiceResult updateStockTaking(List<StockTakingVO> stockTakingList);
}
