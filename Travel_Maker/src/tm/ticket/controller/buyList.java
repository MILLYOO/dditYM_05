package tm.ticket.controller;

import java.io.IOException;
import java.util.List;

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

@WebServlet("/buyList.do")
public class buyList extends HttpServlet{

	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		HttpSession loginSession = req.getSession(false);
		String loginId = ((MemberVO)loginSession.getAttribute("memVO")).getTm_id();
		
		ITicketService service = TicketService.getInstance();
		
		CartVO cartVO = new CartVO();
		cartVO.setTm_id(loginId);
		cartVO.setTm_cart_no(1);
		service.cartOxUpdate(cartVO);
		
		List<CartVO> list = service.myBuyList(loginId);
		
		CartVO vo = service.cartTotal(loginId);
		
		req.setAttribute("vo", vo);
		req.setAttribute("list", list);
		req.getRequestDispatcher("WEB-INF/views/Ticket/buyList.jsp").forward(req, resp);
		
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		doPost(req, resp);
	}
}
