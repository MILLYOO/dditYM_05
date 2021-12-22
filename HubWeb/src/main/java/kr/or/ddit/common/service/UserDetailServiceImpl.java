package kr.or.ddit.common.service;

import javax.inject.Inject;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import kr.or.ddit.common.dao.MemberDAO;
import kr.or.ddit.info1.vo.MemberVO;
import kr.or.ddit.info1.vo.MemberVOWrapper;
import lombok.extern.slf4j.Slf4j;

@Service("userDetailService")
public class UserDetailServiceImpl implements UserDetailsService {
	
	@Inject
	private MemberDAO memberDAO;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		//사용자의 아이디를 통해 사용자의 정보를 가져온다
		MemberVO member = memberDAO.selectMemberForAuth(username);
		if(member==null)
			throw new UsernameNotFoundException(username+"에 해당하는 사용자가 없음.");
		//스프링 시큐리티 사용을 위해 사용자의 정보를 Wrapping 한다
		MemberVOWrapper wrapper = new MemberVOWrapper(member);
		return wrapper;
	}
}


