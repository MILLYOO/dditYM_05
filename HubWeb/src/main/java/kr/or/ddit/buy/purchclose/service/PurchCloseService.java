package kr.or.ddit.buy.purchclose.service;
/**
 * 매입마감 service
 */

import java.util.List;

import kr.or.ddit.buy.vo.CloseRawsVO;
import kr.or.ddit.buy.vo.IncomingRawsVO;
import kr.or.ddit.buy.vo.IncomingVO;
import kr.or.ddit.buy.vo.PurchCloseVO;
import kr.or.ddit.common.vo.HubSearchVO;
import kr.or.ddit.enumpkg.ServiceResult;

public interface PurchCloseService {

	/*****************************************************************/
	/*							매입마감서   							 */
	/*****************************************************************/
	
	/**
	 * 매입마감서 리스트 출력
	 * @param hubSearch
	 * @return
	 */
	public List<PurchCloseVO> retrievePurchCloseList(HubSearchVO hubSearch);
	
	/**
	 * 매입마감 상세 조회
	 * @param purchCloseVO
	 * @return
	 */
	public PurchCloseVO retrievePurchClose(PurchCloseVO purchCloseVO);
	
	/**
	 * 매입마감서 작성 위한 메소드
	 * @param purchClose
	 * @return
	 */
	public ServiceResult createOrModifyPurchClose(List<PurchCloseVO> purchCloseVOList);
	
	/**
	 * 매입마감서 삭제 위한 메서드
	 * @param purchClose
	 * @return
	 */
	public ServiceResult removePurchClose(List<PurchCloseVO> purchCloseVOList);
	
	
	
	/*****************************************************************/
	/*							매입마감서 상세							 */
	/*****************************************************************/
	
	/**
	 * 매입마감서 원자재 리스트 조회
	 * @param purcNum
	 * @return
	 */
	public List<CloseRawsVO> retrievePurchCloseRawsList(PurchCloseVO purchCloseVO);
	
	/**
	 * 매입마감서 원자재 조회
	 * @param closeRawsVO
	 * @return
	 */
	public CloseRawsVO retrieveCloseRaws(CloseRawsVO closeRawsVO);
	
	/**
	 * 매입마감서 원자재 등록
	 * @param close
	 * @return
	 */
	public ServiceResult createOrUpdateRaws(List<CloseRawsVO> closeRawsVOList);
	
	/**
	 * 매입마감서 상세 삭제
	 * @param purchClose
	 * @return
	 */
	public ServiceResult deleteRaws(List<CloseRawsVO> closeRawsVOList);
	
	
	
	/*****************************************************************/
	/*				매입마감서 - 입고처리서 적용							 */
	/*****************************************************************/
	
	/**
	 * 매입마감서 - 입고처리를 위한 목록 조회
	 * @param hubSearchVO
	 * @return
	 */
	public List<IncomingVO> retrieveIncListForPclose(HubSearchVO hubSearchVO);
	
	/**
	 * 매입마감서 - 입고처리적용을 위한 입고처리(원재료) 목록조회
	 * @param incomingVO
	 * @return
	 */
	public List<IncomingRawsVO> retrieveIncRawsListForPclose(IncomingVO incomingVO);
	
	/**
	 * 입고처리서 적용
	 * @param incoming
	 * @return
	 */
	public ServiceResult insertPcloseByInc(List<IncomingVO> incoming);
}
