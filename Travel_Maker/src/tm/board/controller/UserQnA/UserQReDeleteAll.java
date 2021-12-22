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
import tm.comm.vo.BoardVO;
import tm.comm.vo.ReplyVO;
import tm.member.vo.MemberVO;


@WebServlet("/qboard/redeleteall.do")
public class UserQReDeleteAll extends HttpServlet{
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		HttpSession loginSession = req.getSession(false);
		String loginId = "nonUser";
		
		if(loginSession!=null) {
		 loginId = ((MemberVO)loginSession.getAttribute("memVO")).getTm_id();
		}
		
		int tm_b_no = Integer.parseInt(req.getParameter("TM_B_NO"));

		String msg = "";
		String userReq = "삭제";
		int cnt = 0;
		
		ReplyVO reVO = new ReplyVO();
		reVO.setTm_bc_no(tm_b_no);
		
		IUserQnaBoardService boardService = UserQnaServiceImple.getInstance();
		
		//로그인 아이디랑 글 아이디 가져와서 비교
		String writer = boardService.checkBoardWriter(tm_b_no);
		if(writer.equals(loginId)) {
			cnt = boardService.deleteReplyAll(tm_b_no);
			msg = "성공";
		}else {
			msg = "실패";
		}
		req.setAttribute("cnt", cnt);
		req.setAttribute("msg", msg);
		req.setAttribute("userReq", userReq);
		
		req.getRequestDispatcher("/WEB-INF/views/common/resultUpdateDelete.jsp").forward(req, resp);
	
	}

}
