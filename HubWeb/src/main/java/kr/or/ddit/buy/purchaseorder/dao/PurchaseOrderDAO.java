package kr.or.ddit.buy.purchaseorder.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import kr.or.ddit.buy.vo.OrderRawsVO;
import kr.or.ddit.buy.vo.OrderVO;
import kr.or.ddit.common.vo.HubSearchVO;

/**
 * 발주서 
 */
@Repository
public interface PurchaseOrderDAO {
	
	/**
	 * 새로운 문서 체크
	 * @param orderVO
	 * @return
	 */
	public int insertCheck(OrderVO orderVO);
	
	/**
	 * 발주서 목록 조회
	 * @param hubSearchVO
	 * @return
	 */
	public List<OrderVO> selectOrderList(HubSearchVO hubSearchVO);
	
	/**
	 * 발주서 상세 확인
	 * @param orderVO
	 * @return
	 */
	public OrderVO selectOrder(OrderVO orderVO);
	
	/**
	 * 해당하는 발주서의 원자재 리스트 조회
	 * @param orderVO
	 * @return
	 */
	public List<OrderRawsVO> selectOrderRawsList(OrderVO orderVO);
	
	/**
	 * 발주서입력
	 * @param order
	 * @return
	 */
	public int insertOrder(OrderVO order);

	/**
	 * 발주서 원자재 입력
	 * @param orderRaws
	 * @return
	 */
	public int insertOrderRaws(OrderRawsVO orderRaws);
	
	/**
	 * 발주서 수정
	 * @param order
	 * @return
	 */
	public int updateOrder(OrderVO order);
	
	/**
	 * 발주서 원자재 수정
	 * @param orderRaws
	 * @return
	 */
	public int updateOrderRaws(OrderRawsVO orderRaws);
	
	/**
	 * 발주서 삭제
	 * @param order
	 * @return
	 */
	public int deleteOrder(OrderVO order);
	
	/**
	 * 발주서 상세 삭제
	 * @param orderRaws
	 * @return
	 */
	public int deleteOrderRaws(OrderRawsVO orderRaws);
	
	/**
	 * 발주서를 삭제하면 해당하는 원자재 전부 삭제
	 * @param ordNum
	 * @return
	 */
	public int deleteAllOrdRaws(String ordNum);
	
	/**
	 * 발주서 상세 확인
	 * @param orderRawsVO
	 * @return
	 */
	public OrderRawsVO selectOrderRaws(OrderRawsVO orderRawsVO);
	
}