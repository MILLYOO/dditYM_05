package kr.or.ddit.prod.dao;

import java.util.List;

import kr.or.ddit.vo.PagingVO;
import kr.or.ddit.vo.ProdVO;

/**
 * 상품 관리용 Persistence Layer
 *
 */
public interface ProdDAO {
	/**
	 * @param prod
	 * @return
	 */
	public int insertProd(ProdVO prod);
	
	/**
	 * @param pagingVO
	 * @return
	 */
	public int selectTotalRecord(PagingVO<ProdVO> pagingVO);
	
	/**
	 * @param pagingVO
	 * @return
	 */
	public List<ProdVO> selectProdList(PagingVO<ProdVO> pagingVO);
	
	/**
	 * @param prodId
	 * @return 조회시 없으면 null
	 */
	public ProdVO selectProd(String prodId);
	
	/**
	 * delete는 생략
	 * @param prod
	 * @return
	 */
	public int updateProd(ProdVO prod);
	
}
