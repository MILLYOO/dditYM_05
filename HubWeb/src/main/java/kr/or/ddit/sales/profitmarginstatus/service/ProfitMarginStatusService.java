package kr.or.ddit.sales.profitmarginstatus.service;

import java.util.List;

import kr.or.ddit.common.vo.HubSearchVO;
import kr.or.ddit.sales.vo.ProfitVO;

/**
 * 매출이익현황
 */
public interface ProfitMarginStatusService {
	
	/**
	 * 매출이익현황 목록 조회 거래처
	 * @param hubSearchVO
	 * @return
	 */
	public List<ProfitVO> retrieveProfitListPC(HubSearchVO hubSearchVO);
	
	/**
	 * 매출이익현황 목록 조회 부서
	 * @param hubSearchVO
	 * @return
	 */
	public List<ProfitVO> retrieveProfitListDP(HubSearchVO hubSearchVO);
	
	/**
	 * 매출이익현황 목록 조회 사원
	 * @param hubSearchVO
	 * @return
	 */
	public List<ProfitVO> retrieveProfitListEMP(HubSearchVO hubSearchVO);

}
