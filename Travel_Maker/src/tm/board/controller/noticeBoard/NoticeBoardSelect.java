package tm.board.controller.noticeBoard;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import tm.board.service.INoticeBoardService;
import tm.board.service.NoticeBoardService;
import tm.comm.vo.BoardVO;
import tm.member.vo.MemberVO;

@WebServlet("/NoticeBoardSelect.do")
public class NoticeBoardSelect extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
//		HttpSession loginSession = req.getSession(false);
//	    String loginId = ((MemberVO)loginSession.getAttribute("memVO")).getTm_id();
//	    req.setAttribute("loginId", loginId);
		
		int noticeNo = Integer.parseInt(req.getParameter("tm_b_no"));
		
		INoticeBoardService service = NoticeBoardService.getInstance();
		
		BoardVO vo = service.noticeSelect(noticeNo);
		
		req.setAttribute("vo", vo);
		
		req.getRequestDispatcher("WEB-INF/views/notice/NoticeSelect.jsp").forward(req, resp);
		
	}
	
}
