package kr.or.ddit.stock.stockvaluation.service;

import java.util.List;

import javax.annotation.Resource;
import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.or.ddit.common.dao.DocCheckDAO;
import kr.or.ddit.common.vo.HubSearchVO;
import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.stock.stockvaluation.dao.InventoryValuationDAO;
import kr.or.ddit.stock.vo.StoValuVO;
import kr.or.ddit.stock.vo.StovalItemVO;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class InventoryValuationServiceImpl implements InventoryValuationService {

	@Inject 
	private InventoryValuationDAO dao;
	
	//재고평가를 완료한 리스트 조회
	@Override
	public List<StoValuVO> retrieveInventoryValuationList(HubSearchVO hunSearchVO) {
		log.info("서비스에 왔다");
		return dao.selectValuationList(hunSearchVO);
	}

	//재고평가하기(등록하기)
	//등록은 무조건 한개씩이다.
	@Transactional
	@Override
	public ServiceResult createInventoryValuation(StoValuVO stoValuVO) {
		ServiceResult result = null;
		int cnt = 0;
		//재고평가문서 등록
		cnt = dao.insertValuation(stoValuVO);
		if(cnt > 0) {
			int cntCheck = dao.insertCheck(stoValuVO);
		}
		//재고평가 결과 조회해오기
		log.info("stoValuVO:{}",stoValuVO);
		List<StovalItemVO> stovalItemList = dao.selectValuationResult(stoValuVO);
		log.info("stovalItemList:{}",stovalItemList);
		//조회 결과를 insert 하기
		if(stovalItemList != null) {
			for(StovalItemVO stovalItemVO : stovalItemList) {
				stovalItemVO.setStvNum(stoValuVO.getStvNum());
				cnt += dao.insertValuationResult(stovalItemVO);
			}
		}
		if(cnt == stovalItemList.size() + 1) {
			result = ServiceResult.OK;
		}else {
			result = ServiceResult.FAILED;
		}
		return result;
	}

//	@Transactional
//	@Override
//	public ServiceResult createValuationResult(StoValuVO stoValuVO) {
//		ServiceResult result = null;
//		int cnt = 0;
//		
//		//방금 등록한 재고평가문서 번호 가져오기
//		StoValuVO recentDoc = dao.selectRecentDoc();
//		stoValuVO.setStvNum(recentDoc.getStvNum());
//		//재고평가 결과 조회해오기
//		List<StovalItemVO> stovalItemList = dao.selectValuationResult(stoValuVO);
//		//조회 결과를 insert 하기
//		if(stovalItemList != null) {
//			for(StovalItemVO stovalItemVO : stovalItemList) {
//				cnt += dao.insertValuationResult(stovalItemVO);
//			}
//		}
//		
//		if(cnt == stovalItemList.size()) {
//			result = ServiceResult.OK;
//		}else {
//			result = ServiceResult.FAILED;
//		}
//		return result;
//	}

	@Override
	public ServiceResult deleteInventoryValuation(StoValuVO stoValuVO) {
		ServiceResult result = null;
		
		int cnt = dao.deleteInventoryValuation(stoValuVO);
		
		if(cnt > 0) {
			result = ServiceResult.OK;
		}else {
			result = ServiceResult.FAILED;
		}
		return result;
	}

	
	//재고평가서 결과 조회
	@Override
	public List<StovalItemVO> inventoryValuationItemList(StovalItemVO stovalItemVO) {
		return dao.selectValuationItemList(stovalItemVO);
	}

}
