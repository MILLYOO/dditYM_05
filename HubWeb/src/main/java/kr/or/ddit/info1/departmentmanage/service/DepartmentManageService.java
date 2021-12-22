package kr.or.ddit.info1.departmentmanage.service;

import java.util.List;

import kr.or.ddit.common.vo.HubSearchVO;
import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.info1.vo.DepartmentVO;

/**
 * 부서 관리 service
 */
public interface DepartmentManageService {
	
	/**
	 * 부서 리스트 조회
	 * @return
	 */
	public List<DepartmentVO> retrieveDepartmentManageList(HubSearchVO hubSearch);
	
	/**
	 * 부서 등록
	 * @param department
	 * @return
	 */
	public ServiceResult createDepartmentManage(DepartmentVO department);
	
	/**
	 * 부서 수정
	 * @param department
	 * @return
	 */
	public ServiceResult updateDepartmentManage(DepartmentVO department);
	
	/**
	 * 부서 삭제 메서드
	 * @param department
	 * @return
	 */
	public ServiceResult deleteDepartmentManage(DepartmentVO department);
	

}
