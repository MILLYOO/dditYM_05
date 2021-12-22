package kr.or.ddit.sales.estimate.service;

import java.util.List;

import kr.or.ddit.common.vo.HubSearchVO;
import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.sales.vo.EstimateProdVO;
import kr.or.ddit.sales.vo.EstimateVO;

/**
 * 견적서
 */
public interface EstimateService {
	
	/**
	 * 견적서 리스트 조회
	 * @param hubSearch
	 * @return
	 */
	public List<EstimateVO> retrieveEstimateList(HubSearchVO hubSearchVO);
	
	/**
	 * 견적서 한건 조회
	 * @param estimate
	 * @return
	 */
	public EstimateVO selectEstimate(EstimateVO estimate);
	
	/**
	 * 견적서 상세 한건 조회
	 * @param estimateProd
	 * @return
	 */
	public EstimateProdVO selectEstmateProd(EstimateProdVO estimateProd);
	
	
	/**
	 * 견적서 등록
	 * @param estimate
	 * @return
	 */
	public ServiceResult createORupdateEstimate(List<EstimateVO> list);
	
	/**
	 * 견적서 수정
	 * @param estimate
	 * @return
	 */
	public ServiceResult updateEstimate(EstimateVO estimateVO);
	
	/**
	 * 견적서 삭제
	 * @param estimate
	 * @return
	 */
	public ServiceResult deleteEstimate(EstimateVO estimateVO);
	
	
	/**
	 * 견적서 제품 내역 조회
	 * @param estCode
	 * @return
	 */
	public List<EstimateProdVO> retrieveEstimateProdList(EstimateVO estimateVO);
	
	/**
	 * 견적서 제품 등록
	 * @param estimateProd
	 * @return
	 */
	public ServiceResult createRaws(List<EstimateProdVO> estimateProdVO);
	
	/**
	 * 견적서 상세 수정
	 * @param estimateProd
	 * @return
	 */
	public ServiceResult updateRaws(EstimateProdVO estimateProdVO);
	
	/**
	 * 견적서 상세 삭제
	 * @param estimateProd
	 * @return
	 */
	public ServiceResult deleteRaws(List<EstimateProdVO> estimateProdVO);

	
}
