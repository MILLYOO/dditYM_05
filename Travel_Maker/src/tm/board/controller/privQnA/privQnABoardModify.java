package tm.board.controller.privQnA;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import tm.board.service.privQnABoardService;
import tm.board.service.IprivQnABoardService;
import tm.comm.vo.BoardVO;
import tm.comm.vo.ReplyVO;



/**
 * Servlet implementation class ReplyModify
 */
@WebServlet("/privQnABoardModify.do")
public class privQnABoardModify extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public privQnABoardModify() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		
		//0
		int renum = Integer.parseInt(request.getParameter("TM_B_NO"));
		String retitle = request.getParameter("TM_B_TITLE");
		String recontent = request.getParameter("TM_B_CONTENT");
		
		BoardVO vo = new BoardVO();
		vo.setTm_b_no(renum);
		vo.setTm_b_title(retitle);
		vo.setTm_b_content(recontent);
		
		
		//1
		IprivQnABoardService service = privQnABoardService.getService();
		
		//2
		int res = service.ModifyBoard(vo);
		
		//3
		request.setAttribute("res", res);
		
		//4
		request.getRequestDispatcher("/WEB-INF//views/privQnA/privQnAmodify.jsp").forward(request, response);
		
		
		
		
		
		
		
		
		
		
		
		
		
		
	}

}
