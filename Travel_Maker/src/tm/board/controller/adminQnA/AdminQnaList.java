package tm.board.controller.adminQnA;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import tm.board.service.IQnaBoardService;
import tm.board.service.QnaBoardService;
import tm.comm.vo.BoardVO;
import tm.comm.vo.PagingVO;
import tm.comm.vo.ReplyVO;
import tm.member.vo.MemberVO;

@WebServlet("/AdminQnaList.do")
public class AdminQnaList extends HttpServlet{
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String inputSearch = req.getParameter("inputSearch");	//검색어
		
		IQnaBoardService service = QnaBoardService.getInstance();
		
		int pageNo = req.getParameter("pageNo") == null ? 1 : Integer.parseInt(req.getParameter("pageNo"));
		int totalCnt = service.qnaAllCount();
		PagingVO pagingVO = new PagingVO();
		pagingVO.setCountPerPage(10);	// 한 페이지당 게시되는 게시물 건 수
		pagingVO.setPageSize(2);	// 
		pagingVO.setTotalCount(totalCnt);	// 전체 게시물 건 수
		pagingVO.setCurrentPageNo(pageNo);
		
		req.setAttribute("pagingVO", pagingVO); // 화면에 쓰기 위해 
		
		
		List<BoardVO> list = null;
		
		if(inputSearch == null) {					// 검색어 없을 시 전체 목록			
			list = service.qnaSort(pagingVO);
		}else {
			//검색목록
			MemberVO memberVO = new MemberVO();
			memberVO.setTm_id(inputSearch);
			
			BoardVO boardVO = new BoardVO();
			boardVO.setTm_id(inputSearch);
			int totalSearchCnt = service.qnaSearchCount(boardVO); // 검색 결과 수
			
			pagingVO.setTotalCount(totalSearchCnt);
			memberVO.setPagingvo(pagingVO);
			list = service.qnaSearch(memberVO);
		}
		
		req.setAttribute("list", list);
		
		
		req.getRequestDispatcher("WEB-INF/views/admin/qnaManage.jsp").forward(req, resp);
	}
	
	
}
