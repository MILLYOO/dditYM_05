package tm.plan.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import tm.plan.service.IPlanService;
import tm.plan.service.PlanService;

/**
 * Servlet implementation class DeletePlan
 */
@WebServlet("/DeletePlan.do")
public class DeletePlan extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("서블릿 진입");
		
		String tm_plan_id = request.getParameter("tm_plan_id");
		System.out.println("받아온 아이디 : " + tm_plan_id);
		
		
		IPlanService service = PlanService.getService();
		
		int res = service.planDeleteDetail(tm_plan_id);
		res = service.planDelete(tm_plan_id);
		
		request.setAttribute("res", res);
		request.getRequestDispatcher("/WEB-INF/views/notice/noticeRes.jsp").forward(request, response);
		
		
	}

}
