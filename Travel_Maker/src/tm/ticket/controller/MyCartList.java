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
import tm.my.service.IMyInfoService;
import tm.my.service.MyInfoServiceImpl;
import tm.ticket.service.ITicketService;
import tm.ticket.service.TicketService;

@WebServlet("/MyCartList.do")
public class MyCartList extends HttpServlet{

	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		doPost(req, resp);
	}
	
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		HttpSession loginSession = req.getSession(false);
		String loginId = ((MemberVO)loginSession.getAttribute("memVO")).getTm_id();
		
		ITicketService service = TicketService.getInstance();
		
		List<CartVO> list = service.myCartList(loginId);
		
		CartVO vo = service.cartTotal(loginId);
		
		IMyInfoService memService = MyInfoServiceImpl.getInstance();
		
		MemberVO memVO = memService.getMember(loginId);
		
//		HttpSession cartSession = req.getSession();
		
		req.setAttribute("vo", vo);
		req.setAttribute("memVO", memVO);
		req.setAttribute("list", list);
		req.getRequestDispatcher("views/MyCart.jsp").forward(req, resp);
		
	}
}
