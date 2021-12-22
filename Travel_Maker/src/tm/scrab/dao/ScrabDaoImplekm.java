package tm.scrab.dao;

import java.sql.SQLException;
import java.util.List;

import com.ibatis.sqlmap.client.SqlMapClient;

import tm.black.dao.BlackMemDaoImple;
import tm.black.dao.IBlackMemDao;
import tm.comm.util.SqlMapClientFactory;
import tm.comm.vo.PagingVO;
import tm.scrab.vo.ScrabVO;

public class ScrabDaoImplekm implements IScrabDaokm{
	
	private SqlMapClient smc;
	private static IScrabDaokm dao;
	
	private ScrabDaoImplekm() {
		smc = SqlMapClientFactory.getInstance();
	}
	
	public static IScrabDaokm getInstance() {
		
		if(dao == null) {
			dao = new ScrabDaoImplekm();
		}
		
		return dao;
	}
	
	//---------------------------------------------------------------------

	@Override
	public List<ScrabVO> selectMyScrab(ScrabVO scVO) throws SQLException {
		
		return smc.queryForList("scrab.selectMyScrab", scVO);
	}

	@Override
	public int countMyScrab(String tm_id) throws SQLException {
		
		return (int) smc.queryForObject("scrab.countMyScrab", tm_id);
	}

	@Override
	public List<ScrabVO> scrabForPlan(String tm_id) throws SQLException {
		
		return smc.queryForList("scrab.scrabForPlan", tm_id);
	}


}
