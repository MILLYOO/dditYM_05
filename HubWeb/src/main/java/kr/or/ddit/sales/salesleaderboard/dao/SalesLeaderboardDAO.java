package kr.or.ddit.sales.salesleaderboard.dao;
/**
 * 매출순위표
 */

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import kr.or.ddit.common.vo.HubSearchVO;
import kr.or.ddit.sales.vo.SalesRankVO;

@Repository
public interface SalesLeaderboardDAO {
	
	/**
	 * 매출순위표 목록 조회 거래처
	 * @param hubSearchVO
	 * @return
	 */
	public List<SalesRankVO> selectSalesLeaderBoardListPC(@Param("hubSearchVO")HubSearchVO hubSearchVO);
	/**
	 * 매출순위표 목록 조회 부서
	 * @param hubSearchVO
	 * @return
	 */
	public List<SalesRankVO> selectSalesLeaderBoardListDP(@Param("hubSearchVO")HubSearchVO hubSearchVO);
	/**
	 * 매출순위표 목록 조회 사원
	 * @param hubSearchVO
	 * @return
	 */
	public List<SalesRankVO> selectSalesLeaderBoardListEMP(@Param("hubSearchVO")HubSearchVO hubSearchVO);
	
	/**
	 * 매출순위표 하위 조회
	 * @param salesRank
	 * @return
	 */
	public List<SalesRankVO> selectSalesLeaderBoardProdList(@Param("SalesRankVO")SalesRankVO salesRankVO);

}
