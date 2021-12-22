package tm.search.service;

import java.sql.SQLException;
import java.util.HashMap;

import tm.comm.dao.BoardLikeDao;
import tm.comm.dao.IBoardLikeDao;
import tm.comm.vo.LikeHateVO;
import tm.comm.vo.SearchLikeHateVO;
import tm.search.dao.ISearchLikeDao;
import tm.search.dao.SearchLikeDao;

public class SearchLikeService implements ISearchLikeService{

	private ISearchLikeDao dao;
	private static ISearchLikeService service;
	
	private SearchLikeService() {
		dao = SearchLikeDao.getDao();
	}
	
	public static ISearchLikeService getService() {
		if(service == null) service = new SearchLikeService();
		
		return service;		
	}
//===========================================================
	// 좋아요 추가
	@Override
	public int addSearchLike(SearchLikeHateVO vo) {
		int res = 0;
		
		try {
			res = dao.addSearchLike(vo);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return res;
	}
	
	// 좋아요 취소
	@Override
	public int cancelSearchLike(SearchLikeHateVO vo) {
		int res = 0;
		
		try {
			res = dao.cancelSearchLike(vo);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return res;
	}


	// 좋아요 개수
	@Override
	public int getSearchLikeCount(SearchLikeHateVO vo) {
		int cnt = 0;
		
		try {
			cnt = dao.getSearchLikeCount(vo);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return cnt;
	}


	// 좋아요 여부 체크
	@Override
	public int chkSearchLike(SearchLikeHateVO vo) {
		int res = 0;
		
		try {
			res = dao.chkSearchLike(vo);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return res;
	}


	
	
	//=================================================================
	
	
	

}
