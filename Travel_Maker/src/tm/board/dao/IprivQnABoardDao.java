package tm.board.dao;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import tm.comm.vo.BoardVO;
import tm.comm.vo.ReplyVO;

public interface IprivQnABoardDao {

	// 전체 리스트
		public List<BoardVO> selectAll() throws SQLException;
		
		// 페이지별 리스트
		public List<BoardVO> selectByPage(Map<String, Integer> map) throws SQLException;
		
		// 전체 글 개수 가져오는거
		public int getTotalCount() throws SQLException;
		
		// 글 수정하기
		public int ModifyBoard(BoardVO vo) throws SQLException;
		
		// 글 저장하기
		public int insertBoard(BoardVO vo) throws SQLException;
		
		// 글 삭제하기
		public int DeleteBoard(int TM_B_NO) throws SQLException;
		
		// 답변 달기
		public int replySave(ReplyVO vo) throws SQLException;
		
		// 답변 리스트
		public List<ReplyVO> replyList(int bonum) throws SQLException;
		
		// 답변 수정
		public int replyModify(ReplyVO vo) throws SQLException;
		
		// 답변 삭제
		public int replyDelete(int renum) throws SQLException;
		
		// 답변 전체삭제(글 전체삭제용)
		public int replyDeleteAll(int TM_B_NO) throws SQLException;
		
		// 조회수 증가
		public int hitUpdate(int seq) throws SQLException;
		
		// 글 상세
		public BoardVO getBoard(int tM_B_NO) throws SQLException;
		
}
