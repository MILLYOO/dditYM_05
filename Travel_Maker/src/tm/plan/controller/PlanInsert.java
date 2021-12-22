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
import tm.plan.vo.planDetailVO;
import tm.plan.vo.planVO;

/**
 * Servlet implementation class PlanInsert
 */
@WebServlet("/PlanInsert.do")
public class PlanInsert extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PlanInsert() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession loginSession = request.getSession(false);
		
		String tm_id = ((MemberVO)loginSession.getAttribute("memVO")).getTm_id();
		
		IPlanService service = PlanService.getService();
		
		planVO vo = new planVO();
		planDetailVO vo2 = new planDetailVO();
		
		
		vo.setTm_id(tm_id);
		vo.setTm_plan_name(request.getParameter("Ptitle"));
		System.out.println(vo.getTm_plan_name()+"<<<<<<<<<<<<<<<<<<<<<<<<");
		int req = service.planInsert(vo);	//일정 추가 반환값 : 방금 넣은 tm_plan_id
		
		System.out.println("여기 와짐?"+ req);
		String list = request.getParameter("Pid");
		
		String[] dlist = list.split(",");
		
		for(int i = 0 ; i < dlist.length ; i++) {
			vo2.setTm_plan_no(0);
			vo2.setTm_plan_id(req);
			vo2.setTm_plan_detail(dlist[i]);
			vo2.setTm_plan_start(request.getParameter("startDate"));
			vo2.setTm_plan_end(request.getParameter("endDate"));
			service.planDInsert(vo2);
		}
	}

}
