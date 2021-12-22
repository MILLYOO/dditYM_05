package tm.board.controller.BestDestination;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import tm.board.service.INoticeBoardService;
import tm.board.service.NoticeBoardService;
import tm.search.vo.SearchVO;

@WebServlet("/BestDestination.do")
public class BestDestination extends HttpServlet{
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		// 서비스 객체 불러와
		INoticeBoardService service = NoticeBoardService.getInstance();
		// 서비스.메서드
		List<SearchVO> list = service.bestDestList();
		// setAttribute
		req.setAttribute("list", list);
		// foward
		req.getRequestDispatcher("views/bestDestList.jsp").forward(req, resp);
	}
	

}
