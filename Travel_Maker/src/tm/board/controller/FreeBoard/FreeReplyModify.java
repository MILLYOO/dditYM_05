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
 * Servlet implementation class ReplyModify
 */
@WebServlet("/FreeReplyModify.do")
public class FreeReplyModify extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FreeReplyModify() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");

		//1
		IFreeBoardService service = FreeBoardService.getService();
		
		HttpSession loginSession = request.getSession(false);
		
		String loginId = ((MemberVO)loginSession.getAttribute("memVO")).getTm_id();
		int tm_author = -1;
		tm_author = service.checkNotUser(loginId);
		//0
		int renum = Integer.parseInt(request.getParameter("TM_BC_NO"));
		String recont = request.getParameter("TM_BC_CONTENT");
		
		String msg = "";
		String userReq = "수정";
		int cnt = 0;
		
		ReplyVO vo = new ReplyVO();
		vo.setTm_bc_no(renum);
		vo.setTm_bc_content(recont);
		
		
		//로그인 아이디랑 글 아이디 가져와서 비교
		String writer = service.checkReWriter(renum);
		if(writer.equals(loginId) || tm_author == 0) {
			cnt = service.replyModify(vo);
			msg = "성공";
		}else {
			msg = "실패";
		}
		request.setAttribute("cnt", cnt);
		request.setAttribute("msg", msg);
		request.setAttribute("userReq", userReq);
		
		request.getRequestDispatcher("/WEB-INF/views/common/resultUpdateDelete.jsp").forward(request, response);
		
		
		/*
		//2
		int res = service.replyModify(vo);
		
		//3
		request.setAttribute("res", res);
		
		//4
		request.getRequestDispatcher("/WEB-INF//views/Free/Freemodify.jsp").forward(request, response);
		*/
		
		
		
		
		
		
		
		
		
		
		
		
		
	}

}
