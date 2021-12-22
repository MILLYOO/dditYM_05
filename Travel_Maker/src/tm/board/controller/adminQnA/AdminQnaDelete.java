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

@WebServlet("/AdminQnaDelete.do")
public class AdminQnaDelete extends HttpServlet{
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		int no = Integer.parseInt(req.getParameter("modiNo"));
		
		IQnaBoardService service = QnaBoardService.getInstance();
		
		BoardVO vo = new BoardVO();
		
		vo.setTm_b_no(no);
		
		// 댓삭 먼저
		service.qnaReDelWith(no);
		// 글삭
		int res = service.qnaDelete(vo);
		
		req.setAttribute("res", res);
		
		req.getRequestDispatcher("WEB-INF/views/admin/memManageRes.jsp").forward(req, resp);
		
	}

}
