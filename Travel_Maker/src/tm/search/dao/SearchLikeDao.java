package tm.search.dao;

import java.sql.SQLException;
import java.util.HashMap;

import com.ibatis.sqlmap.client.SqlMapClient;

import tm.comm.util.SqlMapClientFactory;
import tm.comm.vo.LikeHateVO;
import tm.comm.vo.SearchLikeHateVO;

public class SearchLikeDao implements ISearchLikeDao{

	private SqlMapClient smc;
	private static ISearchLikeDao dao;
	
	private SearchLikeDao() {
		smc = SqlMapClientFactory.getInstance();
	}
	
	public static ISearchLikeDao getDao() {
		if(dao == null)dao = new SearchLikeDao();
		return dao;
	}

	
	//========================================================
	@Override
	// 좋아요 추가
	public int addSearchLike(SearchLikeHateVO vo) throws SQLException {
		
		return (int)smc.update("searchlikehate.addSearchLike", vo);
	}

	// 좋아요 취소
	@Override
	public int cancelSearchLike(SearchLikeHateVO vo) throws SQLException {
		
		return (int)smc.update("searchlikehate.cancelSearchLike", vo);
	}


	// 좋아요 갯수 파악
	@Override
	public int getSearchLikeCount(SearchLikeHateVO vo) throws SQLException {

		return (int)smc.queryForObject("searchlikehate.getSearchLikeCount", vo);
	}

	// 회원의 좋아요 여부 파악
	@Override
	public int chkSearchLike(SearchLikeHateVO vo) throws SQLException {
		
		return (int)smc.queryForObject("searchlikehate.chkSearchLike", vo);
	}


	
	
	//==============================================
	

}
