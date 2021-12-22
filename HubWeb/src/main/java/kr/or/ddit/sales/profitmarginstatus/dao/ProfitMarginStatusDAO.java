package kr.or.ddit.sales.profitmarginstatus.dao;
/**
 * 매출이익현황
 */

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import kr.or.ddit.common.vo.HubSearchVO;
import kr.or.ddit.sales.vo.ProfitVO;

@Repository
public interface ProfitMarginStatusDAO {
	
	/**
	 * 매출이익현황 목록 조회 거래처
	 */
	public List<ProfitVO> selectProfitListPC(@Param("hubSearchVO")HubSearchVO hubSearchVO);
	
	/**
	 * 매출이익현황 목록 조회 부서
	 * @param hubSearchVO
	 * @return
	 */
	public List<ProfitVO> selectProfitListDP(@Param("hubSearchVO")HubSearchVO hubSearchVO);
	
	/**
	 * 매출이익현황 목록 조회 사원
	 * @param hubSearchVO
	 * @return
	 */
	public List<ProfitVO> selectProfitListEMP(@Param("hubSearchVO")HubSearchVO hubSearchVO);

}
