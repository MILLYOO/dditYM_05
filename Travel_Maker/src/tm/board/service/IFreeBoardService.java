package tm.board.service;

import java.util.List;
import java.util.Map;

import tm.comm.vo.BoardVO;
import tm.comm.vo.ReplyVO;

public interface IFreeBoardService {

	// 전체 리스트
	public List<BoardVO> selectAll();
	
	// 페이지별 리스트
	public List<BoardVO> selectByPage(Map<String, Integer> map);
	
	// 전체 글 개수 가져오는거
	public int getTotalCount();
	
	//글쓴이 체크
	public String checkBoardWriter(int tm_b_no);
	
	//댓쓴이 체크
	public String checkReWriter(int tm_bc_no);
	
	//일반회원 체크
	public int checkNotUser(String tm_id);
	
	// 글 수정하기
	public int ModifyBoard(BoardVO vo);
	
	// 글 저장하기
	public int insertBoard(BoardVO vo);
	
	// 글 삭제하기
	public int DeleteBoard(BoardVO boardVO);
	
	// 답변 달기
	public int replySave(ReplyVO vo);
	
	// 답변 리스트
	public List<ReplyVO> replyList(int bonum);
	
	// 답변 수정
	public int replyModify(ReplyVO vo);
	
	// 답변 삭제
	public int replyDelete(int renum);
	
	// 답변 전체삭제(글 전체삭제용)
	public int replyDeleteAll(int TM_B_NO);
	
	// 조회수 증가
	public int hitUpdate(int seq);

	// 글 상세보기
	public BoardVO getBoard(int tM_B_NO);
	
	
	// 페이지별, ID별 리스트
	public List<BoardVO> selectByPageID(Map<String, Object> map);
	
	
}



























