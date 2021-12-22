package kr.or.ddit.info1.departmentmanage.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.or.ddit.common.vo.HubSearchVO;
import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.info1.departmentmanage.dao.DepartmentManageDAO;
import kr.or.ddit.info1.employeemanage.dao.EmployeeManageDAO;
import kr.or.ddit.info1.vo.DepartmentVO;
import kr.or.ddit.info1.vo.EmployeeVO;

@Service
public class DepartmentManageServiceImpl implements DepartmentManageService {
	@Inject
	private DepartmentManageDAO depDao;
	@Inject
	private EmployeeManageDAO empDao;
	
	@Override
	public List<DepartmentVO> retrieveDepartmentManageList(HubSearchVO hubSearch) {
		List<DepartmentVO> deptList = depDao.selectDeptList(hubSearch);
		return deptList;
	}

	@Transactional
	@Override
	public ServiceResult createDepartmentManage(DepartmentVO department) {
		ServiceResult result = null;
		int rowcnt = depDao.insertDepartment(department);
		if(rowcnt > 0) {
			result = ServiceResult.OK;
		}else {
			result = ServiceResult.FAILED;
		}
		return result;
	}

	@Transactional
	@Override
	public ServiceResult updateDepartmentManage(DepartmentVO department) {
		ServiceResult result = null;
		int rowcnt = depDao.updateDepartment(department);
		if(rowcnt > 0) {
			result = ServiceResult.OK;
		}else {
			result = ServiceResult.FAILED;
		}
		return result;
	}

	@Transactional
	@Override
	public ServiceResult deleteDepartmentManage(DepartmentVO department) {
		ServiceResult result = null;
		HubSearchVO hubSearch = new HubSearchVO();
		hubSearch.setDeptCode(department.getDeptCode());
		hubSearch.setDeptName(department.getDeptName());
		List<EmployeeVO> empList = empDao.selectEmpList(hubSearch);
		if(empList.isEmpty()) {
			int	rowcnt = depDao.deleteDepartment(department);
			if(rowcnt > 0) {
				result = ServiceResult.OK;
			}else {
				result = ServiceResult.FAILED;
			}
		}else {
			result = ServiceResult.BADREQUEST;
		}
		return result;
	}

}
