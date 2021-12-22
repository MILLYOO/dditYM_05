package kr.or.ddit.common.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import kr.or.ddit.common.vo.DocCheckVO;

@Repository
public interface DocCheckDAO {
	
	public List<DocCheckVO> selectHistory();
	public List<DocCheckVO> selectDocunit();
	
	public int updateChkYN(String docNum);
}
