package tm.board.controller.TravelBoard;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import tm.board.service.FreeBoardService;
import tm.board.service.IFreeBoardService;
import tm.board.service.ITravelBoardService;
import tm.board.service.TravelBoardService;
import tm.comm.vo.BoardVO;
import tm.comm.vo.ReplyVO;

/**
 * Servlet implementation class TravelSelect
 */
@WebServlet("/TravelSelect.do")
public class TravelSelect extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TravelSelect() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("글 세부 조회로 들어옴");
		
		request.setCharacterEncoding("UTF-8");
		
		int tm_b_no = Integer.parseInt(request.getParameter("TM_B_NO"));
		
		ITravelBoardService service = TravelBoardService.getService();
		
		service.updateHit(tm_b_no);
		BoardVO boardVO = service.getBoard(tm_b_no);
		List<ReplyVO> reList = service.replyList(tm_b_no);
		
		request.setAttribute("boardVO", boardVO);
		request.setAttribute("reList", reList);
		
		request.getRequestDispatcher("/WEB-INF/views/Travel/TravelSelect.jsp").forward(request, response);
		
	}
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
