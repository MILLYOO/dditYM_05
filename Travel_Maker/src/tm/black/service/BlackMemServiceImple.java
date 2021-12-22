package tm.black.service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import tm.black.dao.BlackMemDaoImple;
import tm.black.dao.IBlackMemDao;
import tm.black.vo.BlackMemVO;
import tm.comm.vo.PagingVO;
import tm.member.vo.MemberVO;

public class BlackMemServiceImple implements IBlackMemService{
	
	private static IBlackMemDao blackDao;
	private static IBlackMemService blackService;
	
	private BlackMemServiceImple() {
		blackDao = BlackMemDaoImple.getInstance();
	}
	
	public static IBlackMemService getInstatnce() {
		
		if(blackService == null) {
			blackService = new BlackMemServiceImple();
		}
		
		return blackService;
	}
	
	//-------------------------------------------------------------

	@Override
	public List<BlackMemVO> getAllBlackMember(PagingVO pagingVO) {
		
		List<BlackMemVO> blackList = new ArrayList<BlackMemVO>();
		
		try {
			blackList = blackDao.getAllBlackMember(pagingVO);
			
		} catch (SQLException e) {
			e.printStackTrace();
			
		}
		
		
		return blackList;
	}
	
	

	@Override
	public int countBlackMember() {
		
		int cnt = 0;
		
		try {
			cnt = blackDao.countBlackMember();
		}catch (SQLException e) {
			e.printStackTrace();
		}
		
		return cnt;
	}
	
	

	@Override
	public int checkBlackMember(String tm_id) {
		
		int cnt = 0;
		
		try {
			cnt = blackDao.checkBlackMember(tm_id);
		
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return cnt;
	}

	
	//블랙리스트 등록 --> 블랙테이블  insert
	@Override
	public int insertBlackMember(BlackMemVO blackVO) {
		
		int cnt = 0;
		
		try {
			cnt = blackDao.insertBlackMember(blackVO);
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
		return cnt;
	}
	
	//블랙리스트 재등록 --> 블랙테이블 update
	@Override
	public int insertAgainBlack(BlackMemVO blackVO) {
		
		int cnt = 0;
		
		try {
			cnt = blackDao.insertAgainBlack(blackVO);
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
		return cnt;
	}

	//회원 권한 수정  --> 멤버테이블 update
	@Override
	public int memToBlack(MemberVO memVO) {
		
		int cnt = 0;
		
		try {
			cnt = blackDao.memToBlack(memVO);
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
		return cnt;
	}

	//일반 회원전환 --> 멤버테이블 update
	@Override
	public int blackToMem(MemberVO memVO) {
		
		int cnt = 0;
		
		try {
			cnt = blackDao.blackToMem(memVO);
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
		return cnt;
	}



}
