package tm.search.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import tm.search.service.ISearchService;
import tm.search.service.SearchService;
import tm.search.vo.SearchVO;

/**
 * Servlet implementation class searchInfo
 */
@WebServlet("/searchInfo2.do")
public class searchInfo2Servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public searchInfo2Servlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		ISearchService service = SearchService.getInstance();
		
		String key = (String)request.getParameter("data");
		SearchVO vo = service.searchInfo2(key);
		request.setAttribute("vo", vo);
		request.getRequestDispatcher("views/Search/searchInfo2.jsp").forward(request, response);
	}

}
