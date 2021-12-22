package tm.board.controller.FreeBoard;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import tm.board.service.FreeBoardService;
import tm.board.service.IFreeBoardService;
import tm.board.service.IUserQnaBoardService;
import tm.board.service.UserQnaServiceImple;
import tm.comm.vo.BoardVO;
import tm.comm.vo.ReplyVO;

/**
 * Servlet implementation class FreeSelect
 */
@WebServlet("/FreeSelect.do")
public class FreeSelect extends HttpServlet {

    
    @Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		int tm_b_no = Integer.parseInt(req.getParameter("TM_B_NO"));
		
		IFreeBoardService service = FreeBoardService.getService();
		
		BoardVO boardVO = service.getBoard(tm_b_no);
		List<ReplyVO> reList = service.replyList(tm_b_no);
		
		req.setAttribute("boardVO", boardVO);
		req.setAttribute("reList", reList);
		
		req.getRequestDispatcher("/WEB-INF/views/Free/FreeSelect.jsp").forward(req, resp);
		
	}
	
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}

}
