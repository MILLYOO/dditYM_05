package kr.or.ddit.info2.bommanage.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import kr.or.ddit.info2.vo.BomRawsVO;
import kr.or.ddit.info2.vo.BomVO;

/**
 * BOM 관리 DAO
 */
@Repository
public interface BomManageDAO {
	
	// bom 코드 조회
	public String selectBomCode(BomVO bom);
	
	// bom 리스트 조회
	public BomVO selectBom(BomVO bom);
	
	// bom 인서트
	public int insertBom(BomVO bom);
	
	// bom 업데이트
	public int updateBom(BomVO bom);
	
	// bom 삭제
	public int deleteBom(BomVO bom);
	
	// bomRawsList 조회
	public List<BomRawsVO> selectBomRawsList(@Param("bomVO")BomVO bom);
	
	// bomRawsList 인서트
	public int insertBomRaws(BomRawsVO bomRaws);
	
	// bomRawsList 수정
	public int updateBomRaws(BomRawsVO bomRaws);
	
	// bomRawsList 삭제
	public int deleteBomRaws(BomRawsVO bomRaws);

}