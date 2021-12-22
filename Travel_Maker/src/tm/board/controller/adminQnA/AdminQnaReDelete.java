package tm.board.controller.adminQnA;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import tm.board.service.IQnaBoardService;
import tm.board.service.QnaBoardService;
import tm.comm.vo.ReplyVO;

@WebServlet("/AdminQnaReDelete.do")
public class AdminQnaReDelete extends HttpServlet{

	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		int delReNo = Integer.parseInt(req.getParameter("delReNo"));
		int delBno = Integer.parseInt(req.getParameter("delBno"));
		
		IQnaBoardService service = QnaBoardService.getInstance();
		
		ReplyVO vo = new ReplyVO();
		vo.setTm_bc_no(delReNo);
		
		int res = service.qnaReDelete(vo);
		int cnt = service.qnaReCount(delBno);
		
		if(cnt <= 0) {
			service.qnaReOxDel(delBno);
		}
		
		req.setAttribute("res", res);
		req.setAttribute("rebNo", delBno);
		
		req.getRequestDispatcher("WEB-INF/views/admin/memManageRes.jsp").forward(req, resp);
	}
}
