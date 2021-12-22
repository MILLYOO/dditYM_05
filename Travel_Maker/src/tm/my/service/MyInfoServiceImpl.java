package tm.my.service;

import java.sql.SQLException;

import tm.member.vo.MemberVO;
import tm.my.dao.IMyInfoDao;
import tm.my.dao.MyInfoDaoImpl;

public class MyInfoServiceImpl implements IMyInfoService{

	private IMyInfoDao myInfoDao;
	
	private static IMyInfoService myInfoService;
	
	private MyInfoServiceImpl() {
		myInfoDao = MyInfoDaoImpl.getInstance();
	}
	
	public static IMyInfoService getInstance() {
		
		if(myInfoService == null) {
			myInfoService = new MyInfoServiceImpl();
		}
		return myInfoService;
		
	}
	
	
	
	@Override
	public MemberVO getMember(String memId) {
		MemberVO mv = null;
		
		try {
			mv = myInfoDao.getMember(memId);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return mv;
	}

}
