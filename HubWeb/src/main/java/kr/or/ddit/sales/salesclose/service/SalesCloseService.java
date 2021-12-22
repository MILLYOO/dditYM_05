package kr.or.ddit.sales.salesclose.service;

import java.util.List;

import kr.or.ddit.common.vo.HubSearchVO;
import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.sales.vo.RelProdVO;
import kr.or.ddit.sales.vo.ReleaseVO;
import kr.or.ddit.sales.vo.SalProdVO;
import kr.or.ddit.sales.vo.SalesCloseVO;

/**
 * 매출마감
 */
public interface SalesCloseService {
	
	/**
	 * 매출마감 리스트 조회
	 * @param hubSearch
	 * @return
	 */
	public List<SalesCloseVO> retrieveSalesCloseList(HubSearchVO hubSearchVO);
	
	/**
	 * 매출마감 등록
	 * @return
	 */
	public ServiceResult createORupdateSalesClose(List<SalesCloseVO> salesCloseList);
	
	/**
	 * 매출마감 수정
	 * @param salesClose
	 * @return
	 */
	public ServiceResult updateSalesClose(SalesCloseVO salesCloseVO);
	
	/**
	 * 매출마감 삭제
	 * @param salesCloseVO
	 * @return
	 */
	public ServiceResult deleteSalesClose(SalesCloseVO salesCloseVO);
	
	/**
	 * 매출마감 제품 내역 조회
	 * @param salesCloseVO
	 * @return
	 */
	public List<SalProdVO> retrieveSalesCloseProdList(SalesCloseVO salesCloseVO);
	
	/**
	 * 매출마감 제품 등록
	 * @param salProdVO
	 * @return
	 */
	public ServiceResult createRaws(List<SalProdVO> salProdVO);
	
	/**
	 * 매출마감 제품 수정
	 * @param salProdVO
	 * @return
	 */
	public ServiceResult updateRaws(SalProdVO salProdVO);
	
	/**
	 * 매출마감 제품 삭제
	 * @param salProdVO
	 * @return
	 */
	public ServiceResult deleteRaws(List<SalProdVO> salProdVO);
	
	/**
	 * 매출마감 적용을 위한 목록 조회
	 * @param hubSearchVO
	 * @return
	 */
	public List<ReleaseVO> retreiveSalesCloseListForPI(HubSearchVO hubSearchVO);
	
	/**
	 * 매출마감 - 제품 적용을 위한 목록 조회
	 * @param scVO
	 * @return
	 */
	public List<RelProdVO> retrieveSalesCloseProdListForPI(ReleaseVO scVO);
	
	/**
	 * 매출마감 - 제품 적용
	 * @param salesCloseVO
	 * @return
	 */
	public List<ReleaseVO> insertSalesCloseTotalListForPI(ReleaseVO releaseVO);
	

}
