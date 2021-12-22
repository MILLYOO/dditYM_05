package kr.or.ddit.employee.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import kr.or.ddit.vo.DepartmentVO;

public interface DepartmentDAO {
	public List<DepartmentVO> selectDepartmentList();

}
