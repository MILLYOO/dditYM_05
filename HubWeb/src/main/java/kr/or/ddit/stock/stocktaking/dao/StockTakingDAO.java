package kr.or.ddit.stock.stocktaking.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import kr.or.ddit.stock.vo.StockTakingVO;

@Repository
public interface StockTakingDAO {

	//재고실사 등록
	public int insertStockTaking(StockTakingVO stockTakingVO);
	
	//입출고 조정에서 확인하지 않은 재고실사를 조회
	public List<StockTakingVO> selectStockTakingYN();
	
	//재고실사 업데이트
	public int updateStockTaking(StockTakingVO stockTakingVO);
}
