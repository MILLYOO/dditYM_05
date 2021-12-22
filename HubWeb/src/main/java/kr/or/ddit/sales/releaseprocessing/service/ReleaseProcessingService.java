package kr.or.ddit.sales.releaseprocessing.service;

import java.util.List;

import kr.or.ddit.common.vo.HubSearchVO;
import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.sales.vo.RelProdVO;
import kr.or.ddit.sales.vo.ReleaseOrderProdVO;
import kr.or.ddit.sales.vo.ReleaseOrderVO;
import kr.or.ddit.sales.vo.ReleaseVO;

/**
 * 출고처리
 */
public interface ReleaseProcessingService {
	
	/**
	 * 출고처리서 출고처리번호 최대값 
	 * @param releaseVO
	 * @return
	 */
	public String retrieveMaxId(ReleaseVO releaseVO);
	
	
	/**
	 * 출고처리 리스트 조회
	 * @param hubSearch
	 * @return
	 */
	public List<ReleaseVO> retrieveReleaseProcessingList(HubSearchVO hubSearch);
	
	/**
	 * 출고처리 등록
	 * @param ReleaseListVO 리스트로 보관한 모델
	 * @return
	 */
	public ServiceResult createORupdateReleaseProcessing(List<ReleaseVO> releaseList);
	
	/**
	 * 출고처리 수정
	 * @param release
	 * @return
	 */
	public ServiceResult updateReleaseProcessing(ReleaseVO release);
	
	/**
	 * 출고처리 삭제
	 * @param release
	 * @return
	 */
	public ServiceResult deleteReleaseProcessing(ReleaseVO release);
	
	/**
	 * 출고처리 상세 내역 조회
	 * @param release
	 * @return
	 */
	public List<RelProdVO> releaseProcessing(ReleaseVO release);
	
	/**
	 * 출고처리 상세 등록
	 * @param relProd
	 * @return
	 */
	public ServiceResult createRaws(List<RelProdVO> relProdVO);
	
	/**
	 * 출고처리 상세 수정
	 * @param relProd
	 * @return
	 */
	public ServiceResult updateRaws(RelProdVO relProd);
	
	/**
	 * 출고처리 상세 삭제
	 * @param relProd
	 * @return
	 */
	public ServiceResult deleteRaws(List<RelProdVO> relProd);
	
	/**
	 * 출고지시서 적용을 위한 목록 조회
	 * @param hubSearchVO
	 * @return
	 */
	public List<ReleaseOrderVO> retreiveReleaseOrderListForPI(HubSearchVO hubSearchVO);
	
	/**
	 * 출고지시서 - 제품 적용을 위한 목록 조회
	 * @param roVO
	 * @return
	 */
	public List<ReleaseOrderProdVO> retrieveReleaseOrderProdListForPI(ReleaseOrderVO roVO);
	
	/**
	 * 출고지시서 - 제품 적용
	 * @param releaseOrderVO
	 * @return
	 */
	public List<ReleaseOrderVO> retrieveReleaseOrderTotalListForPI(ReleaseOrderVO releaseOrderVO);
	
	
}
