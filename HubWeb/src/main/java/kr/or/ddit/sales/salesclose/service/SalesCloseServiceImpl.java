package kr.or.ddit.sales.salesclose.service;

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
import kr.or.ddit.sales.salesclose.dao.SalesCloseDAO;
import kr.or.ddit.sales.vo.RelProdVO;
import kr.or.ddit.sales.vo.ReleaseVO;
import kr.or.ddit.sales.vo.SalProdVO;
import kr.or.ddit.sales.vo.SalesCloseVO;

@Service
public class SalesCloseServiceImpl implements SalesCloseService {
	
	// 매출마감 다오
	@Inject
	private SalesCloseDAO dao;
	
	
	// 매출마감 전체 선택
	@Override
	public List<SalesCloseVO> retrieveSalesCloseList(HubSearchVO hubSearchVO) {
		return dao.selectSalesCloseList(hubSearchVO);
	}


	// 매출마감서 등록 및 수정
	@Override
	@Transactional
	public ServiceResult createORupdateSalesClose(List<SalesCloseVO> salesCloseList) {
				// 매출마감 등록 결과 값
				ServiceResult result = null;
				// 매출마감 제품 등록 결과 값
				// 매출마감번호 객체 생성
				String salcNum = null;
				// 결과 값을 담을 맵
				// 매출마감리스트 받아오기
//				List<SalesCloseVO> salesCloseList = salesCloseVO.getDataList();
				// if문 실행 가져온 매출마감 리스트를 가져온다.
				if (salesCloseList != null) {
					// 가져온 매출마감서 리스트에서 반복문으로 매출마감서 하나씩 가져오기
					for (SalesCloseVO salesClose : salesCloseList) {
						// 매출마감서 하나에서 가져온 매출마감번호 꺼냄
							salcNum = salesClose.getSalcNum();
							// 매출마감번호가 빈값이거나 널이면 아래 조건문 실행
							if (!StringUtils.isNotBlank(salcNum)) {
								// 매출마감번호가 없으면 매출마감등록 실행
								int rowcnt = dao.insertSalesClose(salesClose);
									// 매출마감 등록시 셀렉트키로 만들어진 매출마감번호를 입력
									salcNum = salesClose.getSalcNum();
									// 매출마감등록 조건에 따라 조건문 실행
									if (rowcnt > 0) {
										List<SalProdVO> salProdList = salesClose.getDataProdList();
										int cntCheck = dao.insertCheck(salesClose);
										if (salProdList != null) {
											for (SalProdVO salProdVO : salProdList) {
												Integer relNo = salProdVO.getSalcNo();
												salProdVO.setSalcNum(salcNum);
												if (relNo == null) {
													rowcnt = dao.insertSalesCloseProd(salProdVO);
													if (rowcnt > 0) {
														result = ServiceResult.OK;
													}else {
														result = ServiceResult.FAILED;
													}
												}else {
													rowcnt = dao.updateSalesCloseProd(salProdVO);
													if (rowcnt > 0) {
														result = ServiceResult.OK;
													}else {
														result = ServiceResult.FAILED;
													}
												}
											}// 반복문 끝
										}else { // 조건문 끝
											if (rowcnt > 0) {
												result = ServiceResult.OK;
											}else {
												result = ServiceResult.FAILED;
											}
										}
									} // 등록 끝
							}else { // 있을 경우 업데이트
								// 매출마감수정 처리
								int rowcnt = dao.updateSalesClose(salesClose);
									if (rowcnt > 0) {
										// 매출마감 결과값 초기화
										result = ServiceResult.OK;
									}else {
										// 매출마감 결과값 초기화
										result = ServiceResult.FAILED;
									}
							} // else 끝
					} // for문 끝
				}else {
				} // if문 끝
				return result;
	}

	// 매출마감 수정
	@Override
	@Transactional
	public ServiceResult updateSalesClose(SalesCloseVO salesCloseVO) {
		// 결과값 선언
		ServiceResult result = null;
		// 매출마감 수정 쿼리
		int rowcnt = dao.updateSalesClose(salesCloseVO);
		// 결과값 초기화
		if (rowcnt > 0) {
			result = ServiceResult.OK;
		}else {
			result = ServiceResult.FAILED;
		}
		return result;
	}
	
	// 매출마감서 삭제
	@Override
	@Transactional
	public ServiceResult deleteSalesClose(SalesCloseVO salesCloseVO) {
		ServiceResult result = null; // 객체 선언
		int rowcnt = dao.deleteSalesClose(salesCloseVO);
		// 결과값 초기화
		if (rowcnt > 0) {
			result = ServiceResult.OK;
		}else {
			result = ServiceResult.FAILED;
		}
		return result;
	}
	
	// 매출마감 제품 조회
	@Override
	public List<SalProdVO> retrieveSalesCloseProdList(SalesCloseVO salesCloseVO) {
		// 매출마감서 가지고 가서 해당 매출마감서에 제품 리스트 조회
		return dao.selectSalesCloseProdList(salesCloseVO);
	}
	
	// 매출마감 제품 추가
	@Override
	@Transactional
	public ServiceResult createRaws(List<SalProdVO> salProdVO) { // 매출마감리스트 파라미터로 넘겨받아 
		ServiceResult result = null; // 결과값 선언
		for (SalProdVO salProd : salProdVO) { // 매출마감 제품 리스트에서 매출마감 제품 한건씩 반복문으로 실행
			Integer salcNo = salProd.getSalcNo(); // 매출마감 제품 에서 매출마감번호 꺼내서 초기화
				if (salcNo == null) {
						// 매출마감제품 추가
						int rowcnt = dao.insertSalesCloseProd(salProd);
							if(rowcnt > 0) {
								result = ServiceResult.OK;
							}else {
								result = ServiceResult.FAILED;
							} // if문 끝
				}else {
					// 매출마감제품이 있을 경우
					int rowcnt = dao.updateSalesCloseProd(salProd);
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
	public ServiceResult updateRaws(SalProdVO salProdVO) {
		// TODO Auto-generated method stub
		return null;
	}
	
	//매출마감 제품 삭제
	@Override
	@Transactional
	public ServiceResult deleteRaws(List<SalProdVO> salProdVO) {
		ServiceResult result = null;
		// 매출마감 제품 삭제 반복문
		for (SalProdVO salProd : salProdVO) { // 매출마감 제품 리스트에서 한건씩 반복문으로 실행
			int rowcnt = dao.deleteSalesCloseProd(salProd); // 삭제 쿼리 실행
				if (rowcnt > 0) {
					result = ServiceResult.OK;
				}else {
					result = ServiceResult.FAILED;
				}
		} // 반복문 끝
		return result;
	}

	// 매출마감 - 출고처리서 적용 목록 조회
	@Override
	public List<ReleaseVO> retreiveSalesCloseListForPI(HubSearchVO hubSearchVO) {
		return dao.selectSalesCloseListForPI(hubSearchVO);
	}

	// 매출마감 - 출고처리서 - 제품 적용 목록 조회
	@Override
	public List<RelProdVO> retrieveSalesCloseProdListForPI(ReleaseVO releaseVO) {
		// 해당 제품의 매출마감서가 존재하는지 먼저 조회
		ReleaseVO saved = dao.selectSalesCloseForPI(releaseVO);
		if (saved == null) {
			throw new PKNotFoundException(releaseVO.getRelNum() + "에 해당하는 제품 없음");
		}
		return dao.selectSalesCloseProdListForPI(releaseVO);
	}

	// 매출마감서 적용
	   @Override
	   public List<ReleaseVO> insertSalesCloseTotalListForPI(ReleaseVO releaseVO) {
	      // 넘겨받은 데이터에서 출고처리서 꺼내기
	      List<ReleaseVO> releaseList = releaseVO.getDataList();
	      // 넘겨받은 데이터에서 출고처리서 - 제품 꺼내기
	      List<RelProdVO> releaseProdList = releaseVO.getDataProdList();
	      ReleaseVO saved = null;
	      List<ReleaseVO> relList = new ArrayList<>();
	      int count = 0;
	      
	      if(releaseProdList != null) {
	    	  for (ReleaseVO release : releaseList) {
	    		// 넘겨받은 데이터에서 출고처리서가 있는지 검증
	 	         saved = dao.selectSalesCloseForPI(release); // 매출마감 객체 생성 한개이기에 리스트로 만들 필요가 없다.
	 	         if (saved == null) {
	 	            throw new PKNotFoundException(releaseVO.getRelNum() + "에 해당하는 제품 없음");
	 	         }
	 	         // 제품 내역을 담을 리스트 
	 		      List<RelProdVO> savedProdList = new ArrayList<>();
	 		      // 넘겨받은 데이터 중 해당 매출마감서 제품 반복문으로 가져오기(실제 넘겨받은 데이터가 있는지 검증)
	 		      for (RelProdVO rProdVO : releaseProdList) {
	 		         RelProdVO savedProd = dao.selectSalesCloseProdForPI(rProdVO);
	 		         savedProdList.add(savedProd); // 가져온 데이터 리스트에 추가
	 		         saved.setDataProdList(savedProdList); // 만들어진 리스트를 기존의 출고처리서에 세팅
	 		      } // 반복문 끝
	 		      relList.add(count,saved);
	 		      ++count;
			}
	      }else {
	    	  for (ReleaseVO release : releaseList) {
		    		// 넘겨받은 데이터에서 출고처리서가 있는지 검증
	    		  List<RelProdVO> list = release.getDataProdList();
	    		  if (list == null) {
					list = dao.selectSalesCloseProdListForPI(release);
	    		  }
		 	         saved = dao.selectSalesCloseForPI(release); // 매출마감 객체 생성 한개이기에 리스트로 만들 필요가 없다.
		 	         if (saved == null) {
		 	            throw new PKNotFoundException(releaseVO.getRelNum() + "에 해당하는 제품 없음");
		 	         }
		 	         // 제품 내역을 담을 리스트 
		 		      List<RelProdVO> savedProdList = new ArrayList<>();
		 		      // 넘겨받은 데이터 중 해당 매출마감서 제품 반복문으로 가져오기(실제 넘겨받은 데이터가 있는지 검증)
		 		      for (RelProdVO rProdVO : list) {
		 		         RelProdVO savedProd = dao.selectSalesCloseProdForPI(rProdVO);
		 		         savedProdList.add(savedProd); // 가져온 데이터 리스트에 추가
		 		         saved.setDataProdList(savedProdList); // 만들어진 리스트를 기존의 출고처리서에 세팅
		 		      } // 반복문 끝
		 		      relList.add(count,saved);
		 		      ++count;
				}
	      }
	      return relList;
	   }
}
