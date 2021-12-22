package tm.ticket.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import tm.comm.vo.ProdVO;
import tm.ticket.service.ITicketService;
import tm.ticket.service.TicketService;

@WebServlet("/TicketSearch.do")
public class TicketSearch extends HttpServlet{

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String inputSearch = req.getParameter("inValue");
		
		List<ProdVO> list = null;
		
		if(inputSearch != null) {
			ITicketService service = TicketService.getInstance();
			
			ProdVO vo = new ProdVO();
			vo.setTm_prod_comp(inputSearch);
			
			list = service.ticketSearch(vo);
			
		}
		
		req.setAttribute("list", list);
		
		req.getRequestDispatcher("WEB-INF/views/Ticket/ticketSearch.jsp").forward(req, resp);
		
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}
	
}
