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

@WebServlet("/login.do")
public class Login extends HttpServlet{
	
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		req.getRequestDispatcher("/views/login.html").forward(req, resp);
				
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		HttpSession loginSession = req.getSession(true);
		
		String tm_id = req.getParameter("tm_id").trim();
		String tm_pass = req.getParameter("tm_pass").trim();
		String msg = "";
		
		IMemberService service = MemberService.getservice();
		
		MemberVO memVO = new MemberVO();
		memVO.setTm_id(tm_id);
		memVO.setTm_pass(tm_pass);
		
		int cnt = service.loginCheck(memVO);
		
		if(cnt == 1) {
			
			msg = "성공";
			
			loginSession.setAttribute("memVO", memVO);
			loginSession.setAttribute("msg", msg);
			
			System.out.println("세션id : " + loginSession);
			System.out.println("아이디 : " + ((MemberVO)loginSession.getAttribute("memVO")).getTm_id());
			
		}else {
			msg = "실패";
			loginSession.setAttribute("msg", msg);
		}
		
		req.getRequestDispatcher("/WEB-INF/views/login/loginResult.jsp").forward(req, resp);
		
	}

}
