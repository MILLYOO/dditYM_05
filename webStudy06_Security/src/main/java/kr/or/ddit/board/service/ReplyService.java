package kr.or.ddit.board.service;

import java.util.List;

import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.vo.PagingVO;
import kr.or.ddit.vo.ReplyVO;

public interface ReplyService {
	
	public List<ReplyVO> retrieveReplyList(PagingVO<ReplyVO> pagingVO);
	
	public ServiceResult createReply(ReplyVO reply);
}
