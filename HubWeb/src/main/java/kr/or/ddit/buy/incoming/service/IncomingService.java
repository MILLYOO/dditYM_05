package kr.or.ddit.buy.incoming.service;
/**
 * 입고처리서 service
 */

import java.util.List;

import kr.or.ddit.buy.vo.IncomingRawsVO;
import kr.or.ddit.buy.vo.IncomingVO;
import kr.or.ddit.buy.vo.OrderRawsVO;
import kr.or.ddit.buy.vo.OrderVO;
import kr.or.ddit.common.vo.HubSearchVO;
import kr.or.ddit.enumpkg.ServiceResult;

public interface IncomingService {
	/*****************************************************************/
	/*							입고처리서  							 */
	/*****************************************************************/
	
	/**
	 * 검색 조건에 따른 입고처리서의 리스트를 출력하기 위한 메소드
	 * 만약 List<IncomingVO>의 사이즈가 0이라면 바로 입력하는 그리드
	 * @param hubSearch
	 * @return
	 */
	public List<IncomingVO> retrieveIncomingList(HubSearchVO hubSearchVO);
	
	/**
	 * 입고처리서 상세 조회
	 * @param incomingVO
	 * @return
	 */
	public IncomingVO retrieveIncoming(IncomingVO incomingVO);

	/**
	 * 입고처리서를 작성하기 위한 메소드
	 * @param incoming
	 * @return
	 */
	public ServiceResult createOrModifyIncoming(List<IncomingVO> incomingList);

	/**
	 * 작성된 입고처리서의 내용을 수정하기 위한 메소드
	 * @param incoming
	 * @return
	 */
//	public ServiceResult modifyIncoming(IncomingVO incoming);
	
	/**
	 * 작성된 입고처리서를 삭제하는 기능 (Incoming, (우선)Incoming_Raw에서 지워야함)
	 * @param incoming
	 * @return
	 */
	public ServiceResult removeIncoming(List<IncomingVO> incoming);
	
	
	/*****************************************************************/
	/*							입고처리서 상세							 */
	/*****************************************************************/
	
	/**
	 * 하나의 입고처리서안의 품목을 조회하는 기능
	 * 만약 List<IncomingRawsVO>의 사이즈가 0이라면 바로 입력하는 그리드
	 * @param ingNum
	 * @return List<IncomingRawsVO>
	 */
	public List<IncomingRawsVO> retrieveincomingRawsList(IncomingVO incomingVO);
	
	/**
	 * 입고처리서 상세 조회
	 * @param incomingRawsVO
	 * @return
	 */
	public IncomingRawsVO retrieveIncomingRaws(IncomingRawsVO incomingRawsVO);
	
	/**
	 * 입고처리서에 품목을 추가,수정하는 기능
	 * @param incoming
	 * @return
	 */
	public ServiceResult createOrModifyRaws(List<IncomingRawsVO> incomingRawsList);

	/**
	 * 작성된 입고처리서의 안의 품목을 수정하는 기능
	 * @param incoming
	 * @return
	 */
//	public ServiceResult modifyRaws(IncomingRawsVO incomingRaws);
	
	/**
	 * 작성된 입고처리서의 품목을 삭제하는 기능
	 * @param incoming
	 * @return
	 */
	public ServiceResult removeRaws(List<IncomingRawsVO> incomingRaws);
	
	
	
	
	
	/**
	 * 입고처리서 - 발주서적용을 위한 목록 조회
	 * @param hubSearchVO
	 * @return
	 */
	public List<OrderVO> retrieveOrderListForInc(HubSearchVO hubSearchVO);

	/**
	 * 입고처리서 - 발주서적용을 위한 발주서-원재료 목록조회
	 * @param orderVO
	 * @return
	 */
	public List<OrderRawsVO> retrieveOrderRawsListForInc(OrderVO orderVO);
	
	/**
	 * 발주서 적용
	 * @param orderVO
	 * @return
	 */
	public ServiceResult insertIncByOrd(List<OrderVO> orderVO);
	
	/**
	 * 입고처리서 - 발주서 전개등록
	 * @param orderRawsVO
	 * @return
	 */
	public ServiceResult createIncRawsByOrdApply(List<OrderRawsVO> orderRawsVO);
	
}
