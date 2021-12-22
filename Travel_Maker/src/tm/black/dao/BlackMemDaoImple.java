package tm.black.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.ibatis.sqlmap.client.SqlMapClient;

import tm.black.vo.BlackMemVO;
import tm.comm.util.SqlMapClientFactory;
import tm.comm.vo.PagingVO;
import tm.member.vo.MemberVO;

public class BlackMemDaoImple implements IBlackMemDao{
	
	//싱글톤
	private SqlMapClient smc;
	private static IBlackMemDao blackDao;
	
	private BlackMemDaoImple() {
		smc = SqlMapClientFactory.getInstance();
	}
	
	public static IBlackMemDao getInstance() {
		
		if(blackDao == null) {
			blackDao = new BlackMemDaoImple();
		}
		
		return blackDao;
	}
	
	//--------------------------------------------------

	//전체 조회
	@Override
	public List<BlackMemVO> getAllBlackMember(PagingVO pagingVO) throws SQLException {
		
		List<BlackMemVO> boardList = new ArrayList<BlackMemVO>();
		boardList = smc.queryForList("black.getAllBlackMember", pagingVO);
		
		return boardList;
	}

	//전체 블랙멤버 수
	@Override
	public int countBlackMember() throws SQLException {
		
		return (int) smc.queryForObject("black.countBlackMember");
	}


	//중복조회(이미 블랙리스트 회원인지)
	@Override
	public int checkBlackMember(String tm_id) throws SQLException {
		
		return (int) smc.queryForObject("black.checkBlackMember", tm_id);
	}

	//블랙리스트 등록 --> 블랙테이블  insert
	@Override
	public int insertBlackMember(BlackMemVO blackVO) throws SQLException {
		
		return smc.update("black.insertBlackMember", blackVO);
	}
	
	//블랙리스트 재등록 --> 블랙테이블 UPDATE
	@Override
	public int insertAgainBlack(BlackMemVO blackVO) throws SQLException {
		
		return smc.update("black.insertAgainBlack", blackVO);
	}

	//회원 권한 수정  --> 멤버테이블 update
	@Override
	public int memToBlack(MemberVO memVO) throws SQLException {
		
		return smc.update("black.memToBlack", memVO);
	}

	//일반 회원전환 --> 멤버테이블 update
	@Override
	public int blackToMem(MemberVO memVO) throws SQLException {
		
		return smc.update("black.blackToMem", memVO);
	}

	

	

}
