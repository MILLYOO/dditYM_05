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

@WebServlet("/SpamList.do")
public class spamSelect extends HttpServlet{

	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		int tmNo = Integer.parseInt(req.getParameter("tm_b_no"));
		int tmCate = Integer.parseInt(req.getParameter("tm_category_id"));
		
		
		ISpamService service = SpamServiceImpl.getInstance();
		
		BoardVO vo = new BoardVO();
		vo.setTm_b_no(tmNo);
		vo.setTm_category_id(tmCate);
		
		BoardVO select = service.spamSelect(vo);
		
		req.setAttribute("select", select);
		req.setAttribute("tmCate", tmCate);
		req.getRequestDispatcher("WEB-INF/views/admin/spamSelect.jsp").forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		doGet(req, resp);
		
	}
}
