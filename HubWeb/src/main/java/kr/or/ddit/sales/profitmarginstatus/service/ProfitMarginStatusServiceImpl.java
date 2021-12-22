package kr.or.ddit.sales.profitmarginstatus.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import kr.or.ddit.common.vo.HubSearchVO;
import kr.or.ddit.sales.profitmarginstatus.dao.ProfitMarginStatusDAO;
import kr.or.ddit.sales.vo.ProfitVO;

@Service
public class ProfitMarginStatusServiceImpl implements ProfitMarginStatusService {

	@Inject
	private ProfitMarginStatusDAO dao;
	
	// 매출이익현황 목록 조회 거래처
	@Override
	public List<ProfitVO> retrieveProfitListPC(HubSearchVO hubSearchVO) {
		return dao.selectProfitListPC(hubSearchVO);
	}

	// 매출이익현황 부서
	@Override
	public List<ProfitVO> retrieveProfitListDP(HubSearchVO hubSearchVO) {
		return dao.selectProfitListDP(hubSearchVO);
	
	}

	// 매출이익현황 사원
	@Override
	public List<ProfitVO> retrieveProfitListEMP(HubSearchVO hubSearchVO) {
		return dao.selectProfitListEMP(hubSearchVO);
	
	}
	
	

}
