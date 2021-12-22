package tm.member.dao;

import java.sql.SQLException;
import java.util.List;

import com.ibatis.sqlmap.client.SqlMapClient;

import tm.comm.util.SqlMapClientFactory;
import tm.member.vo.MemberVO;

public class MemberDao implements IMemberDao{
	
	private SqlMapClient smc;
	private static MemberDao dao; //IMemberDao를 상속한 클래스가 많을 경우 IMemberDao가 와도 된다. 
	
	private MemberDao() {
		smc = SqlMapClientFactory.getInstance();
	}
	
	public static IMemberDao getDao() {
		if(dao == null)dao = new MemberDao();
		
		return dao;
	}

	@Override
	public List<MemberVO> selectAll() throws SQLException {
		return (List<MemberVO>)smc.queryForList("join.selectAll");
		
	}

	@Override
	public String insertMember(MemberVO vo) throws SQLException {
		
		return (String)smc.insert("join.insertMember", vo);
	}

	@Override
	public String searchId(String id) throws SQLException{
		
		return (String)smc.queryForObject("join.searchId", id);
	}
	
	// 아이디찾기
	@Override
	public String FindId(MemberVO mv) throws SQLException {
		return (String) smc.queryForObject("member.findId",mv);
	}

	@Override
	public String FindPw(MemberVO mv) throws SQLException {
		return (String) smc.queryForObject("member.findPw",mv);
	}

	@Override
	public int loginCheck(MemberVO memVO) throws SQLException {
		
		return (int) smc.queryForObject("member.loginCheck", memVO);
	}

	@Override
	public int checkAuthor(String tm_id) throws SQLException {
		
		return (int) smc.queryForObject("member.checkAuthor", tm_id);
	}


}
