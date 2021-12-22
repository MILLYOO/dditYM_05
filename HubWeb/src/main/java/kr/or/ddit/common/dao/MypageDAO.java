package kr.or.ddit.common.dao;

import org.springframework.stereotype.Repository;

import kr.or.ddit.info1.vo.MemberVO;

@Repository
public interface MypageDAO {
	public MemberVO selectMemberForAuth(String memId);
}
