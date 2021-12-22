package tm.member.dao;

import java.sql.SQLException;
import java.util.List;

import com.ibatis.sqlmap.client.SqlMapClient;

import tm.comm.util.SqlMapClientFactory;
import tm.comm.vo.PagingVO;
import tm.member.vo.MemberVO;

public class MemManageDao implements IMemManageDao{
	
	private SqlMapClient smc;
	private static MemManageDao dao; 
	
	private MemManageDao() {
		smc = SqlMapClientFactory.getInstance();
	}
	
	public static IMemManageDao getDao() {
		if(dao == null)dao = new MemManageDao();
		
		return dao;
	}
	
	// 회원 전체 목록
	@Override
	public List<MemberVO> memAllList(PagingVO pagingVO) throws SQLException {
		return smc.queryForList("admin.memAllList", pagingVO);
	}

	// 회원 정보 조회
	@Override
	public MemberVO memSelect(String id) throws SQLException {
		return (MemberVO) smc.queryForObject("admin.memSelect", id);
	}

	// 회원 검색
	@Override
	public List<MemberVO> memSearch(MemberVO vo) throws SQLException {
		return smc.queryForList("admin.memSearch",vo);
	}

	@Override
	public int memUpdate(MemberVO vo) throws SQLException {
		return smc.update("admin.memUpdate",vo);
	}

	@Override
	public int memDelete(String id) throws SQLException {
		return smc.update("admin.memDelete",id);
	}

	@Override
	public int memAllCount() throws SQLException {
		return (int) smc.queryForObject("admin.memAllCount");
	}

	@Override
	public int memSearchCount(MemberVO memvo) throws SQLException {
		return (int) smc.queryForObject("admin.memSearchCount",memvo);
	}



}
