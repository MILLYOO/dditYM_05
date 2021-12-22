package kr.or.ddit.buy.purchaseorder.service;
/**
 * 발주서 service
 */

import java.util.List;

import kr.or.ddit.buy.vo.OrderRawsVO;
import kr.or.ddit.buy.vo.OrderVO;
import kr.or.ddit.common.vo.HubSearchVO;
import kr.or.ddit.enumpkg.ServiceResult;

public interface PurchaseOrderService {
	
	/*****************************************************************/
	/*							   발주서   	  							 */
	/*****************************************************************/
	
	/**
	 * 발주서 리스트 출력
	 * @param hubSearch
	 * @return
	 */
	public List<OrderVO> retrieveOrderList(HubSearchVO hubSearchVO);
	
	/**
	 * 발주서 상세 조회
	 * @param orderVO
	 * @return
	 */
	public OrderVO retrieveOrder(OrderVO orderVO);
	
	/**
	 * 발주서를 작성하기 위한 메소드
	 * @param order
	 * @return
	 */
	public ServiceResult createOrModifyOrder(List<OrderVO> order);
	
	
	/**
	 * 작성된 발주서를 삭제 하는 기능(하위 원자재 품목부터 지워야함)
	 * @param order
	 * @return
	 */
	public ServiceResult removeOrder(List<OrderVO> orderVO);
	
	/*****************************************************************/
	/*							   발주서 상세   							 */
	/*****************************************************************/
	
	/**
	 * 발주서 상세 내역 조회
	 * @param ordNum
	 * @return
	 */
	public List<OrderRawsVO> retrieveOrderRawsList(OrderVO orderVO);
	
	/**
	 * 발주서 상세 조회
	 * @param orderRawsVO
	 * @return
	 */
	public OrderRawsVO retrieveOrderRaws(OrderRawsVO orderRawsVo);
	
	/**
	 * 발주서 상세 내역 등록
	 * @param orderRaws
	 * @return
	 */
	public ServiceResult createOrModifyRaws(List<OrderRawsVO> orderRawsVO);
	
	/**
	 * 발주서 상세 내역 삭제
	 * @param orderRaws
	 * @return
	 */
	public ServiceResult removeRaws(List<OrderRawsVO> orderRawsVO);
	
	
}
