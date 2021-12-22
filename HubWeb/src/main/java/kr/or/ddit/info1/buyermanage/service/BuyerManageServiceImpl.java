package kr.or.ddit.info1.buyermanage.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.or.ddit.common.vo.HubSearchVO;
import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.info1.buyermanage.dao.BuyerManageDAO;
import kr.or.ddit.info1.vo.BuyerVO;

@Service
public class BuyerManageServiceImpl implements BuyerManageService {
	@Inject
	private BuyerManageDAO dao;

	@Override
	public List<BuyerVO> retrieveBuyerList(HubSearchVO hubSearch) {
		List<BuyerVO> buyerList = dao.selectBuyerList(hubSearch);
		return buyerList;
	}
	
	@Transactional
	@Override
	public ServiceResult createBuyer(BuyerVO buyer) {
		ServiceResult result = null;

		int rowcnt = dao.insertBuyer(buyer);
		if(rowcnt > 0 ) {
			result = ServiceResult.OK;
		}else {
			result = ServiceResult.FAILED;
		}

		return result;
	}

	@Transactional
	@Override
	public ServiceResult updateBuyer(BuyerVO buyer) {
		ServiceResult result = null;
		int rowcnt = dao.updateBuyer(buyer);
		if(rowcnt > 0) {
			result = ServiceResult.OK;
		}else {
			result = ServiceResult.FAILED;
		}
		return result;
	}

	@Transactional
	@Override
	public ServiceResult updateBuyerDetail(BuyerVO buyer) {
		ServiceResult result = null;
		int rowcnt = dao.updateBuyerDetail(buyer);
		if(rowcnt > 0) {
			result = ServiceResult.OK;
		}else {
			result = ServiceResult.FAILED;
		}
		return result;
	}

	@Transactional
	@Override
	public ServiceResult deleteBuyer(BuyerVO buyer) {
		ServiceResult result = null;
		int rowcnt = dao.deleteBuyer(buyer);
		if(rowcnt > 0) {
			result = ServiceResult.OK;
		}else {
			result = ServiceResult.FAILED;
		}
		return result;
	}
}
