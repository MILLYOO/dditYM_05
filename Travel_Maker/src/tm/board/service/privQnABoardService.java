package tm.board.service;


import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import tm.board.dao.privQnABoardDao;

import tm.board.dao.IprivQnABoardDao;
import tm.comm.vo.BoardVO;
import tm.comm.vo.ReplyVO;

public class privQnABoardService implements IprivQnABoardService{

	private IprivQnABoardDao dao;
	private static IprivQnABoardService service;
	
	private privQnABoardService() {
		dao = privQnABoardDao.getDao();
	}
	
	public static IprivQnABoardService getService() {
		if(service == null) service = new privQnABoardService();
		
		return service;
	}
	
	
//==============================================================================	
	@Override
	public List<BoardVO> selectAll() {
		List<BoardVO> list = null;
		
		try {
			list = dao.selectAll();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return list;
	}

	@Override
	public List<BoardVO> selectByPage(Map<String, Integer> map) {
		List<BoardVO> list = null;
		
		try {
			list = dao.selectByPage(map);
		} catch (SQLException e) {

			e.printStackTrace();
		}
		
		return list;
	}

	@Override
	public int getTotalCount() {
		int cnt = 0;
		
		try {
			cnt = dao.getTotalCount();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return cnt;
	}

	@Override
	public int insertBoard(BoardVO vo) {
		int seq = 0;
		
		try {
			seq = dao.insertBoard(vo);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return seq;
	}

	@Override
	public int replySave(ReplyVO vo) {
		
		int seq = 0;
		
		try {
			seq = dao.replySave(vo);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return seq;
	}

	@Override
	public List<ReplyVO> replyList(int TM_B_NO) {
		List<ReplyVO> list = null;
		
		try {
			list = dao.replyList(TM_B_NO);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return list;
	}

	@Override
	public int replyModify(ReplyVO vo) {
		int res = 0;
		
		try {
			res = dao.replyModify(vo);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
		return res;
	}

	@Override
	public int replyDelete(int TM_BC_NO) {
		int res = 0;
		
		try {
			res = dao.replyDelete(TM_BC_NO);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return res;
	}

	@Override
	public int hitUpdate(int seq) {
		
		int res = 0;
		
		try {
			res = dao.hitUpdate(seq);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return res;
	}

	@Override
	public int ModifyBoard(BoardVO vo) {
		int res = 0;
		
		try {
			res = dao.ModifyBoard(vo);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return res;
	}

	@Override
	public int DeleteBoard(int TM_B_NO) {
		int res = 0;
		
		try {
			res = dao.DeleteBoard(TM_B_NO);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return res;
	}

	@Override
	public BoardVO getBoard(int TM_B_NO) {
		BoardVO vo = null;
		
		try {
			vo = dao.getBoard(TM_B_NO);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
		return vo;
	}

	@Override
	public int replyDeleteAll(int TM_B_NO) {
		int res = 0;
		
		try {
			res = dao.replyDeleteAll(TM_B_NO);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
		return res;
	}

	
}