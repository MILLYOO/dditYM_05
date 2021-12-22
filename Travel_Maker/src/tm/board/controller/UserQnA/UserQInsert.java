package tm.board.controller.UserQnA;

import java.io.IOException;
import java.net.URLEncoder;

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

@WebServlet("/qboard/insert.do")
public class UserQInsert extends HttpServlet{
	
	//submit 클릭 --> db 추가
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		
		HttpSession loginSession = req.getSession(false);
		
		String tm_id = ((MemberVO)loginSession.getAttribute("memVO")).getTm_id();
		String tm_b_title = req.getParameter("tm_b_title");
		String tm_b_content = req.getParameter("tm_b_content");

		IUserQnaBoardService boardService = UserQnaServiceImple.getInstance();
		
		BoardVO boardVO = new BoardVO();
		boardVO.setTm_id(tm_id);
		boardVO.setTm_b_title(tm_b_title);
		boardVO.setTm_b_content(tm_b_content);

		
		int cnt = boardService.insertBoard(boardVO);
			
		req.setAttribute("cnt", cnt);
		
		req.getRequestDispatcher("/WEB-INF/views/common/resultBoard.jsp").forward(req, resp);
	
	
	}

}
