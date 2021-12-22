package tm.my.dao;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.ibatis.sqlmap.client.SqlMapClient;

import tm.comm.util.SqlMapClientFactory;
import tm.comm.vo.BoardVO;

public class MyBoardDaoImpl implements IMyBoardDao {

	private SqlMapClient smc;
	private static IMyBoardDao dao;
	
	private MyBoardDaoImpl() {
		smc = SqlMapClientFactory.getInstance();
	}
	
	public static IMyBoardDao getDao() {
		
		if(dao == null) dao = new MyBoardDaoImpl();
		
		return dao;
	}
	
	
	
	
	@Override
	public List<BoardVO> selectAll(Map<String, Object> map) throws SQLException {
		
		return smc.queryForList("my.selectByPage", map);
	}

	@Override
	public int getTotalCount() throws SQLException{
		
		int cnt = (int)smc.queryForObject("my.getTotalCount");
		
		return cnt;
	}

}
