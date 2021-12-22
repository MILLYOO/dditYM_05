package tm.board.controller.FreeBoard;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import tm.board.service.FreeBoardService;
import tm.board.service.IFreeBoardService;
import tm.comm.vo.ReplyVO;
import tm.member.vo.MemberVO;

/**
 * Servlet implementation class FreeReplyDeleteAll
 */
@WebServlet("/FreeReplyDeleteAll.do")
public class FreeReplyDeleteAll extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession loginSession = request.getSession(false);
		String loginId = "nonUser";
		
		if(loginSession!=null) {
		 loginId = ((MemberVO)loginSession.getAttribute("memVO")).getTm_id();
		}
		
		//0.
		//int renum = Integer.parseInt(request.getParameter("TM_BC_NO"));
		
		int tm_b_no = Integer.parseInt(request.getParameter("TM_B_NO"));
		String msg = "";
		String userReq = "삭제";
		int cnt = 0;
		
		ReplyVO reVO = new ReplyVO();
		reVO.setTm_bc_no(tm_b_no);
		
		//1.
		IFreeBoardService service = FreeBoardService.getService();
		
		//로그인 아이디랑 글 아이디 가져와서 비교
		String writer = service.checkBoardWriter(tm_b_no);
		if(writer.equals(loginId)) {
			cnt = service.replyDeleteAll(tm_b_no);
			msg = "성공";
		}else {
			msg = "실패";
		}
		request.setAttribute("cnt", cnt);
		request.setAttribute("msg", msg);
		request.setAttribute("userReq", userReq);
		
		request.getRequestDispatcher("/WEB-INF/views/common/resultUpdateDelete.jsp").forward(request, response);
	
		
		/*
		//2.
		int res = service.replyDelete(renum);
		
		//3.
		request.setAttribute("renum", res);
		
		//4.
		request.getRequestDispatcher("/WEB-INF//views/Free/Freedelete.jsp").forward(request, response);
		*/
		
		
		
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
