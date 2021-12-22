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
 * Servlet implementation class TravelReplyDelete
 */
@WebServlet("/TravelReplyDelete.do")
public class TravelReplyDelete extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TravelReplyDelete() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//1.
		ITravelBoardService service = TravelBoardService.getService();

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
				
		
		String writer = service.checkReWriter(tm_bc_no);
		System.out.println("댓번호" + tm_bc_no);
		System.out.println("로그인 누구? " + loginId);
		System.out.println("작성자 누구야" + writer);
		if(writer.equals(loginId) || tm_author == 0) {
			cnt = service.replyDelete(tm_bc_no);
			msg = "성공";
		}else {
			msg = "실패";
		}
		request.setAttribute("cnt", cnt);
		request.setAttribute("msg", msg);
		request.setAttribute("userReq", userReq);
		
		//4.
		request.getRequestDispatcher("/WEB-INF/views/common/resultUpdateDelete.jsp").forward(request, response);
		
	
	}

}
