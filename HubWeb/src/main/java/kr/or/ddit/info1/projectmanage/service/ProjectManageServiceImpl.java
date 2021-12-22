package kr.or.ddit.info1.projectmanage.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.or.ddit.common.vo.HubSearchVO;
import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.info1.projectmanage.dao.ProjectManageDAO;
import kr.or.ddit.info1.vo.ProjectVO;

@Service
public class ProjectManageServiceImpl implements ProjectManageService {

	@Inject
	private ProjectManageDAO dao;

	@Override
	public List<ProjectVO> retrieveProjectList(HubSearchVO hubSearch) {
		List<ProjectVO> projList = dao.selectProjectList(hubSearch);
		return projList;
	}

	@Transactional
	@Override
	public ServiceResult createProject(ProjectVO project) {
		ServiceResult result = null;
		int rowcnt = dao.insertPorject(project);
		if (rowcnt > 0) {
			result = ServiceResult.OK;
		} else {
			result = ServiceResult.FAILED;
		}
		return result;
	}

	@Transactional
	@Override
	public ServiceResult updateProject(ProjectVO project) {
		ServiceResult result = null;
		int rowcnt = dao.updateProject(project);
		if (rowcnt > 0) {
			result = ServiceResult.OK;
		} else {
			result = ServiceResult.FAILED;
		}
		return result;
	}

	@Transactional
	@Override
	public ServiceResult updateDetailProject(ProjectVO project) {
		ServiceResult result = null;
		int rowcnt = dao.updateDetailProject(project);
		if (rowcnt > 0) {
			result = ServiceResult.OK;
		} else {
			result = ServiceResult.FAILED;
		}
		return result;
	}

	@Transactional
	@Override
	public ServiceResult deleteProject(ProjectVO project) {
		ServiceResult result = null;
		int rowcnt = dao.deleteProject(project);
		if (rowcnt > 0) {
			result = ServiceResult.OK;
		} else {
			result = ServiceResult.FAILED;
		}
		return result;
	}

}
