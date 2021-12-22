package tm.member.contoller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import tm.member.service.IMemberService;
import tm.member.service.MemberService;
import tm.member.vo.MemberVO;

@WebServlet("/FindPw.do")
public class FindPw extends HttpServlet{
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		
		String id = req.getParameter("id");
		String tel = req.getParameter("tel");
		String email = req.getParameter("email");
		
		MemberVO mv = new MemberVO();
		mv.setTm_id(id);
		mv.setTm_tel(tel);
		mv.setTm_email(email);
	
		IMemberService service = MemberService.getservice();
		
		String res = service.FindPw(mv);
		
		if(res != null) {
			// 비밀번호 이메일 전송
			SendEmail.MailSend(res, email);			
		}
		
		
		// --------------------- 비밀번호 jsp로 보내는 부분 ---------------------------
		
		req.setAttribute("res", res);
		
		req.getRequestDispatcher("WEB-INF/views/find/find.jsp").forward(req, resp);
		
		
		
	}

}
