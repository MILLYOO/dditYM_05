package tm.member.contoller;

import java.io.IOException;
import java.util.List;

import javax.security.auth.message.callback.PrivateKeyCallback.Request;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import tm.comm.vo.PagingVO;
import tm.member.service.IMemManageService;
import tm.member.service.MemManageService;
import tm.member.vo.MemberVO;


@WebServlet("/MemManage.do")
public class MemManage extends HttpServlet{

	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		// 로그인 세션 ---이 페이지 접근을 홈에서 막으면 세션 필요 없음 !!
//		HttpSession loginSession = req.getSession(false);
//	    String loginId = ((MemberVO)loginSession.getAttribute("memVO")).getTm_id();
//	    
//		
//	    if(loginId.equals("admin")) {
		

	    	
			IMemManageService service = MemManageService.getservice();
			
			////////////////////////페이징///////////////////////
			int pageNo = req.getParameter("pageNo") == null ? 1 : Integer.parseInt(req.getParameter("pageNo"));
			
	
			int totalCnt = service.memAllCount();
			PagingVO pagingVO = new PagingVO();
			pagingVO.setCountPerPage(10);	// 한 페이지당 게시되는 게시물 건 수
			pagingVO.setPageSize(2);	// 
			pagingVO.setTotalCount(totalCnt);	// 전체 게시물 건 수
			pagingVO.setCurrentPageNo(pageNo);
			
			req.setAttribute("pagingVO", pagingVO); // 화면에 쓰기 위해 
			req.setAttribute("total", totalCnt);
			////////////////////////////////////////////////////
			
			
			req.setCharacterEncoding("utf-8");
			String searchSelect = req.getParameter("searchSelect");
			String inputSearch = req.getParameter("inputSearch");
			
			
			List<MemberVO> list = null;
			
			if(inputSearch == null || searchSelect == null ) {		// 전체목록
				list = service.memAllList(pagingVO);
			}else {												// 검색목록
				MemberVO vo = new MemberVO();
				
				String[] sel = {"아이디", "이름", "탈퇴여부"};
				
				if(searchSelect.equals(sel[0])) {
					vo.setTm_id(inputSearch);
				}else if(searchSelect.equals(sel[1])) {
					vo.setTm_name(inputSearch);
				}else if(searchSelect.equals(sel[2])) {
					vo.setTm_diss(inputSearch);
				}
				
				int totalSearchCnt = service.memSearchCount(vo);
				
				pagingVO.setTotalCount(totalSearchCnt);
				vo.setPagingvo(pagingVO);
				
				list = service.memSearch(vo);
			}
			
			req.setAttribute("list", list);
			
			req.getRequestDispatcher("WEB-INF/views/admin/memManage.jsp").forward(req, resp);
			
			
//		    }else {
//	    	  req.getRequestDispatcher("WEB-INF/views/admin/access.jsp").forward(req, resp);
//	      }
	    
	}
}
