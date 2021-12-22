package kr.or.ddit.buy.purchclose.service;

import java.util.Calendar;
import java.util.List;

import javax.annotation.Resource;
import javax.inject.Inject;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.or.ddit.buy.incoming.dao.IncomingDAO;
import kr.or.ddit.buy.purchclose.dao.PurchCloseDAO;
import kr.or.ddit.buy.vo.CloseRawsVO;
import kr.or.ddit.buy.vo.IncomingRawsVO;
import kr.or.ddit.buy.vo.IncomingVO;
import kr.or.ddit.buy.vo.PurchCloseVO;
import kr.or.ddit.common.dao.DocCheckDAO;
import kr.or.ddit.common.vo.HubSearchVO;
import kr.or.ddit.enumpkg.ServiceResult;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class PurchCloseServiceImpl implements PurchCloseService {
	
	@Inject
	private PurchCloseDAO purchCloseDAO;

	@Resource(name="incomingDAO")
	private IncomingDAO incDAO;
	
	/**
	 * 매입마감서 목록 조회
	 */
	@Override
	public List<PurchCloseVO> retrievePurchCloseList(HubSearchVO hubSearch) {
		return purchCloseDAO.selectPurchCloseList(hubSearch);
	}

	/**
	 * 매입마감서 선택###############
	 */
	@Override
	public PurchCloseVO retrievePurchClose(PurchCloseVO purchCloseVO) {
		return purchCloseDAO.selectPclose(purchCloseVO);
	}

	/**
	 * 매입마감서"등록"이긴한데...이거 굳이 필요없는데.... 필요없지 않아!!!!!!!!! 저장은 되야되니까 얘가 쓰영!!!
	 */
	@Transactional
	@Override
	public ServiceResult createOrModifyPurchClose(List<PurchCloseVO> purchCloseVOList) {
		
		ServiceResult result = null;
		int successCnt = 0;
		
		for(PurchCloseVO purchCloseVO : purchCloseVOList) {
			String year = purchCloseVO.getBuyYy();
			String month = purchCloseVO.getBuyMm();
			String day = purchCloseVO.getBuyDd();
			String docDate = year+"-"+month+"-"+day;
			purchCloseVO.setDocDate(docDate);
		
		String purVat = purchCloseVO.getPurVat();
		if(StringUtils.isNotBlank(purVat)) {
			purVat = purVat.toUpperCase();	// Y,N 대문자로 변경
			purchCloseVO.setPurVat(purVat);
		}
		String purNum = purchCloseVO.getPurNum();
				
		if(StringUtils.isBlank(purNum)) {
			// 등록
			int cnt = purchCloseDAO.insertPclose(purchCloseVO);
		    if(cnt > 0) {
		    	int cntCheck = purchCloseDAO.insertCheck(purchCloseVO);
			    	if (cntCheck > 0) {
			    		successCnt++;
					}
			}
		}else {
			// 수정
			int cnt = purchCloseDAO.updatePclose(purchCloseVO);
			if(cnt > 0) {
						successCnt++;
			}
		}
	}
	if(successCnt == purchCloseVOList.size()) {
		result = ServiceResult.OK;
	}else {
		result = ServiceResult.FAILED;
	}
	return result;
	}
	
	/**
	 * 매입마감서 삭제
	 */
	@Transactional
	@Override
	public ServiceResult removePurchClose(List<PurchCloseVO> purchCloseVOList) {
		int allcnt = 0;
		ServiceResult result = null;
		
		for(PurchCloseVO purCloseVO : purchCloseVOList) {
			
			List<CloseRawsVO> rawsList = purchCloseDAO.selectPcloseRawsList(purCloseVO);
			
			if(rawsList.size() > 0) { //매입마감서의 품목이 존재하면
				int cnt = purchCloseDAO.deleteAllPcloseRaws(purCloseVO.getPurNum());
				if(cnt > 0) {
					int delCnt = purchCloseDAO.deletePclose(purCloseVO);
					if(delCnt > 0) {
						allcnt++;
					}
				}
			}else {
				int delCnt = purchCloseDAO.deletePclose(purCloseVO);
				if(delCnt > 0) {
					allcnt++;
				}
			}
		}
		if(allcnt == purchCloseVOList.size()) {
			result = ServiceResult.OK;
		}else {
			result = ServiceResult.FAILED;
		}
		return result;
	}

	
	
	
	
	
	
	
	
	/**
	 * 원자재 목록 조회
	 */
	@Override
	public List<CloseRawsVO>  retrievePurchCloseRawsList(PurchCloseVO purchCloseVO) {
		return purchCloseDAO.selectPcloseRawsList(purchCloseVO);
	}

	
	public CloseRawsVO retrieveCloseRaws(CloseRawsVO closeRaws) {
		return purchCloseDAO.selectPcloseRaws(closeRaws);
	}
	
	/**
	 * 원자재 등록 및 수정
	 * @param closeRaws
	 * @return
	 */
	@Override
	public ServiceResult createOrUpdateRaws(List<CloseRawsVO> closeRawsVOList) {
		ServiceResult result = null;
		int successCnt = 0;
		
		for(CloseRawsVO closRaws : closeRawsVOList){
			Integer purNo = closRaws.getPurNo();
			if(purNo == null) {
				//등록
				int cnt = purchCloseDAO.insertPcloseRaws(closRaws);
				if(cnt > 0) {
					successCnt++;
				}
			}else {
				//수정
				int cnt = purchCloseDAO.updatePcloseRaws(closRaws);
				if(cnt > 0) {
					successCnt++;
				}
			}
		}
		if(successCnt == closeRawsVOList.size()) {
			result = ServiceResult.OK;
		}else {
			result = ServiceResult.FAILED;
		}
		return result;
	}
	
	/**
	 * 원자재 삭제
	 * @param closeRaws
	 * @return
	 */
	@Transactional
	@Override
	public ServiceResult deleteRaws(List<CloseRawsVO> closeRawsVOList) {
		ServiceResult result = null;
		int successCnt = 0;
		
		for(CloseRawsVO closRaws : closeRawsVOList) {
			CloseRawsVO saved = purchCloseDAO.selectPcloseRaws(closRaws);
			if(saved != null) {
				int cnt = purchCloseDAO.deletePcloseRaws(closRaws);
				if(cnt > 0) {
					successCnt++;
				}
			}else {
				result = ServiceResult.PKDUPLICATED;
			}
		}
		if(successCnt == closeRawsVOList.size()) {
			result = ServiceResult.OK;
		}else {
			result = ServiceResult.FAILED;
		}
		return result;
	}
	
	
	
	
	
//입고처리서 적용-----------------------------------------------------------------------------------------
	//입고처리서 목록 조회
	@Override
	public List<IncomingVO> retrieveIncListForPclose(HubSearchVO hubSearchVO) {
		return purchCloseDAO.selectIncListForPclose(hubSearchVO);
	}
	
	//입고처리서 - 원자재 목록 조회
	@Override
	public List<IncomingRawsVO> retrieveIncRawsListForPclose(IncomingVO incomingVO) {
		return purchCloseDAO.selectIncRawsListForPclose(incomingVO);
	}
	
	
	//입고처리서 적용
	@Transactional
	@Override
	public ServiceResult insertPcloseByInc(List<IncomingVO> incomingVOList) {
		ServiceResult result = null;
		int successCnt = 0;
		int successRaw = 0;
		boolean flag = true;

		Calendar now = Calendar.getInstance();
		int yy = now.get(Calendar.YEAR);
		int mm = now.get(Calendar.MONTH)+1;
		int dd = now.get(Calendar.DATE);
		String docDate = yy+""+mm+""+dd;
		
		for(int i = 0; i < incomingVOList.size(); i++) {
			List<IncomingRawsVO> incomingRawsList = incDAO.selectIncomingRawsList(incomingVOList.get(i));
			if(incomingRawsList.isEmpty()) {
				flag = false; //원자재가 없으면 적용 안 되는 거 말하는 거야
			}
		}
		if(flag) {
			for(IncomingVO incomingVO : incomingVOList) {
				IncomingVO saved = incDAO.selectIncoming(incomingVO); //입고처리서
//				List<IncomingVO> incList = purchCloseDAO.selectIncListForPclose(incomingVO); 
				List<IncomingRawsVO> incRaws = purchCloseDAO.selectIncRawsListForPclose(saved); //입고처리서 - 원자재
				if(saved != null) { //입고처리서 존재 시
					
					//입고처리서 정보를 매입마감서에 저장
					PurchCloseVO purchClose = new PurchCloseVO();
					purchClose.setDocDate(docDate);
					purchClose.setBuyerCode(saved.getBuyerCode());
					purchClose.setBuyerName(saved.getBuyerName());
					purchClose.setDeptName(saved.getDeptName());
					purchClose.setEmpName(saved.getEmpName());
					purchClose.setPurVat(saved.getIncVat());
				
				int cnt = purchCloseDAO.insertPclose(purchClose);
				String purNum = purchClose.getPurNum(); //얘 따로 빼줘서 purnum 만든 거 집어넣어주는 거야
				
				if(cnt > 0) {
					int cntCheck = purchCloseDAO.insertCheck(purchClose);
					//입고처리서-원자재 정보를 매입마감서-원자재에 저장
					for(IncomingRawsVO incoming : incRaws) {
						CloseRawsVO closeRawsVO = new CloseRawsVO(); 
						closeRawsVO.setRawsCode(incoming.getRawsCode());
						closeRawsVO.setRawsName(incoming.getRawsName());
						closeRawsVO.setGcommName(incoming.getGcommName());
						closeRawsVO.setUcommName(incoming.getUcommName());
						closeRawsVO.setPcrQty(incoming.getIrQty());
						closeRawsVO.setPrcUprice(incoming.getIrUprice());
						closeRawsVO.setPrcSp(incoming.getIrSp());
						closeRawsVO.setPrcVat(incoming.getIrVat());
						closeRawsVO.setPrcSumcost(incoming.getIrSumcost());
						closeRawsVO.setProjName(incoming.getProjName());
						closeRawsVO.setWarName(incoming.getWarName());
						closeRawsVO.setPurNum(purNum);
						
						int rawsCnt = purchCloseDAO.insertPcloseRaws(closeRawsVO);
						if(rawsCnt > 0) {
							successRaw++;
						}
					}//for끝
					
					if(successRaw == incRaws.size()) {
						successCnt++;
					}
				}else {
					return ServiceResult.FAILED;
				}
			}
		}
	}
				
	if(incomingVOList.size() == successCnt) {
			result = ServiceResult.OK;
		}else {
			result = ServiceResult.FAILED;
		}
		return result;
		}

	}

		