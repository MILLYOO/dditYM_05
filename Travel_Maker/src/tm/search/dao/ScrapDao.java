package tm.search.dao;

import java.sql.SQLException;
import java.util.List;

import com.ibatis.sqlmap.client.SqlMapClient;

import tm.comm.util.SqlMapClientFactory;
import tm.search.vo.ScrapVO;


public class ScrapDao implements IScrapDao{
	//싱글톤 부분-------------------------------------------------------
	private static IScrapDao dao;
	
	private SqlMapClient smc;
	
	private ScrapDao() {
		smc = SqlMapClientFactory.getInstance();
	}
	
	public static IScrapDao getInstance() {
		
		if(dao == null) {
			dao = new ScrapDao();
		}
		return dao;
	}
	//--------------------------------------------------------------
	@Override
	public int insertScrap(ScrapVO vo) throws SQLException {
		return (int) smc.update("search.insertScrap", vo);
	}

	@Override
	public int deleteScrap(ScrapVO vo) throws SQLException {
		return (int) smc.update("search.deleteScrap", vo);
	}

	@Override
	public int checkScrap(ScrapVO vo) throws SQLException {
		return (int) smc.queryForObject("search.checkScrap", vo);
	}

	@Override
	public List<ScrapVO> listScrap(String id) throws SQLException {
		return (List<ScrapVO>)smc.queryForList("search.listScrap", id);
	}
}
