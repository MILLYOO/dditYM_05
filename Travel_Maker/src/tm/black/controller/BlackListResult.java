package tm.black.controller;

import java.io.IOException;
import java.util.List;

import javax.security.auth.message.callback.PrivateKeyCallback.Request;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import tm.black.service.BlackMemServiceImple;
import tm.black.service.IBlackMemService;
import tm.black.vo.BlackMemVO;
import tm.comm.vo.PagingVO;

@WebServlet("/black/list.do")
public class BlackListResult extends HttpServlet{
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		//요청페이지 번호
		int page = req.getParameter("page") == null ? 
				1 : Integer.parseInt(req.getParameter("page"));
		
		IBlackMemService service = BlackMemServiceImple.getInstatnce();
		
		//페이지 객체
		int totalCount = service.countBlackMember();
		
		PagingVO pagingVO = new PagingVO();
		pagingVO.setCountPerPage(10); 		//페이지당 게시물 수
		pagingVO.setPageSize(10);			//페이지 목록 수
		pagingVO.setTotalCount(totalCount); //전체 게시물 수
		pagingVO.setCurrentPageNo(page);  //현제 페이지 번호
		
		req.setAttribute("pagingVO", pagingVO);
		
		//블랙리스트 조회
		List<BlackMemVO> blackList = service.getAllBlackMember(pagingVO);
		req.setAttribute("blackList", blackList);
		
		req.getRequestDispatcher("/WEB-INF/views/black/blacklist.jsp").forward(req, resp);
		
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		doGet(req, resp);
	}
	
}
