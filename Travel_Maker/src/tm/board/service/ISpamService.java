package tm.board.service;

import java.util.List;

import tm.comm.vo.BoardVO;

public interface ISpamService {
	
	
	public List<BoardVO> spamAllList();
	
	public BoardVO spamSelect(BoardVO vo);
	
	public int spamUpdate(BoardVO vo);
	
	public int spamDelete(BoardVO vo);
	
	public int spamDeleteWithLike(BoardVO vo);

}
