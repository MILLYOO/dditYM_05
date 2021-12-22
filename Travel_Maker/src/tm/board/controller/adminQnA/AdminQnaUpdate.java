package tm.board.controller.adminQnA;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import tm.board.service.IQnaBoardService;
import tm.board.service.QnaBoardService;
import tm.comm.vo.BoardVO;

@WebServlet("/AdminQnaUpdate.do")
public class AdminQnaUpdate extends HttpServlet{

	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String modiTitle = req.getParameter("modiTitle");
		String modiCont = req.getParameter("modiCont");
		int modiNo = Integer.parseInt(req.getParameter("modiNo"));
		
		IQnaBoardService service = QnaBoardService.getInstance();
		
		BoardVO vo = new BoardVO();
		
		vo.setTm_b_title(modiTitle);
		vo.setTm_b_content(modiCont);
		vo.setTm_b_no(modiNo);
		
		int res = service.qnaUpdate(vo);
		
		req.setAttribute("res", res);
		req.setAttribute("rebNo", modiNo);
		
		req.getRequestDispatcher("WEB-INF/views/admin/memManageRes.jsp").forward(req, resp);
		
	}
}
