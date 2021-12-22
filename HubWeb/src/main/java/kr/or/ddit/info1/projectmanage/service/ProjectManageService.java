package kr.or.ddit.info1.projectmanage.service;
/**
 * 프로젝트 관리 service
 */

import java.util.List;

import kr.or.ddit.common.vo.HubSearchVO;
import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.info1.vo.ProjectVO;

public interface ProjectManageService {
	
	/**
	 * 프로젝트 리스트 조회
	 * @return
	 */
	public List<ProjectVO> retrieveProjectList(HubSearchVO hubSearch);
	
	/**
	 * 프로젝트 등록
	 * @param project
	 * @return
	 */
	public ServiceResult createProject(ProjectVO project);
	
	/**
	 * 프로젝트 수정
	 * @param project
	 * @return
	 */
	public ServiceResult updateProject(ProjectVO project);
	
	/**
	 * 프로젝트 디테일 수정
	 * @param project
	 * @return
	 */
	public ServiceResult updateDetailProject(ProjectVO project);
	/**
	 * 프로젝트 삭제
	 * @param project
	 * @return
	 */
	public ServiceResult deleteProject(ProjectVO project);


}
