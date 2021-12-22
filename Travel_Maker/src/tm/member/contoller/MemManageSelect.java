package tm.member.contoller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import tm.member.service.IMemManageService;
import tm.member.service.MemManageService;
import tm.member.vo.MemberVO;

@WebServlet("/memManageSelect.do")
public class MemManageSelect extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String id = req.getParameter("MEM_ID");
		
		IMemManageService service = MemManageService.getservice();
		
		MemberVO vo = service.memSelect(id);
		
		req.setAttribute("vo", vo);
		
		req.getRequestDispatcher("WEB-INF/views/admin/memManageSelect.jsp").forward(req, resp);
	}
}
