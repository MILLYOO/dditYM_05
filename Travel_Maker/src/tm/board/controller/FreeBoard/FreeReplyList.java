package tm.board.controller.FreeBoard;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import tm.board.service.FreeBoardService;
import tm.board.service.IFreeBoardService;
import tm.comm.vo.ReplyVO;



/**
 * Servlet implementation class ReplyList
 */
@WebServlet("/FreeReplyList.do")
public class FreeReplyList extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FreeReplyList() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//0
		int TM_B_NO = Integer.parseInt(request.getParameter("TM_B_NO"));
		
		//1
		IFreeBoardService service = FreeBoardService.getService();
		
		//2
		List<ReplyVO> list = service.replyList(TM_B_NO);
		
		//3
		request.setAttribute("list", list);
		
		//4
		request.getRequestDispatcher("/WEB-INF//views/Free/FreereplyList.jsp").forward(request, response);
		
	}

}
