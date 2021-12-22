package kr.or.ddit.buy.purchaseleaderboard.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import kr.or.ddit.buy.vo.CloseRawsVO;
import kr.or.ddit.buy.vo.PurchCloseVO;
import kr.or.ddit.common.vo.HubSearchVO;

/**
 * 매입순위표 DAO
 */
@Repository
public interface PurchaseLeaderboardDAO {

	/**
	 * (거래처별)매입순위표 목록 조회
	 * @param hubSearchVO
	 * @return
	 */
	public List<PurchCloseVO> selectPurchLeaderBoardListPC(HubSearchVO hubSearch);
	
	/**
	 * (거래처별)매입순위표 상세 확인
	 * @param incoming
	 * @return
	 */
	public PurchCloseVO selectPurchLeaderBoardPC(PurchCloseVO purchClose);
	
	/**
	 * (거래처별)매입순위표 원자재 목록 조회
	 * @param incomingRawsVO
	 * @return
	 */
	public List<CloseRawsVO> selectPurchLeaderBoardRawsListPC(PurchCloseVO purchCloseVO);
	
	/**
	 * (거래처별)매입순위표 원자재 상세 확인
	 * @param incomingRaws
	 * @return
	 */
	public CloseRawsVO selectPurchLeaderBoardRawsPC(CloseRawsVO closeRawsVO);
	
	
	
	/**
	 * (부서별)매입순위표 목록 조회
	 * @param hubSearchVO
	 * @return
	 */
	public List<PurchCloseVO> selectPurchLeaderBoardListDP(HubSearchVO hubSearchVO);
	
	/**
	 * (부서별)매입순위표 상세 확인
	 * @param incoming
	 * @return
	 */
	public PurchCloseVO selectPurchLeaderBoardDP(PurchCloseVO purchClose);
	
	/**
	 * (부서별)매입순위표 원자재 목록 조회
	 * @param incomingRawsVO
	 * @return
	 */
	public List<CloseRawsVO> selectPurchLeaderBoardRawsListDP(PurchCloseVO purchCloseVO);
	
	/**
	 * (부서별)매입순위표 원자재 상세 확인
	 * @param incomingRaws
	 * @return
	 */
	public CloseRawsVO selectPurchLeaderBoardRawsDP(CloseRawsVO closeRawsVO);
	
	
	
	/**
	 * (사원별)매입순위표 목록 조회
	 * @param hubSearchVO
	 * @return
	 */
	public List<PurchCloseVO> selectPurchLeaderBoardListEMP(HubSearchVO hubSearchVO);
	
	/**
	 * (사원별)매입순위표 상세 확인
	 * @param incoming
	 * @return
	 */
	public PurchCloseVO selectPurchLeaderBoardEMP(PurchCloseVO purchClose);
	
	/**
	 * (사원별)매입순위표 원자재 목록 조회
	 * @param incomingRawsVO
	 * @return
	 */
	public List<CloseRawsVO> selectPurchLeaderBoardRawsListEMP(PurchCloseVO purchCloseVO);
	
	/**
	 * (시원별)매입순위표 원자재 상세 확인
	 * @param incomingRaws
	 * @return
	 */
	public CloseRawsVO selectPurchLeaderBoardRawsEMP(CloseRawsVO closeRawsVO);
	
	
	
	
}
