package kr.or.ddit.info1.employeemanage.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.or.ddit.common.vo.HubSearchVO;
import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.info1.employeemanage.dao.EmployeeManageDAO;
import kr.or.ddit.info1.vo.EmployeeVO;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class EmployeeManageServiceImpl implements EmployeeManageService {
	@Inject
	private EmployeeManageDAO dao;

	@Override
	public List<EmployeeVO> retrieveEmployeeList(HubSearchVO hubSearch) {
		List<EmployeeVO> empList = dao.selectEmpList(hubSearch);
		return empList;
	}

	@Transactional
	@Override
	public ServiceResult createEmployee(EmployeeVO employee) {
		ServiceResult result = null;
		int rowcnt = dao.insertEmployee(employee);
		if (rowcnt > 0) {
			result = ServiceResult.OK;
		} else {
			result = ServiceResult.FAILED;
		}
		return result;
	}

	@Transactional
	@Override
	public ServiceResult updateEmployee(EmployeeVO employee) {
		ServiceResult result = null;
		int rowcnt = dao.updateEmployee(employee);
		if (rowcnt > 0) {
			result = ServiceResult.OK;
		} else {
			result = ServiceResult.FAILED;
		}
		return result;
	}

	@Transactional
	@Override
	public ServiceResult updateEmployeeDetail(EmployeeVO employee) {
		ServiceResult result = null;
		int rowcnt = dao.updateEmployeeDetail(employee);
		if (rowcnt > 0) {
			result = ServiceResult.OK;
		} else {
			result = ServiceResult.FAILED;
		}
		return result;
	}

	@Transactional
	@Override
	public ServiceResult deleteEmployee(EmployeeVO employee) {
		ServiceResult result = null;
		int rowcnt = dao.deleteEmployee(employee);
		if (rowcnt > 0) {
			result = ServiceResult.OK;
		} else {
			result = ServiceResult.FAILED;
		}
		return result;
	}

}
