package kr.or.ddit.vo;

import java.util.List;

import lombok.Data;

@Data
public class DepartmentVO {
	private Integer departmentId;
	private String departmentName;
	private Integer managerId;
	private Integer locationId;
	
	private List<EmployeeVO> empList;
}
