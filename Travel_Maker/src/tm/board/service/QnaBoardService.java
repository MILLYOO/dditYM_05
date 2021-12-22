package tm.board.service;

import java.sql.SQLException;
import java.util.List;

import tm.board.dao.IQnaBoardDao;
import tm.board.dao.QnaBoardDao;
import tm.comm.vo.BoardVO;
import tm.comm.vo.PagingVO;
import tm.comm.vo.ReplyVO;
import tm.member.vo.MemberVO;

public class QnaBoardService implements IQnaBoardService{
	
	private IQnaBoardDao dao;
	private static IQnaBoardService service;
	
	private QnaBoardService() {
		dao = QnaBoardDao.getInstance();
	}
	
	public static IQnaBoardService getInstance() {
		if(service == null) {
			service = new QnaBoardService();
		}
		return service;
	}
	
	// 1:1 문의게시판 목록
	@Override
	public List<BoardVO> qnaAllList() {
		List<BoardVO> list = null;
		try {
			list = dao.qnaAllList();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	// 1:1 문의게시판 조회
	@Override
	public BoardVO qnaSelect(int tmBNo) {
		BoardVO vo = null;
		try {
			vo = dao.qnaSelect(tmBNo);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return vo;
	}

	// 1:1 문의게시판 수정
	@Override
	public int qnaUpdate(BoardVO vo) {
		int res = 0;
		try {
			res = dao.qnaUpdate(vo);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return res;
	}

	// 1:1 문의게시판 삭제
	@Override
	public int qnaDelete(BoardVO vo) {
		int res = 0;
		try {
			res = dao.qnaDelete(vo);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return res;
	}

	// 1:1 문의게시판 답변 조회
	@Override
	public List<ReplyVO> qnaReSelect(int tmBNo) {
		List<ReplyVO> list = null;
		try {
			list = dao.qnaReSelect(tmBNo);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	
	
	// 1:1 문의게시판 답변 등록
	@Override
	public int qnaReInsert(ReplyVO vo) {
		int res = 0;
		try {
			res = dao.qnaReInsert(vo);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return res;
	}

	// 1:1 문의게시판 답변 수정
	@Override
	public int replyUpdate(ReplyVO vo) {
		int res = 0;
		try {
			res = dao.replyUpdate(vo);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return res;
	}

	// 1:1 문의게시판 답변 삭제
	@Override
	public int qnaReDelete(ReplyVO vo) {
		int res = 0;
		try {
			res = dao.qnaReDelete(vo);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return res;
	}

	@Override
	public int qnaReOxUpdate(int tmBNo) {
		int res = 0;
		try {
			res = dao.qnaReOxUpdate(tmBNo);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return res;
	}

	@Override
	public int qnaReDelWith(int tmBNo) {
		int res = 0;
		try {
			res = dao.qnaReDelWith(tmBNo);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return res;
	}

	@Override
	public int qnaReOxDel(int tmBno) {
		int res = 0;
		try {
			res = dao.qnaReOxDel(tmBno);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return res;
	}

	@Override
	public int qnaReCount(int tmBNo) {
		int res = 0;
		try {
			res = dao.qnaReCount(tmBNo);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return res;
	}

	@Override
	public List<BoardVO> qnaSort(PagingVO pagingVO) {
		List<BoardVO> list = null;
		try {
			list = dao.qnaSort(pagingVO);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public int qnaAllCount() {
		int cnt = 0;
		try {
			cnt = dao.qnaAllCount();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return cnt;
	}

	@Override
	public int qnaSearchCount(BoardVO boardVO) {
		int cnt = 0;
		try {
			cnt = dao.qnaSearchCount(boardVO);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return cnt;
	}

	@Override
	public List<BoardVO> qnaSearch(MemberVO memberVO) {
		List<BoardVO> list = null;
		try {
			list = dao.qnaSearch(memberVO);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

}
