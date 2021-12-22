package tm.board.dao;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import tm.comm.vo.BoardVO;
import tm.comm.vo.PagingVO;
import tm.search.vo.SearchVO;

public interface INoticeBoardDao {
	
	// 베스트 여행지 목록
	public List<SearchVO> bestDestList() throws SQLException;

	// 공지사항 목록
	public List<BoardVO> noticeAllList() throws SQLException;
	
	// 공지사항 조회
	public BoardVO noticeSelect(int tmBNo) throws SQLException;
	
	// 공지사항 추가 (관리자)
	public int noticeInsert(BoardVO vo) throws SQLException;
	
	// 공지사항 수정 (관리자)
	public int noticeUpdate(BoardVO vo) throws SQLException;
	
	// 공지사항 삭제 (관리자)
	public int noticeDelete(BoardVO vo) throws SQLException;
	
	// 공지사항 글 개수
	public int getAllNoticeCount() throws SQLException;
	
	// 조회수
	public int noticeHitUpdate(int tmBNo) throws SQLException;
	
	// 페이지 별 리스트
	public List<BoardVO> selectByPage(PagingVO pagingvo) throws SQLException;
	
	//일반회원 체크
	public int checkNotUser(String tm_id) throws SQLException;
}
