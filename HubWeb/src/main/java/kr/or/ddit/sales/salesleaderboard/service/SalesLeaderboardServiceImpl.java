package kr.or.ddit.sales.salesleaderboard.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import kr.or.ddit.common.vo.HubSearchVO;
import kr.or.ddit.sales.salesleaderboard.dao.SalesLeaderboardDAO;
import kr.or.ddit.sales.vo.SalesRankVO;

@Service
public class SalesLeaderboardServiceImpl implements SalesLeaderboardService {
	
	@Inject
	private SalesLeaderboardDAO dao;

	// 매출순위표 마스터 목록 조회 거래처
	@Override
	public List<SalesRankVO> retrieveSalesLeaderboardListPC(HubSearchVO hubSearchVO) {
		return dao.selectSalesLeaderBoardListPC(hubSearchVO);
	}
	// 매출순위표 마스터 목록 조회 부서
	@Override
	public List<SalesRankVO> retrieveSalesLeaderboardListDP(HubSearchVO hubSearchVO) {
		return dao.selectSalesLeaderBoardListDP(hubSearchVO);
	}
	// 매출순위표 마스터 목록 조회 사원
	@Override
	public List<SalesRankVO> retrieveSalesLeaderboardListEMP(HubSearchVO hubSearchVO) {
		return dao.selectSalesLeaderBoardListEMP(hubSearchVO);
	}
	
	// 매출순위표 디테일 목록 조회
	@Override
	public List<SalesRankVO> retrieveSalesLeaderboardProdList(SalesRankVO salesRankVO) {
		return dao.selectSalesLeaderBoardProdList(salesRankVO);
	}

}
