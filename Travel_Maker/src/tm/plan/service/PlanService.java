package tm.plan.service;

import java.sql.SQLException;
import java.util.List;

import tm.plan.dao.IPlanDao;
import tm.plan.dao.PlanDaoImpl;
import tm.plan.vo.planDetailVO;
import tm.plan.vo.planVO;

public class PlanService implements IPlanService{

	private IPlanDao dao;
	private static IPlanService service;
	
	private PlanService() {
		dao = PlanDaoImpl.getDao();
	}
	
	public static IPlanService getService() {
		if(service == null) service = new PlanService();
		return service;
	}
	
	
	
	
	@Override
	public List<planVO> selectAll(String tm_id) {
		List<planVO> list = null;
		
		try {
			list = dao.selectAll(tm_id);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return list;
	}

	
	
	
	
	@Override
	public int planDelete(String tm_plan_id) {
		int res = 0;
		
		try {
			res = dao.planDelete(tm_plan_id);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return res;
	}

	
	@Override
	public int planDeleteDetail(String tm_plan_id) {
		int res = 0;
		
		try {
			res = dao.planDeleteDetail(tm_plan_id);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return res;
	}
	
	
	
	@Override
	public List<planDetailVO> planDetail(String tm_plan_id) {
		List<planDetailVO> list = null;
		
		try {
			list = dao.planDetail(tm_plan_id);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return list;
	}

	@Override
	public int planInsert(planVO vo) {
		int res = 0;
		
		try {
			res = dao.planInsert(vo);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return res;
	}

	@Override
	public void planDInsert(planDetailVO vo) {
		
		try {
			dao.planDInsert(vo);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}



}
