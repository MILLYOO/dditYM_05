package tm.member.service;

import java.util.List;

import tm.member.vo.MemberVO;

public interface IMemberService {

	//전체 리스트
	public List<MemberVO> selectAll(); 
	
	//회원가입 메서드
	public String insertMember(MemberVO vo);
	
	//id중복검사 메서드
	public String searchId(String id);
	
	// ID 찾기
	public String FindId(MemberVO mv);
	
	// PW 찾기
	public String FindPw(MemberVO mv);
	
	//로그인 입력값 대조
	public int loginCheck(MemberVO memVO);
	
	/*//회원비회원 대조
	public int memCheck*/
	
	//회원등급 체크
	public int checkAuthor(String tm_id);
	
}
