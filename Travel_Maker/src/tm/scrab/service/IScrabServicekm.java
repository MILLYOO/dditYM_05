package tm.scrab.service;

import java.util.List;

import tm.comm.vo.PagingVO;
import tm.scrab.vo.ScrabVO;


public interface IScrabServicekm {

	//스크랩 조회
	public List<ScrabVO> selectMyScrab (ScrabVO scVO);
	
	//일정용 조회
	public List<ScrabVO> scrabForPlan (String tm_id);
	
	//전체 스크랩 갯수(페이징 용)
	public int countMyScrab(String tm_id);
	
}
