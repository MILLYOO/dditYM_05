package tm.board.controller.UserQnA;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import tm.board.service.IUserQnaBoardService;
import tm.board.service.UserQnaServiceImple;
import tm.comm.vo.ReplyVO;


@WebServlet("/qboard/relist.do")
public class UserQReList extends HttpServlet{
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		int tm_b_no = Integer.parseInt(req.getParameter("tm_b_no"));
		
		IUserQnaBoardService boardService = UserQnaServiceImple.getInstance();
		
		List<ReplyVO> list = boardService.replyList(tm_b_no);
		
		req.setAttribute("list", list);
		req.getRequestDispatcher("/WEB-INF/views/userqna/reList.jsp").forward(req, resp);
	}

}
