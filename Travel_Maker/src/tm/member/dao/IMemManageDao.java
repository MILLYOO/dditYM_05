package tm.member.dao;

import java.sql.SQLException;
import java.util.List;

import tm.comm.vo.PagingVO;
import tm.member.vo.MemberVO;

public interface IMemManageDao {
	
	// 전체 회원 목록
	public List<MemberVO> memAllList(PagingVO pagingVO) throws SQLException;
	
	// 회원 정보 조회
	public MemberVO memSelect(String id) throws SQLException;
	
	// 회원 검색
	public List<MemberVO> memSearch(MemberVO vo) throws SQLException;
	
	// 회원 정보 수정
	public int memUpdate(MemberVO vo) throws SQLException;
	
	// 회원 삭제
	public int memDelete(String id) throws SQLException;
	
	// 전체 회원 수 
	public int memAllCount() throws SQLException;
	
	// 검색 회원 수
	public int memSearchCount(MemberVO memvo) throws SQLException;

}
