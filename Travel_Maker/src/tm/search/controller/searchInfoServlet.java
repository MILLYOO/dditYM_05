package tm.search.controller;

import java.io.IOException;
import java.util.ArrayList;
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
@WebServlet("/searchInfo.do")
public class searchInfoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public searchInfoServlet() {
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
		System.out.println(key);
		List<SearchVO> list = service.searchInfo(key);
		List<String> imgList = new ArrayList<String>();
		
		for(int i=0; i<list.size(); i++) {
			imgList.add(i, "img" + i);
		}
		
		request.setAttribute("imgList", imgList);
		request.setAttribute("list", list);
		System.out.println(list);
		request.getRequestDispatcher("views/Search/searchInfo.jsp").forward(request, response);
	}

}
