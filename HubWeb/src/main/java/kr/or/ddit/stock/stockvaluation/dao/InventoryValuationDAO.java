package kr.or.ddit.stock.stockvaluation.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import kr.or.ddit.common.vo.HubSearchVO;
import kr.or.ddit.stock.vo.StoValuVO;
import kr.or.ddit.stock.vo.StovalItemVO;

/**
 * 재고평가
 */
@Repository
public interface InventoryValuationDAO {

	//재고평가 목록 리스트 조회
	public List<StoValuVO> selectValuationList(HubSearchVO hunSearchVO);
	
	//재고평가 리스트(결과) 조회 
	public List<StovalItemVO> selectValuationItemList(StovalItemVO stovalItemVO);
	
	//재고평가 하기(insert)
	public int insertValuation(StoValuVO stoValuVO);
	//재고평과결과 조회해오기
	public List<StovalItemVO> selectValuationResult(StoValuVO stoValuVO);
	//조회된 결과 insert하기
	public int insertValuationResult(StovalItemVO stovalItemVO);
	//최신문서 가져오기
	public StoValuVO selectRecentDoc();
	
	//재고평가 삭제
	public int deleteInventoryValuation(StoValuVO stoValuVO);
	
	
	//문서 체크 테이블 insert
	public int insertCheck(StoValuVO stoValuVO);
}
