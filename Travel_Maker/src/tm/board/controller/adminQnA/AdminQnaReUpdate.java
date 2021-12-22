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
import tm.comm.vo.ReplyVO;

@WebServlet("/AdminQnaReUpdate.do")
public class AdminQnaReUpdate extends HttpServlet{

	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String modiReCont = req.getParameter("modiReCont");
		int rebNo = Integer.parseInt(req.getParameter("rebNo"));
		int rebcNo = Integer.parseInt(req.getParameter("rebcNo"));
		
		IQnaBoardService service = QnaBoardService.getInstance();
		
		ReplyVO vo = new ReplyVO();
		vo.setTm_b_no(rebNo);
		vo.setTm_bc_no(rebcNo);
		vo.setTm_bc_content(modiReCont);
		
		int res = service.replyUpdate(vo);
		
		req.setAttribute("res", res);
		req.setAttribute("rebNo", rebNo);
		
		req.getRequestDispatcher("WEB-INF/views/admin/memManageRes.jsp").forward(req, resp);
		
		
	}
}
