package kr.or.ddit.stock.stockvaluation.service;

import java.util.List;

import kr.or.ddit.common.vo.HubSearchVO;
import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.stock.vo.StoValuVO;
import kr.or.ddit.stock.vo.StovalItemVO;

/**
 * 재고평가
 */
public interface InventoryValuationService {
	/*****************************************************************/
	/*							재고평가서 							 */
	/*****************************************************************/

	/**
	 * 재고평가서 조회
	 * @param hubSearch
	 * @return
	 */
	public List<StoValuVO> retrieveInventoryValuationList(HubSearchVO hubSearch);
	
	/**
	 * 재고평가서 등록
	 * @param stoValue
	 * @return
	 */
	public ServiceResult createInventoryValuation(StoValuVO stoValuVO);
	
	/**
	 * 재고평가서 수정
	 * @param stoValue
	 * @return
	 */
//	public ServiceResult createValuationResult(StoValuVO stoValuVO);
	
	/**
	 * 재고평가서 삭제
	 * @param stoValue
	 * @return
	 */
	public ServiceResult deleteInventoryValuation(StoValuVO stoValuVO);
	
	
	/*****************************************************************/
	/*							재고평가서 상세							 */
	/*****************************************************************/
	
	/**
	 * 재고평가서 - 결과 조회
	 * @param stovalItem
	 * @return
	 */
	public List<StovalItemVO> inventoryValuationItemList(StovalItemVO stovalItem);
	
}























