package tm.board.dao;

import java.sql.SQLException;
import java.util.List;

import tm.comm.vo.BoardVO;

public interface ISpamDao {
	
	public List<BoardVO> spamAllList() throws SQLException;

	public BoardVO spamSelect(BoardVO vo) throws SQLException;
	
	public int spamUpdate(BoardVO vo) throws SQLException;
	
	public int spamDelete(BoardVO vo) throws SQLException;
	
	public int spamDeleteWithLike(BoardVO vo) throws SQLException;
}
