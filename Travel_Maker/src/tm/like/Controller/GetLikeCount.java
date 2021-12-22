package tm.like.Controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import tm.comm.service.BoardLikeService;
import tm.comm.service.IBoardLikeService;
import tm.comm.vo.LikeHateVO;
import tm.comm.vo.SearchLikeHateVO;
import tm.search.service.ISearchLikeService;
import tm.search.service.SearchLikeService;

/**
 * Servlet implementation class GetLikeCount
 */
@WebServlet("/GetLikeCount.do")
public class GetLikeCount extends HttpServlet {
	private static final long serialVersionUID = 1L;
       


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String TM_SEARCH_ID = request.getParameter("TM_SEARCH_ID");
		System.out.println("카운트 들어옴");
		System.out.println("TM_SEARCH_ID : " + TM_SEARCH_ID);
		
		
		SearchLikeHateVO vo = new SearchLikeHateVO();
		vo.setTm_search_id(TM_SEARCH_ID);
		
		
		
		ISearchLikeService service = SearchLikeService.getService();
		
		int cnt = service.getSearchLikeCount(vo);
		
		request.setAttribute("cnt", cnt);
		
		request.getRequestDispatcher("/WEB-INF/views/common/resultBoard.jsp").forward(request, response);
		
		
		
	}

}
