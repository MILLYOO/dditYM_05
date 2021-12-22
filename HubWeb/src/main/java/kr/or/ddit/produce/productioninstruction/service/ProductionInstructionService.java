package kr.or.ddit.produce.productioninstruction.service;

import java.util.List;

import kr.or.ddit.common.vo.HubSearchVO;
import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.info2.vo.BomRawsVO;
import kr.or.ddit.info2.vo.ItemVO;
import kr.or.ddit.produce.vo.InstRawsVO;
import kr.or.ddit.produce.vo.InstrucprodVO;
import kr.or.ddit.sales.vo.OrderBookProdVO;
import kr.or.ddit.sales.vo.OrderBookVO;

/**
 * 생산지시서
 */
public interface ProductionInstructionService {
	/*****************************************************************/
	/*							생산지시서 							 */
	/*****************************************************************/
	
	/**
	 * 생산지시서 조회
	 * @param hubSearch
	 * @return
	 */
	public List<InstrucprodVO> retrieveProductionInstructionList(HubSearchVO hubSearch);
	
	
	/**
	 * 생산지시서 상세 조회
	 * @param instRawsVO
	 * @return
	 */
	public InstrucprodVO retrieveProductionInstruction(InstrucprodVO instrucProdVO);
	
	
	/**
	 * 생산지시서 등록 및 수정
	 * @param hubSearch
	 * @return
	 */
	public ServiceResult createOrUpdateProductionInstruction(List<InstrucprodVO> instrucProdVOList);
	
	/**
	 * 생산지시서 수정
	 * @param Instrucprod
	 * @return
	 */
//	public ServiceResult updateProductionInstruction(InstrucprodVO instrucProdVO);
	
	/**
	 * 생산지시서 삭제
	 * @param Instrucprod
	 * @return
	 */
	public ServiceResult deleteProductionInstruction(List<InstrucprodVO> instrucProdVO);
	
	
	/*****************************************************************/
	/*							생산지시서 상세 							 */
	/*****************************************************************/
	
	/**
	 * 생산지시서-품목 리스트 출력
	 * @param instRaws
	 * @return
	 */
	public List<InstRawsVO> retrieveInstRawsList(InstrucprodVO instrucProdVO);
	
	/**
	 * @param instRawsVO
	 * @return
	 */
	public InstRawsVO retrieveProductionRaws(InstRawsVO instRawsVO);
	
	/**
	 * 생산지시서-품목 등록 및 수정
	 * @param instRaws
	 * @return
	 */
	public ServiceResult createOrUpdateProduct(List<InstRawsVO> instRawsVO);

	/**
	 * 생산지시서-품목 수정
	 * @param instRaws
	 * @return
	 */
//	public ServiceResult updateProduct(InstRawsVO instRawsVO);
	
	/**
	 * 생산지시서-품목 삭제
	 * @param instRaws
	 * @return
	 */
	public ServiceResult deleteProduct(List<InstRawsVO> instRawsVO);
	
	
	
	/**
	 * 생산지시서-수주서적용위한 목록 조회
	 * @param hubSearch
	 * @return
	 */
	public List<OrderBookVO> retrieveOrderBookListForPI(HubSearchVO hubSearch);
	
	/**
	 * 생산지시서-수주서적용위한 수주서-제품 목록 조회
	 * @param orderBookPordVO
	 * @return
	 */
	public List<OrderBookProdVO> retrieveOrderBookProdListforPI(OrderBookVO orderBookVO);
	
	/**
	 * 수주서 적용
	 * @param orderBookVO
	 * @return
	 */
	public ServiceResult insertInstProdByOb(List<OrderBookVO> orderBookVO);
	
	
	
	
	
	/**
	 * 생산지시서 - BOM 전개를 위한 BOM_RAWS 목록 조회
	 * @param prodCode
	 * @return
	 */
//	public List<BomRawsVO> retrieveBomRawListForPI(String prodCode);
	
	/**
	 * 생산지시서 - BOM 전개를 위한 INST_RAWS 등록
	 * @param bomRawsVO
	 * @return
	 */
	public ServiceResult createInstRawsByBomApply(List<BomRawsVO> bomRawsVO);
	
	
	
	
	/**
	 * 현재고
	 * @param hubSearchVO
	 * @return
	 */
	public List<ItemVO> retrieveStockStatus(HubSearchVO hubSearchVO);
}


















