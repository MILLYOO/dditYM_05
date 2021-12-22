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

@WebServlet("/BasketUpdate.do")
public class BasketUpdate extends HttpServlet{

	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		HttpSession loginSession = req.getSession(false);
		String loginId = ((MemberVO)loginSession.getAttribute("memVO")).getTm_id();
		
		int qty = Integer.parseInt(req.getParameter("newval"));
		
		String prodId = req.getParameter("prodId");
		
		ITicketService service = TicketService.getInstance();
		
		CartVO vo = new CartVO();
		vo.setTm_id(loginId);
		vo.setTm_cart_qty(qty);
		vo.setTm_prod_id(prodId);
		
		int res = service.cartUpdate(vo);
		String result = "";
		if(res > 0) {
			result = "성공";
		}else {
			result = "실패";
		}
		
		req.setAttribute("result", result);
		req.getRequestDispatcher("WEB-INF/views/Ticket/ticketResult.jsp").forward(req, resp);
		
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}
}
