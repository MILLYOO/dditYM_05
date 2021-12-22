package kr.or.ddit.produce.productincoming.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import kr.or.ddit.common.vo.HubSearchVO;
import kr.or.ddit.produce.vo.InstrucprodVO;
import kr.or.ddit.produce.vo.ProdWareVO;
import kr.or.ddit.produce.vo.PrwaProdVO;
import kr.or.ddit.stock.vo.WarProdVO;

/**
 * 생산품입고처리
 */
@Repository
public interface ProductIncomingDAO {
	
	/**
	 * 새로운 생산품 입고처리 문서 체크
	 * @param prodWareVO
	 * @return
	 */
	public int insertCheck(ProdWareVO prodWareVO);
	
	/**
	 * 생산품입고처리 목록 조회
	 * @param hubSearchVO
	 * @return
	 */
	public List<ProdWareVO> selectProdIncomingList(HubSearchVO hubSearchVO);

	
	/**
	 * 생산품입고-품목 목록 조회
	 * @param prodWareVO
	 * @return
	 */
	public List<PrwaProdVO> selectProdIncomingRawsList(ProdWareVO prodWareVO);
	
	
	/**
	 * 생산품입고처리 1건 조회
	 * @param prodWareVO
	 * @return
	 */
	public ProdWareVO selectProdIncoming(ProdWareVO prodWareVO);
	
	
	/**
	 * 생산품입고-품목 1건 조회
	 * @param prwaProdVO
	 * @return
	 */
	public PrwaProdVO selectProdIncomingRaws(PrwaProdVO prwaProdVO);
	
	
	
	
	/**
	 * 생산품입고처리 등록
	 * @param prodWareVO
	 * @return
	 */
	public int insertProdIncoming(ProdWareVO prodWareVO);
	
	/**
	 * 생산품입고처리 수정
	 * @param prodWareVO
	 * @return
	 */
	public int updateProdIncoming(ProdWareVO prodWareVO);
	
	
	/**
	 * 생산품입고-품목 등록
	 * @param prwaProdVO
	 * @return
	 */
	public int insertProdIncomingRaws(PrwaProdVO prwaProdVO);
	
	/**
	 * 생산품입고-품목 수정
	 * @param prwaProdVO
	 * @return
	 */
	public int updateProdIncomingRaws(PrwaProdVO prwaProdVO);
	
	
	
	/**
	 * 제품수량증가
	 * @param prodCode
	 * @return
	 */
	public int updateProductQtyPlus(PrwaProdVO prwa);
	
	/**
	 * 제품창고수량증가
	 * @param warProd
	 * @return
	 */
	public int updateProductWarQtyPlus(WarProdVO warProd);
	
	/**
	 * 제품수량감소
	 * @param prodCode
	 * @return
	 */
	public int updateProductQtyMinus(PrwaProdVO prwa);
	
	/**
	 * 제품창고수량감소
	 * @param warProd
	 * @return
	 */
	public int updateProductWarQtyMinus(WarProdVO warProd);
	
	
	/**
	 * 창고이름가져오기
	 * @param prodCode
	 * @return
	 */
	public List<String> selectWarNameForProduct(String prodCode);
	
	
	
	
	/**
	 * 생산품입고처리
	 * @param prodWare
	 * @return
	 */
	public int deleteProductIncoming(ProdWareVO prodWare);
	
	/**
	 * 생산품입고처리-품목 모두삭제
	 * @param arrNum
	 * @return
	 */
	public int deleteAllProductIncomingRaws(String arrNum);
	
	
	/**
	 * 생산품입고처리-품목 삭제
	 * @param prwaProd
	 * @return
	 */
	public int deleteProductIncomingRaws(PrwaProdVO prwaProd);
	
	
	
	/**
	 * code로 품목계정 얻기
	 * @param code
	 * @return
	 */
	public String selectIcodeName(String code);
	
	
	
	/**
	 * 지시서 적용 시 생산지시서에 적용 여부 변경
	 * @param instVO
	 * @return
	 */
	public int updateInstPwAppyn(InstrucprodVO instVO);
	
	/**
	 * 지시서 적용 시 생산지시서 목록 ( N )
	 * @param hubsearchVO
	 * @return
	 */
	public List<InstrucprodVO> selectPiApplyList(HubSearchVO hubsearchVO);
}
