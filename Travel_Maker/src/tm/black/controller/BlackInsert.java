package tm.black.controller;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.beanutils.BeanUtils;

import tm.black.service.BlackMemServiceImple;
import tm.black.service.IBlackMemService;
import tm.black.vo.BlackMemVO;
import tm.member.vo.MemberVO;

@WebServlet("/black/insert.do")
public class BlackInsert extends HttpServlet {
	
	//url로 접근 시 insert.jsp로 이동 --> form 작성
	@Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    	request.getRequestDispatcher("/WEB-INF/views/black/blackinsert.jsp").forward(request, response);
	}

	
	
//submit 클릭 --> db 추가
@Override
protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
	HttpSession loginSession = request.getSession(false);
	
	String tm_id = request.getParameter("tm_id");
	String reason = request.getParameter("tm_bl_rs");
	String admin = ((MemberVO)loginSession.getAttribute("memVO")).getTm_id();
	//String admin = request.getParameter("tm_bl_admn");
	
	System.out.println("관리자 세션id : " + loginSession);
	System.out.println("관리자아이디 : " + ((MemberVO)loginSession.getAttribute("memVO")).getTm_id());
	
	IBlackMemService service = BlackMemServiceImple.getInstatnce();
	
	MemberVO memVO = new MemberVO();
	memVO.setTm_id(tm_id);
	
	BlackMemVO blackVO = new BlackMemVO();
	
	blackVO.setTm_id(tm_id);
	blackVO.setTm_bl_admn(admin);
	blackVO.setTm_bl_rs(reason);
	
	int cnt = service.checkBlackMember(tm_id);
	
	if(cnt == 1) {
		service.memToBlack(memVO);
		service.insertAgainBlack(blackVO);
	}else {
		service.memToBlack(memVO);
		service.insertBlackMember(blackVO);
	}
	
	
	String redirect = request.getContextPath() + "/views/blacklist.html";
	response.sendRedirect(redirect);
	
	}
}

