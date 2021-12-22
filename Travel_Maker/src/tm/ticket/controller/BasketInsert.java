package tm.ticket.controller;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import tm.comm.vo.CartVO;
import tm.comm.vo.ProdVO;
import tm.member.vo.MemberVO;
import tm.ticket.service.ITicketService;
import tm.ticket.service.TicketService;

@WebServlet("/BasketInsert.do")
public class BasketInsert extends HttpServlet{

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		HttpSession loginSession = req.getSession(false);
		String loginId = ((MemberVO)loginSession.getAttribute("memVO")).getTm_id();
		
		// 로그인 한 아이디 가져와야 함!!
		String id = loginId;
		
		String[] arr = req.getParameterValues("arr");	// prod_id	배열
		String[] arrCount = req.getParameterValues("arrCount");		// 상품 개수 배열
		
		
		int[] arrCount2 = new int[arrCount.length];	// 상품 개수 배열

		for(int i = 0 ; i < arrCount.length ; i++) {
			int kk = Integer.parseInt(arrCount[i]);
			arrCount2[i] = kk;
		}
		
		ITicketService service = TicketService.getInstance();
		
		
		
		int res = 0;
		
		String result = "";
		
		for(int i = 0; i < arr.length; i++) {
			CartVO vo = new CartVO();
			vo.setTm_id(id);
			vo.setTm_prod_id(arr[i]);
			vo.setTm_cart_qty(arrCount2[i]);
			res = service.bascketInsert(vo);
			if(res <= 0) {
				result = "실패";
			}else {
				result = "성공";
			}
		}
		
		req.setAttribute("result", result);
		req.getRequestDispatcher("WEB-INF/views/Ticket/ticketResult.jsp").forward(req, resp);
		
	}
	
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}
}
