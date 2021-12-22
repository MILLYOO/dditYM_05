package kr.or.ddit.sales.orderbook.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import kr.or.ddit.common.vo.HubSearchVO;
import kr.or.ddit.sales.vo.EstimateProdVO;
import kr.or.ddit.sales.vo.EstimateVO;
import kr.or.ddit.sales.vo.OrderBookProdVO;
import kr.or.ddit.sales.vo.OrderBookVO;

/**
 * 수주서
 */
@Repository
public interface OrderBookDAO {
	/**
	 * 새로운 문서 작성
	 * @param adjustmentVO
	 * @return
	 */
	public int insertCheck(OrderBookVO orderBookVO);

	/**
	 * 수주서 한건 조회
	 * @param OrderBookVO
	 * @return
	 */
	public OrderBookVO selectOrderbook(OrderBookVO orderBook);
	
	/**
	 * 수주서 제품 한건 조회
	 * @param OrderBookProdVO
	 * @return
	 */
	public OrderBookProdVO selectOrderbookProd(OrderBookProdVO orderBookProd);
	
	/**
	 * 수주서 리스트 조회 
	 * @param Orderbook
	 * @return
	 */
	public List<OrderBookVO> selectOrderbookList(@Param("hubSearchVO")HubSearchVO hubSearchVO);
	
	/**
	 * 수주서 제품 리스트 조회
	 * @param Orderbook
	 * @return
	 */
	public List<OrderBookProdVO> selectOrderbookProdList(OrderBookVO orderBookVO);
	
	
	/**
	 * 수주서 입력 
	 * @param Orderbook
	 * @return
	 */
	public int insertOrderbook(OrderBookVO orderBookVO);
	
	/**
	 * 수주서 제품 입력 
	 * @param OrderbookProd
	 * @return
	 */
	public int insertOrderbookProd(OrderBookProdVO orderBookProdVO);
	
	/**
	 * 수주서 수정
	 * @param Orderbook
	 * @return
	 */
	public int updateOrderbook(OrderBookVO orderBookVO);
	
	/**
	 * 수주서 제품 수정
	 * @param OrderbookProd
	 * @return
	 */
	public int updateOrderbookProd(OrderBookProdVO orderBookProdVO);
	
	/**
	 * 수주서 삭제
	 * @param Orderbook
	 * @return
	 */
	public int deleteOrderbook(OrderBookVO orderBookVO);
	
	/**
	 * 수주서 제품 삭제
	 * @param OrderbookProd
	 * @return
	 */
	public int deleteOrderbookProd(OrderBookProdVO orderBookProdVO);
	
	/**
	 * 수주서 - 견적서 적용을 위한 목록 조회
	 * @param hubSearch
	 * @return
	 */
	public List<EstimateVO> selectEstimateListForPI(@Param("hubSearchVO")HubSearchVO hubSearchVO);
	
	/**
	 * 수주서-견적서 적용을 위한 제품 목록 조회
	 * @param estimateVO
	 * @return
	 */
	public List<EstimateProdVO> selectEstimateProdListForPI(EstimateVO estimateVO);
	
	/**
	 * 견적서 존재 여부 체크 위한 조회
	 * @param estimateVO
	 * @return
	 */
	public EstimateVO selectEstimateForPI(EstimateVO estimateVO);
	
	/**
	 * 견적서 제품 존재 여부 체크 위한 조회
	 * @param estimateProdVO
	 * @return
	 */
	public EstimateProdVO selectEstimateProdForPI(EstimateProdVO estimateProdVO);
	
	
}
