package tm.board.controller.privQnA;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import tm.board.service.privQnABoardService;
import tm.board.service.IprivQnABoardService;
import tm.comm.vo.ReplyVO;



/**
 * Servlet implementation class ReplyModify
 */
@WebServlet("/privQnAReplyModify.do")
public class privQnAReplyModify extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public privQnAReplyModify() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		
		//0
		int renum = Integer.parseInt(request.getParameter("TM_BC_NO"));
		String recont = request.getParameter("TM_BC_CONTENT");
		
		ReplyVO vo = new ReplyVO();
		vo.setTm_bc_no(renum);
		vo.setTm_bc_content(recont);
		
		
		//1
		IprivQnABoardService service = privQnABoardService.getService();
		
		//2
		int res = service.replyModify(vo);
		
		//3
		request.setAttribute("res", res);
		
		//4
		request.getRequestDispatcher("/WEB-INF//views/privQnA/privQnAmodify.jsp").forward(request, response);
		
		
		
		
		
		
		
		
		
		
		
		
		
		
	}

}
