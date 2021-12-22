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
import tm.comm.vo.BoardVO;
import tm.member.vo.MemberVO;

/**
 * Servlet implementation class ReplyDelete
 */
@WebServlet("/FreeBoardDelete.do")
public class FreeBoardDelete extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FreeBoardDelete() {
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
		
		
		System.out.println("서블릿 들어옴?");
		HttpSession loginSession = request.getSession(false);
		String loginId = "nonUser";
		
		if(loginSession!=null) {
			 loginId = ((MemberVO)loginSession.getAttribute("memVO")).getTm_id();
			}
		int tm_author = -1;
		tm_author = service.checkNotUser(loginId);
		//0.
		int TM_B_NO = Integer.parseInt(request.getParameter("tm_b_no"));
		
		String msg = "";
		String userReq = "삭제";
		int cnt = 0;
		
		BoardVO boardVO = new BoardVO();
		boardVO.setTm_b_no(TM_B_NO);
				
		//2.
		//int res = service.DeleteBoard(TM_B_NO);
		
		//로그인 아이디랑 글 아이디 가져와서 비교
		String writer = service.checkBoardWriter(TM_B_NO);
		if(writer.equals(loginId) || tm_author == 0) {
			cnt = service.DeleteBoard(boardVO);
			msg = "성공";
		}else {
			msg = "실패";
		}
		request.setAttribute("cnt", cnt);
		request.setAttribute("msg", msg);
		request.setAttribute("userReq", userReq);
		
//		//3.
//		request.setAttribute("renum", res);
		
		//4.
		request.getRequestDispatcher("/WEB-INF/views/common/resultUpdateDelete.jsp").forward(request, response);
		
		
		
	}


}
