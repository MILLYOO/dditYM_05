package tm.my.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import tm.board.service.FreeBoardService;
import tm.board.service.IFreeBoardService;
import tm.comm.vo.BoardVO;
import tm.member.vo.MemberVO;
import tm.my.service.IMyBoardService;
import tm.my.service.IMyInfoService;
import tm.my.service.MyBoardServiceImpl;
import tm.my.service.MyInfoServiceImpl;

/**
 * Servlet implementation class ListMyBoardServlet
 */
@WebServlet("/ListMyBoardServlet.do")
public class ListMyBoardServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
     
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession loginSession = request.getSession(false);

		int page = request.getParameter("page") == null ? 
				1 : Integer.parseInt(request.getParameter("page"));
		
		String userid = ((MemberVO)loginSession.getAttribute("memVO")).getTm_id();
		System.out.println("들어옴");
		
		if(userid == null) {
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter writer = response.getWriter();
			writer.println("<script>alert('로그인 해주세요'); </script>");
			writer.close();
		}
		System.out.println("나감");
		
		IMyBoardService service = MyBoardServiceImpl.getService();
		
		int perList = 10;
		int perPage = 2;
		
		int totalCount = service.getTotalCount();
		
		int start = (page-1) * perList + 1;
		int end = start + perList - 1;
		if(end > totalCount) end = totalCount;
		
		Map<String, Object> map = new HashMap<>();
		map.put("start", start);
		map.put("end", end);
		map.put("tm_id", userid);
		
		System.out.println(map);
		
		List<BoardVO> list = service.selectAll(map);
		
		
		int totalPage = (int) (Math.ceil((double)totalCount / perList));
		int startPage = ((page-1) / perPage * perPage)+1;
		int endPage = startPage + perPage -1;
		if(endPage > totalPage) endPage = totalPage;
		
		
		request.setAttribute("selectPage", list);
		request.setAttribute("startPage", startPage);
		request.setAttribute("endPage", endPage);
		request.setAttribute("totalPage", totalPage);
		
		
		request.getRequestDispatcher("/WEB-INF/views/mypage/myboard.jsp").forward(request, response);
	}

}


























