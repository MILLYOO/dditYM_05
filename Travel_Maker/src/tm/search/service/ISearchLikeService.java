package tm.search.service;

import java.util.HashMap;

import tm.comm.vo.LikeHateVO;
import tm.comm.vo.SearchLikeHateVO;

public interface ISearchLikeService {

		// 좋아요 추가
	public int addSearchLike(SearchLikeHateVO vo);	
	
		// 좋아요 취소
	public int cancelSearchLike(SearchLikeHateVO vo);

		// 좋아요 개수 조회
	public int getSearchLikeCount(SearchLikeHateVO vo);
	

	
		// 좋아요 여부 조회
	public int chkSearchLike(SearchLikeHateVO vo);
	

	
}
