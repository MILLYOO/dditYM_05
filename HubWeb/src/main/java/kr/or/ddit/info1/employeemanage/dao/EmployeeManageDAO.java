package kr.or.ddit.info1.employeemanage.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import kr.or.ddit.common.vo.HubSearchVO;
import kr.or.ddit.info1.vo.EmployeeVO;

/**
 * 사원 관리 DAO
 */
@Repository
public interface EmployeeManageDAO {
	
	//사원 리스트 조회
	public List<EmployeeVO> selectEmpList(@Param("hubSearchVO")HubSearchVO hubSearch);

	//사원 등록
	public int insertEmployee(EmployeeVO employee);
	
	//사원 수정
	public int updateEmployee(EmployeeVO employee);
	
	//사원 디테일 수정
	public int updateEmployeeDetail(EmployeeVO employee);
	
	//사원 삭제
	public int deleteEmployee(EmployeeVO employee);
}
