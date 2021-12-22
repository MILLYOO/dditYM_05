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
 * Servlet implementation class HitUpdate
 */
@WebServlet("/privQnAHitUpdate.do")
public class privQnAHitUpdate extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public privQnAHitUpdate() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		int seq = Integer.parseInt(request.getParameter("seq"));
		
		IprivQnABoardService service = privQnABoardService.getService();
		
		int aa  = service.hitUpdate(seq);
		
		request.setAttribute("res", aa);
		
		request.getRequestDispatcher("/views/modify.jsp").forward(request, response);
		
	}



}
