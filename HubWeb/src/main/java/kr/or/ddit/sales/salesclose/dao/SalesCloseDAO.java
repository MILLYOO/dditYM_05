package kr.or.ddit.sales.salesclose.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import kr.or.ddit.common.vo.HubSearchVO;
import kr.or.ddit.sales.vo.RelProdVO;
import kr.or.ddit.sales.vo.ReleaseVO;
import kr.or.ddit.sales.vo.SalProdVO;
import kr.or.ddit.sales.vo.SalesCloseVO;

/**
 * 매출마감
 */
@Repository
public interface SalesCloseDAO {
	
	public int insertCheck(SalesCloseVO salesCloseVO);
	
	/**
	 * 매출마감 한건 조회
	 * @param salesCloseVO
	 * @return
	 */
	public SalesCloseVO selectSalesClose(SalesCloseVO salesCloseVO);
	
	/**
	 * 매출마감 제품 한건 조회
	 * @param salProdVO
	 * @return
	 */
	public SalProdVO selectSalesCloseProd(SalProdVO salProdVO);
	
	/**
	 * 매출마감 리스트 조회
	 * @param hubSearchVO
	 * @return
	 */
	public List<SalesCloseVO> selectSalesCloseList(@Param("hubSearchVO")HubSearchVO hubSearchVO);
	
	/**
	 * 매출마감 제품 리스트 조회
	 * @param salesCloseVO
	 * @return
	 */
	public List<SalProdVO> selectSalesCloseProdList(SalesCloseVO salesCloseVO);
	
	/**
	 * 매출마감 입력
	 * @param salesCloseVO
	 * @return
	 */
	public int insertSalesClose(SalesCloseVO salesCloseVO);
	
	/**
	 * 매출마감 제품 입력
	 * @param salProdVO
	 * @return
	 */
	public int insertSalesCloseProd(SalProdVO salProdVO);
	
	/**
	 * 매출마감 수정
	 * @param salesCloseVO
	 * @return
	 */
	public int updateSalesClose(SalesCloseVO salesCloseVO);
	
	/**
	 * 매출마감 제품 수정
	 * @param salProdVO
	 * @return
	 */
	public int updateSalesCloseProd(SalProdVO salProdVO);
	
	
	/**
	 * 매출마감 삭제
	 * @param salesCloseVO
	 * @return
	 */
	public int deleteSalesClose(SalesCloseVO salesCloseVO);
	
	/**
	 * 매출마감 제품 삭제
	 * @param salProdVO
	 * @return
	 */
	public int deleteSalesCloseProd(SalProdVO salProdVO);
	
	/**
	 * 매출마감 - 출고처리서 적용 위한 목록 조회
	 * @param hubSearchVO
	 * @return
	 */
	public List<ReleaseVO> selectSalesCloseListForPI(@Param("hubSearchVO")HubSearchVO hubSearchVO);
	
	/**
	 * 매출마감 - 출고처리서 적용을 위한 제품 목록 조회
	 * @param salesCloseVO
	 * @return
	 */
	public List<RelProdVO> selectSalesCloseProdListForPI(ReleaseVO releaseVO);
	
	/**
	 * 매출마감 존재 여부 (적용 시킬때도 사용)
	 * @param salesCloseVO
	 * @return
	 */
	public ReleaseVO selectSalesCloseForPI(ReleaseVO releaseVO);
	
	/**
	 * 매출마감 제품 존재 여부(적용 시킬때도 사용)
	 * @param rProdVO
	 * @return
	 */
	public RelProdVO selectSalesCloseProdForPI(RelProdVO rProdVO);
	
	public List<RelProdVO> selectSalesGraph();
	
}
