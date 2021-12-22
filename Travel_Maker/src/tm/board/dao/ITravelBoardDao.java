package tm.board.dao;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import tm.comm.vo.BoardVO;
import tm.comm.vo.ReplyVO;

public interface ITravelBoardDao {
	   
	// 전체 리스트
	public List<BoardVO> selectAll() throws SQLException;
	
	// 페이지별 리스트
	public List<BoardVO> selectByPage(Map<String, Integer> map) throws SQLException;
	
	// 전체 글 개수 가져오는거
	public int getTotalCount() throws SQLException;
	
	//글쓴이 체크
	public String checkBoardWriter(int tm_b_no) throws SQLException;
	
	//댓쓴이 체크
	public String checkReWriter(int tm_bc_no) throws SQLException;
	
	//일반회원 체크
	public int checkNotUser(String tm_id) throws SQLException;
	
	/*// 글 수정하기
	public int ModifyBoard(BoardVO vo) throws SQLException;*/
	
	// 글 저장하기
	public int insertBoard(BoardVO vo) throws SQLException;
	
	/*// 글 삭제하기
	public int DeleteBoard(int TM_B_NO) throws SQLException;*/
	
	//글수정
	public int updateBoard(BoardVO boardVO) throws SQLException;
	
	//글삭제
	public int deleteBoard(BoardVO boardVO) throws SQLException;
	
	// 답변 달기
	public int replySave(ReplyVO vo) throws SQLException;
	
	// 답변 리스트
	public List<ReplyVO> replyList(int bonum) throws SQLException;
	
	// 답변 수정
	public int replyModify(ReplyVO vo) throws SQLException;
	
	// 답변 삭제
	public int replyDelete(int renum) throws SQLException;
	
	//댓글 전체삭제 --> 게시글 삭제 시 수행
	public int deleteReplyAll(int tm_b_no) throws SQLException;
	
	// 조회수 증가
	//public int hitUpdate(int seq) throws SQLException;
	
	//조회수 증가
	public int updateHit(int tm_b_no) throws SQLException;
	
	// 글 상세
	public BoardVO getBoard(int tM_B_NO) throws SQLException;
	
	// 페이지별, ID별 리스트
	public List<BoardVO> selectByPageID(Map<String, Object> map) throws SQLException;
		
	}
