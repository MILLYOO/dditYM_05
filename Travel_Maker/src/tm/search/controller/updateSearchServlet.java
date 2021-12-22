package tm.search.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import tm.search.service.ISearchService;
import tm.search.service.SearchService;
import tm.search.vo.SearchVO;

/**
 * Servlet implementation class updateSearchServlet
 */
@WebServlet("/updateSearch.do")
public class updateSearchServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public updateSearchServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		SearchVO vo = new SearchVO();
//		try {
//			BeanUtils.populate(vo, req.getParameterMap());
//		} catch (IllegalAccessException | InvocationTargetException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		vo.setTm_search_id((String)request.getParameter("tm_search_id"));
		vo.setTm_search_addr((String)request.getParameter("tm_search_addr"));
		vo.setTm_search_cate((String)request.getParameter("tm_search_cate"));
		vo.setTm_search_con((String)request.getParameter("tm_search_con"));
		vo.setTm_search_name((String)request.getParameter("tm_search_name"));
		vo.setTm_search_x((String)request.getParameter("tm_search_x"));
		vo.setTm_search_y((String)request.getParameter("tm_search_y"));
		vo.setTm_search_tel((String)request.getParameter("tm_search_tel"));
		vo.setTm_search_hit("0");
		ISearchService service = SearchService.getInstance();
		int result = service.updateInfo(vo);
		
		request.setAttribute("result", result);
		request.getRequestDispatcher("views/Search/result.jsp").forward(request, response);
	}

}
