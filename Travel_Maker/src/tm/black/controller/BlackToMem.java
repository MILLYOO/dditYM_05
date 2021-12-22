package tm.black.controller;

import java.io.IOException;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import tm.black.service.BlackMemServiceImple;
import tm.black.service.IBlackMemService;
import tm.black.vo.BlackMemVO;
import tm.member.vo.MemberVO;

@WebServlet("/black/btom.do")
public class BlackToMem extends HttpServlet{
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String tm_id = req.getParameter("id");
		System.out.println("테스트 " + tm_id);
		
		IBlackMemService service = BlackMemServiceImple.getInstatnce();
		
		MemberVO memVO = new MemberVO();
		memVO.setTm_id(tm_id);
		
		int cnt = service.blackToMem(memVO);
	
		req.setAttribute("cnt", cnt);
		req.setAttribute("id", tm_id);
		req.getRequestDispatcher("/WEB-INF/views/black/ResultBtoM.jsp").forward(req, resp);
		
	}

}
