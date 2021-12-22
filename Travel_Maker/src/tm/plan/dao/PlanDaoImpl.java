package tm.plan.dao;

import java.sql.SQLException;
import java.util.List;

import com.ibatis.sqlmap.client.SqlMapClient;

import tm.comm.util.SqlMapClientFactory;
import tm.plan.vo.planDetailVO;
import tm.plan.vo.planVO;

public class PlanDaoImpl implements IPlanDao{

	private SqlMapClient smc;
	private static IPlanDao dao;
	
	private PlanDaoImpl() {
		smc = SqlMapClientFactory.getInstance();
	}
	
	public static IPlanDao getDao() {
		
		if(dao == null) dao = new PlanDaoImpl();
		
		return dao;
	}
	
	
	@Override
	public List<planVO> selectAll(String tm_id) throws SQLException {
		
		return smc.queryForList("plan.selectAll", tm_id);
	}

	@Override
	public int planDelete(String tm_plan_id) throws SQLException {
		
		return smc.delete("plan.deleteBoard", tm_plan_id);
	}

	@Override
	public int planDeleteDetail(String tm_plan_id) throws SQLException {
		
		return smc.delete("plan.deleteBoardDetail", tm_plan_id);
	}
	
	@Override
	public List<planDetailVO> planDetail(String tm_plan_id) throws SQLException {
		
		return smc.queryForList("plan.planDetail", tm_plan_id);
	}

	@Override
	public int planInsert(planVO vo) throws SQLException {
		return Integer.parseInt((String) smc.insert("plan.planInsert", vo));
	}

	@Override
	public void planDInsert(planDetailVO vo) throws SQLException {
		smc.insert("plan.planDInsert", vo);
	}


}
