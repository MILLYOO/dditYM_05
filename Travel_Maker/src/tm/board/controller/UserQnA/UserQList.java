package tm.board.controller.UserQnA;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import tm.board.service.IUserQnaBoardService;
import tm.board.service.UserQnaServiceImple;
import tm.comm.vo.BoardVO;
import tm.comm.vo.PagingVO;


@WebServlet("/qboard/list.do")
public class UserQList extends HttpServlet{
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		//요청페이지 번호
		int page = req.getParameter("pageNo") == null ? 
				1 : Integer.parseInt(req.getParameter("pageNo"));
		
		IUserQnaBoardService boardService = UserQnaServiceImple.getInstance();
		
		//페이징 객체
		int totalCount = boardService.countTotalCount();
		
		//페이징 세팅
		PagingVO pagingVO = new PagingVO();
		pagingVO.setCountPerPage(10);	// 한 페이지당 게시되는 게시물 건 수
		pagingVO.setPageSize(2);	// 
		pagingVO.setTotalCount(totalCount);	// 전체 게시물 건 수
		pagingVO.setCurrentPageNo(page);
		
		req.setAttribute("pagingVO", pagingVO); // 화면에 쓰기 위해 
		
		//글 조회
		List<BoardVO> list = boardService.selectAll(pagingVO);
		req.setAttribute("list", list);
		
		System.out.println("리스트 확인 : " + list);
		System.out.println("리스트 0번 : " + list.indexOf(0));
		
		req.getRequestDispatcher("/views/boardUserQ.jsp").forward(req, resp);
				
	}

}
