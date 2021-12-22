package tm.member.contoller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import tm.member.service.IMemberService;
import tm.member.service.MemberService;
import tm.member.vo.MemberVO;

@WebServlet("/logout.do")
public class Logout extends HttpServlet{
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		HttpSession loginSession = req.getSession(false);
		
		MemberVO memvo = (MemberVO)loginSession.getAttribute("memVO");
		String tm_id = "notUser";
		int tm_author = -1;
		
		IMemberService service = MemberService.getservice();
		
		if(memvo!=null){
			tm_id = memvo.getTm_id();
			tm_author = service.checkAuthor(tm_id);
		}
		
		req.getRequestDispatcher("/WEB-INF/views/login/logoutResult.jsp").forward(req, resp);
	}

}
