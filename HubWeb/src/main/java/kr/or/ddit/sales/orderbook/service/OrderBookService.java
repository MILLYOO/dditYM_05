package kr.or.ddit.sales.orderbook.service;

import java.util.List;

import kr.or.ddit.common.vo.HubSearchVO;
import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.sales.vo.EstimateProdVO;
import kr.or.ddit.sales.vo.EstimateVO;
import kr.or.ddit.sales.vo.OrderBookProdVO;
import kr.or.ddit.sales.vo.OrderBookVO;

/**
 * 수주서
 */
public interface OrderBookService {
	
	/**
	 * 수주서 리스트 조회
	 * @param hubSearch
	 * @return
	 */
	public List<OrderBookVO> retrieveOrderBookList(HubSearchVO hubSearchVO);
	
	/**
	 * 수주서 등록
	 * @param orderBook
	 * @return
	 */
	public ServiceResult createORupdateOrderBook(List<OrderBookVO> orderBookVO);
	
	/**
	 * 수주서 수정
	 * @param orderBook
	 * @return
	 */
	public ServiceResult updateOrderBook(OrderBookVO orderBookVO);
	
	/**
	 * 수주서 삭제
	 * @param orderBook
	 * @return
	 */
	public ServiceResult deleteOrderBook(OrderBookVO orderBookVO);

	/**
	 * 수주서 제품내역 조회
	 * @param orderBook
	 * @return
	 */
	public List<OrderBookProdVO> retrieveOrderBookProdList(OrderBookVO orderBookVO);
	
	/**
	 * 수주서 제품 등록
	 * @param orderBookProd
	 * @return
	 */
	public ServiceResult createRaws(List<OrderBookProdVO> orderBookProdVO);
	
	/**
	 * 수주서 제품 수정
	 * @param orderBookProd
	 * @return
	 */
	public ServiceResult updateRaws(OrderBookProdVO orderBookProdVO);
	
	/**
	 * 수주서 제품 삭제
	 * @param odBookProd
	 * @return
	 */
	public ServiceResult deleteRaws(List<OrderBookProdVO> orderBookProdVO);
	
	/**
	 * 견적서 적용을 위한 목록 조회
	 * @param hubSearch
	 * @return
	 */
	public List<EstimateVO> retrieveEstimateListForPI(HubSearchVO hubSearchVO);
	
	/**
	 * 견적서 - 제품 적용을 위한 목록 조회
	 * @param estimate
	 * @return
	 */
	public List<EstimateProdVO> retrieveEstimateProdListForPI(EstimateVO estimateVO);
	
	/**
	 * 견적서 - 제품 적용
	 * @param estimateVO
	 * @return
	 */
	public List<EstimateVO> insertEstimateTotalListForPI(EstimateVO estimateVO);
	
}
