package tm.board.service;

import java.sql.SQLException;
import java.util.List;

import tm.comm.vo.BoardVO;
import tm.comm.vo.PagingVO;
import tm.comm.vo.ReplyVO;

public interface IUserQnaBoardService {
	
	//전체 조회
	public List<BoardVO> selectAll(PagingVO pagingVO);
	
	//전체 글 개수(페이지별 목록 조회용)
	public int countTotalCount();
	
	//개별 조회
	public BoardVO selctBoard(int tm_b_no);
	
	//조회수 증가
	public int updateHit(int tm_b_no);
	
	//글작성
	public int insertBoard(BoardVO boardVO);
	
	//글쓴이 체크
	public String checkBoardWriter(int tm_b_no);
	
	//댓쓴이 체크
	public String checkReWriter(int tm_bc_no);
	
	//일반회원 체크
	public int checkNotUser(String tm_id);
	
	//글수정
	public int updateBoard(BoardVO boardVO);
	
	//글삭제
	public int deleteBoard(BoardVO boardVO);
	
	//글 검색
	public List<BoardVO> searchBoard(BoardVO boardVO);
	
	//댓글조회
	public List<ReplyVO> replyList(int tm_b_no);
	
	//댓글작성
	public int insertReply(ReplyVO reVO);
	
	//댓글수정
	public int updateReply(ReplyVO reVO);
	
	//댓글 개별삭제
	public int deleteReply(int tm_bc_no);
	
	//댓글 전체삭제 --> 게시글 삭제 시 수행
	public int deleteReplyAll(int tm_b_no);
	
}
