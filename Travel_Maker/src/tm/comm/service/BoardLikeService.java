package tm.comm.service;

import java.sql.SQLException;
import java.util.HashMap;

import tm.comm.dao.BoardLikeDao;
import tm.comm.dao.IBoardLikeDao;
import tm.comm.vo.LikeHateVO;

public class BoardLikeService implements IBoardLikeService{

	private IBoardLikeDao dao;
	private static IBoardLikeService service;
	
	private BoardLikeService() {
		dao = BoardLikeDao.getDao();
	}
	
	public static IBoardLikeService getService() {
		if(service == null) service = new BoardLikeService();
		
		return service;		
	}
	
	
	//=================================================================
	
	
	@Override
	//int tm_b_no, String tm_id
	public int addLike(LikeHateVO vo) {
		int res = 0;
		
		try {
			res = dao.addLike(vo);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return res;
	}

	
	
	@Override
	public int cancelLike(LikeHateVO vo) {
		int res = 0;
		
		try {
			res = dao.cancelLike(vo);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return res;
	}

	
	
	@Override
	public int addHate(LikeHateVO vo) {
		int res = 0;
		
		try {
			res = dao.addHate(vo);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return res;
	}

	
	
	@Override
	public int cancelHate(LikeHateVO vo) {
		int res = 0;
		
		try {
			res = dao.cancelHate(vo);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return res;
	}

	
	
	@Override
	public int getLikeCount(LikeHateVO vo) {
		int cnt = 0;
		
		try {
			cnt = dao.getLikeCount(vo);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return cnt;
	}

	
	
	@Override
	public int getHateCount(LikeHateVO vo) {
		int cnt = 0;
		
		try {
			cnt = dao.getHateCount(vo);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return cnt;
	}

	
	
	@Override
	public int chkLike(LikeHateVO vo) {
		int res = 0;
		
		try {
			res = dao.chkLike(vo);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return res;
	}

	
	
	@Override
	public int chkHate(LikeHateVO vo) {
		int res = 0;
		
		try {
			res = dao.chkHate(vo);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return res;
	}

}
