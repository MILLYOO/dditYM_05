package kr.or.ddit.sales.salesleaderboard.service;

import java.util.List;

import kr.or.ddit.common.vo.HubSearchVO;
import kr.or.ddit.sales.vo.SalesRankVO;

/**
 * 매출순위표
 */
public interface SalesLeaderboardService {
	
	/**
	 * 매출순위표 마스터 목록 조회 거래처
	 * @param hubSearchVO
	 * @return
	 */
	public List<SalesRankVO> retrieveSalesLeaderboardListPC(HubSearchVO hubSearchVO);
	/**
	 * 매출순위표 마스터 목록 조회 부서
	 * @param hubSearchVO
	 * @return
	 */
	public List<SalesRankVO> retrieveSalesLeaderboardListDP(HubSearchVO hubSearchVO);
	/**
	 * 매출순위표 마스터 목록 조회 사원
	 * @param hubSearchVO
	 * @return
	 */
	public List<SalesRankVO> retrieveSalesLeaderboardListEMP(HubSearchVO hubSearchVO);
	
	/**
	 * 매출순위표 디테일 목록 조회
	 * @param salesRank
	 * @return
	 */
	public List<SalesRankVO> retrieveSalesLeaderboardProdList(SalesRankVO salesRank);

}
