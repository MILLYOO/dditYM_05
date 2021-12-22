package tm.board.controller.spamManage;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import tm.board.service.ISpamService;
import tm.board.service.SpamServiceImpl;
import tm.comm.vo.BoardVO;

@WebServlet("/SpamUpdate.do")
public class SpamUpdate extends HttpServlet{
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		
		
		String title = (String)req.getParameter("modiTitle");
		String cont = (String)req.getParameter("modiCont");
		int no = Integer.parseInt(req.getParameter("tmbNo"));
		int cate = Integer.parseInt(req.getParameter("category"));
		
		ISpamService service = SpamServiceImpl.getInstance();
		
		BoardVO vo = new BoardVO();
		vo.setTm_b_content(cont);
		vo.setTm_b_title(title);
		vo.setTm_b_no(no);
		vo.setTm_category_id(cate);
		
		int res = service.spamUpdate(vo);
		
		req.setAttribute("res", res);
		req.getRequestDispatcher("WEB-INF/views/notice/noticeRes.jsp").forward(req, resp);
		
	}

}
