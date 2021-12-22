package kr.or.ddit.buy.purchaseorder.service;

import java.util.List;

import javax.annotation.Resource;
import javax.inject.Inject;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.or.ddit.buy.purchaseorder.dao.PurchaseOrderDAO;
import kr.or.ddit.buy.vo.OrderRawsVO;
import kr.or.ddit.buy.vo.OrderVO;
import kr.or.ddit.common.dao.DocCheckDAO;
import kr.or.ddit.common.vo.HubSearchVO;
import kr.or.ddit.enumpkg.ServiceResult;

@Service
public class PurchaseOrderServiceImpl implements PurchaseOrderService {

	@Inject
	private PurchaseOrderDAO orderDao;

	/**
	 * 발주서 리스트 조회
	 */
	@Override
	public List<OrderVO> retrieveOrderList(HubSearchVO hubSearchVO) {
		return orderDao.selectOrderList(hubSearchVO);
	}

	/**
	 * 발주서 상세 조회
	 */
	@Override
	public OrderVO retrieveOrder(OrderVO orderVO) {
		return orderDao.selectOrder(orderVO);
	}

	/**
	 * 발주서 등록(+수정)
	 */
	@Transactional
	@Override
	public ServiceResult createOrModifyOrder(List<OrderVO> orderVOList) {
		
		ServiceResult result = null;
		int successCnt = 0;
		
		for(OrderVO orderVO : orderVOList) {
			String year = orderVO.getBuyYy();
			String month = orderVO.getBuyMm();
			String day = orderVO.getBuyDd();
			String ordDate = year+"-"+month+"-"+day;
			orderVO.setOrdDate(ordDate);
			String ordFin = orderVO.getOrdFinish();
			if(StringUtils.isNotBlank(ordFin)) {
				ordFin = ordFin.toUpperCase();
				orderVO.setOrdFinish(ordFin);
			}
			String ordNum = orderVO.getOrdNum();
		
			if(StringUtils.isBlank(ordNum)) {
				//등록
				int cnt = orderDao.insertOrder(orderVO);
				if(cnt > 0) {
					int cntCheck = orderDao.insertCheck(orderVO);
					successCnt++;
				}
			}else {
				//수정
				int cnt = orderDao.updateOrder(orderVO);
				if(cnt > 0) {
					successCnt++;
				}
			}
		}
		if(successCnt == orderVOList.size()) {
			result = ServiceResult.OK;
		}else {
			result = ServiceResult.FAILED;
		}
		return result;
	}

	
	/**
	 * 발주서 삭제
	 */
	@Transactional
	@Override
	public ServiceResult removeOrder(List<OrderVO> orderVOList) {
		int allcnt = 0;
		ServiceResult result = null;
		
		for(OrderVO orderVo : orderVOList) {
			List<OrderRawsVO> rawsList = orderDao.selectOrderRawsList(orderVo);

			if (rawsList.size() > 0) { //발주서의 원자재가 먼저 존재하면
				int cnt = orderDao.deleteAllOrdRaws(orderVo.getOrdNum());  //품목존재 시 먼저 전체 삭제
				if(cnt > 0) {
					int delCnt = orderDao.deleteOrder(orderVo);
					if(delCnt > 0) {
						allcnt++;
					}
				}
			}else {
				int delCnt = orderDao.deleteOrder(orderVo);
				if(delCnt > 0) {
					allcnt++;
				}
			}
		}
		if(allcnt == orderVOList.size()) {
			result = ServiceResult.OK;
		}else {
			result = ServiceResult.FAILED;
		}
		return result;
	}

/*****************************************************************************************************************/		
	//발주서 원자재 목록 조회
	@Override
	public List<OrderRawsVO> retrieveOrderRawsList(OrderVO orderVO) {
//		OrderVO saved = orderDao.selectOrder(orderVO);
//		if(saved == null) {
//			throw new PKNotFoundException(orderVO.getOrdNum() + "에 해당하는 품목 없음");
//		}
		return orderDao.selectOrderRawsList(orderVO);
	}

	//발주서 원자재 선택
	@Override
	public OrderRawsVO retrieveOrderRaws(OrderRawsVO orderRawsVo) {
		return orderDao.selectOrderRaws(orderRawsVo);
	}

	//발주서 원자재 등록 및 수정
	@Transactional
	@Override
	public ServiceResult createOrModifyRaws(List<OrderRawsVO> orderRawsVOList) {
		ServiceResult result = null;
		int successCnt = 0;
		
		for(OrderRawsVO ordRaws : orderRawsVOList) {
			Integer ordNo = ordRaws.getOrdNo();
			if(ordNo == null) {
				//등록
				int cnt = orderDao.insertOrderRaws(ordRaws);
				if(cnt > 0) {
					successCnt++;
				}
			}else {
				//수정
				int cnt = orderDao.updateOrderRaws(ordRaws);
				if(cnt > 0) {
					successCnt++;
				}
			}
		}
		if(successCnt == orderRawsVOList.size()) {
			result = ServiceResult.OK;
		}else {
			result = ServiceResult.FAILED;
		}
		return result;
	}

	//원자재 삭제
	@Transactional
	@Override
	public ServiceResult removeRaws(List<OrderRawsVO> ordRawsVOList) {
		ServiceResult result = null;
		int successCnt = 0;
		
		
		for(OrderRawsVO ordRaws : ordRawsVOList) {
			OrderRawsVO saved = orderDao.selectOrderRaws(ordRaws);
			if(saved != null) {
				int cnt = orderDao.deleteOrderRaws(ordRaws);
				if(cnt > 0) {
					successCnt++;
				}
			}else {
				result = ServiceResult.PKDUPLICATED;
			}
		}
		if(successCnt == ordRawsVOList.size()) {
			result = ServiceResult.OK;
		}else {
			result = ServiceResult.FAILED;
		}
		return result;
		}
}
