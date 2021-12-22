package kr.or.ddit.stock.inoutadjustment.service;

import java.util.List;

import javax.annotation.Resource;
import javax.inject.Inject;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.or.ddit.common.dao.DocCheckDAO;
import kr.or.ddit.common.vo.HubSearchVO;
import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.stock.inoutadjustment.dao.InOutAdjustmentDAO;
import kr.or.ddit.stock.vo.AdjItemVO;
import kr.or.ddit.stock.vo.AdjustmentVO;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class InOutAdjustmentServiceImpl implements InOutAdjustmentService {

	@Inject
	private InOutAdjustmentDAO dao;
	
	
	
	//입출고 목록 조회
	@Override
	public List<AdjustmentVO> retrieveInOutAdjustmentList(HubSearchVO hubSearch) {
		
		List<AdjustmentVO> inOutList = dao.selectInOutAdjustmentList(hubSearch);
		return inOutList;
	}

	//입출고 등록
	@Transactional
	@Override
	public ServiceResult createOrUpdateInOutAdjustment(List<AdjustmentVO> list) {
		ServiceResult result = null;
		int successCnt = 0;
		for(AdjustmentVO adjustment : list) {
			//날짜 set
			String year = adjustment.getStockYy();
			String month = adjustment.getStockMm();
			String day = adjustment.getStockDd();
			String adjDate = year+"-"+month+"-"+day;
			adjustment.setAdjDate(adjDate);
			
			if(StringUtils.isBlank(adjustment.getAdjNum())) {
				// 등록
				int cnt = dao.insertInOutAdjustmentList(adjustment);
				if(cnt > 0) {
					int cntCheck = dao.insertCheck(adjustment);
					if(cntCheck > 0) {
						successCnt++;
					}
				}
			}else {
				// 수정
				int cnt = dao.updateInOutAdjustmentList(adjustment);
				if(cnt > 0) {
					successCnt++;
				}
			}
		}
		if(successCnt == list.size()) {
			result = ServiceResult.OK;
		}else {
			result = ServiceResult.FAILED;
		}
		return result;
	}


	//입출고조정 삭제
	@Transactional
	@Override
	public ServiceResult deleteInOutAdjustment(List<AdjustmentVO> adjustmentList) {
		int allcnt = 0;
		ServiceResult result = null;
		
		for(AdjustmentVO adjustment : adjustmentList) {
		
			AdjItemVO adjItemVO = new AdjItemVO();
			adjItemVO.setAdjNum(adjustment.getAdjNum());		
			List<AdjItemVO> saved = dao.selectInOutItemList(adjItemVO);
			
				if(saved.size() > 0) {
					int cnt = dao.deleteAllAdjustment(adjustment.getAdjNum()); 	// 품목존재 시 먼저 전체 삭제
					//품목별 삭제가 아닌 전체삭제시 수 되돌리는 쿼리 ....
					
					if(cnt > 0) {
						int delCnt = dao.deleteInOutAdjustmentList(adjustment);
						if(delCnt > 0) {
							allcnt++;
						}
					}
				}else {
					int delCnt = dao.deleteInOutAdjustmentList(adjustment);
					if(delCnt > 0) {
						allcnt++;
					}
				}
		}
			if(allcnt == adjustmentList.size()) {
				result = ServiceResult.OK;
			}else {
				result = ServiceResult.FAILED;
			}
			return result;
	}

	//입출고조정 상세----------------------------------------------------------------
	@Override
	public List<AdjItemVO> inOutAdjustmentItemList(AdjItemVO adjItem) {
//		String adjNum = adjItem.getAdjNum();
//		AdjustmentVO adjustmentVO = new AdjustmentVO();
//		adjustmentVO.setAdjNum(adjNum);
//		AdjustmentVO inOutList = dao.selectInOutAdjustment(adjustmentVO);
//		if(inOutList == null) {
//			throw new PKNotFoundException(adjItem.getAdjNum() + "에 해당하는 품목 없음");
//		}
		return dao.selectInOutItemList(adjItem);
	}

	// 상세 등록
	@Override
	@Transactional
	public ServiceResult createOrUpdateItem(List<AdjItemVO> adjItemList) {
		ServiceResult result = null;
		int successCnt = 0;
		int cnt1 = 0;
		for(AdjItemVO adjItem : adjItemList) {
			Integer adjNo = adjItem.getAdjNo();
			if(adjNo == null) {// 등록
				cnt1 = dao.insertInOutItem(adjItem);
				if((adjItem.getIcodeName()).equals("제품")&&(adjItem.getAdjSort()).equals("입고")) {
					cnt1 += dao.updateProdEnter(adjItem);//제품 테이블 수량 증가
					cnt1 += dao.updateWarProdEnter(adjItem);//제품-창고 테이블 수량 증가
				}else if((adjItem.getIcodeName()).equals("제품")&&(adjItem.getAdjSort()).equals("출고")){
					cnt1 += dao.updateProdOut(adjItem);//제품 테이블 수량 감소
					cnt1 += dao.updateWarProdOut(adjItem);//제품-창고 테이블 수량 감소
				}else if(!(adjItem.getIcodeName()).equals("제품")&&(adjItem.getAdjSort()).equals("입고")){
					cnt1 += dao.updateRawsEnter(adjItem);//원자재 테이블 수량 증가
					cnt1 += dao.updateWarRawsEnter(adjItem);//원자재-창고 테이블 수량 증가
				}else {
					cnt1 += dao.updateRawsOut(adjItem);//원자재 테이블 수량 감소
					cnt1 += dao.updateWarRawsOut(adjItem);//원자재-창고 테이블 수량 감소
				}
				if(cnt1 == 3) {
					successCnt++;
				}
			}else {// 수정
				cnt1 = dao.updateInOutItem(adjItem);
				if((adjItem.getIcodeName()).equals("제품")&&(adjItem.getAdjSort()).equals("입고")) {
					cnt1 += dao.updateProdEnter(adjItem);//제품 테이블 수량 증가
					cnt1 += dao.updateWarProdEnter(adjItem);//제품-창고 테이블 수량 증가
				}else if((adjItem.getIcodeName()).equals("제품")&&(adjItem.getAdjSort()).equals("출고")){
					cnt1 += dao.updateProdOut(adjItem);//제품 테이블 수량 감소
					cnt1 += dao.updateWarProdOut(adjItem);//제품-창고 테이블 수량 감소
				}else if(!(adjItem.getIcodeName()).equals("제품")&&(adjItem.getAdjSort()).equals("입고")){
					cnt1 += dao.updateRawsEnter(adjItem);//원자재 테이블 수량 증가
					cnt1 += dao.updateWarRawsEnter(adjItem);//원자재-창고 테이블 수량 증가
				}else {
					cnt1 += dao.updateRawsOut(adjItem);//원자재 테이블 수량 감소
					cnt1 += dao.updateWarRawsOut(adjItem);//원자재-창고 테이블 수량 감소
				}
				if(cnt1 == 3) {
					successCnt++;
				}
			}
		}
		if(successCnt == adjItemList.size()) {
			result = ServiceResult.OK;
		}else {
			result = ServiceResult.FAILED;
		}
		return result;
	}

	
	//상세 삭제
	@Override
	public ServiceResult deleteItem(List<AdjItemVO> adjItemList) {
		ServiceResult result = null;
		int successCnt = 0;
		int cnt1 = 0;
		int cnt2 = 0;
		
		for(AdjItemVO adjItem : adjItemList) {
			// 등록
			if((adjItem.getIcodeName()).equals("제품")&&(adjItem.getAdjSort()).equals("입고")) {
				//제품 테이블 수량 증가
				cnt1 = dao.deleteProdEnter(adjItem);
				//제품-창고 테이블 수량 증가
				cnt2 = dao.deleteWarProdEnter(adjItem);
			}else if((adjItem.getIcodeName()).equals("제품")&&(adjItem.getAdjSort()).equals("출고")){
				//제품 테이블 수량 감소
				cnt1 = dao.deleteProdOut(adjItem);
				//제품-창고 테이블 수량 감소
				cnt2 = dao.deleteWarProdOut(adjItem);
			}else if(!(adjItem.getIcodeName()).equals("제품")&&(adjItem.getAdjSort()).equals("입고")){
				//원자재 테이블 수량 증가
				cnt1 = dao.deleteRawsEnter(adjItem);
				//원자재-창고 테이블 수량 증가
				cnt2 = dao.deleteWarRawsEnter(adjItem);
			}else {
				//원자재 테이블 수량 감소
				cnt1 = dao.deleteRawsOut(adjItem);
				//원자재-창고 테이블 수량 감소
				cnt2 = dao.deleteWarRawsOut(adjItem);
			}
			if(cnt1 > 0 && cnt2 > 0) {
				successCnt ++;
			}
		}
		if(successCnt == adjItemList.size()) {
			result = ServiceResult.OK;
		}else {
			result = ServiceResult.FAILED;
		}
		return result;
	}

	//입출고조정 한개 조회
//	@Override
//	public AdjustmentVO retrieveInOutAdjustment(AdjustmentVO adjustmentVO) {
//		return dao.selectInOutAdjustment(adjustmentVO);
//	}

	//입출고조정-품목 한개 조회
//	@Override
//	public AdjItemVO retrieveInOutItem(AdjItemVO adjItemVO) {
//		return dao.selectInOutItem(adjItemVO);
//	}

	@Override
	public ServiceResult updateItem(AdjItemVO adjItem) {
		// TODO Auto-generated method stub
		return null;
	}

	

}
