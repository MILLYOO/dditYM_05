package tm.search.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import tm.member.vo.MemberVO;
import tm.search.service.IScrapService;
import tm.search.service.ISearchService;
import tm.search.service.ScrapService;
import tm.search.service.SearchService;
import tm.search.vo.ScrapVO;
import tm.search.vo.SearchVO;

/**
 * Servlet implementation class ScrapListServlet
 */
@WebServlet("/ScrapList.do")
public class ScrapListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ScrapListServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession loginSession = request.getSession(false);
		MemberVO memvo = (MemberVO)loginSession.getAttribute("memVO");
		
		String tm_id = "";
		if(memvo==null) {
			tm_id = "비회원";
		}else {
			tm_id = memvo.getTm_id();
		}

		 IScrapService service = ScrapService.getInstance();
		 ISearchService service2 = SearchService.getInstance();
		 List<ScrapVO> list2 = (List<ScrapVO>)service.listScrap(tm_id);
		
		 List<SearchVO> list = new ArrayList<>();
		 for(int i = 0 ; i < list2.size() ; i++) {
		 list.add((SearchVO)service2.searchInfo2(list2.get(i).getTm_search_id()));
		 }
		 
		 List<String> imgList = new ArrayList<String>();
			
		for(int i=0; i<list2.size(); i++) {
			imgList.add(i, "img" + i);
			System.out.println("일정생성 이미지 테스트: " + imgList.get(i));
		}
		
		 request.setAttribute("imgList", imgList);
		 request.setAttribute("list", list);
		 request.getRequestDispatcher("views/Search/allInfo.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
