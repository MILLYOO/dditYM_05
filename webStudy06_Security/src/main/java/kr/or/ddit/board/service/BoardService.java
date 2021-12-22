package kr.or.ddit.board.service;

import java.util.List;

import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.vo.AttatchVO;
import kr.or.ddit.vo.BoardVO;
import kr.or.ddit.vo.PagingVO;

public interface BoardService {
	/**
	 * @param board
	 * @return 성공 실패
	 */
	public ServiceResult createBoard(BoardVO board);
	
	/**
	 * pagingVO를 CallbyRe~ 구조로 채워줘야함
	 * @param pagingVO
	 * @return 
	 */
	public List<BoardVO> retrieveBoardList(PagingVO<BoardVO> pagingVO);
	
	/**
	 * 게시글 번호가 삭제되고 없으면 PKNOT 발생
	 * @param boNo
	 * @return
	 */
	public BoardVO retrieveBoard(int boNo);
	
	/**
	 * @param board
	 * @return pkNotFound, 작성자인증, 성공, 실패
	 */
	public ServiceResult modifyBoard(BoardVO board);
	
	/**
	 * 응집력을 높여서 따로 인증하는것만 만들기.
	 * @param board
	 * @return pkNotFound, INVALIDPASSWORD, 성공, 실패
	 */
	public ServiceResult removeBoard(BoardVO board);
	
	/**
	 * @param attNo
	 * @return 있거나, pkNotFound
	 */
	public AttatchVO download(int attNo);
}
