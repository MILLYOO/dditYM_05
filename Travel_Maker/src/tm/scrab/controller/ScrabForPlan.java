package tm.scrab.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import tm.comm.vo.PagingVO;
import tm.member.vo.MemberVO;
import tm.plan.service.IPlanService;
import tm.scrab.service.IScrabServicekm;
import tm.scrab.service.ScrabServiceImplekm;
import tm.scrab.vo.ScrabVO;
import tm.search.service.IScrapService;


@WebServlet("/scrabplan.do")
public class ScrabForPlan extends HttpServlet{
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		
		System.out.println("서블릿 실행");
		HttpSession loginSession = req.getSession(false);
		String tm_id = ((MemberVO)loginSession.getAttribute("memVO")).getTm_id();
		
		System.out.println("아이디 :  " + tm_id);
		
		IScrabServicekm service = ScrabServiceImplekm.getInstatnce();
		
		List<ScrabVO> scrabList = service.scrabForPlan(tm_id);
		req.setAttribute("scrabList", scrabList);
		
		for(int i=0; i<scrabList.size(); i++){
			ScrabVO vo = scrabList.get(i);
		}
		
		req.getRequestDispatcher("/WEB-INF/views/scrab/ResultForPlan.jsp").forward(req, resp);
	}

}
