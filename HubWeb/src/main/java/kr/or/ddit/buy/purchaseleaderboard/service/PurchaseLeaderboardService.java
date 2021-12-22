package kr.or.ddit.buy.purchaseleaderboard.service;
/**
 * 매입순위표 service
 */

import java.util.List;

import kr.or.ddit.buy.vo.CloseRawsVO;
import kr.or.ddit.buy.vo.PurchCloseVO;
import kr.or.ddit.common.vo.HubSearchVO;

public interface PurchaseLeaderboardService {

	/**********************************************************************/
	/*                             매입순위표                                                         / 
	/**********************************************************************/
	
	/**
	 * 거래처별 매입순위표 master 목록 조회
	 * @param hubSearch
	 * @return
	 */
	public List<PurchCloseVO> retrievePurchaseLeaderboardListPC(HubSearchVO hubSearch);
	
	/**
	 * 거래처별 매입순위표 master 상세 조회
	 * @param incoming
	 * @return
	 */
	public PurchCloseVO retrievePurchaseLeaderboardPC(PurchCloseVO purchClose);
	
	/**
	 * 부서별 매입순위표 master 목록 조회
	 * @param hubSearch
	 * @return
	 */
	public List<PurchCloseVO> retrievePurchaseLeaderboardListDP(HubSearchVO hubSearch);
	
	/**
	 * 부서별 매입순위표 master 상세 조회
	 * @param incoming
	 * @return
	 */
	public PurchCloseVO retrievePurchaseLeaderboardDP(PurchCloseVO purchClose);
	
	/**
	 * 사원별 매입순위표 master 목록 조회
	 * @param hubSearch
	 * @return
	 */
	public List<PurchCloseVO> retrievePurchaseLeaderboardListEMP(HubSearchVO hubSearch);
	
	/**
	 * 사원별 매입순위표 master 상세 조회
	 * @param incoming
	 * @return
	 */
	public PurchCloseVO retrievePurchaseLeaderboardEMP(PurchCloseVO purchClose);
	
	
	
	
	
	/**********************************************************************/
	/*                          매입순위표 품목                                                        / 
	/**********************************************************************/
	
	/**
	 * 거래처별 매입순위표 detail 목록 조회
	 * @param hubSearch
	 * @return
	 */
	public List<CloseRawsVO> retrievePurchaseLeaderboardRawListPC(PurchCloseVO purchCloseVO);
	
	/**
	 * 거래처별 매입순위표 detail 상세 조회
	 * @param incoming
	 * @return
	 */
	public CloseRawsVO retrievePurchaseLeaderboardRawPC(CloseRawsVO closeRawsVO);
	
	/**
	 * 부서별 매입순위표 detail 목록 조회
	 * @param hubSearch
	 * @return
	 */
	public List<CloseRawsVO> retrievePurchaseLeaderboardRawListDP(PurchCloseVO purchCloseVO);
	
	/**
	 * 부서별 매입순위표 detail 상세 조회
	 * @param incoming
	 * @return
	 */
	public CloseRawsVO retrievePurchaseLeaderboardRawDP(CloseRawsVO closeRawsVO);
	
	/**
	 * 사원별 매입순위표 detail 목록 조회
	 * @param hubSearch
	 * @return
	 */
	public List<CloseRawsVO> retrievePurchaseLeaderboardRawListEMP(PurchCloseVO purchCloseVO);
	
	/**
	 * 사원별 매입순위표 detail 상세 조회
	 * @param incoming
	 * @return
	 */
	public CloseRawsVO retrievePurchaseLeaderboardRawEMP(CloseRawsVO closeRawsVO);

}
