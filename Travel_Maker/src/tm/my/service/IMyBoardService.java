package tm.my.service;

import java.util.List;
import java.util.Map;

import tm.comm.vo.BoardVO;

public interface IMyBoardService {
	
	//나의 글 전체 조회
	public List<BoardVO> selectAll(Map<String,Object> map);
	
	// 전체 글 개수 가져오는거
	public int getTotalCount();
}
