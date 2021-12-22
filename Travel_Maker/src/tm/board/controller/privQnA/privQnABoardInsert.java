package tm.board.controller.privQnA;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.beanutils.BeanUtils;

import tm.board.service.privQnABoardService;
import tm.board.service.IprivQnABoardService;
import tm.comm.vo.BoardVO;
import tm.member.vo.MemberVO;



/**
 * Servlet implementation class BoardInsert
 */
@WebServlet("/privQnABoardInsert.do")
public class privQnABoardInsert extends HttpServlet {

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession loginSession = request.getSession(false);
		
		String title = request.getParameter("tm_b_title");
		String content = request.getParameter("tm_b_content");
		String tm_id = ((MemberVO)loginSession.getAttribute("memVO")).getTm_id();
		
		
		
		// 0. 데이터 가져오기
		BoardVO vo = new BoardVO();
		vo.setTm_id(tm_id);
		
		try {
			
			BeanUtils.populate(vo, request.getParameterMap());
			
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		
		
		
		// 1. 서비스객체 얻어오기
		IprivQnABoardService service = privQnABoardService.getService();
		
		// 2. 서비스 메서드 호출 -> 결과 받기
		int seq = service.insertBoard(vo);
		
		// 3. 결과값 저장
		request.setAttribute("insertNo", seq);
		
		// 4. 응답데이터 생성하기 -> jsp / forward
		request.getRequestDispatcher("/WEB-INF/views/privQnA/privQnAresult.jsp").forward(request, response);

		
	}

}
