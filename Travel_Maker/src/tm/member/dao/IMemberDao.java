package tm.member.dao;

import java.sql.SQLException;
import java.util.List;

import tm.member.vo.MemberVO;

public interface IMemberDao {

	//전체 리스트
	public List<MemberVO> selectAll()throws SQLException; 
	
	//회원가입 메서드
	public String insertMember(MemberVO vo)throws SQLException;
	
	//id중복검사 메서드
	public String searchId(String pass)throws SQLException;
	
	// ID 찾기
	public String FindId(MemberVO mv) throws SQLException;
	
	// PW 찾기
	public String FindPw(MemberVO mv) throws SQLException;
	
	//로그인 입력값 대조
	public int loginCheck(MemberVO memVO) throws SQLException;
	
	//회원등급 체크
	public int checkAuthor(String tm_id) throws SQLException;
	

}
