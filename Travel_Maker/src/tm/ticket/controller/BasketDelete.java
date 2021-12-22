package tm.ticket.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import tm.comm.vo.CartVO;
import tm.member.vo.MemberVO;
import tm.ticket.service.ITicketService;
import tm.ticket.service.TicketService;

@WebServlet("/BasketDelete.do")
public class BasketDelete extends HttpServlet{
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		HttpSession loginSession = req.getSession(false);
		String loginId = ((MemberVO)loginSession.getAttribute("memVO")).getTm_id();
		
		int delNo = Integer.parseInt(req.getParameter("cartNo"));
		String prodId = (String)req.getParameter("prod");
		
		ITicketService service = TicketService.getInstance();
		
		CartVO vo = new CartVO();
		vo.setTm_prod_id(prodId);
		vo.setTm_id(loginId);
		
		int res = service.cartDelete(vo);
		
		String result = "";
		if(res > 0) {
			result = "성공";
		}else {
			result = "성공";
		}
		
		req.setAttribute("result", result);
		req.getRequestDispatcher("WEB-INF/views/Ticket/ticketResult.jsp").forward(req, resp);
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}

}
