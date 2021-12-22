package kr.or.ddit.stock.inoutadjustment.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import kr.or.ddit.common.vo.HubSearchVO;
import kr.or.ddit.stock.vo.AdjItemVO;
import kr.or.ddit.stock.vo.AdjustmentVO;

/**
 * 입출고조정
 */
@Repository
public interface InOutAdjustmentDAO {

	//입출고조정 리스트 조회
	public List<AdjustmentVO> selectInOutAdjustmentList(HubSearchVO hubSearch);
	//입출고조정 (존재여부확인하려고만들었음)
	public List<AdjustmentVO> selectInOutAdjustment(AdjustmentVO adjustment);
	//입출고조정 상세--------------------------------------------------------------
	//조회
	public List<AdjItemVO> selectInOutItemList(AdjItemVO adjItem);
	//입출고조정상세 (존재여부확인하려고만들었음)
	public AdjItemVO selectInOutItem(AdjItemVO adjItem);
	
	//입출고조정 등록
	public int insertInOutAdjustmentList(AdjustmentVO adjustmentVO);
	//입출고조정-품목 등록
	public int insertInOutItem(AdjItemVO adjItem);
	
	//입출고조정 수정
	public int updateInOutAdjustmentList(AdjustmentVO adjustmentVO);
	//입출고조정-품목 수정
	public int updateInOutItem(AdjItemVO adjItem);
	
	//입출고조정 삭제
	public int deleteInOutAdjustmentList(AdjustmentVO adjustmentVO);
	//입출고조정 삭제 시 입출고조정-품목 모두 삭제를 위한 메서드
	public int deleteAllAdjustment(String adjNum);
	//입출고조정-품목 개별삭제
	public int deleteInOutItem(AdjItemVO adjItem);
	
	//------------문서 등록시 품목 수량 변경시키기------------------------------------------
	//제품 입고
	public int updateProdEnter(AdjItemVO adjItem);
	public int updateWarProdEnter(AdjItemVO adjItem);
	//제품 출고
	public int updateProdOut(AdjItemVO adjItem);
	public int updateWarProdOut(AdjItemVO adjItem);
	//원재료 입고
	public int updateRawsEnter(AdjItemVO adjItem);
	public int updateWarRawsEnter(AdjItemVO adjItem);
	//원재료 출고
	public int updateRawsOut(AdjItemVO adjItem);
	public int updateWarRawsOut(AdjItemVO adjItem);
	//------------문서 삭제시 품목 수량 변경시키기------------------------------------------
	//제품 입고
	public int deleteProdEnter(AdjItemVO adjItem);
	public int deleteWarProdEnter(AdjItemVO adjItem);
	//제품 출고
	public int deleteProdOut(AdjItemVO adjItem);
	public int deleteWarProdOut(AdjItemVO adjItem);
	//원재료 입고
	public int deleteRawsEnter(AdjItemVO adjItem);
	public int deleteWarRawsEnter(AdjItemVO adjItem);
	//원재료 출고
	public int deleteRawsOut(AdjItemVO adjItem);
	public int deleteWarRawsOut(AdjItemVO adjItem);
	
	
	//문서 체크 테이블 insert
	public int insertCheck(AdjustmentVO adjustmentVO);
}
