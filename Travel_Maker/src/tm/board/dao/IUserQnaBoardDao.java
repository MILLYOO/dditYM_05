package tm.board.dao;

import java.sql.SQLException;
import java.util.List;

import tm.comm.vo.BoardVO;
import tm.comm.vo.PagingVO;
import tm.comm.vo.ReplyVO;

public interface IUserQnaBoardDao {

	//전체 조회
	public List<BoardVO> selectAll(PagingVO pagingVO) throws SQLException;
	
	//전체 글 개수(페이지별 목록 조회용)
	public int countTotalCount() throws SQLException;
	
	//개별 조회
	public BoardVO selctBoard(int tm_b_no) throws SQLException;
	
	//조회수 증가
	public int updateHit(int tm_b_no) throws SQLException;
	
	//글작성
	public int insertBoard(BoardVO boardVO) throws SQLException;
	
	//글쓴이 체크
	public String checkBoardWriter(int tm_b_no) throws SQLException;
	
	//댓쓴이 체크
	public String checkReWriter(int tm_bc_no) throws SQLException;
	
	//일반회원 체크
	public int checkNotUser(String tm_id) throws SQLException;
	
	//글수정
	public int updateBoard(BoardVO boardVO) throws SQLException;
	
	//글삭제
	public int deleteBoard(BoardVO boardVO) throws SQLException;
	
	//글 검색
	public List<BoardVO> searchBoard(BoardVO boardVO) throws SQLException;
	
	//댓글조회
	public List<ReplyVO> replyList(int tm_b_no) throws SQLException;
	
	//댓글작성
	public int insertReply(ReplyVO reVO) throws SQLException;
	
	//댓글수정
	public int updateReply(ReplyVO reVO) throws SQLException;
	
	//댓글 개별삭제
	public int deleteReply(int tm_bc_no) throws SQLException;
	
	//댓글 전체삭제 --> 게시글 삭제 시 수행
	public int deleteReplyAll(int tm_b_no) throws SQLException;

}
