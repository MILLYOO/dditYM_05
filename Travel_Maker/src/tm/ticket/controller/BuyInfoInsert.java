package tm.ticket.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import tm.comm.vo.PodVO;
import tm.member.vo.MemberVO;
import tm.ticket.service.ITicketService;
import tm.ticket.service.TicketService;
@WebServlet("/BuyInfoInsert.do")
public class BuyInfoInsert extends HttpServlet{

	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
//		HttpSession loginSession = req.getSession(false);
//		String loginId = ((MemberVO)loginSession.getAttribute("memVO")).getTm_id();
//		
//		String podType = (String)req.getParameter("podType");
//		String podCardName = (String)req.getParameter("podCardName");
//		String podCardNum = (String)req.getParameter("podCardNum");
//		
//		String podData = podCardName + " " + podCardNum;
//		
//		ITicketService service = TicketService.getInstance();
//		
//		PodVO vo = new PodVO();
//		vo.setTm_id(loginId);
//		vo.setTm_pod_type(podType);
//		vo.setTm_pod_data(podData);
//		
//		int res = service.buyInfoInsert(loginId);
//		int res2 = service.payInfoInsert(vo);
//		
//		String result = "";
//		if(res > 0 && res2 > 0) {
//			result = "성공";
//		}else {
//			result = "실패";
//		}
//		
//		req.setAttribute("result", result);
//		req.getRequestDispatcher("WEB-INF/views/Ticket/ticketResult.jsp").forward(req, resp);
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		doPost(req, resp);
	}
}
