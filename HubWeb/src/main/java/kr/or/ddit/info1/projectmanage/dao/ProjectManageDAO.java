package kr.or.ddit.info1.projectmanage.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import kr.or.ddit.common.vo.HubSearchVO;
import kr.or.ddit.info1.vo.ProjectVO;

/**
 * 프로젝트 관리 DAO
 */
@Repository
public interface ProjectManageDAO {
	
	//프로젝트 리스트 검색
	public List<ProjectVO> selectProjectList(@Param("hubSearchVO")HubSearchVO hubSearch);
	
	//프로젝트 추가
	public int insertPorject(ProjectVO project);
	
	//프로젝트 수정
	public int updateProject(ProjectVO project);
	
	//프로젝트 디테일 수정
	public int updateDetailProject(ProjectVO project);
	
	//프로젝트 삭제
	public int deleteProject(ProjectVO project);

}
