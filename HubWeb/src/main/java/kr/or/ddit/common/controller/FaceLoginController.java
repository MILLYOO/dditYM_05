package kr.or.ddit.common.controller;

import javax.annotation.Resource;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.security.web.csrf.CsrfTokenRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import kr.or.ddit.info1.vo.MemberVOWrapper;

@Controller
public class FaceLoginController {
	@Resource(name="authenticationManager")
	AuthenticationManager manager;
	@Resource(name="csrfTokenRepository")
	CsrfTokenRepository csrfTokenRepository;
	@Inject
	UserDetailsService userDetailService;
	
	@RequestMapping(value="/login/faceLoginProcess.do", method=RequestMethod.POST)
	public String index(@RequestParam("memId") String memId //1.얼굴인식 성공 시 회원의 아이디를 받아온다
			,@RequestParam("faceToken") String faceToken //2.URL을 이용한 로그인 방지를 위해 생성한 토큰을 받아온다
			,HttpServletRequest req
			){
	CsrfToken csrfToken = csrfTokenRepository.loadToken(req);//3.토큰을 로드하여 객체로 생성한다
	String faceTokenSession = csrfToken.getToken();//4.토큰을 얻어 변수에 저장한다
	MemberVOWrapper member = (MemberVOWrapper) userDetailService.loadUserByUsername(memId);//5.id를 활용해 member정보를 가져온다
	UsernamePasswordAuthenticationToken inputAuth = null;
	if(faceToken.equals(faceTokenSession)) { //6.토큰 존재시(얼굴인식을 통한 요청 시) 인증을 수행한다
		inputAuth = new UsernamePasswordAuthenticationToken(member, null, member.getAuthorities()) ;
	}
	SecurityContextHolder.getContext().setAuthentication(inputAuth);//7.인증을 처리한다
	
	return "redirect:/common/main.do";//8.로그인 후 메인페이지로 Redirect
	}
}
