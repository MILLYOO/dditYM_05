package kr.or.ddit.stock.stockmove.service;

import java.util.List;

import javax.annotation.Resource;
import javax.inject.Inject;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.or.ddit.common.dao.DocCheckDAO;
import kr.or.ddit.common.vo.HubSearchVO;
import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.stock.stockmove.dao.StockMoveDAO;
import kr.or.ddit.stock.vo.StockMoveVO;
import kr.or.ddit.stock.vo.StomovItemVO;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class StockMoveServiceImpl implements StockMoveService {

	@Inject
	private StockMoveDAO dao;
	
	//재고이동서 조회
	@Override
	public List<StockMoveVO> retrieveStockMoveList(HubSearchVO hubSearch) {
		List<StockMoveVO> stockMoveList = dao.selectStockMoveList(hubSearch);
		return stockMoveList;
	}

	//재고이동서 등록
	@Transactional
	@Override
	public ServiceResult createStockMove(List<StockMoveVO> stockMoveList) {
		ServiceResult result = null;
		int successCnt = 0;
		for(StockMoveVO stockMoveVO : stockMoveList) {
			//날짜 set
			String year = stockMoveVO.getStockYy();
			String month = stockMoveVO.getStockMm();
			String day = stockMoveVO.getStockDd();
			String stmDate = year+"-"+month+"-"+day;
			stockMoveVO.setStmDate(stmDate);
			
			if(StringUtils.isBlank(stockMoveVO.getStmNum())) {
				// 등록
				int cnt = dao.insertStockMove(stockMoveVO);
				if(cnt > 0) {
					int cntCheck = dao.insertCheck(stockMoveVO);
					if(cntCheck > 0) {
						successCnt++;
					}
				}
			}else {
				// 수정
				int cnt = dao.updateStockMove(stockMoveVO);
				if(cnt > 0) {
					successCnt++;
				}
			}
		}
		if(successCnt == stockMoveList.size()) {
			result = ServiceResult.OK;
		}else {
			result = ServiceResult.FAILED;
		}
		return result;
	}

	@Transactional
	@Override
	public ServiceResult deleteStockMove(List<StockMoveVO> stockMoveList) {
		int allcnt = 0;
		ServiceResult result = null;
		
		for(StockMoveVO stockMoveVO : stockMoveList) {
		
			StomovItemVO stomovItemVO = new StomovItemVO();
			stomovItemVO.setStmNum(stockMoveVO.getStmNum());
			List<StomovItemVO> saved = dao.selectStockMoveItemList(stomovItemVO);
			
				if(saved.size() > 0) {//지우려고 하는 서류의 내용(품목 정보가 있을때)
					int cnt = dao.deleteAllStockMoveItem(stockMoveVO.getStmNum()); 	// 품목존재 시 먼저 전체 삭제
					//품목별 삭제가 아닌 전체삭제시 수 되돌리는 쿼리 ....
					
					if(cnt > 0) {
						int delCnt = dao.deleteStockMove(stockMoveVO);
						if(delCnt > 0) {
							allcnt++;
						}
					}
				}else {//품목 정보가 없을 때 : 그냥 지워버리면 된다.
					int delCnt = dao.deleteStockMove(stockMoveVO);
					if(delCnt > 0) {
						allcnt++;
					}
				}
		}
			if(allcnt == stockMoveList.size()) {
				result = ServiceResult.OK;
			}else {
				result = ServiceResult.FAILED;
			}
			return result;
	}

	//재고이동 상세조회
	@Override
	public List<StomovItemVO> retrieveStockMoveItemList(StomovItemVO stomovItemVO) {
		return dao.selectStockMoveItemList(stomovItemVO);
	}

	//재고이동 품목(상세) 등록
	@Override
	public ServiceResult createStockMoveItem(List<StomovItemVO> stomovItemList) {
		ServiceResult result = null;
		int successCnt = 0;
		int cnt = 0;
		int cntAll = 0;
		log.info("cnt0:{}",cnt);
		for(StomovItemVO stomovItemVO : stomovItemList) { //품목 수
			log.info("stomovItemVO:{}",stomovItemVO);
			Integer stmNo = stomovItemVO.getStmNo();
			if(stmNo == null) {
				cnt = dao.insertStockMoveItem(stomovItemVO);
				if(cnt > 0) {
					cntAll++;
				}
				if(stomovItemVO.getRawsCode() != null) { //원자재 일때
					cnt = dao.updateInRaws(stomovItemVO);//입고에 대해 창고 안 품목 수 증가
					if(cnt > 0) {
						cntAll++;
					}
					cnt = dao.updateOutRaws(stomovItemVO);//출고에 대해 창고 안 품목 수 감소
					if(cnt > 0) {
						cntAll++;
					}
				}else { // 제품 일때
					cnt = dao.updateInProd(stomovItemVO);//입고에 대해 창고 안 품목 수 증가
					if(cnt > 0) {
						cntAll++;
					}
					cnt = dao.updateOutProd(stomovItemVO);//출고에 대해 창고 안 품목 수 감소
					if(cnt > 0) {
						cntAll++;
					}
				}
				if(cntAll == 3){
					successCnt ++;
					cntAll = 0;
				}
			}else {
					//수정
					cnt = dao.updateStockMoveItem(stomovItemVO);
					if(stomovItemVO.getRawsCode() != null) {
						cnt = dao.updateInRaws(stomovItemVO);//입고에 대해 창고 안 품목 수 증가
						if(cnt > 0) {
							cntAll++;
						}
						cnt = dao.updateOutRaws(stomovItemVO);//출고에 대해 창고 안 품목 수 감소
						if(cnt > 0) {
							cntAll++;
						}
					}else {
						cnt = dao.updateInProd(stomovItemVO);//입고에 대해 창고 안 품목 수 증가
						if(cnt > 0) {
							cntAll++;
						}
						cnt = dao.updateOutProd(stomovItemVO);//출고에 대해 창고 안 품목 수 감소
						if(cnt > 0) {
							cntAll++;
						}
					}
					if(cntAll == 3) {
						successCnt ++;
						cntAll = 0;
					}
				}	
			}//품목 한개가 돌때마다 successCnt 가 1씩 증가한다
		if(successCnt == stomovItemList.size()) {
			result = ServiceResult.OK;
		}else {
			result = ServiceResult.FAILED;
		}
		return result;
	}

	//재고이동서 상세삭제
	@Override
	public ServiceResult deleteStockMoveItem(List<StomovItemVO> stomovItemList) {
		ServiceResult result = null;
		int successCnt = 0;
		int cnt1 = 0;
		int cnt2 = 0;
		int cntAll = 0;
		
		for(StomovItemVO stomovItemVO : stomovItemList) {
			log.info("stomovItemList:{}",stomovItemList);
			//삭제
			//1.문서삭제 
			cnt1 = dao.deleteStockMoveItem(stomovItemVO);
			//2.이동내역 돌리기
			if(stomovItemVO.getRawsCode() != null) {
				cnt2 = dao.updateInRawsRev(stomovItemVO);//입고에 대해 창고 안 품목 수 증가
				if(cnt2 > 0) {
					cntAll++;
				}
				cnt2 = dao.updateOutRawsRev(stomovItemVO);//출고에 대해 창고 안 품목 수 감소
				if(cnt2 > 0) {
					cntAll++;
				}
			}else {
				cnt2 = dao.updateInProdRev(stomovItemVO);//입고에 대해 창고 안 품목 수 증가
				if(cnt2 > 0) {
					cntAll++;
				}
				cnt2 = dao.updateOutProdRev(stomovItemVO);//출고에 대해 창고 안 품목 수 감소
				if(cnt2 > 0) {
					cntAll++;
				}
			}
			if(cnt1 > 0 && cntAll==2) {
				successCnt ++;
				cntAll = 0;
			}
		}
		
		if(successCnt == stomovItemList.size()) {
			result = ServiceResult.OK;
		}else {
			result = ServiceResult.FAILED;
		}
		return result;
	}


}
