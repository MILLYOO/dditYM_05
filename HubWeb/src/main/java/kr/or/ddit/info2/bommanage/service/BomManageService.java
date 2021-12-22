package kr.or.ddit.info2.bommanage.service;

import java.util.List;

import kr.or.ddit.buy.vo.IncomingRawsVO;
import kr.or.ddit.common.vo.CommonListVO;
import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.info2.vo.BomRawsVO;
import kr.or.ddit.info2.vo.BomVO;
import kr.or.ddit.info2.vo.ProductVO;
import kr.or.ddit.info2.vo.WarehouseVO;

/**
 * BOM 관리 service
 */
public interface BomManageService {
	/*****************************************************************/
	/*							BOM  							     */
	/*****************************************************************/
	/**
	 * BOM 등록
	 * @param bomVO
	 * @return
	 */
	public ServiceResult createBom(BomVO bomVO);
	
	public BomVO selectBom(BomVO bomVO);
	
	public String selectBomCode(BomVO bomVO);
	
	/**
	 * BOM 삭제
	 * @param bomVO
	 * @return
	 */
	public ServiceResult deleteBom(BomVO bomVO);
	
	
	/*****************************************************************/
	/*							BOM원재료  							 */
	/*****************************************************************/
	
	public List<BomRawsVO> bomRawsList(BomVO bom);
	
	public ServiceResult bomRawsCreateOrUdate(CommonListVO<BomRawsVO> bomRaws);

	
	public ServiceResult deleteRaws(CommonListVO<BomRawsVO> bomRaws);
	
}
