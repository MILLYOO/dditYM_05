package kr.or.ddit.info1.authmanage.service;

import java.util.List;

import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.info1.vo.MemAuthVO;
import kr.or.ddit.info1.vo.MemberVO;

/**
 * 권한 설정 service
 */
public interface AuthManageService {
	/** 
	 * 권한을 설정하기 위한 메소드
	 * @param auth
	 * @return OK, FAIL
	 */
	public ServiceResult updateAuth(MemAuthVO memAuth);
	
	public List<MemAuthVO> selectMemAuth(MemberVO member);
	
	/**
	 * 시스템의 권한목록을 조회하기 위한 메소드
	 * @return
	 */
	public List<MemberVO> retrieveMemList();
	
	
}
