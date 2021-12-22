package tm.search.service;

import java.util.List;

import tm.search.vo.SearchVO;

public interface ISearchService {
	// 전체 정보 출력
	public List<SearchVO> allInfo();
	
	// 정보 입력
	public int setInfo(SearchVO vo);
	
	// 정보 수정
	public int updateInfo(SearchVO vo);
	
	// 정보 삭제
	public int deleteInfo(String id);
	
	// 지역 검색하여 출력(오버레이시 활용)
	public List<SearchVO> searchInfo(String key);
	
	public SearchVO searchInfo2(String key);
}
