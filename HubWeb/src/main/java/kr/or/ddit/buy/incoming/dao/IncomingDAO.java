package kr.or.ddit.buy.incoming.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import kr.or.ddit.buy.vo.IncomingRawsVO;
import kr.or.ddit.buy.vo.IncomingVO;
import kr.or.ddit.buy.vo.OrderRawsVO;
import kr.or.ddit.buy.vo.OrderVO;
import kr.or.ddit.common.vo.HubSearchVO;

/**
 * 입고처리서DAO
 */
@Repository
public interface IncomingDAO {
	
	/**
	 * 새로운 문서 체크
	 * @param incomingVO
	 * @return
	 */
	public int insertCheck(IncomingVO incomingVO);
	
	
	/**
	 * 입고처리서 목록 조회
	 * @param hubSearchVO
	 * @return
	 */
	public List<IncomingVO> selectIncomingList(HubSearchVO hubSearchVO);
	
	/**
	 * 입고처리서 상세 확인
	 * @param incomingVO
	 * @return
	 */
	public IncomingVO selectIncoming(IncomingVO incomingVO);
	
	/**
	 * 해당 입고처리서 원자재 리스트 조회
	 * @param incoming 입고처리서에 해당하는 원자재 목록 조회
	 * @return
	 */
	public List<IncomingRawsVO> selectIncomingRawsList(IncomingVO incomingVO);
	
	/**
	 * 원자재 상세 확인
	 * @param incomingRawsVO
	 * @return
	 */
	public IncomingRawsVO selectIncomingRaws(IncomingRawsVO incomingRawsVO);
	
//===============================================================================================	
	
	/**
	 * 입고처리서 입력
	 * @param incoming
	 * @return
	 */
	public int insertIncoming(IncomingVO incoming);
	
	/**
	 * 입고처리서 원자재 입력
	 * @param incomingRaws
	 * @return
	 */
	public int insertIncomingRaws(IncomingRawsVO incomingRaws);
	
	/**
	 * 입고처리서 수정
	 * @param incoming
	 * @return
	 */
	public int updateIncoming(IncomingVO incoming);
	
	/**
	 * 입고처리서 원자재 수정
	 * @param incomingraws
	 * @return
	 */
	public int updateIncomingRaws(IncomingRawsVO incomingRaws);

	/**
	 * 입고처리서 삭제
	 * @param incoming
	 * @return
	 */
	public int deleteIncoming(IncomingVO incoming);
	
	/**
	 * 입고처리서 원자재 삭제
	 * @param incomingRaws
	 * @return
	 */
	public int deleteIncomingRaws(IncomingRawsVO incomingRaws);
	
	/**
	 * 입고처리서를 삭제하면 해당하는 원자재 전부 다 삭제
	 * @param incNum
	 * @return
	 */
	public int deleteAllIncRaws(String incNum);
	
	
	
	
	
	/**
	 * 입고처리서 - 발주서 적용을 위한 발주서 목록 조회 
	 * @param hubSearchVO
	 * @return
	 */
	public List<OrderVO> selectOrderListForInc(HubSearchVO hubSearchVO);
	
	/**
	 * 입고처리서 - 발주서 적용을 위한 발주서 원재료 목록 조회
	 * @param orderRawsVO
	 * @return
	 */
	public List<OrderRawsVO> selectOrderRawsListforInc(OrderVO orderVO);
	
	/**
	 * 발주서 존재 여부
	 * @param orderVO
	 * @return
	 */
	public OrderVO selectOrderForInc(OrderVO orderVO);
	
	/**
	 * 발주서 원자재 존재여부
	 * @param orderRawsVO
	 * @return
	 */
	public List<OrderRawsVO> selectOrderRawsForInc(String ordNum);
	
	/**
	 * 발주서 적용된 입고처리서 찾기
	 * @param incoming
	 * @return
	 */
	public List<IncomingVO> selectOdApplyInc(IncomingVO incoming);
	
	/**
	 * 입고처리서 - 발주서 원자재를 등록
	 * @param orderRawsVO
	 * @return
	 */
	public int insertIncRawsByOrdApply(OrderRawsVO orderRawsVO);
	
	/**
	 * 발주서 적용 시 적용여부 변경
	 * @param orderVO
	 * @return
	 */
	public int updateOrderApplyYn(OrderVO orderVO);
	
	
	
	/**
	 * 원자재 입고
	 * @param incoming
	 * @return
	 */
	public int updateRawsEnter(IncomingRawsVO incomingRaws);
	
	/**
	 * 원자재 창고 입고
	 * @param incoming
	 * @return
	 */
	public int updateWarRawsEnter(IncomingRawsVO incomingRaws);
	
	/**
	 * 원자재 출고
	 * @param incoming
	 * @return
	 */
	public int updateRawsOut(IncomingRawsVO incomingRaws);
	
	/**
	 * 원자재 창고 출고
	 * @param incoming
	 * @return
	 */
	public int updateWarRawsOut(IncomingRawsVO incomingRaws);
	

	// 원재료 입고
	public int deleteRawsEnter(IncomingRawsVO incomingRaws);
	public int deleteWarRawsEnter(IncomingRawsVO incomingRaws);

	// 원재료 출고
	public int deleteRawsOut(IncomingRawsVO incomingRaws);
	public int deleteWarRawsOut(IncomingRawsVO incomingRaws);
}
