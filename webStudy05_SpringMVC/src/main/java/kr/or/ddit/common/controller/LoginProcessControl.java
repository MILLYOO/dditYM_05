package kr.or.ddit.common.controller;

import javax.inject.Inject;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.member.service.AuthenticateService;
import kr.or.ddit.vo.MemberVO;

@Controller
public class LoginProcessControl extends HttpServlet {

	@Inject
	private AuthenticateService service;

	@RequestMapping(value = "/login/loginProcess.do", method = RequestMethod.POST)
	public String doPost(@RequestParam(required = true) String memId, @RequestParam(required = true) String memPass,
			HttpSession session) {
		// 모델 2 구조는 보통 파라미터검증 -> 인증 -> 보내고
//		String mem_id = req.getParameter("mem_id");
//		String mem_pass = req.getParameter("mem_pass");
		MemberVO member = new MemberVO(memId, memPass);

		boolean valid = validate(member);
		String location = null;
		String message = null;
//		HttpSession session = req.getSession();
		if (valid) {
			ServiceResult result = service.authenticated(member);
			if (ServiceResult.OK.equals(result)) {
				location = "redirect:/";
				session.setAttribute("authMember", member);
			} else {
				location = "redirect:/login/loginForm.jsp";
				session.setAttribute("failId", member.getMemId());
				if (ServiceResult.NOTEXIST.equals(result)) {
					message = "아이디가 잘못됐음. 확인하쇼";
				} else {
					message = "비번이 잘못됐음. 확인하쇼";
				}
//				req.getRequestDispatcher(dest).forward(req, resp);
			}
		} else {
//			resp.sendError(HttpServletResponse.SC_BAD_REQUEST);
			location = "redirect:/login/loginForm.jsp";
			message = "아이디나 비밀번호가 누락됬음. 확인하셈요";
		}
		session.setAttribute("message", message);
//		resp.sendRedirect(req.getContextPath() + location);
		return location;
	}


	private boolean validate(MemberVO member) {
		boolean valid = true;
		if (StringUtils.isBlank(member.getMemId())) {
			valid = false;
		}
		if (StringUtils.isBlank(member.getMemPass())) {
			valid = false;
		}
		return valid;
	}
}
