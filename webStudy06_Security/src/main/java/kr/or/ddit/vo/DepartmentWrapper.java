package kr.or.ddit.vo;

import java.util.ArrayList;
import java.util.List;

public class DepartmentWrapper implements FancyTreeNode{
	
	private DepartmentVO data;
	
	public DepartmentWrapper(DepartmentVO data) {
		this.data = data;
	}
	@Override
	public String getTitle() {
		return data.getDepartmentName();
	}

	@Override
	public String getKey() {
		return data.getDepartmentId().toString();
	}

	@Override
	public boolean isFolder() { // 모든 부서는 한명 이상의 직원이 있다는 가정하게 true를 반환
		return true;
	}
	
	@Override
	public boolean isLazy() {
		return isFolder();
	}
	
	@Override
	public Object getData() {
		return data;
	}
	
	@Override
	public List<FancyTreeNode> getChildren() {
		List<EmployeeVO> empList = data.getEmpList();
		List<FancyTreeNode> nodeList = new ArrayList<>(empList.size());
		for(EmployeeVO emp : empList) {
			nodeList.add(new EmployeeWrapper(emp));
		}
		return nodeList;
	}
}
