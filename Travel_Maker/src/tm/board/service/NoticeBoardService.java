package tm.board.service;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import tm.board.dao.INoticeBoardDao;
import tm.board.dao.NoticeBoardDao;
import tm.comm.vo.BoardVO;
import tm.comm.vo.PagingVO;
import tm.search.vo.SearchVO;

public class NoticeBoardService implements INoticeBoardService{

	private INoticeBoardDao dao;
	private static INoticeBoardService service;
	
	private NoticeBoardService() {
		dao = NoticeBoardDao.getInstance();
	}
	
	public static INoticeBoardService getInstance() {
		if(service == null) {
			service = new NoticeBoardService();
		}
		return service;
	}
	
	// 공지사항 목록
	@Override
	public List<BoardVO> noticeAllList() {
		List<BoardVO> list = null;
		try {
			list = dao.noticeAllList();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	
	// 공지사항 조회 
	@Override
	public BoardVO noticeSelect(int tmBNo) {
		BoardVO vo = null;
		try {
			vo = dao.noticeSelect(tmBNo);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return vo;
	}

	// 공지사항 등록 (관리자)
	@Override
	public int noticeInsert(BoardVO vo) {
		int res = 0;
		try {
			res = dao.noticeInsert(vo);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return res;
	}

	// 공지사항 수정 (관리자)
	@Override
	public int noticeUpdate(BoardVO vo) {
		int res = 0;
		try {
			res = dao.noticeUpdate(vo);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return res;
	}

	// 공지사항 삭제 (관리자)
	@Override
	public int noticeDelete(BoardVO vo) {
		int res = 0;
		try {
			res = dao.noticeDelete(vo);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return res;
	}

	// 공지사항 글 개수
	@Override
	public int getAllNoticeCount() {
		int res = 0;
		try {
			res = dao.getAllNoticeCount();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return res;
	}

	// 조회수
	@Override
	public int noticeHitUpdate(int tmBNo) {
		int res = 0;
		try {
			res = dao.noticeHitUpdate(tmBNo);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return res;
	}
	
	@Override
	public List<BoardVO> selectByPage(PagingVO pagingvo) {
		List<BoardVO> list = null;
		try {
			list = dao.selectByPage(pagingvo);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public int checkNotUser(String tm_id) {
		int tm_author = 0;
		
		try {
			tm_author = dao.checkNotUser(tm_id);
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
				
		return tm_author;
	}

	@Override
	public List<SearchVO> bestDestList() {
		List<SearchVO> list = null;
		try {
			list = dao.bestDestList();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

}
