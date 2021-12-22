package tm.comm.Controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import tm.board.service.FreeBoardService;
import tm.board.service.IFreeBoardService;


/**
 * Servlet implementation class HitUpdate
 */
@WebServlet("/HitUpdate.do")
public class HitUpdate extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public HitUpdate() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		int seq = Integer.parseInt(request.getParameter("seq"));
		
		IFreeBoardService service = FreeBoardService.getService();
		
		int aa  = service.hitUpdate(seq);
		
		request.setAttribute("res", aa);
		
		request.getRequestDispatcher("/views/modify.jsp").forward(request, response);
		
	}



}
