package kr.or.ddit.info2.itemmanage.service;

import java.util.List;

import kr.or.ddit.common.vo.CommonListVO;
import kr.or.ddit.common.vo.HubSearchVO;
import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.info2.vo.ItemVO;

/**
 * 품목 관리 service
 */
public interface ItemManageService {

	/**
	 * 품목 리스트 조회
	 * @return
	 */
	public List<ItemVO> retrieveItemList(HubSearchVO hubSearch);
	
	/**
	 * 현재고 총괄현황 조회
	 * @param hubSearch
	 * @return
	 */
	public List<ItemVO> retrieveStockAllStatus(HubSearchVO hubSearch);
	
	/**
	 * 품목 등록
	 * NotNull => 품목 수정
	 * @param productVO
	 * @return
	 * 
	 */
	public ServiceResult createItem(CommonListVO<ItemVO> item);
	
	/**
	 * 품목 업데이트
	 * @param item
	 * @return
	 */
	public ServiceResult updateItem(ItemVO item);
	
	/**
	 * 품목 상세정보 수정
	 * @param item
	 * @return
	 */
	public ServiceResult updateItemDetail(ItemVO item);
	
	/**
	 * 품목 삭제
	 * @param productVO
	 * @return
	 */
	public ServiceResult deleteItem(ItemVO item);

}
