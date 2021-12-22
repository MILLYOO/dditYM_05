package tm.member.service;

import java.util.List;

import tm.comm.vo.PagingVO;
import tm.member.vo.MemberVO;

public interface IMemManageService {
	
	// 전체 회원 목록
	public List<MemberVO> memAllList(PagingVO pagingVO);
	
	// 회원 정보 조회
	public MemberVO memSelect(String id);
	
	// 회원 검색
	public List<MemberVO> memSearch(MemberVO vo);
	
	// 회원 정보 수정
	public int memUpdate(MemberVO vo);
	
	// 회원 삭제
	public int memDelete(String id);
	
	// 전체 회원 수 
	public int memAllCount();

	// 검색 회원 수 
	public int memSearchCount(MemberVO memvo);
}
