package tm.plan.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import tm.board.service.FreeBoardService;
import tm.board.service.IFreeBoardService;
import tm.comm.vo.BoardVO;
import tm.comm.vo.ReplyVO;
import tm.plan.service.IPlanService;
import tm.plan.service.PlanService;
import tm.plan.vo.planDetailVO;

/**
 * Servlet implementation class PlanSelect
 */
@WebServlet("/PlanSelect.do")
public class PlanSelect extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("서블릿 진입");
		
		String tm_plan_id = request.getParameter("Tm_plan_id");
		System.out.println("받아온 아이디" + tm_plan_id);
		
		
		IPlanService service = PlanService.getService();
		
		List<planDetailVO> list = service.planDetail(tm_plan_id);
		
		
		request.setAttribute("selectplanList", list);
		
		request.getRequestDispatcher("/WEB-INF/views/plan/selectPlan.jsp").forward(request,  response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		doGet(request, response);
	}

}
