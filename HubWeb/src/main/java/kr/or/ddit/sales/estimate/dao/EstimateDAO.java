package kr.or.ddit.sales.estimate.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import kr.or.ddit.board.vo.PagingVO;
import kr.or.ddit.common.vo.HubSearchVO;
import kr.or.ddit.sales.vo.EstimateProdVO;
import kr.or.ddit.sales.vo.EstimateVO;

/**
 * 견적서
 */
@Repository
public interface EstimateDAO {

	/**
	 * 전체 레코드 선택
	 * @param pagingVO
	 * @return
	 */
	public int selectTotalRecoard(PagingVO<EstimateVO> pagingVO);
	
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
	public EstimateProdVO selectEstimateProd(EstimateProdVO estimateProd);
	
	/**
	 * 견적서 입력 
	 * @param estimate
	 * @return
	 */
	public int insertEstimate(EstimateVO estimateVO);
	
	/**
	 * 견적서 제품 입력 
	 * @param estimateProd
	 * @return
	 */
	public int insertEstimateProd(EstimateProdVO estimateProdVO);
	
	/**
	 * 견적서 리스트 조회 
	 * @param estimate
	 * @return
	 */
	public List<EstimateVO> selectEstimateList(@Param("hubSearchVO")HubSearchVO hubSearchVO);
	
	/**
	 * 견적서 제품 리스트 조회
	 * @param estimate
	 * @return
	 */
	public List<EstimateProdVO> selectEstimateProdList(EstimateVO estimateVO);

	/**
	 * 견적서 수정
	 * @param estimate
	 * @return
	 */
	public int updateEstimate(EstimateVO estimateVO);
	
	/**
	 * 견적서 제품 수정
	 * @param estimateProd
	 * @return
	 */
	public int updateEstimateProd(EstimateProdVO estimateProdVO);
	
	/**
	 * 견적서 삭제
	 * @param estimate
	 * @return
	 */
	public int deleteEstimate(EstimateVO estimateVO);
	
	/**
	 * 견적서 제품 삭제
	 * @param estimateProd
	 * @return
	 */
	public int deleteEstimateProd(EstimateProdVO estimateProdVO);
	
	/**
	 * 새로운 작성 문서 체크
	 * @param estimateVO
	 * @return
	 */
	public int insertCheck(EstimateVO estimateVO);
	
	
}
