package kr.or.ddit.common.servlet;

import java.io.IOException;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
@WebServlet("/login/logout.do")
public class LogoutServlet extends HttpServlet {
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
		if(session.isNew()) {
			resp.sendError(HttpServletResponse.SC_BAD_REQUEST);
			return;
		}
		
//		session.removeAttribute("authMember");
		session.invalidate(); // 세션 만료시키기, 위의 과정이 필요 없어짐.
		String message = URLEncoder.encode("로그아웃 성공", "UTF-8");// 퍼센트인코딩.URL인코딩
		resp.sendRedirect(req.getContextPath() + "/index.jsp?message="+message);
	}
}
