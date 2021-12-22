package kr.or.ddit.stock.stockmove.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import kr.or.ddit.common.vo.HubSearchVO;
import kr.or.ddit.stock.vo.StockMoveVO;
import kr.or.ddit.stock.vo.StomovItemVO;

/**
 * 재고이동
 */
@Repository
public interface StockMoveDAO {

	//재고이동서 조회
	public List<StockMoveVO> selectStockMoveList(HubSearchVO hubSearch);
	
	//재고이동서 등록
	public int insertStockMove(StockMoveVO stockMoveVO);
	
	//재고이동서 수정
	public int updateStockMove(StockMoveVO stockMoveVO);
	
	//재고이동서 삭제
	public int deleteStockMove(StockMoveVO stockMoveVO);
	
	//재고이동서 상세조회
	public List<StomovItemVO> selectStockMoveItemList(StomovItemVO stomovItemVO);
	
	//재고이동서 삭제시 재고이동서 품목 모두 지우기
	public int deleteAllStockMoveItem(String stmNum);
	
	//재고이동서 상세 작성
	public int insertStockMoveItem(StomovItemVO stomovItemVO);
	
	//재고이동서 상세 수정
	public int updateStockMoveItem(StomovItemVO stomovItemVO);
	
	//재고이동서 상세 삭제
	public int deleteStockMoveItem(StomovItemVO stomovItemVO);
	
	//원자재-입고 창고 품목 수 증가
	public int updateInRaws(StomovItemVO stomovItemVO);
	//원자재-출고 창고 품목 수 감소
	public int updateOutRaws(StomovItemVO stomovItemVO);
	//제품-입고 창고 품목 수 증가
	public int updateInProd(StomovItemVO stomovItemVO);
	//제품-출고 창고 품목 수 감소
	public int updateOutProd(StomovItemVO stomovItemVO);
	
	//삭제시 되돌리기(Rev)
	//원자재-입고 창고 품목 수 증가
	public int updateInRawsRev(StomovItemVO stomovItemVO);
	//원자재-출고 창고 품목 수 감소
	public int updateOutRawsRev(StomovItemVO stomovItemVO);
	//제품-입고 창고 품목 수 증가
	public int updateInProdRev(StomovItemVO stomovItemVO);
	//제품-출고 창고 품목 수 감소
	public int updateOutProdRev(StomovItemVO stomovItemVO);
	
	//문서 체크 테이블 insert
	public int insertCheck(StockMoveVO stockMoveVO);
}



