package kr.or.ddit.stock.inoutadjustment.service;

import java.util.List;

import kr.or.ddit.common.vo.HubSearchVO;
import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.stock.vo.AdjItemVO;
import kr.or.ddit.stock.vo.AdjustmentVO;

/**
 * 입출고조정
 */
public interface InOutAdjustmentService {
	
	/*****************************************************************/
	/*							입출고조정서 							 */
	/*****************************************************************/
	
	/**
	 * 입출고조정 조회
	 * @param hubSearch
	 * @return
	 */
	public List<AdjustmentVO> retrieveInOutAdjustmentList(HubSearchVO hubSearch);
	
	
	/*
	 * 입출고조정 상세조회
	 */
//	public AdjustmentVO retrieveInOutAdjustment(AdjustmentVO adjustmentVO);
	
	/**
	 * 입출고조정 등록 및 수정
	 * @param adjustment
	 * @return
	 */
	public ServiceResult createOrUpdateInOutAdjustment(List<AdjustmentVO> list);

	/**
	 * 입출고조정 수정
	 * @param adjustment
	 * @return
	 */
//	public ServiceResult updateInOutAdjustment(AdjustmentVO adjustment);

	/**
	 * 입출고조정 삭제
	 * @param adjustment
	 * @return
	 */
	public ServiceResult deleteInOutAdjustment(List<AdjustmentVO> adjustmentList);
	
	
	
	/*****************************************************************/
	/*							입출고조정서 상세							 */
	/*****************************************************************/
	/**
	 * 입출고조정-품목 조회
	 * @param adjNum
	 * @return
	 */
	public List<AdjItemVO> inOutAdjustmentItemList(AdjItemVO adjItem);
	
	/**
	 * 입출고조정-품목 한개 조회
	 * @param adjItemVO
	 * @return
	 */
//	public AdjItemVO retrieveInOutItem(AdjItemVO adjItemVO);
	/**
	 * 입출고조정-품목 등록 및 수정
	 * @param adjustment
	 * @return
	 */
	public ServiceResult createOrUpdateItem(List<AdjItemVO> adjItemList);

	/**
	 * 입출고조정-품목 수정
	 * @param adjustment
	 * @return
	 */
	public ServiceResult updateItem(AdjItemVO adjItem);

	/**
	 * 입출고조정-품목 삭제
	 * @param adjustment
	 * @return
	 */
	public ServiceResult deleteItem(List<AdjItemVO> adjItemList);

	
}
