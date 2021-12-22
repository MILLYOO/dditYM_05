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

@WebServlet("/AdminQnaReInsert.do")
public class AdminQnaReInsert extends HttpServlet{

	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String memId = req.getParameter("memId");
		int num = Integer.parseInt(req.getParameter("num"));
		String reWriter = req.getParameter("reWriter");
		String replycont = req.getParameter("replycont");
		
		IQnaBoardService service = QnaBoardService.getInstance();
		
		ReplyVO vo = new ReplyVO();
		vo.setTm_id(memId);
		vo.setTm_b_no(num);
		vo.setTm_bc_writer(reWriter);
		vo.setTm_bc_content(replycont);
		
		int res = service.qnaReInsert(vo);
		
		// 답변 여부 변경
		service.qnaReOxUpdate(num);
		
		req.setAttribute("res", res);
		req.setAttribute("rebNo", num);
		
		req.getRequestDispatcher("WEB-INF/views/admin/memManageRes.jsp").forward(req, resp);
	}
}
