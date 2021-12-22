package kr.or.ddit.info1.employeemanage.service;

import java.util.List;

import kr.or.ddit.common.vo.HubSearchVO;
import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.info1.vo.EmployeeVO;

public interface EmployeeManageService {

	/**
	 * 사원 리스트 조회
	 * @return
	 */
	public List<EmployeeVO> retrieveEmployeeList(HubSearchVO hubSearch);
	
	/**
	 * 사원 등록
	 * @param employee
	 * @return
	 */
	public ServiceResult createEmployee(EmployeeVO employee);
	
	/**
	 * 사원 수정
	 * @param employee
	 * @return
	 */
	public ServiceResult updateEmployee(EmployeeVO employee);
	
	/**
	 * 사원 디테일 수정
	 * @param employee
	 * @return
	 */
	public ServiceResult updateEmployeeDetail(EmployeeVO employee);
	
	/**
	 * 사원 삭제
	 * @param employee
	 * @return
	 */
	public ServiceResult deleteEmployee(EmployeeVO employee);
	
	
	
}
