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

@WebServlet("/NoticeInsert.do")
public class NoticeBoardInsert extends HttpServlet{
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String id = req.getParameter("noId");
		String title = req.getParameter("noTitle");
		String content = req.getParameter("noContent");
		
		BoardVO vo = new BoardVO();
		vo.setTm_id(id);
		vo.setTm_b_title(title);
		vo.setTm_b_content(content);		
		
		INoticeBoardService service = NoticeBoardService.getInstance();
		
		int res = service.noticeInsert(vo);
		
		req.setAttribute("res", res);
		
		req.getRequestDispatcher("WEB-INF/views/notice/noticeRes.jsp").forward(req, resp);
		
	}

}
