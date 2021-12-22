package kr.or.ddit.info1.departmentmanage.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import kr.or.ddit.common.vo.HubSearchVO;
import kr.or.ddit.info1.vo.DepartmentVO;

/**
 * 부서 관리 DAO
 */
@Repository
public interface DepartmentManageDAO {

	//부서 리스트 조회
	public List<DepartmentVO> selectDeptList(@Param("hubSearchVO")HubSearchVO hubSearch);
	
	//부서 등록
	public int insertDepartment(DepartmentVO department);
	
	//부서 수정
	public int updateDepartment(DepartmentVO department);
	
	//부서 삭제
	public int deleteDepartment(DepartmentVO department);
}
