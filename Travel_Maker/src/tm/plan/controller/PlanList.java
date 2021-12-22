package tm.plan.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import tm.member.vo.MemberVO;
import tm.plan.service.IPlanService;
import tm.plan.service.PlanService;
import tm.plan.vo.planVO;

/**
 * Servlet implementation class PlanList
 */
@WebServlet("/PlanList.do")
public class PlanList extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("서블릿 진입");
		HttpSession loginSession = request.getSession(false);
		
		
		String tm_id = ((MemberVO)loginSession.getAttribute("memVO")).getTm_id();
		System.out.println("세션아이디 " + tm_id);
		
		IPlanService service = PlanService.getService();
		
		List<planVO> list = service.selectAll(tm_id);
		System.out.println(list);
		
		request.setAttribute("list", list);
		
		request.getRequestDispatcher("/WEB-INF/views/plan/planlist.jsp").forward(request, response);
		
	}

}
