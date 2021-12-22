package tm.board.controller.UserQnA;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import tm.board.service.IUserQnaBoardService;
import tm.board.service.UserQnaServiceImple;
import tm.comm.vo.ReplyVO;
import tm.member.vo.MemberVO;


@WebServlet("/qboard/reinsert.do")
public class UserQReInsert extends HttpServlet{
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		HttpSession loginSession = req.getSession(false);
		
		String tm_id = ((MemberVO)loginSession.getAttribute("memVO")).getTm_id();
		
		int tm_b_no = Integer.parseInt(req.getParameter("tm_b_no"));
		String tm_bc_content = req.getParameter("tm_bc_content");
		
		System.out.println("글번호" + tm_b_no);
		System.out.println("내용" + tm_bc_content);
		
		IUserQnaBoardService boardService = UserQnaServiceImple.getInstance();
		
		ReplyVO reVO = new ReplyVO();
		reVO.setTm_id(tm_id);
		reVO.setTm_b_no(tm_b_no);
		reVO.setTm_bc_content(tm_bc_content);
		
		int cnt = boardService.insertReply(reVO);
		
		req.setAttribute("cnt", cnt);
		
		req.getRequestDispatcher("/WEB-INF/views/common/resultBoard.jsp").forward(req, resp);
		
	}

}
