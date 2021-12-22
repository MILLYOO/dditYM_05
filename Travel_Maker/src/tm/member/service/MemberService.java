package tm.member.service;

import java.sql.SQLException;
import java.util.List;

import tm.member.dao.IMemberDao;
import tm.member.dao.MemberDao;
import tm.member.vo.MemberVO;

public class MemberService implements IMemberService{
	
	private IMemberDao dao;
	private static IMemberService service;

	private MemberService() {
		dao = MemberDao.getDao(); 
	}
	
	public static IMemberService getservice() {  
		if(service == null) service = new MemberService();
		
		return service;
	}

	@Override
	public List<MemberVO> selectAll() {
		List<MemberVO> list = null;
		
		try {
			list = dao.selectAll();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
				
		return list;
	}

	@Override
	public String insertMember(MemberVO vo) {
		String id = null;
		try {
			id = dao.insertMember(vo);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return id;
	}

	@Override
	public String searchId(String id) {
		String dbId = null;
		
		try {
			dbId = dao.searchId(id);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return dbId;
	}

	// 아이디 찾기
	@Override
	public String FindId(MemberVO mv) {
		String findId = null; 
		try {
			findId = dao.FindId(mv);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return findId;
	}

	@Override
	public String FindPw(MemberVO mv) {
		String findPw = null;
		try {
			findPw = dao.FindPw(mv);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return findPw;
	}

	//로그인 정보 대조
	@Override
	public int loginCheck(MemberVO memVO) {
		
		int cnt = 0;
		
		try {
			cnt = dao.loginCheck(memVO);
		}catch (SQLException ex) {
			ex.printStackTrace();
		}
		
		return cnt;
	}

	@Override
	public int checkAuthor(String tm_id) {
		
		int author = -1;
		
		try {
			author = dao.checkAuthor(tm_id);
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
		return author;
	}
}
