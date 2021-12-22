package tm.search.service;

import java.sql.SQLException;
import java.util.List;

import tm.search.dao.IScrapDao;
import tm.search.dao.ScrapDao;
import tm.search.vo.ScrapVO;


public class ScrapService implements IScrapService{
	
	//싱글톤 부분-------------------------------------------------------
	private static IScrapService service;
	
	private IScrapDao dao;
	
	private ScrapService() {
		dao = ScrapDao.getInstance();
	}
	
	public static IScrapService getInstance() {
		
		if(service == null) {
			service = new ScrapService();
		}
		return service;
	}
	//--------------------------------------------------------------
	@Override
	public int insertScrap(ScrapVO vo) {
		int cnt = 0;
		
		try {
			cnt = dao.insertScrap(vo);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return cnt;
	}

	@Override
	public int deleteScrap(ScrapVO vo) {
		int cnt = 0;
		
		try {
			cnt = dao.deleteScrap(vo);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return cnt;
	}

	@Override
	public int checkScrap(ScrapVO vo) {
		int cnt = 0;
		
		try {
			cnt = dao.checkScrap(vo);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return cnt;
	}

	@Override
	public List<ScrapVO> listScrap(String tm_id) {
		List<ScrapVO> list = null;
		
		try {
			list = dao.listScrap(tm_id);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}
}
