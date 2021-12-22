package kr.or.ddit.sales.estimate.service;

import java.util.List;

import javax.annotation.Resource;
import javax.inject.Inject;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.or.ddit.common.dao.DocCheckDAO;
import kr.or.ddit.common.vo.HubSearchVO;
import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.sales.estimate.dao.EstimateDAO;
import kr.or.ddit.sales.vo.EstimateProdVO;
import kr.or.ddit.sales.vo.EstimateVO;

@Service
public class EstimateServiceImpl implements EstimateService {

	@Inject
	private EstimateDAO dao;
	@Resource(name="docCheckDAO")
	private DocCheckDAO docCheckDAO;
	
	
		// 견적서 전체 선택
		@Override
		public List<EstimateVO> retrieveEstimateList(HubSearchVO hubSearchVO) {
			List<EstimateVO> estimateList = dao.selectEstimateList(hubSearchVO);
			return estimateList;
		}
		
		// 견적서 등록 및 수정
		@Override
		@Transactional
		public ServiceResult createORupdateEstimate(List<EstimateVO> estimateList) {
			// 견적서 등록 결과 값
			ServiceResult result = null;
			// 견적 코드
			String estCode = null;
			// 견적서 리스트를 검증
			if (estimateList != null) {
				for (EstimateVO estimate : estimateList) {
					estCode = estimate.getEstCode();
					if (!StringUtils.isNotBlank(estCode)) {
						int rowcnt = dao.insertEstimate(estimate);
						estCode = estimate.getEstCode();
						if (rowcnt > 0) {
							// 견적서 등록 성공시 견적서 제품이 있으면 등록
							int cntCheck = dao.insertCheck(estimate);
							List<EstimateProdVO> estimateProdList = estimate.getDataProdList(); // 견적서 제품 리스트
							if (estimateProdList != null) {
								for (EstimateProdVO estimateProdVO : estimateProdList) {
									// 견적서 번호
									Integer estNO = estimateProdVO.getEstNo();
									estimateProdVO.setEstCode(estCode);
										// 견적서 번호 없으면
										if (estNO == null) {
											 rowcnt = dao.insertEstimateProd(estimateProdVO);
											if (rowcnt > 0) {
												result = ServiceResult.OK;
											}else {
												result = ServiceResult.FAILED;
											}
										}else {
											// 견적서 번호가 있으면
											 rowcnt = dao.updateEstimateProd(estimateProdVO);
											 if (rowcnt > 0) {
												result = ServiceResult.OK;
											 }else {
												result = ServiceResult.FAILED;
											 }
										}
								} // 제품 반복문 끝
							} // 제품 조건문 끝
							if (rowcnt > 0) {
								result = ServiceResult.OK;
							 }else {
								result = ServiceResult.FAILED;
							 }
						} // 견적서 등록 조건문 끝
						
						
					}else {
						int rowcnt = dao.updateEstimate(estimate);
						if(rowcnt > 0 ) {
							result = ServiceResult.OK;
						}else {
							result = ServiceResult.FAILED;
						}
						
					}
				}//반복문 끝
			}else {
				
//			int rowcnt = dao.insertEstimate(estimateList);
//			if(rowcnt > 0) {
//				result = ServiceResult.OK;
//			}else {
//				result = ServiceResult.FAILED;
//			}
			}
			return result;
		}
	// 견적서 수정
	@Override
	@Transactional
	public ServiceResult updateEstimate(EstimateVO estimateVO) {
		ServiceResult result = null;
		
		int rowcnt = dao.updateEstimate(estimateVO);
		
		if(rowcnt > 0) {
			result = ServiceResult.OK;
		}else {
			result = ServiceResult.FAILED;
		}
		return result;
	}
	
	// 견적서 삭제
	@Transactional
	@Override
	public ServiceResult deleteEstimate(EstimateVO estimateVO) {

		ServiceResult result = null;
		
		int rowcnt = dao.deleteEstimate(estimateVO);
		
		if(rowcnt > 0) {
			result = ServiceResult.OK;
		}else {
			result = ServiceResult.FAILED;
		}
		return result;
	}
	
	// 견적서 제품 조회
	@Override
	public List<EstimateProdVO> retrieveEstimateProdList(EstimateVO estimateVO) {

		List<EstimateProdVO> estimateProdList = dao.selectEstimateProdList(estimateVO);
		
		return estimateProdList;
	}

	// 견적서 제품 추가
	@Override
	@Transactional
	public ServiceResult createRaws(List<EstimateProdVO> estimateProdVO) {

		ServiceResult result = null; // 결과값
		// 견적서 제품 리스트에서 견적서 제품 한건씩 반복문 실행
		for (EstimateProdVO estimateProd : estimateProdVO) {
				Integer estNo = estimateProd.getEstNo(); // 견적서 제품에서 견적서 번호 꺼내서 초기화
					if (estNo == null) {
						// 견적서 제품 추가
						int rowcnt = dao.insertEstimateProd(estimateProd);
							if (rowcnt > 0) {
								result = ServiceResult.OK;
							}else {
								result = ServiceResult.FAILED;
							} // if문 끝
							
					}else {
						// 견적서 제품이 있을 경우
						int rowcnt = dao.updateEstimateProd(estimateProd);
						if (rowcnt > 0) {
							result = ServiceResult.OK;
						}else {
							result = ServiceResult.FAILED;
						}
					} // if문 끝
		} // for문 끝
		return result;
	}

	@Override
	public ServiceResult updateRaws(EstimateProdVO estimateProd) {
		// TODO Auto-generated method stub
		return null;
	}

	// 견적서 제품 삭제
	@Override
	@Transactional
	public ServiceResult deleteRaws(List<EstimateProdVO> estimateProdVO) {
		
		ServiceResult result = null;
		// 견적서 제품 삭제 반복문
		for (EstimateProdVO estimateProd : estimateProdVO) { // 견적서 제품 리스트에서 하나씩 반복문으로 실행
			int rowcnt = dao.deleteEstimateProd(estimateProd);
				if (rowcnt > 0) {
					result = ServiceResult.OK;
				}else {
					result = ServiceResult.FAILED;
				} // 조건문 끝
		} // 반복문 끝
		return result;
	}

	

	@Override
	public EstimateVO selectEstimate(EstimateVO estimate) {
		return dao.selectEstimate(estimate);
	}

	@Override
	public EstimateProdVO selectEstmateProd(EstimateProdVO estimateProd) {
		return dao.selectEstimateProd(estimateProd);
	}


}
