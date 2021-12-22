package kr.or.ddit.board.service;

import java.util.List;

import kr.or.ddit.board.dao.ReplyDAO;
import kr.or.ddit.board.dao.ReplyDAOImpl;
import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.vo.PagingVO;
import kr.or.ddit.vo.ReplyVO;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ReplyServiceImpl implements ReplyService {
	ReplyDAO replyDAO = new ReplyDAOImpl();
	
	@Override
	public List<ReplyVO> retrieveReplyList(PagingVO<ReplyVO> pagingVO) {
		int totalRecord = replyDAO.selectTotalRecord(pagingVO);
		pagingVO.setTotalRecord(totalRecord);
		List<ReplyVO> reqplyList = replyDAO.selectReplyList(pagingVO);
		pagingVO.setDataList(reqplyList);
		return reqplyList;
	}
	
	@Override
	public ServiceResult createReply(ReplyVO reply) {
		return null;
	}

}
