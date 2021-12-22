package tm.board.controller.spamManage;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import tm.board.service.ISpamService;
import tm.board.service.SpamServiceImpl;
import tm.comm.vo.BoardVO;

@WebServlet("/spamList.do")
public class spamList extends HttpServlet{
	
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		doPost(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		ISpamService service = SpamServiceImpl.getInstance();
		
		List<BoardVO> list = service.spamAllList();
		
		req.setAttribute("list", list);
		
		req.getRequestDispatcher("WEB-INF/views/admin/spamManage.jsp").forward(req, resp);
	}

}
