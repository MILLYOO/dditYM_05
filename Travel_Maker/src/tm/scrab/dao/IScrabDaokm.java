package tm.scrab.dao;

import java.sql.SQLException;
import java.util.List;

import tm.comm.vo.PagingVO;
import tm.scrab.vo.ScrabVO;

public interface IScrabDaokm {

	//스크랩 조회
	public List<ScrabVO> selectMyScrab (ScrabVO scVO) throws SQLException;
	
	//일정용 조회
	public List<ScrabVO> scrabForPlan (String tm_id) throws SQLException;
	
	//전체 스크랩 갯수(페이징 용)
	public int countMyScrab(String tm_id) throws SQLException;

}
