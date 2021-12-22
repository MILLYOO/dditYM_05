package tm.board.dao;

import java.sql.SQLException;
import java.util.List;

import tm.comm.vo.BoardVO;
import tm.comm.vo.PagingVO;
import tm.comm.vo.ReplyVO;
import tm.member.vo.MemberVO;

public interface IQnaBoardDao {
	
	// 검색 수 
	public int qnaSearchCount(BoardVO boardVO) throws SQLException;
	
	// 검색 목록
	public List<BoardVO> qnaSearch(MemberVO memberVO) throws SQLException;
	
	// 1:1 문의게시글 총 개수
	public int qnaAllCount() throws SQLException;

	// 1:1 문의게시판 목록
	public List<BoardVO> qnaAllList() throws SQLException;
	
	// 목록 정렬
	public List<BoardVO> qnaSort(PagingVO pagingVO) throws SQLException;
	
	// 1:1 문의게시판 조회
	public BoardVO qnaSelect(int tmBNo) throws SQLException;
	
	// 1:1 문의게시판 글 수정
	public int qnaUpdate(BoardVO vo) throws SQLException;
	
	// 1:1 문의게시판 글 삭제
	public int qnaDelete(BoardVO vo) throws SQLException;
	
	// 1:1 문의 게시판 삭제 할 때 같이 댓글 삭제
	public int qnaReDelWith(int tmBNo) throws SQLException;
	
	// 1:1 문의 게시판 답변 조회
	public List<ReplyVO> qnaReSelect(int tmBNo) throws SQLException;
	
	// 1:1 문의 게시판 답변 등록
	public int qnaReInsert(ReplyVO vo) throws SQLException;
	
	// 1:1 문의 게시판 답변 등록 후 게시글 답변 여부 변경
	public int qnaReOxUpdate(int tmBNo) throws SQLException;
	
	// 1:1 문의 게시판 답변 수정
	public int replyUpdate(ReplyVO vo) throws SQLException;
	
	// 1:1 문의 게시판 답변 삭제
	public int qnaReDelete(ReplyVO vo) throws SQLException;
	
	// 답변 다 삭제하면 답변여부 다시 변경
	public int qnaReOxDel(int tmBno) throws SQLException;
	public int qnaReCount(int tmBNo) throws SQLException;
	
}
