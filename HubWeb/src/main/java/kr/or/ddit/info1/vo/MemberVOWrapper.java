package kr.or.ddit.info1.vo;

import java.util.ArrayList;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;

import lombok.Getter;
/*
 * MemberVOWrapper.java
 * By 유밀_211201(최종수정)
 * User Class를 상속받아 사용자의 정보를 스프링 시큐리티가 이용할 수 있도록 해주는 Wrapper 클래스 입니다
 */

@Getter
public class MemberVOWrapper extends User{
	private MemberVO authMember;

	public MemberVOWrapper(MemberVO authMember) {
		super(authMember.getMemId(), authMember.getMemPass(), 
		createAuthorityList(authMember.getMemAuthList()));
		this.authMember = authMember;
	}
	//사용자의 권한 리스트를 GrantedAuthority 클래스에 담는다
	public static List<GrantedAuthority> createAuthorityList(List<MemAuthVO> memAuthList) {
		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>(memAuthList.size());
		for (MemAuthVO memAuth : memAuthList) {
			authorities.add(new SimpleGrantedAuthority(memAuth.getAuthName()));
		}
		return authorities;
	}
}



