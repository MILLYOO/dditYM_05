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

@WebServlet("/SpamDelete.do")
public class SpamDelete extends HttpServlet{
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		int no = Integer.parseInt(req.getParameter("modiNo"));
		int cate = Integer.parseInt(req.getParameter("category"));
		
		System.out.println(":::::::::::::::::::::::::::::::::::::::");
		System.out.println("글번호" + no);
		System.out.println("카테고리번호" + cate);
		
		ISpamService service = SpamServiceImpl.getInstance();
		
		BoardVO vo = new BoardVO();
		
		vo.setTm_b_no(no);
		vo.setTm_category_id(cate);
		
		service.spamDeleteWithLike(vo);
		int res = service.spamDelete(vo);
		
		req.setAttribute("res", res);
		req.getRequestDispatcher("WEB-INF/views/notice/noticeRes.jsp").forward(req, resp);
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}

}
