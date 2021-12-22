package tm.my.service;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import tm.comm.vo.BoardVO;
import tm.my.dao.IMyBoardDao;
import tm.my.dao.MyBoardDaoImpl;

public class MyBoardServiceImpl implements IMyBoardService{

	private IMyBoardDao dao;
	private static IMyBoardService service;
	
	private MyBoardServiceImpl() {
		dao = MyBoardDaoImpl.getDao();
	}
	
	public static IMyBoardService getService() {
		if(service == null) service = new MyBoardServiceImpl();
		
		return service;
	}
	
	@Override
	public List<BoardVO> selectAll(Map<String, Object> map) {
		
		List<BoardVO> list = null;
		
		try {
			list=dao.selectAll(map);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
		return list;
	}
	// 

	@Override
	public int getTotalCount() {
		int cnt = 0;
		
		try {
			cnt = dao.getTotalCount();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return cnt;
	}
}
