package kr.or.ddit.produce.productioninstruction.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import kr.or.ddit.common.vo.HubSearchVO;
import kr.or.ddit.info2.vo.BomRawsVO;
import kr.or.ddit.info2.vo.ItemVO;
import kr.or.ddit.produce.vo.InstRawsVO;
import kr.or.ddit.produce.vo.InstrucprodVO;
import kr.or.ddit.sales.vo.OrderBookProdVO;
import kr.or.ddit.sales.vo.OrderBookVO;

/**
 * 생산지시서
 */
@Repository
public interface ProductionInstructionDAO {
	
	/**
	 * 새로운 생산지시서 등록시 체크하기 위한 
	 * @param instrucProdVO
	 * @return
	 */
	public int insertCheck(InstrucprodVO instrucProdVO);
	
	/**
	 * 생산지시서 목록 조회
	 * @return
	 */
	public List<InstrucprodVO> selectProductionInstructionList(HubSearchVO hubSearch);
	
	/**
	 * 생산지시서 상세 (존재여부확인하려고만들었음)
	 * @param instrucProdVO
	 * @return
	 */
	public InstrucprodVO selectProductionInstruction(InstrucprodVO instrucProdVO);
	
	/**
	 * 생산지시서 별 생산지시서-원자재 목록 조회
	 * @param instrucProdVO에 instNum
	 * @return
	 */
	public List<InstRawsVO> selectInstRawsList(InstrucprodVO instrucProdVO);
	
	/**
	 * 생산지시서-원자재 상세 (존재여부확인하려고만듬)
	 * @param instRawsVO
	 * @return
	 */
	public InstRawsVO selectProductionRaws(InstRawsVO instRawsVO);
	
	
	
	
	/**
	 * 생산지시서 등록
	 * @param instrucProdVO
	 * @return
	 */
	public int insertProductionInstruction(InstrucprodVO instrucProdVO);
	
	
	
	/**
	 * 생산지시서-원자재 등록
	 * @param instrucProdVO
	 * @return
	 */
	public int insertProductionRaws(InstRawsVO instRawsVO);
	
	
	
	
	
	/**
	 * 생산지시서 수정
	 * @param instrucProdVO
	 * @return
	 */
	public int updateProductionInstruction(InstrucprodVO instrucProdVO);
	
	/**
	 * 생산지시서-원자재 수정
	 * @param instRaws
	 * @return
	 */
	public int updateProductRaws(InstRawsVO instRawsVO);
	
	
	
	
	/**
	 * 생산지시서 삭제
	 * @param instrucProdVO
	 * @return
	 */
	public int deleteProductionInstruction(InstrucprodVO instrucProdVO);
	
	/**
	 * 생산지시서 삭제 시 생산지시서-원자재 모두 삭제를 위한 메서드
	 * @param instNum
	 * @return
	 */
	public int deleteAllProdRaws(String instNum);
	
	/**
	 * 생산지시서-원자재 개별 삭제
	 * @param instRaws
	 * @return
	 */
	public int deleteProducRaws(InstRawsVO instRawsVO);
	
	

	/**
	 * 지시수량 변경 시 품목의 투입예정수량 변경
	 * @param instVO
	 * @return
	 */
	public int updatePrQtyByLeadQty(InstRawsVO instRawsVO);
	
	
	
	/**
	 * 생산지시서 - 수주서 적용을 위한 수주서 목록 조회
	 * @param hubSearch
	 * @return
	 */
	public List<OrderBookVO> selectOrderBookListForPI (HubSearchVO hubSearch);
	
	/**
	 * 생산지시서-수주서적용위한 수주서-제품 목록 조회
	 * @param orderBookPordVO
	 * @return
	 */
	public List<OrderBookProdVO> selectOrderBookProdListforPI(OrderBookVO orderBookVO);
	
	/**
	 * 수주서 존재 여부 체크하기 위한 조회
	 * @param orderBookProdVO
	 * @return
	 */
	public OrderBookVO selectOrderBookforPI(OrderBookVO orderBookVO);
	
	/**
	 * 수주서-제품 존재 여부 체크하기 위한 조회
	 * @param orderBookProdVO
	 * @return
	 */
	public OrderBookProdVO selectOrderBookProdforPI(OrderBookProdVO orderBookProdVO);
	
	
	/**
	 * 수주서 적용 시 적용여부 변경
	 * @param orderBookVO
	 * @return
	 */
	public int updateOderBookApplyYn(OrderBookVO orderBookVO);
	
	
	/**
	 * 수주서 적용된 지시서 찾기
	 * @param instrucprodVO
	 * @return
	 */
	public List<InstrucprodVO> selectObApplyPi(InstrucprodVO instrucprodVO);
	
	
	
	/**
	 * 생산지시서 - BOM 전개를 위한 BOM_RAWS 조회 목록
	 * @param prodCode
	 * @return
	 */
	public List<BomRawsVO> selectBomRawByProd(String prodCode);
	
	public List<BomRawsVO> selectBomRawByRaws(String prodCode);
	
	
	/**
	 * 생산지시서 - BOM 전개를 위한 INST_RAWS 등록
	 * @param bomRawsVO
	 * @return
	 */
	public int insertInstRawsByBomApply(BomRawsVO bomRawsVO);
	
	
	
	
	/**
	 * 현재고 조회
	 * @param hubSearch
	 * @return
	 */
	public List<ItemVO> selectStockStatus(HubSearchVO hubSearch);
	
	
	/**
	 * 품목계정조회
	 * @param code
	 * @return
	 */
	public String selectIcodeName(String code);
}
