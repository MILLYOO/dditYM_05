package tm.board.controller.noticeBoard;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import tm.board.service.INoticeBoardService;
import tm.board.service.NoticeBoardService;
import tm.comm.vo.BoardVO;

@WebServlet("/NoticeDelete.do")
public class NoticeBoardDelete extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		int no = Integer.parseInt(req.getParameter("modiNo"));
		
		INoticeBoardService service = NoticeBoardService.getInstance();
		
		BoardVO vo = new BoardVO();
		vo.setTm_b_no(no);
		
		int res = service.noticeDelete(vo);
		
		req.setAttribute("res", res);
		
		req.getRequestDispatcher("WEB-INF/views/notice/noticeRes.jsp").forward(req, resp);
	}
	
}
