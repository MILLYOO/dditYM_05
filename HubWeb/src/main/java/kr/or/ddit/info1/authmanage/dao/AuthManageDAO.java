package kr.or.ddit.info1.authmanage.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import kr.or.ddit.info1.vo.MemAuthVO;
import kr.or.ddit.info1.vo.MemberVO;

/**
 * 권한 설정 DAO
 */
@Repository
public interface AuthManageDAO {

	/**
	 * 전체 멤버 조회
	 * @return
	 */
	public List<MemberVO> selectMemList();
	
	public List<MemAuthVO> selecMemAuth(MemberVO member);
	
	/**
	 * 멤버의 권한을 변경
	 * @param member
	 * @return
	 */
	public int updateMemberAuth(MemAuthVO memAuth);
	
}
