package tm.board.controller.privQnA;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import tm.board.service.privQnABoardService;
import tm.board.service.IprivQnABoardService;

/**
 * Servlet implementation class privQnAReplyDeleteAll
 */
@WebServlet("/privQnAReplyDeleteAll.do")
public class privQnAReplyDeleteAll extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		//0.
		int renum = Integer.parseInt(request.getParameter("TM_BC_NO"));
				
		//1.
		IprivQnABoardService service = privQnABoardService.getService();
		
		//2.
		int res = service.replyDelete(renum);
		
		//3.
		request.setAttribute("renum", res);
		
		//4.
		request.getRequestDispatcher("/WEB-INF//views/privQnA/privQnAdelete.jsp").forward(request, response);
		
		
		
		
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
