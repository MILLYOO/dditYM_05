package tm.comm.dao;

import java.sql.SQLException;
import java.util.HashMap;

import com.ibatis.sqlmap.client.SqlMapClient;

import tm.comm.util.SqlMapClientFactory;
import tm.comm.vo.LikeHateVO;

public class BoardLikeDao implements IBoardLikeDao{

	private SqlMapClient smc;
	private static IBoardLikeDao dao;
	
	private BoardLikeDao() {
		smc = SqlMapClientFactory.getInstance();
	}
	
	public static IBoardLikeDao getDao() {
		if(dao == null)dao = new BoardLikeDao();
		return dao;
	}
	
	
	
	//==============================================
	@Override
	public int addLike(LikeHateVO vo) throws SQLException {
		
		return (int)smc.update("likehate.addLike", vo);
	}

	@Override
	public int cancelLike(LikeHateVO vo) throws SQLException {
		
		return (int)smc.update("likehate.cancelLike", vo);
	}

	@Override
	public int addHate(LikeHateVO vo) throws SQLException {

		return (int)smc.update("likehate.addHate", vo);
	}

	@Override
	public int cancelHate(LikeHateVO vo) throws SQLException {

		return (int)smc.update("likehate.cancelHate", vo);
	}

	@Override
	public int getLikeCount(LikeHateVO vo) throws SQLException {
		int cnt = (int)smc.queryForObject("likehate.getLikeCount", vo);
		
		return cnt;
	}

	@Override
	public int getHateCount(LikeHateVO vo) throws SQLException {
		int cnt = (int)smc.queryForObject("likehate.getHateCount", vo);
		
		return cnt;
	}

	@Override
	public int chkLike(LikeHateVO vo) throws SQLException {
		
		int cnt = (int)smc.queryForObject("likehate.chkLike", vo);
		
		return cnt;
	}

	@Override
	public int chkHate(LikeHateVO vo) throws SQLException{
		
		int cnt = (int)smc.queryForObject("likehate.chkHate", vo);
		
		return cnt;
	}

}
