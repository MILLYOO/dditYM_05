package tm.like.Controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import tm.comm.service.BoardLikeService;
import tm.comm.service.IBoardLikeService;
import tm.comm.vo.LikeHateVO;
import tm.member.vo.MemberVO;

/**
 * Servlet implementation class Like
 */
@WebServlet("/BoardLike.do")
public class BoardLike extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String tm_id = request.getParameter("tm_id");
		int tm_b_no = Integer.parseInt(request.getParameter("tm_b_no"));
		int tm_category_id = Integer.parseInt(request.getParameter("tm_category_id"));
		
		LikeHateVO vo = new LikeHateVO();
		vo.setTm_id(tm_id);
		vo.setTm_b_no(tm_b_no);
		vo.setTm_category_id(tm_category_id);
		
		
		IBoardLikeService service = BoardLikeService.getService();
		
		int cnt = service.addLike(vo);
		
		
		request.setAttribute("cnt", cnt);
		
		request.getRequestDispatcher("/WEB-INF/views/common/resultBoard.jsp").forward(request, response);
	}

}
