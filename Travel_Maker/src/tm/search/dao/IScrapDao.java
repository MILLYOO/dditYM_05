package tm.search.dao;

import java.sql.SQLException;
import java.util.List;

import tm.search.vo.ScrapVO;


public interface IScrapDao {
	//스크랩 추가
	public int insertScrap(ScrapVO vo) throws SQLException;
	
	//스크랩 삭제
	public int deleteScrap(ScrapVO vo) throws SQLException;
	
	//스크랩 조회(버튼을 위해)
	public int checkScrap(ScrapVO vo) throws SQLException;
	
	//스크랩 리스트
	public List<ScrapVO> listScrap(String id) throws SQLException;
}
