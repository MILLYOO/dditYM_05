package tm.board.controller.noticeBoard;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import tm.board.service.INoticeBoardService;
import tm.board.service.NoticeBoardService;
import tm.comm.vo.BoardVO;
import tm.comm.vo.PagingVO;
import tm.member.vo.MemberVO;

@WebServlet("/NoticeList.do")
public class NoticeBoardList extends HttpServlet{
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		//로그인세션
//		HttpSession loginSession = req.getSession(false);
//	    String loginId = ((MemberVO)loginSession.getAttribute("memVO")).getTm_id();
//	    req.setAttribute("loginId", loginId);

		INoticeBoardService service = NoticeBoardService.getInstance();

		////////////////////////페이징///////////////////////
		int pageNo = req.getParameter("pageNo") == null ? 1 : Integer.parseInt(req.getParameter("pageNo"));
		

		int totalCnt = service.getAllNoticeCount();
		PagingVO pagingVO = new PagingVO();
		pagingVO.setCountPerPage(10);	// 한 페이지당 게시되는 게시물 건 수
		pagingVO.setPageSize(2);	// 
		pagingVO.setTotalCount(totalCnt);	// 전체 게시물 건 수
		pagingVO.setCurrentPageNo(pageNo);
		
		req.setAttribute("pagingVO", pagingVO); // 화면에 쓰기 위해 
		////////////////////////////////////////////////////
		
		
		List<BoardVO> list = service.selectByPage(pagingVO);
		
		req.setAttribute("list", list);
		
		req.getRequestDispatcher("/views/listPage.jsp").forward(req, resp);
	}
}
