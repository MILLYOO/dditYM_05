package kr.or.ddit.employee.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import kr.or.ddit.vo.DepartmentVO;
@Repository
public interface DepartmentDAO {
	public List<DepartmentVO> selectDepartmentList();

}
