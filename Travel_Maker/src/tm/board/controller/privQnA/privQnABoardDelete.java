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
 * Servlet implementation class ReplyDelete
 */
@WebServlet("/privQnABoardDelete.do")
public class privQnABoardDelete extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public privQnABoardDelete() {
        super();
        // TODO Auto-generated constructor stub
    }

    
    
//======================================================================================================================    
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		//0.
		int TM_B_NO = Integer.parseInt(request.getParameter("TM_B_NO"));
				
		//1.
		IprivQnABoardService service = privQnABoardService.getService();
		
		//2.
		int res = service.DeleteBoard(TM_B_NO);
		
		//3.
		request.setAttribute("renum", res);
		
		//4.
		request.getRequestDispatcher("/WEB-INF/views/privQnA/privQnAdelete.jsp").forward(request, response);
		
		
		
	}


}
