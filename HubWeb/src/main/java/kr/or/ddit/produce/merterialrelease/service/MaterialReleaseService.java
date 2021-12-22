package kr.or.ddit.produce.merterialrelease.service;

import java.util.List;

import kr.or.ddit.common.vo.HubSearchVO;
import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.info2.vo.BomRawsVO;
import kr.or.ddit.produce.vo.InstrucprodVO;
import kr.or.ddit.produce.vo.MreleaseRawsVO;
import kr.or.ddit.produce.vo.MreleaseVO;

/**
 * 자재출고처리
 */
public interface MaterialReleaseService {
	
	/*****************************************************************/
	/*							자재출고처리서							 */
	/*****************************************************************/

	/**
	 * 자재출고처리서 조회
	 * @param hubSearch
	 * @return
	 */
	public List<MreleaseVO> retrieveMaterialReleaseList(HubSearchVO hubSearch);
	
	
	/**
	 * 자재출고처리 조회
	 * @param mreleaseVO
	 * @return
	 */
	public MreleaseVO retrieveMaterialRelease(MreleaseVO mreleaseVO);
	
	
	/**
	 * 자재출고처리서 등록 및 수정
	 * @param mrelease
	 * @return
	 */
	public ServiceResult createOrUpdateMaterialRelease(List<MreleaseVO> mrelease);
	
	/**
	 * 자재출고처리서 수정
	 * @param mrelease
	 * @return
	 */
//	public ServiceResult updateMaterialRelease(List<MreleaseVO> mrelease);
	
	/**
	 * 자재출고처리서 삭제
	 * @param mrelease
	 * @return
	 */
	public ServiceResult deleteMaterialRelease(List<MreleaseVO> mrelease);
	
	
	
	/*****************************************************************/
	/*							자재출고처리서 상세		   				 */
	/*****************************************************************/
	
	/**
	 * 자재출고처리서-원자재 리스트 조회
	 * @return
	 */
	public List<MreleaseRawsVO> retrieveMaterialReleaseRawsList(MreleaseRawsVO mreleaseRaws);
	
	
	/**
	 * 자재출고처리-원자재 조회
	 * @param mreleaseRawsVO
	 * @return
	 */
	public MreleaseRawsVO retrieveMaterialReleaseRaws(MreleaseRawsVO mreleaseRawsVO);
	
	
	/**
	 * 자채출고처리서-원자재 등록 및 수정
	 * @param mreleaseRaws
	 * @return
	 */
	public ServiceResult createOrUpdateRaws(List<MreleaseRawsVO> mreleaseRaws);
	
	/**
	 * 자재출고처리서-원자재 수정
	 * @param mreleaseRaws
	 * @return
	 */
//	public ServiceResult updateRaws(MreleaseRawsVO mreleaseRaws);
	
	/**
	 * 자재출고처리서-원자재 삭제
	 * @param mreleaseRaws
	 * @return
	 */
	public ServiceResult deleteRaws(List<MreleaseRawsVO> mreleaseRaws);
	
	
	
	/**
	 * 자재출고처리서-생산지시서적용
	 * @param instProdVO
	 * @return
	 */
	public ServiceResult createPiApplyMrelease(List<InstrucprodVO> instProdVO);
	
	
	/**
	 * 자재출고처리서-bom전개
	 * @param bomRaws
	 * @return
	 */
	public ServiceResult createBomApplyMrelease(List<BomRawsVO> bomRaws);
	
	
	
	
	/**
	 * 지시서 적용을 위한 지시서 목록
	 * @param hubSearchVO
	 * @return
	 */
	public List<InstrucprodVO> retrieveInstAppList(HubSearchVO hubSearchVO);

}













