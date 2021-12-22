package kr.or.ddit.produce.productincoming.service;

import java.util.List;

import kr.or.ddit.common.vo.HubSearchVO;
import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.produce.vo.InstrucprodVO;
import kr.or.ddit.produce.vo.ProdWareVO;
import kr.or.ddit.produce.vo.PrwaProdVO;

/**
 * 생산품입고처리
 */
public interface ProductIncomingService {
	/*****************************************************************/
	/*							생산품입고처리서 						 */
	/*****************************************************************/
	/**
	 * 생산품입고처리서 조회
	 * @param hubSearch
	 * @return
	 */
	public List<ProdWareVO> retrieveProductIncomingList(HubSearchVO hubSearch);
	
	/**
	 * 생산품입고처리서 1건 조회
	 * @param prodWareVO
	 * @return
	 */
	public ProdWareVO retrieveProductIncoming(ProdWareVO prodWareVO);
	
	/**
	 * 생산품입고처리서 등록+수정
	 * @param ProdWare
	 * @return
	 */
	public ServiceResult createOrUpdateProductIncoming(List<ProdWareVO> prodWare);
	
	/**
	 * 생산품입고처리서 삭제
	 * @param ProdWare
	 * @return
	 */
	public ServiceResult deleteProductIncoming(List<ProdWareVO> prodWare);
	
	
	/*****************************************************************/
	/*							생산품입고처리서 상세	 					 */
	/*****************************************************************/
	
	/**
	 * 생산품입고처리서-품목 리스트 조회
	 * @param prwaProd
	 * @return
	 */
	public List<PrwaProdVO> retrieveProductIncomingProductList(ProdWareVO prodWareVO);
	
	/**
	 * 생산품입고처리서-품목 1건 조회
	 * @param prwaProdVO
	 * @return
	 */
	public PrwaProdVO retrieveProductIncomingProduct(PrwaProdVO prwaProdVO);
	
	/**
	 * 생산품입고처리서-품목 등록+수정
	 * @param prwaProd
	 * @return
	 */
	public ServiceResult createOrUpdateIncomingProduct(List<PrwaProdVO> prwaProd);
	
	
	/**
	 * 생산품입고처리서-품목 삭제
	 * @param prwaProd
	 * @return
	 */
	public ServiceResult deleteIncomingProduct(List<PrwaProdVO> prwaProd);
	
	
	
	
	
	
	
	/**
	 * 생산품입고처리서-생산지시서적용
	 * @param instProdVO
	 * @return
	 */
	public ServiceResult createPiApplyProdIncoming(List<InstrucprodVO> instProdVO);
	
	
	/**
	 * 지시서 적용 위한 생산지시서 목록 ( N )
	 * @param hubSearchVO
	 * @return
	 */
	public List<InstrucprodVO> retrievePiApplyList(HubSearchVO hubSearchVO);
	
}




















