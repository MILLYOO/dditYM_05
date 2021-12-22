package tm.board.controller.TravelBoard;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import tm.board.service.FreeBoardService;
import tm.board.service.IFreeBoardService;
import tm.board.service.ITravelBoardService;
import tm.board.service.TravelBoardService;
import tm.comm.vo.ReplyVO;
import tm.member.vo.MemberVO;

/**
 * Servlet implementation class TravelReplyDeleteAll
 */
@WebServlet("/TravelReplyDeleteAll.do")
public class TravelReplyDeleteAll extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession loginSession = request.getSession(false);
		String loginId = "nonUser";
		
		if(loginSession!=null) {
		 loginId = ((MemberVO)loginSession.getAttribute("memVO")).getTm_id();
		}
		
		//0.
		int tm_b_no = Integer.parseInt(request.getParameter("TM_B_NO"));
		
		String msg = "";
		String userReq = "삭제";
		int cnt = 0;
		
		ReplyVO reVO = new ReplyVO();
		reVO.setTm_bc_no(tm_b_no);
				
		//1.
		ITravelBoardService service = TravelBoardService.getService();
		
		//2.
		/*int res = service.replyDelete(renum);
		
		//3.
		request.setAttribute("renum", res);
		
		//4.
		request.getRequestDispatcher("/WEB-INF/views/Travel/Traveldelete.jsp").forward(request, response);*/
		
		//로그인 아이디랑 글 아이디 가져와서 비교
		String writer = service.checkBoardWriter(tm_b_no);
		if(writer.equals(loginId)) {
			cnt = service.deleteReplyAll(tm_b_no);
			msg = "성공";
		}else {
			msg = "실패";
		}
		request.setAttribute("cnt", cnt);
		request.setAttribute("msg", msg);
		request.setAttribute("userReq", userReq);
		
		
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
