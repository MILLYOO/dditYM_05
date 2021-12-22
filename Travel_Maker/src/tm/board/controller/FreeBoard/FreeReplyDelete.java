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
 * Servlet implementation class ReplyDelete
 */
@WebServlet("/FreeReplyDelete.do")
public class FreeReplyDelete extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FreeReplyDelete() {
        super();
        // TODO Auto-generated constructor stub
    }

    
    
//======================================================================================================================    
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//1.
		IFreeBoardService service = FreeBoardService.getService();
		
		HttpSession loginSession = request.getSession(false);
		String loginId = "nonUser";
		
		if(loginSession!=null) {
		 loginId = ((MemberVO)loginSession.getAttribute("memVO")).getTm_id();
		}
		
		int tm_author = -1;
		tm_author = service.checkNotUser(loginId);
		
		//0.
		int tm_bc_no = Integer.parseInt(request.getParameter("TM_BC_NO"));
		String msg = "";
		String userReq = "삭제";
		int cnt = 0;
		
		ReplyVO reVO = new ReplyVO();
		reVO.setTm_bc_no(tm_bc_no);
		
		
		//로그인 아이디랑 글 아이디 가져와서 비교
		String writer = service.checkReWriter(tm_bc_no);
		if(writer.equals(loginId) || tm_author == 0) {
			cnt = service.replyDelete(tm_bc_no);
			msg = "성공";
		}else {
			msg = "실패";
		}
		
		System.out.println(cnt);
		
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


}
