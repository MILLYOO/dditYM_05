package tm.board.dao;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.ibatis.sqlmap.client.SqlMapClient;

import tm.comm.util.SqlMapClientFactory;
import tm.comm.vo.BoardVO;
import tm.comm.vo.ReplyVO;

public class TravelBoardDao implements ITravelBoardDao {
	
	   private static ITravelBoardDao dao;
	   private SqlMapClient smc;
	   private TravelBoardDao() {
	      smc = SqlMapClientFactory.getInstance();
	   }
	   
	   public static ITravelBoardDao getInstance() {
	      if(dao == null) {
	         dao = new TravelBoardDao();
	      }
	      return dao;
	   }

	@Override
	public List<BoardVO> selectAll() throws SQLException {
		return smc.queryForList("tBoard.selectAll");
	}

	@Override
	public List<BoardVO> selectByPage(Map<String, Integer> map) throws SQLException {
		return smc.queryForList("tBoard.selectByPage", map);
	}

	@Override
	public int getTotalCount() throws SQLException {
		int cnt = 0;
		
		cnt = (int)smc.queryForObject("tBoard.getTotalCount");
		
		return cnt;
	}

	@Override
	public int insertBoard(BoardVO vo) throws SQLException {
		
		return (int) smc.update("tBoard.insertBoard", vo);
		
		
	}

	@Override
	public int replySave(ReplyVO vo) throws SQLException {

		return (int)smc.update("tBoard.replySave", vo);
	}

	@Override
	public List<ReplyVO> replyList(int TM_B_NO) throws SQLException {

		return smc.queryForList("tBoard.replyList", TM_B_NO);
	}

	@Override
	public int replyModify(ReplyVO vo) throws SQLException {

		return smc.update("tBoard.replyModify",vo);
	}

	@Override
	public int replyDelete(int TM_BC_NO) throws SQLException {
		
		return smc.delete("tBoard.replyDelete",TM_BC_NO);
	}

//	@Override
//	public int hitUpdate(int seq) throws SQLException {
//
//		return smc.update("tBoard.hitUpdates",seq);
//	}

	/*@Override
	public int ModifyBoard(BoardVO vo) throws SQLException {
		
		return smc.update("tBoard.modifyBoard", vo);
	}

	@Override
	public int DeleteBoard(int TM_B_NO) throws SQLException {
		
		return smc.delete("tBoard.deleteBoard", TM_B_NO);
	}*/

	@Override
	public BoardVO getBoard(int TM_B_NO) throws SQLException {
		
		return (BoardVO) smc.queryForObject("tBoard.getBoard", TM_B_NO);
	}
	
	

	@Override
	public List<BoardVO> selectByPageID(Map<String, Object> map) throws SQLException {

		return smc.queryForList("tBoard.selectByPage", map);
	}

	@Override
	public String checkBoardWriter(int tm_b_no) throws SQLException {
		
		return (String) smc.queryForObject("tBoard.checkBoardWriter", tm_b_no);
	}

	@Override
	public String checkReWriter(int tm_bc_no) throws SQLException {
		
		return (String) smc.queryForObject("tBoard.checkReWriter", tm_bc_no);
	}

	@Override
	public int checkNotUser(String tm_id) throws SQLException {
		
		return (int) smc.queryForObject("tBoard.checkNotUser", tm_id);
	}

	@Override
	public int updateHit(int tm_b_no) throws SQLException {
		
		return smc.update("tBoard.updateHit", tm_b_no);
	}

	@Override
	public int updateBoard(BoardVO boardVO) throws SQLException {
		
		return smc.update("tBoard.updateBoard", boardVO);
	}

	@Override
	public int deleteBoard(BoardVO boardVO) throws SQLException {
		
		return smc.delete("tBoard.deleteBoard", boardVO);
	}

	@Override
	public int deleteReplyAll(int tm_b_no) throws SQLException {
		
		return smc.delete("userqna.deleteReplyAll", tm_b_no);
	}
	
	

}
