package kr.or.ddit.sales.releaseorder.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import kr.or.ddit.common.vo.HubSearchVO;
import kr.or.ddit.sales.vo.OrderBookProdVO;
import kr.or.ddit.sales.vo.OrderBookVO;
import kr.or.ddit.sales.vo.ReleaseOrderProdVO;
import kr.or.ddit.sales.vo.ReleaseOrderVO;

/**
 * 출고지시서
 */
@Repository
public interface ReleaseOrderDAO {
	
	public int insertCheck(ReleaseOrderVO releaseOrderVO);
	
	/**
	 * 출고지시서 번호 가져오기
	 * @param releaseOrderVO
	 * @return
	 */
	public String selectMaxId(ReleaseOrderVO releaseOrderVO);
	
	/**
	 * 출고지시서 한건 조회
	 * @param releaseOrderVO
	 * @return
	 */
	public ReleaseOrderVO selectReleaseOrder(ReleaseOrderVO releaseOrderVO);
	
	/**
	 * 출고지시서 제품 한건 조회
	 * @param releaseOrderProdVO
	 * @return
	 */
	public ReleaseOrderProdVO selectReleaseOrderProd(ReleaseOrderProdVO releaseOrderProdVO);
	
	/**
	 * 출고지시서 입력
	 * @param releaseOrderVO
	 * @return
	 */
	public int insertReleaseOrder(ReleaseOrderVO releaseOrderVO);
	
	/**
	 * 출고지시서 제품 내역 입력
	 * @param releaseOrderProdVO
	 * @return
	 */
	public int insertReleaseOrderProd(ReleaseOrderProdVO releaseOrderProdVO);
	
	/**
	 * 출고지시서 리스트 조회
	 * @param hubSearchVO
	 * @return
	 */
	public List<ReleaseOrderVO> selectReleaseOrderList(@Param("hubSearchVO")HubSearchVO hubSearchVO);
	
	/**
	 * 출고지시서 제품 리스트 조회
	 * @param releaseOrderVO
	 * @return
	 */
	public List<ReleaseOrderProdVO> selectReleaseOrderProdList(ReleaseOrderVO releaseOrderVO);
	
	/**
	 * 출고지시서 수정
	 * @param releaseOrderVO
	 * @return
	 */
	public int updateReleaseOrder(ReleaseOrderVO releaseOrderVO);
	
	/**
	 * 출고지시서 제품 수정
	 * @param releaseOrderProdVO
	 * @return
	 */
	public int updateReleaseOrderProd(ReleaseOrderProdVO releaseOrderProdVO);

	/**
	 * 출고지시서 삭제
	 * @param releaseOrderVO
	 * @return
	 */
	public int deleteReleaseOrder(ReleaseOrderVO releaseOrderVO);
	
	/**
	 * 출고지시서 제품 삭제
	 * @param releaseOrderProdVO
	 * @return
	 */
	public int deleteReleaseOrderProd(ReleaseOrderProdVO releaseOrderProdVO);
	
	/**
	 * 출고지시서 - 수주서 적용을 위한 목록 조회
	 * @param hubSearchVO
	 * @return
	 */
	public List<OrderBookVO> selectOrderbookListForPI(@Param("hubSearchVO")HubSearchVO hubSearchVO);
	
	/**
	 * 출고지시서 - 수주서 적용을 위한 제품 목록 조회
	 * @param orderBookVO
	 * @return
	 */
	public List<OrderBookProdVO> selectOrderbookProdLIstForPI(OrderBookVO orderBookVO);
	
	/**
	 * 수주서 존재 여부 체크
	 * @param orderBookVO
	 * @return
	 */
	public OrderBookVO selectOrderbookForPI(OrderBookVO orderBookVO);
	
	/**
	 * 수주서 - 제품 목록 존재 여부 체크
	 * @param orderBookProdVO
	 * @return
	 */
	public OrderBookProdVO selectOrderbookProdForPI(OrderBookProdVO orderBookProdVO);
	
}
