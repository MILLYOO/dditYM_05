package kr.or.ddit.board.dao;

import java.util.List;

import kr.or.ddit.vo.PagingVO;
import kr.or.ddit.vo.ReplyVO;

/**
 * FreeReply 테이블을 대상으로 한 CRUD
 */
public interface ReplyDAO {
	
	/**
	 * @param replyy
	 * @return
	 */
	public int insertReply(ReplyVO reply);
	
	/**
	 * @param pagingVO
	 * @return
	 */
	public int selectTotalRecord(PagingVO<ReplyVO> pagingVO);
	
	/**
	 * @param pagingVO
	 * @return
	 */
	public List<ReplyVO> selectReplyList(PagingVO<ReplyVO> pagingVO);
	
	/**
	 * @param reply
	 * @return
	 */
	public int updateReply(ReplyVO reply);
	
	/**
	 * @param repNo
	 * @return
	 */
	public int deleteReply(int repNo);
}
