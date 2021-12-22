package kr.or.ddit.sales.releaseorder.service;

import java.util.List;
import java.util.Map;

import kr.or.ddit.common.vo.HubSearchVO;
import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.sales.vo.OrderBookProdVO;
import kr.or.ddit.sales.vo.OrderBookVO;
import kr.or.ddit.sales.vo.ReleaseOrderProdVO;
import kr.or.ddit.sales.vo.ReleaseOrderVO;

/**
 * 출고지시서
 */
public interface ReleaseOrderService {
	
	/**
	 * 출고지시서 출고지시번호 최대값 
	 * @param releaseOrderVO
	 * @return
	 */
	public String retrieveMaxId(ReleaseOrderVO releaseOrderVO);
	
	/**
	 * 출고지시서 리스트 조회
	 * @param hubSearch
	 * @return
	 */
	public List<ReleaseOrderVO> retrieveReleaseOrderList(HubSearchVO hubSearchVO);
	
	/**
	 * 출고지시서 등록
	 * @param release
	 * @return
	 */
	public ServiceResult createORupdateReleaseOrder(List<ReleaseOrderVO> releaseOrderVO);
	
	/**
	 * 출고지시서 수정
	 * @param release
	 * @return
	 */
	public ServiceResult updateReleaseOrder(ReleaseOrderVO releaseOrderVO);
	
	/**
	 * 출고지시서 삭제
	 * @param release
	 * @return
	 */
	public ServiceResult deleteReleaseOrder(ReleaseOrderVO releaseOrderVO);
	
	/**
	 * 출고지시서 상세내역 조회
	 * @param release
	 * @return
	 */
	public List<ReleaseOrderProdVO> ReleaseOrderList(ReleaseOrderVO releaseOrderVO);
	
	/**
	 * 출고지시서 상세내역 등록
	 * @param relProd
	 * @return
	 */
	public ServiceResult createRaws(List<ReleaseOrderProdVO> releaseOrderProdVO);
	
	/**
	 * 출고지시서 상세내역 수정
	 * @param relProd
	 * @return
	 */
	public ServiceResult updateRaws(ReleaseOrderProdVO releaseOrderProdVO);
	
	/**
	 * 출고지시서 상세내역 삭제
	 * @param relProd
	 * @return
	 */
	public ServiceResult deleteRaws(List<ReleaseOrderProdVO> releaseOrderProdVO);
	
	/**
	 * 수주서 적용을 위한 목록 조회
	 * @param hubSearchVO
	 * @return
	 */
	public List<OrderBookVO> retrieveOrderbookListForPI(HubSearchVO hubSearchVO);
	
	/**
	 * 수주서 - 제품 적용을 위한 목록 조회
	 * @param orderBookVO
	 * @return
	 */
	public List<OrderBookProdVO> retrieveOrderbookProdListForPI(OrderBookVO orderBookVO);
	
	
	/**
	 * 수주서 - 제품 동시 적용(수주서용) 
	 * @param releaseOrderMap
	 * @return
	 */
	public Map<String, Object> createReleaseOrderMap(Map<String, Object> releaseOrderMap);
	
	/**
	 * 수주서 - 제품 적용
	 * @param 
	 * @return
	 */
	public List<OrderBookVO> insertOrderBookTotalListForPI(OrderBookVO orderBookVO);
	
	
	
	
	

}
