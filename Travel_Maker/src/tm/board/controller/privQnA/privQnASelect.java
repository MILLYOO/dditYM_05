package tm.board.controller.privQnA;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import tm.board.service.privQnABoardService;
import tm.board.service.IprivQnABoardService;
import tm.comm.vo.BoardVO;

/**
 * Servlet implementation class privQnASelect
 */
@WebServlet("/privQnASelect.do")
public class privQnASelect extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("글 세부 조회로 들어옴");
		
		request.setCharacterEncoding("UTF-8");
		
		int tm_b_no = Integer.parseInt(request.getParameter("TM_B_NO"));
		System.out.println("글번호 : " + tm_b_no);
		
		IprivQnABoardService service = privQnABoardService.getService();
		
		BoardVO vo = service.getBoard(tm_b_no);
		System.out.println("서비스 통과");
		System.out.println(vo);

		request.setAttribute("board", vo);
		
		request.getRequestDispatcher("/WEB-INF/views/privQnA/privQnASelect.jsp").forward(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
