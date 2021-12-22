package kr.or.ddit.sales.releaseprocessing.service;

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
import kr.or.ddit.sales.releaseprocessing.dao.ReleaseProcessingDAO;
import kr.or.ddit.sales.vo.RelProdVO;
import kr.or.ddit.sales.vo.ReleaseOrderProdVO;
import kr.or.ddit.sales.vo.ReleaseOrderVO;
import kr.or.ddit.sales.vo.ReleaseVO;

@Service	
public class ReleaseProcessingServiceImpl implements ReleaseProcessingService {
	
	@Inject
	private ReleaseProcessingDAO dao;
	
	
	// 출고처리서 전체 선택
	@Override
	public List<ReleaseVO> retrieveReleaseProcessingList(HubSearchVO hubSearchVO) {
		return dao.selectReleaseList(hubSearchVO);
	}
	
	// 출고처리서 등록 및 업데이트
	@Override
	@Transactional
	public ServiceResult createORupdateReleaseProcessing(List<ReleaseVO> releaseList) {
		ServiceResult result = null;
		String relNum = null;
//		List<ReleaseVO> releaseList = releaseListMap.getDataList();
		if (releaseList != null) { // 출고처리서가 존재할때
			for(ReleaseVO releaseVO : releaseList) { // 출고처리서 리스트에서 한건씩 반복문으로 꺼냄
				relNum = releaseVO.getRelNum(); // 출고처리서 넘버
					if(!StringUtils.isNotBlank(relNum)) { // 출고처리서 넘버가 없으면
						int rowcnt = dao.insertRelease(releaseVO); // 인서트
						relNum = releaseVO.getRelNum(); // 인서트 후 출고처리서 번호 초기화
							if (rowcnt > 0) { // 출고처리서 인서트 성공 후
								int cntCheck = dao.insertCheck(releaseVO);
								List<RelProdVO> relProdList = releaseVO.getDataProdList();
								if (relProdList != null) {
									for (RelProdVO relProdVO : relProdList) {
										Integer reoNo = relProdVO.getRelNo();
										relProdVO.setRelNum(relNum);
										if (reoNo == null) {
											rowcnt = dao.insertReleaseProd(relProdVO);
											if (rowcnt > 0) {
												result = ServiceResult.OK;
											}else {
												result = ServiceResult.FAILED;
											}
										}else {
											rowcnt = dao.updateReleaseProd(relProdVO);
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
						int rowcnt = dao.updateRelease(releaseVO);
						if(rowcnt > 0 ) {
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
	
		
//		List<RelProdVO> releaseProdList = releaseListMap.getDataProdList();
//		if (releaseProdList != null) {
//			for(RelProdVO relProdVO : releaseProdList) {
//				Integer rep = relProdVO.getRelNo();
//				relProdVO.setRelNum(maxId);
//					if (rep == null) {
//						int rowcnt = dao.insertReleaseProd(relProdVO);
//						if (rowcnt > 0) {
//							resultProd = ServiceResult.OK;
//							// 창고에 해당 제품 수량 감소 시키기
//							int tocnt = dao.decreaseQty(relProdVO);
//							int cellcnt = dao.decreaseWarQty(relProdVO);
//						}else {
//							resultProd = ServiceResult.FAILED;
//						}
//					}else {
//						// 제품 수량 수정
//						dao.updateProdQty(relProdVO);
//						// 전체 수량 수정
//						dao.updateQty(relProdVO);
//						int rowcnt = dao.updateReleaseProd(relProdVO);
//						if (rowcnt > 0) {
//							resultProd = ServiceResult.OK;
//							resultMap.put("resultProd", resultProd);
//						}else {
//							resultProd = ServiceResult.FAILED;
//							resultMap.put("resultProd", resultProd);
//						}
//					}
//			} // for문 끝
//		}
//		return result;
//	}
	
	// 출고처리서 수정
	@Override
	@Transactional
	public ServiceResult updateReleaseProcessing(ReleaseVO releaseVO) {
		ServiceResult result = null;
		int rowcnt = dao.updateRelease(releaseVO);
		if (rowcnt > 0) {
			result = ServiceResult.OK;
		}else {
			result = ServiceResult.FAILED;
		}
		return result;
	}
	
	// 출고처리서 삭제
	@Transactional
	@Override
	public ServiceResult deleteReleaseProcessing(ReleaseVO releaseVO) {
		ServiceResult result = null;
		int rowcnt = dao.deleteRelease(releaseVO);
		if (rowcnt > 0) {
			result = ServiceResult.OK;
		}else {
			result = ServiceResult.FAILED;
		}
		return result;
	}
	
	// 출고처리서 제품 조회
	@Override
	public List<RelProdVO> releaseProcessing(ReleaseVO releaseVO) {
		return dao.selectReleaseProdList(releaseVO);
	}
	
	// 출고처리서 제품 추가
	@Override
	@Transactional
	public ServiceResult createRaws(List<RelProdVO> relProdList) {
		ServiceResult result = null;
		for(RelProdVO relProdVO : relProdList) {
			Integer rel = relProdVO.getRelNo();
			if (rel == null) {
				int rowcnt = dao.insertReleaseProd(relProdVO);
				if (rowcnt > 0) {
					result = ServiceResult.OK;
					int tocnt = dao.decreaseQty(relProdVO);
					int cellcnt = dao.decreaseWarQty(relProdVO);
				}else {
					result = ServiceResult.FAILED;
				}
			}else {
				// 제품 수량 수정
				dao.updateProdQty(relProdVO);
				// 전체 수량 수정
				dao.updateQty(relProdVO);
				int rowcnt = dao.updateReleaseProd(relProdVO);
				if (rowcnt > 0) {
					result = ServiceResult.OK;
				}else {
					result = ServiceResult.FAILED;
				}
			}
		} // for문 끝
		return result;
	}
	
	// 출고처리서 제품 삭제
		@Override
		@Transactional
		public ServiceResult deleteRaws(List<RelProdVO> relProdList) {
			ServiceResult result = null;
			for(RelProdVO relProdVO : relProdList) {
				int rowcnt = dao.deleteReleaseProd(relProdVO);
				if (rowcnt > 0) {
					result = ServiceResult.OK;
					int tocnt = dao.increaseQty(relProdVO);
					int cellcnt = dao.increaseWarQty(relProdVO);
				}else {
					result = ServiceResult.FAILED;
				}
			} // for문 끝
			return result;
		}
	

	@Override
	public ServiceResult updateRaws(RelProdVO relProd) {
		// TODO Auto-generated method stub
		return null;
	}


	// 출고처리서번호 가져오기
	@Override
	public String retrieveMaxId(ReleaseVO releaseVO) {
		return dao.selectMaxId(releaseVO);
	}

	// 출고처리서 - 출고지시서 적용 목록 조회
	@Override
	public List<ReleaseOrderVO> retreiveReleaseOrderListForPI(HubSearchVO hubSearchVO) {
		return dao.selectReleaseOrderListForPI(hubSearchVO);
	}

	// 출고처리서 - 출고지시서 - 제품 적용 목록 조회
	@Override
	public List<ReleaseOrderProdVO> retrieveReleaseOrderProdListForPI(ReleaseOrderVO releaseOrderVO) {
		ReleaseOrderVO saved = dao.selectReleaseOrderForPI(releaseOrderVO);
		if (saved == null) {
			throw new PKNotFoundException(releaseOrderVO.getReoNum() + "에 해당하는 제품 없음");
		}
		return dao.selectReleaseOrderProdListForPI(releaseOrderVO);
	}

	// 출고지시서 적용
	@Override
	public List<ReleaseOrderVO> retrieveReleaseOrderTotalListForPI(ReleaseOrderVO releaseOrderVO) {
		
		// 해당 브이오에서 출고지시서 꺼내기
		List<ReleaseOrderVO> reList = releaseOrderVO.getDataList();
		ReleaseOrderVO saved = new ReleaseOrderVO();
		List<ReleaseOrderVO> roList = new ArrayList<>();
		int count = 0;
		for (ReleaseOrderVO vo : reList) {
			List<ReleaseOrderProdVO> list = releaseOrderVO.getDataProdList();
			if (list == null) {
				list = dao.selectReleaseOrderProdListForPI(vo);
			}
			saved = dao.selectReleaseOrderForPI(vo);
			if (saved == null) {
				throw new PKNotFoundException(releaseOrderVO.getReoNum() + "에 해당하는 제품 없음");
			}
			List<ReleaseOrderProdVO> savedProdList = new ArrayList<>();
			
			for (ReleaseOrderProdVO releaseOrderProdVO : list) {
				ReleaseOrderProdVO savedProd = dao.selectReleaseOrderProdForPI(releaseOrderProdVO);
				savedProdList.add(savedProd);
				saved.setDataProdList(savedProdList);
			}
			roList.add(count,saved);
			++count;
		}
		return roList;
	}
}
