package tm.black.dao;

import java.sql.SQLException;
import java.util.List;
import tm.black.vo.BlackMemVO;
import tm.comm.vo.PagingVO;
import tm.member.vo.MemberVO;

public interface IBlackMemDao {
	
	//전체 조회
	public List<BlackMemVO> getAllBlackMember(PagingVO pagingVO) throws SQLException;
	
	//전체 블랙멤버 수
	public int countBlackMember() throws SQLException;
	
	//중복조회(이미 블랙리스트 회원인지)
	public int checkBlackMember(String tm_id) throws SQLException;
	
	//신규 블랙리스트 등록 --> 블랙테이블  insert
	public int insertBlackMember(BlackMemVO blackVO) throws SQLException;
	
	//차단기록 있는 블랙리스트 재등록 --> 블랙테이블  update
	public int insertAgainBlack(BlackMemVO blackVO) throws SQLException;
		
	//회원 권한 수정  --> 멤버테이블 update
	public int memToBlack(MemberVO memVO) throws SQLException;
		
	//일반 회원전환 --> 멤버테이블 update
	public int blackToMem(MemberVO memVO) throws SQLException;

}
