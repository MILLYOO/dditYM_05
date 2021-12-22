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
 * Servlet implementation class deleteInfoServlet
 */
@WebServlet("/deleteInfo.do")
public class deleteInfoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public deleteInfoServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		ISearchService service = SearchService.getInstance();
		
		String id = (String)request.getParameter("id");
		int result = service.deleteInfo(id);
		request.setAttribute("result", result);
		request.getRequestDispatcher("views/Search/result.jsp").forward(request, response);
	}

}
