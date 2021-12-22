package tm.search.service;

import java.util.List;

import tm.search.vo.ScrapVO;

public interface IScrapService {
	
	//스크랩 추가
	public int insertScrap(ScrapVO vo);
	
	//스크랩 삭제
	public int deleteScrap(ScrapVO vo);
	
	//스크랩 조회
	public int checkScrap(ScrapVO vo);
	
	//스크랩 리스트
	public List<ScrapVO> listScrap(String tm_id);

}
