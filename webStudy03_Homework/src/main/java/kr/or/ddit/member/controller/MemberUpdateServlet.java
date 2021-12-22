package kr.or.ddit.member.controller;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang3.StringUtils;

import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.member.service.MemberService;
import kr.or.ddit.member.service.MemberServiceImpl;
import kr.or.ddit.vo.MemberVO;

@WebServlet("/member/memberUpdate.do")
public class MemberUpdateServlet extends HttpServlet{
	private MemberService service = new MemberServiceImpl();
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		MemberVO authMember = (MemberVO) req.getSession().getAttribute("authMember");
		MemberVO member = service.retrieveMember(authMember.getMemId());
		req.setAttribute("member", member);
		
		String viewName = "member/memberForm";
		
		String prefix = "/WEB-INF/views/";
		String suffix = ".jsp";
		req.getRequestDispatcher(prefix + viewName + suffix).forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
//		1. 가입을 하기위한 파라미터 확보 -> MemberVO (빈유틸을 사용하기 위해 name을 vo의 파라미터와 맞추자)
		MemberVO member = new MemberVO();
		req.setAttribute("member", member);
		String memId = req.getParameter("memId");
		member.setMemId(memId);
		
		Map<String, String[]> paramterMap = req.getParameterMap();
		try {
			BeanUtils.populate(member, paramterMap);
		} catch (IllegalAccessException | InvocationTargetException e) {
			throw new ServletException(e);
		}
		
//		2. 검증 : DB스키마에 따른 검증 룰 (NotNull인가, 길이에 대한, 형식 등등)
		Map<String, String> errors = new LinkedHashMap<>();
		req.setAttribute("errors", errors);
		boolean valid = validate(member, errors);
		String viewName = null;
		String message = null;
		if(valid) {
//		3-1. 통과
//			로직 사용
			ServiceResult result = service.modifyMember(member);
			switch(result) {
			case INVALIDPASSWORD :
				viewName = "member/memberForm";
				message = "비밀번호 틀림";
				break;
			case OK : 
//			 - OK	  : index페이지로 이동
				viewName = "redirect:/mypage.do";
				break;
			default :
//			 - FAIL	  : 개발자가 잘못 만든 상황임(일반적으로는 500에러를 띄어야함) -> DB서버가 죽었음 
//			 			->  memberForm으로 이동(기존데이터 + 메시지).
				viewName = "member/memberForm";
				message = "서버 오류";
			}
		}else {
//		3-2. 불통
//			: memberForm으로 이동 (기존데이터 + 검증 결과 메시지 띄우기.)
			viewName = "member/memberForm";
			
		}
		req.setAttribute("message", message);
		if(viewName.startsWith("redirect:")) {
			viewName = viewName.substring("redirect:".length());
			resp.sendRedirect(req.getContextPath() + viewName);
		}else {
			String prefix = "/WEB-INF/views/";
			String suffix = ".jsp";
			req.getRequestDispatcher(prefix + viewName + suffix).forward(req, resp);		
		}
	}

	private boolean validate(MemberVO member, Map<String, String> errors) {
		boolean valid = true;
		if(StringUtils.isBlank(member.getMemId())) {valid = false; errors.put("memId", "회원아이디가(이) 누락되었음.");}
		if(StringUtils.isBlank(member.getMemPass())) {valid = false; errors.put("memPass", "비밀번호가(이) 누락되었음.");}
		if(StringUtils.isBlank(member.getMemName())) {valid = false; errors.put("memName", "회원명가(이) 누락되었음.");}
		if(StringUtils.isBlank(member.getMemRegno1())) {valid = false; errors.put("memRegno1", "비밀번호1가(이) 누락되었음.");}
		if(StringUtils.isBlank(member.getMemRegno2())) {valid = false; errors.put("memRegno2", "비밀번호2가(이) 누락되었음.");}
		if(StringUtils.isBlank(member.getMemZip())) {valid = false; errors.put("memZip", "우편번호가(이) 누락되었음.");}
		if(StringUtils.isBlank(member.getMemAdd1())) {valid = false; errors.put("memAdd1", "주소1가(이) 누락되었음.");}
		if(StringUtils.isBlank(member.getMemAdd2())) {valid = false; errors.put("memAdd2", "주소2가(이) 누락되었음.");}
		if(StringUtils.isBlank(member.getMemHometel())) {valid = false; errors.put("memHometel", "집전번가(이) 누락되었음.");}
		if(StringUtils.isBlank(member.getMemComtel())) {valid = false; errors.put("memComtel", "회사전번가(이) 누락되었음.");}
		if(StringUtils.isBlank(member.getMemMail())) {valid = false; errors.put("memMail", "이메일가(이) 누락되었음.");}
		return valid;
	}
}
