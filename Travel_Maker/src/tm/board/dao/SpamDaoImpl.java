package tm.board.dao;

import java.sql.SQLException;
import java.util.List;

import com.ibatis.sqlmap.client.SqlMapClient;

import tm.comm.util.SqlMapClientFactory;
import tm.comm.vo.BoardVO;

public class SpamDaoImpl implements ISpamDao{

	SqlMapClient smc;
	private static ISpamDao dao;
	
	private SpamDaoImpl(){
		smc = SqlMapClientFactory.getInstance();
	}
	
	public static ISpamDao getInstance() {
		if(dao == null) {
			dao = new SpamDaoImpl();
		}
		return dao;
	}
	
	
	
	@Override
	public List<BoardVO> spamAllList() throws SQLException {
		return smc.queryForList("admin.spamAllList");
	}

	@Override
	public BoardVO spamSelect(BoardVO vo) throws SQLException {
		return (BoardVO) smc.queryForObject("admin.spamSelect",vo);
	}

	@Override
	public int spamUpdate(BoardVO vo) throws SQLException {
		return smc.update("admin.spamUpdate",vo);
	}

	@Override
	public int spamDelete(BoardVO vo) throws SQLException {
		return smc.delete("admin.spamDelete",vo);
	}

	@Override
	public int spamDeleteWithLike(BoardVO vo) throws SQLException {
		return smc.delete("admin.spamDeleteWithLike",vo);
	}
	

}
