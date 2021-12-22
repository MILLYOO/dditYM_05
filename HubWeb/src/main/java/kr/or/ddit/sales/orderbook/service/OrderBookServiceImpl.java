package kr.or.ddit.sales.orderbook.service;


import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.inject.Inject;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.or.ddit.common.PKNotFoundException;
import kr.or.ddit.common.dao.DocCheckDAO;
import kr.or.ddit.common.vo.HubSearchVO;
import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.sales.orderbook.dao.OrderBookDAO;
import kr.or.ddit.sales.vo.EstimateProdVO;
import kr.or.ddit.sales.vo.EstimateVO;
import kr.or.ddit.sales.vo.OrderBookProdVO;
import kr.or.ddit.sales.vo.OrderBookVO;

@Service
public class OrderBookServiceImpl implements OrderBookService {
	
	@Inject
	private OrderBookDAO dao;
	

	// 수주서 전체 선택
	@Override
	public List<OrderBookVO> retrieveOrderBookList(HubSearchVO hubSearch) {
		List<OrderBookVO> orderbookList = dao.selectOrderbookList(hubSearch);
		return orderbookList;
	}
	// 수주서 등록 및 업데이트
		@Override
		@Transactional
		public ServiceResult createORupdateOrderBook(List<OrderBookVO> orderBookList) {
			ServiceResult result = null;
			String orbNum = null;
//			List<OrderBookVO> orderBookList = orderBookVO.getDataList();
				// 수주서 리스트를 검증
				if (orderBookList != null) {
					for (OrderBookVO orderBook : orderBookList) {
						orbNum = orderBook.getOrbNum();
						if (!StringUtils.isNotBlank(orbNum)) {
							int rowcnt = dao.insertOrderbook(orderBook);
							orbNum = orderBook.getOrbNum();
							if (rowcnt > 0) {
								// 문서등록 알림에 사용 할 다오 호출
								int cntCheck = dao.insertCheck(orderBook);
								// 수주서 등록 성공시 수주서 제품이 있으면 등록
								List<OrderBookProdVO> orderBookProdList = orderBook.getDataProdList(); // 수주서 제품 리스트
								if (orderBookProdList != null) {
									for (OrderBookProdVO orderBookProdVO : orderBookProdList) {
										// 수주서 번호
										Integer estNO = orderBookProdVO.getOrbNo();
										orderBookProdVO.setOrbNum(orbNum);
											// 수주서 번호 없으면
											if (estNO == null) {
												 rowcnt = dao.insertOrderbookProd(orderBookProdVO);
												if (rowcnt > 0) {
													result = ServiceResult.OK;
												}else {
													result = ServiceResult.FAILED;
												}
											}else {
												// 수주서 번호가 있으면
												 rowcnt = dao.updateOrderbookProd(orderBookProdVO);
												 if (rowcnt > 0) {
													result = ServiceResult.OK;
												}else {
													result = ServiceResult.FAILED;
												}
											}
									} // 제품 반복문 끝
								}else { // 제품 조건문 끝
									if(rowcnt > 0 ) {
										result = ServiceResult.OK;
									}else {
										result = ServiceResult.FAILED;
									}
								}
							} // 수주서 등록 조건문 끝
						}else {
							int rowcnt = dao.updateOrderbook(orderBook);
							if(rowcnt > 0 ) {
								result = ServiceResult.OK;
							}else {
								result = ServiceResult.FAILED;
							}
						}
					}//반복문 끝
				}else {
		}
				return result;
		}
	
	// 수주서 수정
	@Override
	@Transactional
	public ServiceResult updateOrderBook(OrderBookVO orderBookVO) {
		ServiceResult result = null;
		int rowcnt = dao.updateOrderbook(orderBookVO);
		
		if (rowcnt > 0) {
			result = ServiceResult.OK;
		}else {
			result = ServiceResult.FAILED;
		}
		
		return result;
	}

	// 수주서 삭제
	@Transactional
	@Override
	public ServiceResult deleteOrderBook(OrderBookVO orderBookVO) { 
		ServiceResult result = null;
		int rowcnt = dao.deleteOrderbook(orderBookVO);
		if(rowcnt > 0) {
			result = ServiceResult.OK;
		}else {
			result = ServiceResult.FAILED;
		}
		return result;
	}
	
	// 수주서 제품 조회
	@Override
	public List<OrderBookProdVO> retrieveOrderBookProdList(OrderBookVO orderBookVO) {
		List<OrderBookProdVO> orderbookProdList = dao.selectOrderbookProdList(orderBookVO);
		return orderbookProdList;
	}

	// 수주서 제품 추가
	@Override
	@Transactional
	public ServiceResult createRaws(List<OrderBookProdVO> orderBookProdVO) {
		ServiceResult result = null;
		for (OrderBookProdVO orderBookProd : orderBookProdVO) {
			Integer orbNo = orderBookProd.getOrbNo();
			if (orbNo == null) {
				int rowcnt = dao.insertOrderbookProd(orderBookProd);
				if(rowcnt > 0 ) {
					result = ServiceResult.OK;
				}else {
					result = ServiceResult.FAILED;
				}
			}else {
				int rowcnt = dao.updateOrderbookProd(orderBookProd);
				if(rowcnt > 0 ) {
					result = ServiceResult.OK;
				}else {
					result = ServiceResult.FAILED;
				}
			}
		}
		return result;
	}

	@Override
	public ServiceResult updateRaws(OrderBookProdVO orderBookProd) {
		// TODO Auto-generated method stub
		return null;
	}

	// 제품 삭제
	@Override
	@Transactional
	public ServiceResult deleteRaws(List<OrderBookProdVO> orderBookProdVO) {
		ServiceResult result = null;
		for (OrderBookProdVO orderBookProd : orderBookProdVO) {
			int rowcnt = dao.deleteOrderbookProd(orderBookProd);
			if(rowcnt > 0) {
				result = ServiceResult.OK;
			}else {
				result = ServiceResult.FAILED;
			}
		}
		return result;
	}
	
	

	// --------------------------견적서 적용-------------------------------
	
	// 견적서 목록 조회
	@Override
	public List<EstimateVO> retrieveEstimateListForPI(HubSearchVO hubSearchVO) {
		return dao.selectEstimateListForPI(hubSearchVO);
	}
	// 견적서 상세품목 적용 조회
	@Override
	public List<EstimateProdVO> retrieveEstimateProdListForPI(EstimateVO estimateVO) {
		EstimateVO saved = dao.selectEstimateForPI(estimateVO);
		if (saved == null) {
			throw new PKNotFoundException(estimateVO.getEstCode() + "에 해당하는 제품 없음");
		}
		return dao.selectEstimateProdListForPI(estimateVO);
	}


	// 견적서 - 제품 적용
	@Override
	public List<EstimateVO> insertEstimateTotalListForPI(EstimateVO estimateVO) {
		List<EstimateVO> estimateList = estimateVO.getDataList();
		List<EstimateProdVO> estimateProdList = estimateVO.getDataProdList();
		EstimateVO saved = null;
		List<EstimateVO> estList = new ArrayList<>();
		int count = 0;
		
		if (estimateProdList != null) {
			
			for (EstimateVO estimate : estimateList) { // 견적서 가져온거 하나씩 꺼내서 내보내기 위해 저장
				saved = dao.selectEstimateForPI(estimate);
				if (saved == null) {
					throw new PKNotFoundException(estimateVO.getEstCode() + "에 해당하는 제품 없음");
				}
				List<EstimateProdVO> savedProdList = new ArrayList<>();
				for (EstimateProdVO eProdVO : estimateProdList) {
					EstimateProdVO savedProd = dao.selectEstimateProdForPI(eProdVO);
					savedProdList.add(savedProd);
					saved.setDataProdList(savedProdList);
				}
				estList.add(count,saved);
				++count;
			}
		}else {
		// 견적서 가져와서 모든 제품 꺼내서 가져가기
		for (EstimateVO estimate : estimateList) {
			 List<EstimateProdVO> list = estimate.getDataProdList();
		if (list == null) {
			list = dao.selectEstimateProdListForPI(estimate);
		}
			saved = dao.selectEstimateForPI(estimate);
			if (saved == null) {
				throw new PKNotFoundException(estimateVO.getEstCode() + "에 해당하는 제품 없음");
			}
		List<EstimateProdVO> savedProdList = new ArrayList<>();
		for (EstimateProdVO eProdVO : list) {
			EstimateProdVO savedProd = dao.selectEstimateProdForPI(eProdVO);
			savedProdList.add(savedProd);
			saved.setDataProdList(savedProdList);
		}
		estList.add(count,saved);
		++count;
		}}
		return estList;
	}



	
}
