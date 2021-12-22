package tm.search.service;

import java.sql.SQLException;
import java.util.List;

import tm.search.dao.ISearchDao;
import tm.search.dao.SearchDao;
import tm.search.vo.SearchVO;

public class SearchService implements ISearchService{
	//싱글톤 부분-------------------------------------------------------
	private static ISearchService service;
	
	private ISearchDao dao;
	
	private SearchService() {
		dao = SearchDao.getInstance();
	}
	
	public static ISearchService getInstance() {
		
		if(service == null) {
			service = new SearchService();
		}
		return service;
	}
	//--------------------------------------------------------------
	
	
	//--------------------------------------------------------------
	@Override
	public List<SearchVO> allInfo() {
		List<SearchVO> list = null;
		try {
			list = dao.allInfo();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public int setInfo(SearchVO vo) {
		int cnt = 0;
		try {
			cnt = dao.setInfo(vo);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return cnt;
	}

	@Override
	public List<SearchVO> searchInfo(String key) {
		List<SearchVO> list = null;
		try {
			list = dao.searchInfo(key);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;	
	}

	@Override
	public int updateInfo(SearchVO vo) {
		int cnt = 0;
		try {
			cnt = dao.updateInfo(vo);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return cnt;
	}

	@Override
	public int deleteInfo(String id) {
		int cnt = 0;
		try {
			cnt = dao.deleteInfo(id);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return cnt;
	}

	@Override
	public SearchVO searchInfo2(String key) {
		SearchVO vo = null;
		
		try {
			vo = dao.searchInfo2(key);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return vo;
	}

}
