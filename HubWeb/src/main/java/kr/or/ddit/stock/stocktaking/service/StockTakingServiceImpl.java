package kr.or.ddit.stock.stocktaking.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.stock.stocktaking.dao.StockTakingDAO;
import kr.or.ddit.stock.vo.StockTakingVO;

@Service
public class StockTakingServiceImpl implements StockTakingService{

	@Inject 
	private StockTakingDAO dao;

	@Override
	public List<StockTakingVO> retrieveStockTaking() {
		List<StockTakingVO> stockTakingYNList = dao.selectStockTakingYN();
		return stockTakingYNList;
	}

	@Override
	public ServiceResult createStockTaking(StockTakingVO stockTakingVO) {
		ServiceResult result = null;
		
		int cnt = dao.insertStockTaking(stockTakingVO);
		if(cnt > 0) {
			result = ServiceResult.OK;
		}else {
			result = ServiceResult.FAILED;
		}
		
		return result;
	}

	//재고실사 확인여부 Y 로 업데이트
	//다중 선택이 가능하므로 트랜잭션 관리가 필요하다
	@Transactional
	@Override
	public ServiceResult updateStockTaking(List<StockTakingVO> stockTakingList) {
		ServiceResult result = null;
		int successCnt = 0;
		for(StockTakingVO StockTakingVO : stockTakingList) {
			int cnt = dao.updateStockTaking(StockTakingVO);
			if(cnt > 0) {
				successCnt ++;
			}
		}
		if(successCnt == stockTakingList.size()) {
			result = ServiceResult.OK;
		}else {
			result = ServiceResult.FAILED;
		}
		return result;
	}
	
	
}
