package kr.or.ddit.board.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import kr.or.ddit.vo.BoardVO;
import kr.or.ddit.vo.PagingVO;

@Repository
public interface BoardDAO {
	
	/**
	 * @param board
	 * @return 1>0 성공 0 실패
	 */
	public int insertBoard(BoardVO board);
	
	/**
	 * @param pagingVO
	 * @return 전체레코드수
	 */
	public int selectTotalRecord(PagingVO<BoardVO> pagingVO);
	
	/**
	 * 검색 조건에 맞는게 없어도 null일 경우는 없다. 0이다
	 * @param pagingVO
	 * @return
	 */
	public List<BoardVO> selectBoardList(PagingVO<BoardVO> pagingVO);
	
	/**
	 * @param boNo
	 * @return 해당 게시글 번호가 없으면 null
	 */
	public BoardVO selectBoard(int boNo);
	
	/**
	 * 글을 작성한 작성자만 수정 할 수 있다.
	 * @param board
	 * @return 1>0 성공 0 실패
	 */
	public int updateBoard(BoardVO board);
	
	/**
	 * 글을 작성한 작성자만 수정 할 수 있다.
	 * @param boNo
	 * @return 1>0 성공 0 실패
	 */
	public int deleteBoard(int boNo);
	
	/**
	 * 조회수, 신고수 등을 증가시키기 위한 메소드
	 * @param pMap
	 * @return
	 */
	public int incrementCount(Map<String, Object> pMap);
	
}
