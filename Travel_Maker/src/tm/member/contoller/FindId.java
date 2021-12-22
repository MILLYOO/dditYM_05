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



@WebServlet("/FindId.do")
public class FindId extends HttpServlet{
		
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		
		String name = req.getParameter("name");
		String tel = req.getParameter("tel");
		
		MemberVO mv = new MemberVO();
		mv.setTm_name(name);
		mv.setTm_tel(tel);
	
		IMemberService service = MemberService.getservice();
		
		String res = service.FindId(mv);
		
		req.setAttribute("res", res);
		
		req.getRequestDispatcher("WEB-INF/views/find/find.jsp").forward(req, resp);
	}

}
