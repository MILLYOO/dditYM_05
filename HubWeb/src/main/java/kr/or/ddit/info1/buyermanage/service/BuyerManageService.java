package kr.or.ddit.info1.buyermanage.service;

import java.util.List;

import kr.or.ddit.common.vo.HubSearchVO;
import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.info1.vo.BuyerVO;

public interface BuyerManageService {
	/**
	 * 거래처 리스트 조회
	 * @param hubSearch
	 * @return
	 */
	public List<BuyerVO> retrieveBuyerList(HubSearchVO hubSearch);
	
	/**
	 * 거래처 등록 메서드
	 * @return
	 */
	public ServiceResult createBuyer(BuyerVO buyer);
	
	/**
	 * 거래처 수정 메서드
	 * @param buyer
	 * @return
	 */
	public ServiceResult updateBuyer(BuyerVO buyer);
	
	public ServiceResult updateBuyerDetail(BuyerVO buyer);
	
	/**
	 * 거래처 삭제 메서드
	 * @param buyer
	 * @return
	 */
	public ServiceResult deleteBuyer(BuyerVO buyer);
	
}
