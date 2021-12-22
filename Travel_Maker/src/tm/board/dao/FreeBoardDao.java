package tm.board.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.ibatis.sqlmap.client.SqlMapClient;

import tm.board.service.IFreeBoardService;
import tm.comm.util.SqlMapClientFactory;
import tm.comm.vo.BoardVO;
import tm.comm.vo.ReplyVO;

public class FreeBoardDao implements IFreeBoardDao{

	private SqlMapClient smc;
	private static IFreeBoardDao dao;

	private FreeBoardDao() {
		smc = SqlMapClientFactory.getInstance();
	}
	
	public static IFreeBoardDao getDao() {
		if(dao == null) dao = new FreeBoardDao();
		return dao;
	}
//===========================================================================================
	@Override
	public List<BoardVO> selectAll() throws SQLException {
		
		return smc.queryForList("freeboard.selectAll");
	}

	@Override
	public List<BoardVO> selectByPage(Map<String, Integer> map) throws SQLException {
		
		return smc.queryForList("freeboard.selectByPage", map);
	}

	@Override
	public int getTotalCount() throws SQLException {
		int cnt = (int)smc.queryForObject("freeboard.getTotalCount");
		
		return cnt;
	}

	@Override
	public int insertBoard(BoardVO vo) throws SQLException {
		
		return (int)smc.update("freeboard.insertBoard", vo);
	}

	@Override
	public int replySave(ReplyVO vo) throws SQLException {
		
		int a= 0; 
		Object  obj = smc.insert("freeboard.replySave", vo);
		
		System.out.println("obj====================" + obj);
		
		if(obj == null) a = 1;
		return a;
	}

	@Override
	public List<ReplyVO> replyList(int TM_B_NO) throws SQLException {

		return smc.queryForList("freeboard.replyList", TM_B_NO);
	}

	@Override
	public int replyModify(ReplyVO vo) throws SQLException {
		
		return smc.update("freeboard.replyModify",vo);
	}

	@Override
	public int replyDelete(int TM_BC_NO) throws SQLException {
		
		return smc.delete("freeboard.replyDelete",TM_BC_NO);
	}

	@Override
	public int hitUpdate(int seq) throws SQLException {
		
		return smc.update("freeboard.hitUpdates",seq);
	}

	@Override
	public int ModifyBoard(BoardVO vo) throws SQLException {
		
		return smc.update("freeboard.modifyBoard", vo);
	}

	@Override
	public int DeleteBoard(BoardVO boardVO) throws SQLException {
		
		return smc.delete("freeboard.deleteBoard", boardVO);
	}

	@Override
	public BoardVO getBoard(int TM_B_NO) throws SQLException {
		
		return (BoardVO) smc.queryForObject("freeboard.getBoard", TM_B_NO);
	}

	@Override
	public int replyDeleteAll(int TM_B_NO) throws SQLException {
		
		return smc.delete("freeboard.replyDeleteAll", TM_B_NO);
	}

	@Override
	public List<BoardVO> selectByPageID(Map<String, Object> map) throws SQLException {

		return smc.queryForList("freeboard.selectByPage", map);
	}

	@Override
	public String checkBoardWriter(int tm_b_no) throws SQLException {
		
		return (String) smc.queryForObject("freeboard.checkBoardWriter", tm_b_no);
	}

	@Override
	public String checkReWriter(int tm_bc_no) throws SQLException {
		
		return (String) smc.queryForObject("freeboard.checkReWriter", tm_bc_no);
	}

	@Override
	public int checkNotUser(String tm_id) throws SQLException {
		
		return (int) smc.queryForObject("freeboard.checkNotUser", tm_id);
	}
	
		

}
