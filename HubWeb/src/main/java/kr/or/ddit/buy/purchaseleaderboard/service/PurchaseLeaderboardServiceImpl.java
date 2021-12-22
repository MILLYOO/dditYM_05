package kr.or.ddit.buy.purchaseleaderboard.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import kr.or.ddit.buy.purchaseleaderboard.dao.PurchaseLeaderboardDAO;
import kr.or.ddit.buy.vo.CloseRawsVO;
import kr.or.ddit.buy.vo.PurchCloseVO;
import kr.or.ddit.common.vo.HubSearchVO;

@Service
public class PurchaseLeaderboardServiceImpl implements PurchaseLeaderboardService {

	@Inject
	private PurchaseLeaderboardDAO dao;

	//거래처별 매입순위표 master 목록 조회
	@Override
	public List<PurchCloseVO> retrievePurchaseLeaderboardListPC(HubSearchVO hubSearch) {
		return dao.selectPurchLeaderBoardListPC(hubSearch);
	}

	//거래처별 매입순위표 master 상세 조회
	@Override
	public PurchCloseVO retrievePurchaseLeaderboardPC(PurchCloseVO purchClose) {
		return dao.selectPurchLeaderBoardPC(purchClose);
	}

	//부서별 매입순위표 master 목록 조회
	@Override
	public List<PurchCloseVO> retrievePurchaseLeaderboardListDP(HubSearchVO hubSearch) {
		return dao.selectPurchLeaderBoardListDP(hubSearch);
	}

	//부서별 매입순위표 master 상세 조회
	@Override
	public PurchCloseVO retrievePurchaseLeaderboardDP(PurchCloseVO purchClose) {
		return dao.selectPurchLeaderBoardDP(purchClose);
	}

	//사원별 매입순위표 master 목록 조회
	@Override
	public List<PurchCloseVO> retrievePurchaseLeaderboardListEMP(HubSearchVO hubSearch) {
		return dao.selectPurchLeaderBoardListEMP(hubSearch);
	}

	//사원별 매입순위표 master 상세 조회
	@Override
	public PurchCloseVO retrievePurchaseLeaderboardEMP(PurchCloseVO purchClose) {
		return dao.selectPurchLeaderBoardEMP(purchClose);
	}

//=====================================================================================================================================================================	
	
	//거래처별 매입순위표 detail 목록 조회
	@Override
	public List<CloseRawsVO> retrievePurchaseLeaderboardRawListPC(PurchCloseVO purchCloseVO) {
		return dao.selectPurchLeaderBoardRawsListPC(purchCloseVO);
	}

	//거래처별 매입순위표 detail 상세 조회
	@Override
	public CloseRawsVO retrievePurchaseLeaderboardRawPC(CloseRawsVO closeRawsVO) {
		return dao.selectPurchLeaderBoardRawsDP(closeRawsVO);
	}

	//부서별 매입순위표 detail 목록 조회
	@Override
	public List<CloseRawsVO> retrievePurchaseLeaderboardRawListDP(PurchCloseVO purchCloseVO) {
		return dao.selectPurchLeaderBoardRawsListDP(purchCloseVO);
	}

	//부서별 매입순위표 detail 상세 조회
	@Override
	public CloseRawsVO retrievePurchaseLeaderboardRawDP(CloseRawsVO closeRawsVO) {
		return dao.selectPurchLeaderBoardRawsDP(closeRawsVO);
	}

	//사원별 매입순위표 detail 목록 조회
	@Override
	public List<CloseRawsVO> retrievePurchaseLeaderboardRawListEMP(PurchCloseVO purchCloseVO) {
		return dao.selectPurchLeaderBoardRawsListEMP(purchCloseVO);
	}

	//사원별 매입순위표 detail 상세 조회
	@Override
	public CloseRawsVO retrievePurchaseLeaderboardRawEMP(CloseRawsVO closeRawsVO) {
		return dao.selectPurchLeaderBoardRawsEMP(closeRawsVO);
	}


}
