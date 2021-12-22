package tm.my.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.Writer;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import tm.member.vo.MemberVO;
import tm.my.service.IMyInfoService;
import tm.my.service.MyInfoServiceImpl;

/**
 * Servlet implementation class ListMyInfoServlet
 */
@WebServlet("/ListMyInfoServlet.do")
public class ListMyInfoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		

		
		IMyInfoService myInfoService = MyInfoServiceImpl.getInstance();
		
		HttpSession loginSession = request.getSession(false);
		MemberVO memVO = (MemberVO)loginSession.getAttribute("memVO");
		System.out.println(memVO);
		
		if(memVO == null) {
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter writer = response.getWriter();
			writer.println("<script>alert('로그인 해주세요'); </script>");
			writer.close();
		}
		
		String tm_id = memVO.getTm_id();
		
		MemberVO myList = myInfoService.getMember(tm_id);
		
		
		
		request.setAttribute("myList", myList);
		System.out.println("관리자 세션id : " + loginSession);
		System.out.println("관리자아이디 : " + ((MemberVO)loginSession.getAttribute("memVO")).getTm_id());
		System.out.println(tm_id);
		System.out.println("꺌룰랭");
		
		request.getRequestDispatcher("/WEB-INF/views/mypage/mylist.jsp").forward(request, response);
		

		
		
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
