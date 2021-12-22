package tm.ticket.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/BasketBuy.do")
public class BasketBuy extends HttpServlet{

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String buyName = (String)req.getParameter("buyName");
		int totalPrice1 = Integer.parseInt(req.getParameter("totalPrice1"));
		String memEmail = (String)req.getParameter("memEmail");
		String memName = (String)req.getParameter("memName");
		String memTel = (String)req.getParameter("memTel");
		String memAddr = (String)req.getParameter("memAddr");
		String memZip = (String)req.getParameter("memZip");
		
		req.setAttribute("buyName", buyName);
		req.setAttribute("totalPrice1", totalPrice1);
		req.setAttribute("memEmail", memEmail);
		req.setAttribute("memName", memName);
		req.setAttribute("memTel", memTel);
		req.setAttribute("memAddr", memAddr);
		req.setAttribute("memZip", memZip);
		
		req.getRequestDispatcher("WEB-INF/views/Ticket/ticketBuy.jsp").forward(req, resp);
		
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}
	
}
