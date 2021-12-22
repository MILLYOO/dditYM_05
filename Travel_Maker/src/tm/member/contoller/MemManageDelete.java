package tm.member.contoller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import tm.member.service.IMemManageService;
import tm.member.service.MemManageService;

@WebServlet("/MemManageDelete.do")
public class MemManageDelete extends HttpServlet{

	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String id = req.getParameter("modiId");
		
		IMemManageService service = MemManageService.getservice();
		
		int res = service.memDelete(id);
		
		req.setAttribute("res", res);
		
		req.getRequestDispatcher("WEB-INF/views/admin/memManageRes.jsp").forward(req, resp);
	}
}
