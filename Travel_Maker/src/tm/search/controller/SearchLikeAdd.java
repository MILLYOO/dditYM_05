package tm.search.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import tm.comm.service.BoardLikeService;
import tm.comm.service.IBoardLikeService;
import tm.comm.vo.LikeHateVO;
import tm.comm.vo.SearchLikeHateVO;
import tm.member.vo.MemberVO;
import tm.search.service.ISearchLikeService;
import tm.search.service.SearchLikeService;

/**
 * Servlet implementation class Like
 */
@WebServlet("/SearchLikeAdd.do")
public class SearchLikeAdd extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession loginSession = request.getSession(false);
		String tm_id = ((MemberVO)loginSession.getAttribute("memVO")).getTm_id();
		System.out.println("추가 세션아이디 : " + tm_id);
		
		String tm_search_id = request.getParameter("tm_search_id");
		System.out.println("추가 서치아이디 : " + tm_search_id);
		
		SearchLikeHateVO vo = new SearchLikeHateVO();
		vo.setTm_id(tm_id);
		vo.setTm_search_id(tm_search_id);

		
		
		ISearchLikeService service = SearchLikeService.getService();
		
		int cnt = service.addSearchLike(vo);
		
		
		request.setAttribute("cnt", cnt);
		
		request.getRequestDispatcher("/WEB-INF/views/common/resultBoard.jsp").forward(request, response);
	}

}
