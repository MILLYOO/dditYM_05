package tm.board.dao;

import java.sql.SQLException;
import java.util.List;

import com.ibatis.sqlmap.client.SqlMapClient;

import tm.comm.util.SqlMapClientFactory;
import tm.comm.vo.BoardVO;
import tm.comm.vo.PagingVO;
import tm.comm.vo.ReplyVO;

public class UserQnaDaoImple implements IUserQnaBoardDao{
	
	private SqlMapClient smc;
	private static IUserQnaBoardDao userQDao;
	
	private UserQnaDaoImple() {
		smc = SqlMapClientFactory.getInstance();
	}
	
	public static IUserQnaBoardDao getInstance() {
		
		if(userQDao == null) {
			userQDao = new UserQnaDaoImple();
		}
		
		return userQDao;
	}
	
	//------------------------------------------------------------------------

	@Override
	public List<BoardVO> selectAll(PagingVO pagingVO) throws SQLException {
		
		return smc.queryForList("userqna.selectAll", pagingVO);
	}

	@Override
	public int countTotalCount() throws SQLException {
	
		return (int) smc.queryForObject("userqna.countTotalCount");
	}

	@Override
	public BoardVO selctBoard(int tm_b_no) throws SQLException {
		
		return (BoardVO) smc.queryForObject("userqna.selctBoard", tm_b_no);
	}

	@Override
	public int updateHit(int tm_b_no) throws SQLException {
		
		return smc.update("userqna.updateHit", tm_b_no);
	}

	@Override
	public int insertBoard(BoardVO boardVO) throws SQLException {
		
		return smc.update("userqna.insertBoard", boardVO);
	}
	
	@Override
	public String checkBoardWriter(int tm_b_no) throws SQLException {
		
		return (String) smc.queryForObject("userqna.checkBoardWriter", tm_b_no);
	}

	@Override
	public String checkReWriter(int tm_bc_no) throws SQLException {
		
		return (String) smc.queryForObject("userqna.checkReWriter", tm_bc_no);
	}

	@Override
	public int checkNotUser(String tm_id) throws SQLException {
		
		return (int) smc.queryForObject("userqna.checkNotUser", tm_id);
	}

	@Override
	public int updateBoard(BoardVO boardVO) throws SQLException {
		
		return smc.update("userqna.updateBoard", boardVO);
	}

	@Override
	public int deleteBoard(BoardVO boardVO) throws SQLException {
		
		return smc.delete("userqna.deleteBoard", boardVO);
	}
	
	@Override
	public List<BoardVO> searchBoard(BoardVO boardVO) throws SQLException {
		
		return smc.queryForList("userqna.searchBoard", boardVO);
	}

	@Override
	public List<ReplyVO> replyList(int tm_b_no) throws SQLException {
		
		return smc.queryForList("userqna.replyList", tm_b_no);
	}

	@Override
	public int insertReply(ReplyVO reVO) throws SQLException {
		
		return smc.update("userqna.insertReply", reVO);
	}

	@Override
	public int updateReply(ReplyVO reVO) throws SQLException {
		
		return smc.update("userqna.updateReply", reVO);
	}

	@Override
	public int deleteReply(int tm_bc_no) throws SQLException {
		
		return smc.delete("userqna.deleteReply", tm_bc_no);
	}

	@Override
	public int deleteReplyAll(int tm_b_no) throws SQLException {
		
		return smc.delete("userqna.deleteReplyAll", tm_b_no);
	}

	


}
