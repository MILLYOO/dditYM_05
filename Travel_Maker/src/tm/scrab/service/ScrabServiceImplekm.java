package tm.scrab.service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import tm.black.dao.BlackMemDaoImple;
import tm.black.dao.IBlackMemDao;
import tm.black.service.BlackMemServiceImple;
import tm.black.service.IBlackMemService;
import tm.comm.vo.PagingVO;
import tm.plan.dao.IPlanDao;
import tm.scrab.dao.IScrabDaokm;
import tm.scrab.dao.ScrabDaoImplekm;
import tm.scrab.vo.ScrabVO;

public class ScrabServiceImplekm implements IScrabServicekm{
	
	private static IScrabDaokm dao;
	private static IScrabServicekm service;
	
	private ScrabServiceImplekm() {
		dao = ScrabDaoImplekm.getInstance();
	}
	
	public static IScrabServicekm getInstatnce() {
		
		if(service == null) {
			service = new ScrabServiceImplekm();
		}
		
		return service;
	}
	//-------------------------------------------------------------

	@Override
	public List<ScrabVO> selectMyScrab(ScrabVO scVO) {
		
		List<ScrabVO> scrabList = new ArrayList<ScrabVO>();
		
		try {
			scrabList = dao.selectMyScrab(scVO);
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
		return scrabList;
	}

	@Override
	public int countMyScrab(String tm_id) {
		
		int myCount = 0;
		
		try {
			myCount = dao.countMyScrab(tm_id);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return myCount;
	}

	@Override
	public List<ScrabVO> scrabForPlan(String tm_id) {
		
		List<ScrabVO> scrabList = new ArrayList<ScrabVO>();
		
		try {
			scrabList = dao.scrabForPlan(tm_id);
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
		return scrabList;
	}



}
