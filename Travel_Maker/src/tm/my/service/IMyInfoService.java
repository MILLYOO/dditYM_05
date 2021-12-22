package tm.my.service;

import tm.member.vo.MemberVO;

public interface IMyInfoService {

	/**
	 * 회원 id를 가지고 회원정보를 조회.
	 * @param memId
	 * @return
	 */
	public MemberVO getMember(String memId);
	
}
