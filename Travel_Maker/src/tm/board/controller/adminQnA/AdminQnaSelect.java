package tm.board.controller.adminQnA;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import tm.board.service.IQnaBoardService;
import tm.board.service.QnaBoardService;
import tm.comm.vo.BoardVO;
import tm.comm.vo.ReplyVO;

@WebServlet("/AdminQnaSelect.do")
public class AdminQnaSelect extends HttpServlet{
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		int qnaNo = Integer.parseInt(req.getParameter("tm_b_no"));
		
		IQnaBoardService service = QnaBoardService.getInstance();
		
		BoardVO vo = service.qnaSelect(qnaNo);
		
		List<ReplyVO> relist = service.qnaReSelect(qnaNo);
		
		req.setAttribute("vo", vo);
		req.setAttribute("relist", relist);
		
		req.getRequestDispatcher("WEB-INF/views/admin/qnaSelect.jsp").forward(req, resp);
	
	}

}
