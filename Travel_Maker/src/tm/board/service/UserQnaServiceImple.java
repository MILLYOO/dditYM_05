package tm.board.service;

import java.sql.SQLException;
import java.util.List;

import tm.board.dao.IQnaBoardDao;
import tm.board.dao.IUserQnaBoardDao;
import tm.board.dao.QnaBoardDao;
import tm.board.dao.UserQnaDaoImple;
import tm.comm.vo.BoardVO;
import tm.comm.vo.PagingVO;
import tm.comm.vo.ReplyVO;

public class UserQnaServiceImple implements IUserQnaBoardService{
	
	private IUserQnaBoardDao dao;
	private static IUserQnaBoardService qboardService;
	
	private UserQnaServiceImple() {
		dao = UserQnaDaoImple.getInstance();
	}
	
	public static IUserQnaBoardService getInstance() {
		
		if(qboardService == null) {
			qboardService = new UserQnaServiceImple();
		}
		
		return qboardService;
		
	}

	//--------------------------------------------------------------
	
	@Override
	public List<BoardVO> selectAll(PagingVO pagingVO) {
		
		List<BoardVO> boardList = null;
		
		try {
			boardList = dao.selectAll(pagingVO);
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
		return boardList;
	}

	@Override
	public int countTotalCount() {
		
		int cnt = 0;
		
		try {
			cnt = dao.countTotalCount();
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
		return cnt;
	}

	@Override
	public BoardVO selctBoard(int tm_b_no) {
		
		BoardVO bovo = null;
		
		try {
			bovo = dao.selctBoard(tm_b_no);
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
		return bovo;
	}

	@Override
	public int updateHit(int tm_b_no) {
		
		int hit = 0;
		
		try {
			hit = dao.updateHit(tm_b_no);
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
		return hit;
	}

	@Override
	public int insertBoard(BoardVO boardVO) {
		
		int cnt = 0;
		
		try {
			cnt = dao.insertBoard(boardVO);
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
		return cnt;
	}
	
	@Override
	public String checkBoardWriter(int tm_b_no) {
		
		String tm_id = null;
		
		try {
			tm_id = dao.checkBoardWriter(tm_b_no);
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
		return tm_id;
	}

	@Override
	public String checkReWriter(int tm_bc_no) {
		
		String tm_bc_writer = null;
		
		try {
			tm_bc_writer = dao.checkReWriter(tm_bc_no);
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
		return tm_bc_writer;
	}

	@Override
	public int checkNotUser(String tm_id) {
		
		int tm_author = -1;
		
		try {
			tm_author = dao.checkNotUser(tm_id);
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
				
		return tm_author;
	}

	@Override
	public int updateBoard(BoardVO boardVO) {
		
		int cnt = 0;
		
		try {
			cnt = dao.updateBoard(boardVO);
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
		return cnt;
	}

	@Override
	public int deleteBoard(BoardVO boardVO) {
		
		int cnt = 0;
		
		try {
			cnt = dao.deleteBoard(boardVO);
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
		return cnt;
	}
	
	@Override
	public List<BoardVO> searchBoard(BoardVO boardVO) {
		
		List<BoardVO> bovo = null;
		
		try {
			bovo = dao.searchBoard(boardVO);
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
		return bovo;
	}

	@Override
	public List<ReplyVO> replyList(int tm_b_no) {
		
		List<ReplyVO> revo = null;
		
		try {
			revo = dao.replyList(tm_b_no);
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
		return revo;
	}

	@Override
	public int insertReply(ReplyVO reVO) {
		
		int cnt = 0;
		
		try {
			cnt = dao.insertReply(reVO);
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
		return cnt;
	}

	@Override
	public int updateReply(ReplyVO reVO) {
		
		int cnt = 0;
		
		try {
			cnt = dao.updateReply(reVO);
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
		return cnt;
	}

	@Override
	public int deleteReply(int tm_bc_no) {
		
		int cnt = 0;
		
		try {
			cnt = dao.deleteReply(tm_bc_no);
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
		return cnt;
	}

	@Override
	public int deleteReplyAll(int tm_b_no) {
		
		int cnt = 0;
		
		try {
			cnt = dao.deleteReplyAll(tm_b_no);
			
		}catch (SQLException e) {
			e.printStackTrace();
		}
		
		return cnt;
	}

	


}
