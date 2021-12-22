package tm.search.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import tm.member.vo.MemberVO;
import tm.search.service.IScrapService;
import tm.search.service.ScrapService;
import tm.search.vo.ScrapVO;

/**
 * Servlet implementation class Scrap
 */
@WebServlet("/ScrapDelete.do")
public class ScrapDelete extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public ScrapDelete() {
        super();
    }
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession loginSession = request.getSession(false);
		MemberVO memvo = (MemberVO)loginSession.getAttribute("memVO");
		String search_id = request.getParameter("id");
		String tm_id = "";
		if(memvo==null) {
			tm_id = "비회원";
		}else {
			tm_id = memvo.getTm_id();
		}

		ScrapVO vo = new ScrapVO();
		int cnt = 0;
		if(!tm_id.equals("비회원")) {
			vo.setTm_id(tm_id);
			vo.setTm_search_id(search_id);
			
			IScrapService service = ScrapService.getInstance();
			cnt = service.deleteScrap(vo);
		}
		 request.setAttribute("cnt", cnt);
		 request.getRequestDispatcher("WEB-INF/views/SearchModal/ScrapDelete.jsp").forward(request, response);
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
