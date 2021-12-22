package tm.board.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.ibatis.sqlmap.client.SqlMapClient;

import tm.board.service.IprivQnABoardService;
import tm.comm.util.SqlMapClientFactory;
import tm.comm.vo.BoardVO;
import tm.comm.vo.ReplyVO;

public class privQnABoardDao implements IprivQnABoardDao{

	private SqlMapClient smc;
	private static IprivQnABoardDao dao;

	private privQnABoardDao() {
		smc = SqlMapClientFactory.getInstance();
	}
	
	public static IprivQnABoardDao getDao() {
		if(dao == null) dao = new privQnABoardDao();
		return dao;
	}
//===========================================================================================
	@Override
	public List<BoardVO> selectAll() throws SQLException {
		
		return smc.queryForList("privQnAboard.selectAll");
	}

	@Override
	public List<BoardVO> selectByPage(Map<String, Integer> map) throws SQLException {
		
		return smc.queryForList("privQnAboard.selectByPage", map);
	}

	@Override
	public int getTotalCount() throws SQLException {
		int cnt = (int)smc.queryForObject("privQnAboard.getTotalCount");
		
		return cnt;
	}

	@Override
	public int insertBoard(BoardVO vo) throws SQLException {
		
		return (int)smc.update("privQnAboard.insertBoard", vo);
	}

	@Override
	public int replySave(ReplyVO vo) throws SQLException {
		
		int a= 0; 
		Object  obj = smc.insert("privQnAboard.replySave", vo);
		
		System.out.println("obj====================" + obj);
		
		if(obj == null) a = 1;
		return a;
	}

	@Override
	public List<ReplyVO> replyList(int TM_B_NO) throws SQLException {

		return smc.queryForList("privQnAboard.replyList", TM_B_NO);
	}

	@Override
	public int replyModify(ReplyVO vo) throws SQLException {
		
		return smc.update("privQnAboard.replyModify",vo);
	}

	@Override
	public int replyDelete(int TM_BC_NO) throws SQLException {
		
		return smc.delete("privQnAboard.replyDelete",TM_BC_NO);
	}

	@Override
	public int hitUpdate(int seq) throws SQLException {
		
		return smc.update("privQnAboard.hitUpdates",seq);
	}

	@Override
	public int ModifyBoard(BoardVO vo) throws SQLException {
		
		return smc.update("privQnAboard.modifyBoard", vo);
	}

	@Override
	public int DeleteBoard(int TM_B_NO) throws SQLException {
		
		return smc.delete("privQnAboard.deleteBoard", TM_B_NO);
	}

	@Override
	public BoardVO getBoard(int TM_B_NO) throws SQLException {
		
		return (BoardVO) smc.queryForObject("privQnAboard.getBoard", TM_B_NO);
	}

	@Override
	public int replyDeleteAll(int TM_B_NO) throws SQLException {
		
		return smc.delete("privQnAboard.replyDeleteAll", TM_B_NO);
	}

		
		

}
