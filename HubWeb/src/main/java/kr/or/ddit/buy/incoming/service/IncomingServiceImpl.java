package kr.or.ddit.buy.incoming.service;

import java.util.Calendar;
import java.util.List;

import javax.annotation.Resource;
import javax.inject.Inject;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.or.ddit.buy.incoming.dao.IncomingDAO;
import kr.or.ddit.buy.purchaseorder.dao.PurchaseOrderDAO;
import kr.or.ddit.buy.vo.IncomingRawsVO;
import kr.or.ddit.buy.vo.IncomingVO;
import kr.or.ddit.buy.vo.OrderRawsVO;
import kr.or.ddit.buy.vo.OrderVO;
import kr.or.ddit.common.dao.DocCheckDAO;
import kr.or.ddit.common.vo.HubSearchVO;
import kr.or.ddit.enumpkg.ServiceResult;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class IncomingServiceImpl implements IncomingService {

	@Inject
	private IncomingDAO incomingDao;

	@Resource(name="purchaseOrderDAO") //꼭꼭 앞글자 소문자로 해줘야됑
	private PurchaseOrderDAO orderDAO;

	/**
	 * 입고처리서 목록 조회
	 */
	@Override
	public List<IncomingVO> retrieveIncomingList(HubSearchVO hubSearchVO) {
		
		return incomingDao.selectIncomingList(hubSearchVO);
	}

	/**
	 * 입고처리서 등록(+수정)
	 */
	@Transactional
	@Override
	public ServiceResult createOrModifyIncoming(List<IncomingVO> incomingVOList) {


		ServiceResult result = null;
		int successCnt = 0;
		
		for(IncomingVO incomingVO : incomingVOList) {
			String year = incomingVO.getBuyYy();
			String month = incomingVO.getBuyMm();
			String day = incomingVO.getBuyDd();
			
			if(Integer.parseInt(month) < 10) {
				month = "0"+month;
			}else if(Integer.parseInt(day) < 10) {
				day = "0"+day;
			}
			String incDate = year+month+day;
			incomingVO.setIncDate(incDate);
			
		String incFin = incomingVO.getIncFinish();
		if(StringUtils.isNotBlank(incFin)) {
			incFin = incFin.toUpperCase();	// Y,N 대문자로 변경
			incomingVO.setIncFinish(incFin);
		}
		
		String incNum = incomingVO.getIncNum();
		if(StringUtils.isBlank(incNum)) {
			// 등록
			int cnt = incomingDao.insertIncoming(incomingVO);
			if(cnt > 0) {
				successCnt++;
				int cntCheck = incomingDao.insertCheck(incomingVO);
			}
		}else {
			// 수정
			IncomingVO saved = incomingDao.selectIncoming(incomingVO);
			String ordNum = saved.getOrdNum();
			if(StringUtils.isNotBlank(ordNum)) { //발주서 적용된 입고처리서
				if(!saved.getIcodeName().equals(incomingVO.getIcodeName())) {
					return ServiceResult.BADREQUEST;
				}
			}
			
			int cnt = incomingDao.updateIncoming(incomingVO);
			if(cnt > 0) {
				successCnt++;
			}
		}
	}
	if(successCnt == incomingVOList.size()) {
		result = ServiceResult.OK;
	}else {
		result = ServiceResult.FAILED;
	}
	return result;
	}

	/**
	 * 입고처리서 삭제
	 */
	@Transactional
	@Override
	public ServiceResult removeIncoming(List<IncomingVO> incomingVOList) {
		int allcnt = 0;
		ServiceResult result = null;
		
		for(IncomingVO incomingVO : incomingVOList) {
		
			if(StringUtils.isNotBlank(incomingVO.getIncNum())) {
				OrderVO od = new OrderVO();
				od.setOrdNum(incomingVO.getIncNum());
				OrderVO selectOd = incomingDao.selectOrderForInc(od);
				if(selectOd != null) {
					List<IncomingVO> incAppOdList = incomingDao.selectOdApplyInc(incomingVO);
					if(incAppOdList.size() == 1) {
						if(incAppOdList.get(0).getOrdNum().equals(selectOd.getOrdNum())) {
							OrderVO odVO = new OrderVO();
							odVO.setOrdNum(selectOd.getOrdNum());
							odVO.setOdAppYn("N");
							int updateOd = incomingDao.updateOrderApplyYn(odVO);
							if(updateOd <= 0) {
								allcnt--;
							}
						}
					}
				}
			}
			List<IncomingRawsVO> rawsList =  incomingDao.selectIncomingRawsList(incomingVO);
			
			if(rawsList.size() > 0) { //입고처리서에 품목이 존재하면
				int cnt = incomingDao.deleteAllIncRaws(incomingVO.getIncNum()); // 품목존재 시 먼저 전체 삭제
				if(cnt > 0) {
					int delCnt = incomingDao.deleteIncoming(incomingVO);
					if(delCnt > 0) {
						allcnt++;
					}
				}
			}else {
				int delCnt = incomingDao.deleteIncoming(incomingVO);
				if(delCnt > 0) {
					allcnt++;
				}
			}
		}
		if(allcnt == incomingVOList.size()) {
			result = ServiceResult.OK;
		}else {
			result = ServiceResult.FAILED;
		}
		return result;
	}

	//품목 목록 조회
	@Override
	public List<IncomingRawsVO> retrieveincomingRawsList(IncomingVO incomingVO) {
//		IncomingVO saved = incomingDao.selectIncoming(incomingVO);
//		if(saved == null) {
//			throw new PKNotFoundException(incomingVO.getIncNum() + "에 해당하는 품목 없음");
//		}
		return incomingDao.selectIncomingRawsList(incomingVO);
	}
	
	//품목 등록 및 수정
	@Transactional
	@Override
	public ServiceResult createOrModifyRaws(List<IncomingRawsVO> incomingRawsList) {
		ServiceResult result = null;
		int successCnt = 0;
		
		for(IncomingRawsVO incRaws : incomingRawsList) {
			Integer incNo = incRaws.getIncNo();
			if(incNo == null) {
				//등록
				int cnt = incomingDao.insertIncomingRaws(incRaws);
				if(cnt > 0) {
					successCnt++;
				}
			}else {
				//수정
				int cnt = incomingDao.updateIncomingRaws(incRaws);
				if(cnt > 0) {
					successCnt++;
				}
			}
		}
		if(successCnt == incomingRawsList.size()) {
			result = ServiceResult.OK;
		}else {
			result = ServiceResult.FAILED;
		}
		return result;
	}

	//품목 삭제
	@Transactional
	@Override
	public ServiceResult removeRaws(List<IncomingRawsVO> incomingRawslist) {
		ServiceResult result = null;
		int successCnt = 0;
		
		for(IncomingRawsVO incomingR : incomingRawslist) {
			IncomingRawsVO saved = incomingDao.selectIncomingRaws(incomingR);
			if(saved != null) {
				int cnt = incomingDao.deleteIncomingRaws(incomingR);
				if(cnt > 0) {
					successCnt++;
				}
			}else {
				result = ServiceResult.PKDUPLICATED;
			}
		}
		if(successCnt == incomingRawslist.size()) {
			result = ServiceResult.OK;
		}else {
			result = ServiceResult.FAILED;
		}
		return result;
	}

	@Override
	public IncomingVO retrieveIncoming(IncomingVO incomingVO) {
		return incomingDao.selectIncoming(incomingVO);
	}

	@Override
	public IncomingRawsVO retrieveIncomingRaws(IncomingRawsVO incomingRawsVO) {
		return incomingDao.selectIncomingRaws(incomingRawsVO);
	}

	
	// 발주서 적용 --------------------------------------------------------
	//발주서 목록 조회
	@Override
	public List<OrderVO> retrieveOrderListForInc(HubSearchVO hubSearchVO) {
		return incomingDao.selectOrderListForInc(hubSearchVO);
	}

	//발주서-원자재 목록 조회
	@Override
	public List<OrderRawsVO> retrieveOrderRawsListForInc(OrderVO orderVO) {
		return incomingDao.selectOrderRawsListforInc(orderVO);
	}
	
	//발주서적용
	@Transactional
	@Override
	public ServiceResult insertIncByOrd(List<OrderVO> orderVOList) {//////////////////왜 리스트로 담았는지????
		ServiceResult result = null;
		int successCnt = 0;
		int successRaw = 0;
		boolean flag = true;
		
		Calendar now = Calendar.getInstance();
		int yy = now.get(Calendar.YEAR);
		int mm = now.get(Calendar.MONTH)+1;
		int dd = now.get(Calendar.DATE);
		String incDate = yy+"/"+mm+"/"+dd;
		
		for(int i = 0; i < orderVOList.size(); i++) {
			List<OrderRawsVO> orderRawsList = orderDAO.selectOrderRawsList(orderVOList.get(i));
			if(orderRawsList.isEmpty()) {
				flag = false;
			}
		}
		if(flag) {
			for(OrderVO orderVO : orderVOList) {/////////////////////////
				OrderVO saved = orderDAO.selectOrder(orderVO); //발주서
				List<OrderRawsVO> odRaws = incomingDao.selectOrderRawsListforInc(saved); //발주서-원자재
				
				if(saved != null) {//발주서 존재 시
					
					// 발주서 정보를 입고처리서에 저장
					IncomingVO incoming = new IncomingVO();
					incoming.setIncDate(incDate);
					incoming.setBuyerCode(saved.getBuyerCode());
					incoming.setBuyerName(saved.getBuyerName());
					incoming.setDeptName(saved.getDeptName());
					incoming.setEmpName(saved.getEmpName());
					incoming.setIncVat(saved.getOrdVat());
					incoming.setIncResult(saved.getOrdResult());
					incoming.setIncFinish(saved.getOrdFinish());
					
					int cnt = incomingDao.insertIncoming(incoming);
					if(cnt > 0) {
						int cntCheck = incomingDao.insertCheck(incoming);
						// 발주서-원자재 정보를 입고처리서-원자재에 저장
						for(OrderRawsVO orderRaws : odRaws) {
								IncomingRawsVO incRaw = new IncomingRawsVO();
								incRaw.setIncNum(incoming.getIncNum());
								incRaw.setRawsCode(orderRaws.getRawsCode());
								incRaw.setRawsName(orderRaws.getRawsName());
								incRaw.setGcommName(orderRaws.getGcommName());
								incRaw.setUcommName(orderRaws.getUcommName());
								incRaw.setIrDate(orderRaws.getOrrDate());
								incRaw.setIrQty(orderRaws.getOrrQty());
								incRaw.setIrUprice(orderRaws.getOrrUprice());
								incRaw.setIrSp(orderRaws.getOrrSp());
								incRaw.setIrVat(orderRaws.getOrrVat());
								incRaw.setWarName(orderRaws.getWarName());
								incRaw.setIrSumcost(orderRaws.getOrrSumcost());
								incRaw.setProjName(orderRaws.getProjName());
								
								int rawsCnt = incomingDao.insertIncomingRaws(incRaw);
								if(rawsCnt > 0) {
									successRaw++;
								}
								
						} // for 끝
						
						if(successRaw == odRaws.size()) {
							successCnt++;
						}
						
					}else {
						return ServiceResult.FAILED;
					}	
				}
			}
		}
		
		if(orderVOList.size() == successCnt) {
			result = ServiceResult.OK;
		}else {
			result = ServiceResult.FAILED;
		}
		return result;
	}
	
	
	/**
	 * 입고처리서 - 발주서 전개 등록
	 */
	@Override
	@Transactional
	public ServiceResult createIncRawsByOrdApply(List<OrderRawsVO> orderRawsList) {
		ServiceResult result = null;
		int successCnt = 0;
		
		for(OrderRawsVO orderRawsVO : orderRawsList) {
			IncomingVO incoming = new IncomingVO();
			incoming.setIncNum(orderRawsVO.getOrdNum());
			IncomingVO saved = incomingDao.selectIncoming(incoming);
			
			Integer incQty = saved.getIrQty();
			if(incQty == null) {
				incQty = 1;
			}
			orderRawsVO.setOrrQty(incQty);
			
			//발주서 원재료 목록 조회
			List<OrderRawsVO> selectOrderRawsList = incomingDao.selectOrderRawsForInc(orderRawsVO.getOrdNum());
			
			if(selectOrderRawsList.isEmpty()) {
				result = ServiceResult.PKDUPLICATED;
			}else {
				IncomingVO incomingVO = new IncomingVO();
				incomingVO.setIncNum(orderRawsVO.getIncNum());
				List<IncomingRawsVO> incRawsList = retrieveincomingRawsList(incomingVO);
				
				if(!incRawsList.isEmpty()) {
					int delCnt = incomingDao.deleteAllIncRaws(orderRawsVO.getIncNum());
				}
				for(int i = 0; i < selectOrderRawsList.size(); i++) {
					selectOrderRawsList.get(i).setIncNum(orderRawsVO.getIncNum());
				}
				orderRawsVO.setOrderRawsList(orderRawsList);
				int cnt = incomingDao.insertIncRawsByOrdApply(orderRawsVO);
				
				if(cnt > 0) {
					successCnt++;
				}
			}
		}
		if(successCnt == orderRawsList.size()) {
			result = ServiceResult.OK;
		}else {
			result = ServiceResult.FAILED;
		}
		return result;
	}
}
	
