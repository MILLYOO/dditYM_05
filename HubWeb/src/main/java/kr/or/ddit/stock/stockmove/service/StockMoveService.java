package kr.or.ddit.stock.stockmove.service;

import java.util.List;

import kr.or.ddit.common.vo.HubSearchVO;
import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.stock.vo.StockMoveVO;
import kr.or.ddit.stock.vo.StomovItemVO;

/**
 * 재고이동
 */
public interface StockMoveService {
	/*****************************************************************/
	/*							재고이동서 							 */
	/*****************************************************************/
	
	/**
	 * 재고이동 조회
	 * @param hubSearch
	 * @return
	 */
	public List<StockMoveVO> retrieveStockMoveList(HubSearchVO hubSearch);
	
	/**
	 * 재고이동 등록
	 * @param adjustment
	 * @return
	 */
	public ServiceResult createStockMove(List<StockMoveVO> stockMoveList);

	/**
	 * 재고이동 수정
	 * @param adjustment
	 * @return
	 */
//	public ServiceResult updateInOutAdjustment(StockMoveVO stockMoveVO);

	/**
	 * 재고이동 삭제
	 * @param adjustment
	 * @return
	 */
	public ServiceResult deleteStockMove(List<StockMoveVO> stockMoveList);
	
	
	
	/*****************************************************************/
	/*							재고이동서 상세							 */
	/*****************************************************************/
	/**
	 * 재고이동-품목 조회
	 */
	public List<StomovItemVO> retrieveStockMoveItemList(StomovItemVO stomovItemVO);
	
	/**
	 * 재고이동-품목 등록
	 */
	public ServiceResult createStockMoveItem(List<StomovItemVO> stomovItemList);

	/**
	 * 재고이동-품목 수정
	 */
//	public ServiceResult updateStockMoveItem(StomovItemVO stomovItemVO);

	/**
	 * 재고이동-품목 삭제
	 */
	public ServiceResult deleteStockMoveItem(List<StomovItemVO> stomovItemList);
}
