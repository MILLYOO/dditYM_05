package tm.search.dao;

import java.sql.SQLException;
import java.util.List;

import com.ibatis.sqlmap.client.SqlMapClient;

import tm.comm.util.SqlMapClientFactory;
import tm.search.vo.SearchVO;

public class SearchDao implements ISearchDao{
	//싱글톤 부분-------------------------------------------------------
	private static ISearchDao dao;
	
	private SqlMapClient smc;
	
	private SearchDao() {
		smc = SqlMapClientFactory.getInstance();
	}
	
	public static ISearchDao getInstance() {
		
		if(dao == null) {
			dao = new SearchDao();
		}
		return dao;
	}
	//--------------------------------------------------------------
	@Override
	public List<SearchVO> allInfo() throws SQLException {
		return smc.queryForList("search.allInfo");
	}

	@Override
	public int setInfo(SearchVO vo) throws SQLException {
		return smc.update("search.setInfo", vo);
	}

	@Override
	public List<SearchVO> searchInfo(String key) throws SQLException {
		return smc.queryForList("search.searchInfo", key);
		
	}

	@Override
	public int updateInfo(SearchVO vo) throws SQLException {
		return smc.update("search.updateInfo", vo);
	}

	@Override
	public int deleteInfo(String id) throws SQLException {
		return smc.delete("search.deleteInfo", id);
	}

	@Override
	public SearchVO searchInfo2(String key) throws SQLException {
		// TODO Auto-generated method stub
		return (SearchVO)smc.queryForObject("search.searchInfo2",key);
	}

}
