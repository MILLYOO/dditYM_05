package kr.or.ddit.info2.codemanage.service;

import java.util.List;

import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.info2.vo.CommonVO;
import kr.or.ddit.info2.vo.DivisionVO;

/**
 * 규격/분류/단위/공정 관리 service
 */
public interface CodeManageService {


	/**
	 * 규격/단위/공정 리스트 조회
	 * @return
	 */
	public List<CommonVO> retrieveCodeList(CommonVO common);
	
	/**
	 * 규격/단위/공정 등록
	 * @param codeVO
	 * @return
	 */
	public ServiceResult createCode(CommonVO common);
	
	/**
	 * 규격/단위/공정 수정
	 * @param codeVO
	 * @return
	 */
	public ServiceResult updateCode(CommonVO common);
	
	/**
	 * 규격/단위/공정 삭제
	 * @param codeVO
	 * @return
	 */
	public ServiceResult deleteCode(CommonVO common);
	
	/**
	 * 분류 리스트 조회
	 * @param division
	 * @return
	 */
	public List<DivisionVO> retrieveDivList(DivisionVO division);
	
	/**
	 * 분류 추가
	 * @param common
	 * @return
	 */
	public ServiceResult createDiv(DivisionVO division);
	
	/**
	 * 분류 수정
	 * @param division
	 * @return
	 */
	public ServiceResult updateDiv(DivisionVO division);
	
	/**
	 * 분류 삭제
	 * @param division
	 * @return
	 */
	public ServiceResult deleteDiv(DivisionVO division);
}
