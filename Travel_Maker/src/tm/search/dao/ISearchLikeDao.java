package tm.search.dao;

import java.sql.SQLException;
import java.util.HashMap;

import tm.comm.vo.LikeHateVO;
import tm.comm.vo.SearchLikeHateVO;

public interface ISearchLikeDao {
	
		// 좋아요 추가
	public int addSearchLike(SearchLikeHateVO vo) throws SQLException;	
	
		// 좋아요 취소
	public int cancelSearchLike(SearchLikeHateVO vo) throws SQLException;
	

	
		// 좋아요 개수 조회
	public int getSearchLikeCount(SearchLikeHateVO vo) throws SQLException;
	
	
	
		// 좋아요 여부 조회
	public int chkSearchLike(SearchLikeHateVO vo) throws SQLException;
	


}
