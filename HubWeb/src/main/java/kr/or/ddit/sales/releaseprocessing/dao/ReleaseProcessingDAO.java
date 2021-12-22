package kr.or.ddit.sales.releaseprocessing.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import kr.or.ddit.common.vo.HubSearchVO;
import kr.or.ddit.sales.vo.RelProdVO;
import kr.or.ddit.sales.vo.ReleaseOrderProdVO;
import kr.or.ddit.sales.vo.ReleaseOrderVO;
import kr.or.ddit.sales.vo.ReleaseVO;

/**
 * 출고처리
 */
@Repository
public interface ReleaseProcessingDAO {
	
	public int insertCheck(ReleaseVO releaseVO);
	
	/**
	 * 출고지시서 번호 가져오기
	 * @param releaseOrderVO
	 * @return
	 */
	public String selectMaxId(ReleaseVO releaseVO);
	
	
	/**
	 * 출고처리서 한건 조회
	 * @param releaseVO
	 * @return
	 */
	public ReleaseVO selectRelease(ReleaseVO releaseVO);

	/**
	 * 출고처리서  제품 한건 조회
	 * @param relProdVO
	 * @return
	 */
	public RelProdVO selectReleaseProd(RelProdVO relProdVO);
	/**
	 * 출고처리서 리스트 조회
	 * @param hubSearchVO
	 * @return
	 */
	public List<ReleaseVO> selectReleaseList(@Param("hubSearchVO")HubSearchVO hubSearchVO);
	
	/**
	 * 출고처리서 제품 리스트 조회
	 * @param relProdVO
	 * @return
	 */
	public List<RelProdVO> selectReleaseProdList(ReleaseVO relProdVO);
	
	
	/**
	 * 출고처리서 입력
	 * @param releaseVO
	 * @return
	 */
	public int insertRelease(ReleaseVO releaseVO);
	
	/**
	 * 출고처리서 제품 내역 입력
	 * @param relProdVO
	 * @return
	 */
	public int insertReleaseProd(RelProdVO relProdVO);
	
	/**
	 * 출고처리서 수정
	 * @param releaseVO
	 * @return
	 */
	public int updateRelease(ReleaseVO releaseVO);
	
	/**
	 * 출고처리서 제품 수정
	 * @param relProdVO
	 * @return
	 */
	public int updateReleaseProd(RelProdVO relProdVO);
	
	/**
	 * 출고처리서 삭제
	 * @param releaseVO
	 * @return
	 */
	public int deleteRelease(ReleaseVO releaseVO);
	
	/**
	 * 출고처리서 제품 삭제
	 * @param relProdVO
	 * @return
	 */
	public int deleteReleaseProd(RelProdVO relProdVO);
	
	/**
	 * 출고처리서 - 출고지시서 적용 위한 목록 조회
	 * @param hubSearchVO
	 * @return
	 */
	public List<ReleaseOrderVO> selectReleaseOrderListForPI(@Param("hubSearchVO")HubSearchVO hubSearchVO);
	
	/**
	 * 출고처리서 - 출고지시서 적용을 위한 제품 목록 조회
	 * @param releaseOrderVO
	 * @return
	 */
	public List<ReleaseOrderProdVO> selectReleaseOrderProdListForPI(ReleaseOrderVO releaseOrderVO);
	
	/**
	 * 출고지시서 존재 여부 체크
	 * @param releaseOrderVO
	 * @return
	 */
	public ReleaseOrderVO selectReleaseOrderForPI(ReleaseOrderVO releaseOrderVO);
	
	/**
	 * 출고처리서 - 제품 목록 존재 여부 체크
	 * @param releaseOrderProdVO
	 * @return
	 */
	public ReleaseOrderProdVO selectReleaseOrderProdForPI(ReleaseOrderProdVO releaseOrderProdVO);
	
	/**
	 * 제품별 창고 수량 증가
	 * @param releaseOrderProdVO
	 * @return
	 */
	public int increaseWarQty(RelProdVO relProdVO);
	
	/**
	 * 제품별 창고 수량 감소
	 * @param releaseOrderProdVO
	 * @return
	 */
	public int decreaseWarQty(RelProdVO relProdVO);
	
	/**
	 * 전체 제품 수량 증가
	 * @param releaseOrderProdVO
	 * @return
	 */
	public int increaseQty(RelProdVO relProdVO);

	/**
	 * 전체 제품 수량 감소
	 * @param releaseOrderProdVO
	 * @return
	 */
	public int decreaseQty(RelProdVO relProdVO);
	
	/**
	 * 전체 제품 수량 수정
	 * @param relProdVO
	 * @return
	 */
	public int updateQty(RelProdVO relProdVO);
	
	/**
	 * 제품 수량 수정
	 * @param relProdVO
	 * @return
	 */
	public int updateProdQty(RelProdVO relProdVO);
	
}
