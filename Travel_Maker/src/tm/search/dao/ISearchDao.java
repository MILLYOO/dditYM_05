package tm.search.dao;

import java.sql.SQLException;
import java.util.List;

import tm.search.vo.SearchVO;

public interface ISearchDao {
	
	// 전체 정보 출력
	public List<SearchVO> allInfo() throws SQLException;
	
	// 정보 입력
	public int setInfo(SearchVO vo) throws SQLException;
	
	// 정보 수정
	public int updateInfo(SearchVO vo) throws SQLException;
	
	public int deleteInfo(String id) throws SQLException;
	
	// 지역 검색하여 출력(오버레이시 활용)
	public List<SearchVO> searchInfo(String key) throws SQLException;
	
	public SearchVO searchInfo2(String key) throws SQLException;
}
