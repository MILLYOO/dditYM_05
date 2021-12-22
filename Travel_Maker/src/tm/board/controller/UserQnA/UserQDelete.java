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
import tm.member.vo.MemberVO;


@WebServlet("/qboard/delete.do")
public class UserQDelete extends HttpServlet{
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		IUserQnaBoardService boardService = UserQnaServiceImple.getInstance();
		
		HttpSession loginSession = req.getSession(false);
		String loginId = "nonUser";
		
		if(loginSession!=null) {
			 loginId = ((MemberVO)loginSession.getAttribute("memVO")).getTm_id();
			}
		int tm_author = -1;
		tm_author = boardService.checkNotUser(loginId);
		
		int tm_b_no = Integer.parseInt(req.getParameter("tm_b_no"));

		String msg = "";
		String userReq = "삭제";
		int cnt = 0;
		
		BoardVO boardVO = new BoardVO();
		boardVO.setTm_b_no(tm_b_no);
		
		
		//로그인 아이디랑 글 아이디 가져와서 비교
		String writer = boardService.checkBoardWriter(tm_b_no);
	
		if(writer.equals(loginId) || tm_author == 0) {
			cnt = boardService.deleteBoard(boardVO);
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
