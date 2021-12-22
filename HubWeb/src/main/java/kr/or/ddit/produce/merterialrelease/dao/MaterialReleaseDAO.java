package kr.or.ddit.produce.merterialrelease.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import kr.or.ddit.common.vo.HubSearchVO;
import kr.or.ddit.info2.vo.BomRawsVO;
import kr.or.ddit.produce.vo.InstrucprodVO;
import kr.or.ddit.produce.vo.MreleaseRawsVO;
import kr.or.ddit.produce.vo.MreleaseVO;
import kr.or.ddit.stock.vo.WarRawVO;

/**
 * 자재출고처리
 */
@Repository
public interface MaterialReleaseDAO {
	
	/**
	 * 새로 등록된 자재출고처리 체크 함수
	 * @param mreleaseVO
	 * @return
	 */
	public int insertCheck(MreleaseVO mreleaseVO);
	
	/**
	 * 자재출고처리 목록 조회
	 * @param hubSearchVO
	 * @return
	 */
	public List<MreleaseVO> selectMaterialReleaseList(HubSearchVO hubSearchVO);
	
	
	/**
	 * 자재출고처리 조회
	 * @param mreleaseVO
	 * @return
	 */
	public MreleaseVO selectMaterialRelease(MreleaseVO mreleaseVO);
	
	
	/**
	 * 자재출고처리-원자재 목록 조회
	 * @param mreleaseVO
	 * @return
	 */
	public List<MreleaseRawsVO> selectMaterialReleaseRawsList(MreleaseRawsVO mreleaseRawsVO);
	
	
	/**
	 * 자재출고처리-원자재 조회
	 * @param mreleaseRawsVO
	 * @return
	 */
	public MreleaseRawsVO selectMaterialReleaseRaw(MreleaseRawsVO mreleaseRawsVO);
	
	
	
	
	/**
	 * 자재출고처리서 등록
	 * @param mreleaseVO
	 * @return
	 */
	public int insertMaterialRelease(MreleaseVO mreleaseVO);
	
	
	/**
	 * 자재출고처리서 수정
	 * @param mreleaseVO
	 * @return
	 */
	public int updateMaterialRelease(MreleaseVO mreleaseVO);
	
	
	
	
	/**
	 * 자재출고처리-원자재 등록
	 * @param mreleaseRawsVO
	 * @return
	 */
	public int insertMaterialReleaseRaws(MreleaseRawsVO mreleaseRawsVO);
	

	/**
	 * 자재출고처리-원자재 수정
	 * @param mreleaseRawsVO
	 * @return
	 */
	public int updateMaterialReleaseRaws(MreleaseRawsVO mreleaseRawsVO);
	
	
	
	/**
	 * 자재출고처리서 삭제
	 * @param mreleaseVO
	 * @return
	 */
	public int deleteMaterialRelease(MreleaseVO mreleaseVO);
	
	/**
	 * 자재출고처리서-원자재 모두 삭제
	 * @param mreNum
	 * @return
	 */
	public int deleteAllMaterialReleaseRaws(String mreNum);
	
	/**
	 * 자재출고처리서-원자재 삭제
	 * @param mreleaseRawsVO
	 * @return
	 */
	public int deleteMaterialReleaseRaws(MreleaseRawsVO mreleaseRawsVO);
	
	
	
	/**
	 * 자재출고 시 원자재 수량 감소
	 * @param rawsCode
	 * @return
	 */
	public int updateRawsQtyMinus(MreleaseRawsVO mreleaseRawsVO);
	/**
	 * 자재출고 시 원자재 창고 수량 감소
	 * @param warRawVO
	 * @return
	 */
	public int updateWarQtyMinus(WarRawVO warRawVO);
	
	/**
	 * 자재출고 삭제 시 수량 증가
	 * @param rawsCode
	 * @return
	 */
	public int updateRawsQtyPlus(MreleaseRawsVO mreleaseRawsVO);
	/**
	 * 자재출고 삭제 시 원자재 창고 수량 증가
	 * @param warRawVO
	 * @return
	 */
	public int updateWarQtyPlus(WarRawVO warRawVO);
	
	
	
	
	
	
	
	
	/**
	 * 품목계정가져오기
	 * @param prodCode
	 * @return
	 */
	public String selectIcodeNameByPI(String prodCode);
	
	
	/**
	 * 품목계정가져오기(rawsCode)
	 * @param rawsCode
	 * @return
	 */
	public String selectIcodeNameByRw(String rawsCode);
	
	
	/**
	 * 자재출고 - BOM 전개를 위한 MRELEASE_RAWS등록
	 * @param bomRawsVO
	 * @return
	 */
	public int insertMreleaseRawsByBom(BomRawsVO bomRawsVO);
	
	
	/**
	 * 창고이름가져오기
	 * @param rawCode
	 * @return
	 */
	public List<String> selectWarName(String rawCode);
	
	
	
	/**
	 * 생산지시서 적용을 위한 생산지시서 목록(적용여부N)
	 * @return
	 */
	public List<InstrucprodVO> selectInstApplyList(HubSearchVO hubSearch);
	
	
	/**
	 * 지시서 적용 시 지시서 적용 여부 변경
	 * @param instVO
	 * @return
	 */
	public int updateInstApplyYn(InstrucprodVO instVO);
	
	
	/**
	 * 지시서 적용된 자재출고처리목록
	 * @param mreleaseVO
	 * @return
	 */
	public List<MreleaseVO> selectPiAppMrList(MreleaseVO mreleaseVO);
	
}
