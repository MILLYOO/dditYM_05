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
import tm.comm.vo.BoardVO;
import tm.comm.vo.ReplyVO;

@WebServlet("/qboard/select.do")
public class UserQSelect extends HttpServlet {
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		int tm_b_no = Integer.parseInt(req.getParameter("tm_b_no"));
		
		IUserQnaBoardService boardService = UserQnaServiceImple.getInstance();
		
		boardService.updateHit(tm_b_no);
		BoardVO boardVO = boardService.selctBoard(tm_b_no);
		List<ReplyVO> reList = boardService.replyList(tm_b_no);
		
		System.out.println("서블릿 글번호 : " + tm_b_no);
		
		req.setAttribute("boardVO", boardVO);
		req.setAttribute("reList", reList);
		
		req.getRequestDispatcher("/WEB-INF/views/userqna/select.jsp").forward(req, resp);
		
	}
	
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}

}
