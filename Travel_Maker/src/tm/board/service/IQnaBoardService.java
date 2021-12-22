package tm.board.service;

import java.util.List;

import tm.comm.vo.BoardVO;
import tm.comm.vo.PagingVO;
import tm.comm.vo.ReplyVO;
import tm.member.vo.MemberVO;

public interface IQnaBoardService {
	
	// 검색 수 
	public int qnaSearchCount(BoardVO boardVO);
	
	// 검색 목록
	public List<BoardVO> qnaSearch(MemberVO memberVO);
	
	// 1:1 문의게시글 총 개수
	public int qnaAllCount();
	
	// 1:1 문의게시판 목록
	public List<BoardVO> qnaAllList();
	
	// 목록 정렬
	public List<BoardVO> qnaSort(PagingVO pagingVO);
	
	// 1:1 문의게시판 조회
	public BoardVO qnaSelect(int tmBNo);
	
	// 1:1 문의게시판 글 수정
	public int qnaUpdate(BoardVO vo);
	
	// 1:1 문의게시판 글 삭제
	public int qnaDelete(BoardVO vo);
	
	// 1:1 문의 게시판 삭제 할 때 같이 댓글 삭제
	public int qnaReDelWith(int tmBNo);
	
	// 1:1 문의 게시판 답변 조회
	public List<ReplyVO> qnaReSelect(int tmBNo);
	
	// 1:1 문의 게시판 답변 등록
	public int qnaReInsert(ReplyVO vo);
	
	// 1:1 문의 게시판 답변 등록 후 게시글 답변 여부 변경
	public int qnaReOxUpdate(int tmBNo);
	
	// 1:1 문의 게시판 답변 수정
	public int replyUpdate(ReplyVO vo);
	
	// 1:1 문의 게시판 답변 삭제
	public int qnaReDelete(ReplyVO vo);
	
	// 답변 다 삭제하면 답변여부 다시 변경
	public int qnaReOxDel(int tmBno);
	public int qnaReCount(int tmBNo);
	
}
