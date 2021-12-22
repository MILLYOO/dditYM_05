package kr.or.ddit.buy.purchclose.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import kr.or.ddit.buy.vo.CloseRawsVO;
import kr.or.ddit.buy.vo.IncomingRawsVO;
import kr.or.ddit.buy.vo.IncomingVO;
import kr.or.ddit.buy.vo.PurchCloseVO;
import kr.or.ddit.common.vo.HubSearchVO;
import kr.or.ddit.sales.vo.RelProdVO;

/**
 * 매입마감 DAO
 */
@Repository
public interface PurchCloseDAO {
	
	/**
	 * 새로운 문서 체크
	 * @param purchClose
	 * @return
	 */
	public int insertCheck(PurchCloseVO purchClose);

	/**
	 * 매입마감서 목록 조회
	 * @param hubSearchVO
	 * @return
	 */
	public List<PurchCloseVO> selectPurchCloseList(HubSearchVO hubSearchVO);

	/**
	 * 매입마감서 상세 조회
	 * @param purchCloseVO
	 * @return
	 */
	public PurchCloseVO selectPclose(PurchCloseVO purchClose);
	
	/**
	 * 매입마감서 원자재 상세 조회
	 * @param closeRaws
	 * @return
	 */
	public CloseRawsVO selectPcloseRaws(CloseRawsVO closeRaws);

	/**
	 * 해당 매입마감서 원자재 리스트 조회
	 * @param purchCloseVO
	 * @return
	 */
	public List<CloseRawsVO> selectPcloseRawsList(PurchCloseVO purchCloseVO);
	
	/**
	 * 매입마감서 입력이긴 한데 사실 굳ㅇ  ㅣ 입력 필요없긴 함... 입고적용서 적용하는 거라서.. 혹시 몰라서 만들어둠
	 * @param purchClose
	 * @return
	 */
	public int insertPclose(PurchCloseVO purchClose);

	/**
	 * 매입마감서 원자재 입력 얘도 마찬가지긴 한데...
	 * @param closeRawsVO
	 * @return
	 */
	public int insertPcloseRaws(CloseRawsVO closeRawsVO);

	/**
	 * 매입마감서 수정
	 * @param purchCloseVO
	 * @return
	 */
	public int updatePclose(PurchCloseVO purchCloseVO);
	
	/**
	 * 매입마감서 원자재 수정
	 * @param closeRawsVO
	 * @return
	 */
	public int updatePcloseRaws(CloseRawsVO closeRawsVO);

	/**
	 * 매입마감서 삭제
	 * @param purchCloseVO
	 * @return
	 */
	public int deletePclose(PurchCloseVO purchCloseVO);
	
	/**
	 * 매입마감서 원자재 삭제
	 * @param closeRawsVO
	 * @return
	 */
	public int deletePcloseRaws(CloseRawsVO closeRawsVO);

	/**
	 * 매입마감서를 삭제하면 해당하는 원자재 모두 다 삭제
	 * @param purNum
	 * @return
	 */
	public int deleteAllPcloseRaws(String purNum);

	
	


	/**
	 * 매입마감서 - 입고처리서 적용을 위한 입고처리서 목록조회
	 * @param hubSearchVO
	 * @return
	 */
	public List<IncomingVO> selectIncListForPclose(HubSearchVO hubSearchVO);

	/**
	 * 테스트용
	 * @param incomingVO
	 * @return
	 */
	public List<IncomingVO> selectIncListForPclose(IncomingVO incomingVO);

	/**
	 * 매입마감서 - 입고처리서 적용을 위한 입고처리서 원자재 목록 조회
	 * @param incomingVO
	 * @return
	 */
	public List<IncomingRawsVO> selectIncRawsListForPclose(IncomingVO incomingVO);
	
	/**
	 * 입고처리서 존재 여부
	 * @param incomingVO
	 * @return
	 */
	public IncomingVO selectIncForPclose(IncomingVO incoming);

	/**
	 * 입고처리서 원자재 존재 여부
	 * @param incomingRaws
	 * @return
	 */
	public IncomingRawsVO selectIncRawsForPclose(IncomingRawsVO incomingRaws);


	
	//By 이원균_211207 : main.jsp 그릴때 데이터 가져오기 위해서 메소드 만들었어요\
	public List<PurchCloseVO> selectBuyGraph();
}
