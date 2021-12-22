package tm.board.controller.privQnA;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import tm.board.service.privQnABoardService;
import tm.board.service.IprivQnABoardService;
import tm.comm.vo.BoardVO;
import tm.member.vo.MemberVO;

/**
 * Servlet implementation class privQnABoardList
 */
@WebServlet("/privQnABoardList.do")
public class privQnABoardList extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public privQnABoardList() {
        super();
    }

//=============================================================================================== 
    
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		IprivQnABoardService service = privQnABoardService.getService();
		
		List<BoardVO> list = service.selectAll();
		
		request.setAttribute("selectAll", list);
		
		request.getRequestDispatcher("/WEB-INF/views/privQnA/privQnAListAll.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int page = Integer.parseInt(request.getParameter("page"));
		
		IprivQnABoardService service = privQnABoardService.getService();
		System.out.println("진입함");
		int perList = 10;
		int perPage = 2;
		
		int totalCount = service.getTotalCount();
		
		int start = (page-1) * perList + 1;
		int end = start + perList - 1;
		if(end > totalCount) end = totalCount;
		
		
		
		Map<String, Integer> map= new HashMap<>();
		map.put("start", start);
		map.put("end", end);
		
		
		
		
		List<BoardVO> list = service.selectByPage(map);
		
		int totalPage = (int) (Math.ceil((double)totalCount / perList));
		int startPage = ((page-1) / perPage * perPage)+1;
		int endPage = startPage + perPage -1;
		if(endPage > totalPage) endPage = totalPage;
		

		request.setAttribute("selectPage", list);
		request.setAttribute("startPage", startPage);
		request.setAttribute("endPage", endPage);
		request.setAttribute("totalPage", totalPage);
		
		
		request.getRequestDispatcher("/WEB-INF/views/privQnA/privQnAListPage.jsp").forward(request, response);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
