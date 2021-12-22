package tm.scrab.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import tm.comm.vo.PagingVO;
import tm.member.vo.MemberVO;
import tm.plan.service.IPlanService;
import tm.scrab.service.IScrabServicekm;
import tm.scrab.service.ScrabServiceImplekm;
import tm.scrab.vo.ScrabVO;
import tm.search.service.IScrapService;


@WebServlet("/myscrablist.do")
public class ScrabList extends HttpServlet{
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		int page = req.getParameter("page") == null ? 
				1 : Integer.parseInt(req.getParameter("page"));
		
		HttpSession loginSession = req.getSession(false);
		String tm_id = ((MemberVO)loginSession.getAttribute("memVO")).getTm_id();
		
		IScrabServicekm service = ScrabServiceImplekm.getInstatnce();

		int scrabCount = service.countMyScrab(tm_id);
		
		ScrabVO scVO = new ScrabVO();
		scVO.setCountPerPage(9);
		scVO.setPageSize(5);
		scVO.setTotalCount(scrabCount);
		scVO.setCurrentPageNo(page);
		scVO.setTm_id(tm_id);
		
		req.setAttribute("scVO", scVO);
		
		List<ScrabVO> scrabList = service.selectMyScrab(scVO);
		req.setAttribute("scrabList", scrabList);
		
		List<String> imgList = new ArrayList<String>();
		
		for(int i=0; i<scrabList.size(); i++) {
			imgList.add(i, "img" + i);
			System.out.println("이미지 이름 테스트: " + imgList.get(i));
		}
		
		req.setAttribute("imgList", imgList);
		
		req.getRequestDispatcher("/WEB-INF/views/scrab/ListResult.jsp").forward(req, resp);
	}

}
