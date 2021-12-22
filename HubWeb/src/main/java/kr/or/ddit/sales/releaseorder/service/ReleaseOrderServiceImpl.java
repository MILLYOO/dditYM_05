package kr.or.ddit.sales.releaseorder.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.inject.Inject;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.or.ddit.common.PKNotFoundException;
import kr.or.ddit.common.dao.DocCheckDAO;
import kr.or.ddit.common.vo.HubSearchVO;
import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.sales.releaseorder.dao.ReleaseOrderDAO;
import kr.or.ddit.sales.vo.OrderBookProdVO;
import kr.or.ddit.sales.vo.OrderBookVO;
import kr.or.ddit.sales.vo.ReleaseOrderProdVO;
import kr.or.ddit.sales.vo.ReleaseOrderVO;

@Service
public class ReleaseOrderServiceImpl implements ReleaseOrderService {
	
	@Inject
	private ReleaseOrderDAO dao;
	
	// 출고지시서 전체 선택
	@Override
	public List<ReleaseOrderVO> retrieveReleaseOrderList(HubSearchVO hubSearch) {
		return dao.selectReleaseOrderList(hubSearch);
	}
	
	// 출고지시서 수정
	@Override
	@Transactional
	public ServiceResult updateReleaseOrder(ReleaseOrderVO releaseOrderVO) {
		ServiceResult result  = null;
		int rowcnt = dao.updateReleaseOrder(releaseOrderVO);
		
		if (rowcnt > 0) {
			result = ServiceResult.OK;
		}else {
			result = ServiceResult.FAILED;
		}
		
		return result;
	
	}

	// 출고지시서 삭제
	@Override
	@Transactional
	public ServiceResult deleteReleaseOrder(ReleaseOrderVO releaseOrderVO) {
		ServiceResult result = null;
		int rowcnt = dao.deleteReleaseOrder(releaseOrderVO);
		if(rowcnt > 0) {
			result = ServiceResult.OK;
		}else {
			result = ServiceResult.FAILED;
		}
		return result;
	}
	
	// 출고지시서 제품 조회
	@Override
	public List<ReleaseOrderProdVO> ReleaseOrderList(ReleaseOrderVO releaseOrderVO) {
		return dao.selectReleaseOrderProdList(releaseOrderVO);
	}
	
	// 출고지시서 제품 추가
	@Override
	@Transactional
	public ServiceResult createRaws(List<ReleaseOrderProdVO> releaseOrderProdList) {
		ServiceResult result = null;
		for (ReleaseOrderProdVO releaseOrderProdVO : releaseOrderProdList) {
			
		Integer rOP = releaseOrderProdVO.getReoNo();
		if (rOP == null) {
			int rowcnt = dao.insertReleaseOrderProd(releaseOrderProdVO);
			if(rowcnt > 0 ) {
				result = ServiceResult.OK;
			}else {
				result = ServiceResult.FAILED;
			}
	   }else {
		   int rowcnt = dao.updateReleaseOrderProd(releaseOrderProdVO);
		   if (rowcnt > 0) {
				result = ServiceResult.OK;
			}else {
				result = ServiceResult.FAILED;
			}
		}
		} // for문 끝
		return result;
	}

	// 출고지시서 제품 삭제
	@Override
	@Transactional
	public ServiceResult deleteRaws(List<ReleaseOrderProdVO> releaseOrderProdList) {
		ServiceResult result = null;
		for(ReleaseOrderProdVO releaseOrderProdVO : releaseOrderProdList) {
			
		int rowcnt = dao.deleteReleaseOrderProd(releaseOrderProdVO);
		
		if(rowcnt > 0) {
			result = ServiceResult.OK;
		}else {
			result = ServiceResult.FAILED;
		}
		} //for문 끝
		return result;
	}
	
	// 출고지시서 등록 및 업데이트
	@Override
	@Transactional
	public ServiceResult createORupdateReleaseOrder(List<ReleaseOrderVO> releaseOrderList ) {
		ServiceResult result = null;
		String maxId = null;
//		List<ReleaseOrderVO> releaseOrderList = releaseOrderMap.getDataList();
		if(releaseOrderList != null) {
			for(ReleaseOrderVO releaseOrderVO : releaseOrderList) { // 받아온 데이터 꺼내서
				String ro = releaseOrderVO.getReoNum();
				maxId = dao.selectMaxId(releaseOrderVO);
				if (!StringUtils.isNotBlank(ro)) {
					int rowcnt = dao.insertReleaseOrder(releaseOrderVO);
					ro = releaseOrderVO.getReoNum();
					if (rowcnt > 0) {
						int cntCheck = dao.insertCheck(releaseOrderVO);
						List<ReleaseOrderProdVO> releaseOrderProdList = releaseOrderVO.getDataProdList();
						if (releaseOrderProdList != null) {
							for (ReleaseOrderProdVO releaseOrderProdVO : releaseOrderProdList) {
								Integer reoNo = releaseOrderProdVO.getReoNo();
								releaseOrderProdVO.setReoNum(ro);
								if(reoNo == null) {
									rowcnt = dao.insertReleaseOrderProd(releaseOrderProdVO);
									if (rowcnt > 0) {
										result = ServiceResult.OK;
									}else {
										result = ServiceResult.FAILED;
									}
								}else {
									rowcnt = dao.updateReleaseOrderProd(releaseOrderProdVO);
									if (rowcnt > 0) {
										result = ServiceResult.OK;
									}else {
										result = ServiceResult.FAILED;
									}
								}
							}
						}else {
							if (rowcnt > 0) {
								result = ServiceResult.OK;
							}else {
								result = ServiceResult.FAILED;
							}
						}
					}
				}else {
					int rowcnt = dao.updateReleaseOrder(releaseOrderVO);
					if (rowcnt > 0) {
						result = ServiceResult.OK;
					}else {
						result = ServiceResult.FAILED;
					}
				}
			} // for문 끝
		}else { // if문 끝
			
		}
			return result;
	}
	 
	// 출고지시번호 가져오기
	@Override
	public String retrieveMaxId(ReleaseOrderVO releaseOrderVO) {
		return dao.selectMaxId(releaseOrderVO);
	}
	
	// 출고지시서 적용 목록 조회
	@Override
	public List<OrderBookVO> retrieveOrderbookListForPI(HubSearchVO hubSearchVO) {
		return dao.selectOrderbookListForPI(hubSearchVO);
	}

	// 출고지시서 제품 적용 목록 조회
	@Override
	public List<OrderBookProdVO> retrieveOrderbookProdListForPI(OrderBookVO orderBookVO) {
		OrderBookVO saved = dao.selectOrderbookForPI(orderBookVO);
		if (saved == null) {
			throw new PKNotFoundException(orderBookVO.getOrbNum() + "에 해당하는 제품 없음");
		}
		return dao.selectOrderbookProdLIstForPI(orderBookVO);
	}

	@Override
	public ServiceResult updateRaws(ReleaseOrderProdVO releaseOrderProdVO) {
		// TODO Auto-generated method stub
		return null;
	}
	
	// 수주서 - 제품 동시 적용(수주서용)
	@Override
	public Map<String, Object> createReleaseOrderMap(Map<String, Object> releaseOrderMap) {
		ServiceResult result = null;
		ServiceResult resultProd = null;
		Map<String, Object> resultMap = null;
		
		List<ReleaseOrderVO> releaseOrderList = (List<ReleaseOrderVO>) releaseOrderMap.get("dataList");
		List<ReleaseOrderProdVO> releaseOrderProdList = (List<ReleaseOrderProdVO>) releaseOrderMap.get("dataProdList");
		for(ReleaseOrderVO releaseOrderVO : releaseOrderList) { // 받아온 데이터 꺼내서
			String ro = releaseOrderVO.getReoNum();
		if (ro == "") {
			int rowcnt = dao.insertReleaseOrder(releaseOrderVO);
			if (rowcnt > 0) {
				result = ServiceResult.OK;
			}else {
				result = ServiceResult.FAILED;
			}
		}else if(ro == null) {
			int rowcnt = dao.insertReleaseOrder(releaseOrderVO);
			if (rowcnt > 0) {
				result = ServiceResult.OK;
			}else {
				result = ServiceResult.FAILED;
			}
		}else {
			int rowcnt = dao.updateReleaseOrder(releaseOrderVO);
			if (rowcnt > 0) {
				result = ServiceResult.OK;
			}else {
				result = ServiceResult.FAILED;
			}
		} 
	} // for문 끝
		
		for (ReleaseOrderProdVO releaseOrderProdVO : releaseOrderProdList) {
			
		Integer rOP = releaseOrderProdVO.getReoNo();
		if (rOP == null) {
			int rowcnt = dao.insertReleaseOrderProd(releaseOrderProdVO);
			if(rowcnt > 0 ) {
				resultProd = ServiceResult.OK;
			}else {
				resultProd = ServiceResult.FAILED;
			}
	   }else {
		   int rowcnt = dao.updateReleaseOrderProd(releaseOrderProdVO);
		   if (rowcnt > 0) {
			   resultProd = ServiceResult.OK;
			}else {
				resultProd = ServiceResult.FAILED;
			}
		}
		} // for문 끝
		resultMap.put("result", result);
		resultMap.put("resultProd", resultProd);
		
		return resultMap;
	}

	// 수주서 - 제품 적용
	@Override
	public List<OrderBookVO> insertOrderBookTotalListForPI(OrderBookVO orderBookVO) {
		
		List<OrderBookVO> orderbookList = orderBookVO.getDataList(); // 넘어온 수주서 목록
		List<OrderBookProdVO> orderBookProdList = orderBookVO.getDataProdList(); // 넘어온 수주서 제품 목록
		OrderBookVO saved = null; 
		List<OrderBookVO> orbList = new ArrayList<>();
		int count = 0;	
		if (orderBookProdList != null) { // 넘어온 수주서 목록이 있을 경우
		for (OrderBookVO orderbook : orderbookList) { // 수주서 반복문으로 한건씩 꺼내 찾는다.
			saved = dao.selectOrderbookForPI(orderbook);
			if (saved == null) {
				throw new PKNotFoundException(orderbook.getOrbNum() + "에 해당하는 제품 없음");
			}
		List<OrderBookProdVO> savedProdList = new ArrayList<>(); // 수주서 제품 담을 리스트
		for (OrderBookProdVO oProdVO : orderBookProdList) {
			OrderBookProdVO savedProd = dao.selectOrderbookProdForPI(oProdVO);
			savedProdList.add(savedProd);
			saved.setDataProdList(savedProdList);
		}
		orbList.add(count,saved);
		++count;
		}
		
		}else {
			for (OrderBookVO orderbook : orderbookList) {
				 List<OrderBookProdVO> list = orderbook.getDataProdList();
			if (list == null) {
				list = dao.selectOrderbookProdLIstForPI(orderbook);
			}
				saved = dao.selectOrderbookForPI(orderbook);
				if (saved == null) {
					throw new PKNotFoundException(orderbook.getOrbNum() + "에 해당하는 제품 없음");
				}
			List<OrderBookProdVO> savedProdList = new ArrayList<>();
			for (OrderBookProdVO oProdVO : list) {
				OrderBookProdVO savedProd = dao.selectOrderbookProdForPI(oProdVO);
				savedProdList.add(savedProd);
				saved.setDataProdList(savedProdList);
			}
			orbList.add(count,saved);
			++count;
			}
		}
		return orbList;
	}


}
