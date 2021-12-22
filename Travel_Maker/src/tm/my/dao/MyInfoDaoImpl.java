package tm.my.dao;

import java.sql.SQLException;

import com.ibatis.sqlmap.client.SqlMapClient;

import tm.comm.util.SqlMapClientFactory;
import tm.member.vo.MemberVO;


public class MyInfoDaoImpl implements IMyInfoDao{

	private static IMyInfoDao myInfoDao;
	
	private SqlMapClient smc;
	
	private MyInfoDaoImpl() {
		smc = SqlMapClientFactory.getInstance();
	}
	
	public static IMyInfoDao getInstance() {
		
		if(myInfoDao == null) {
			myInfoDao = new MyInfoDaoImpl();

		}
		return myInfoDao;
	}
	
	
	
	@Override
	public MemberVO getMember(String memId) throws SQLException {
		
		MemberVO mv = (MemberVO) smc.queryForObject("my.getMember", memId);
		
		return mv;
	}




}
