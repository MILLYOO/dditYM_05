package tm.board.controller.FreeBoard;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.beanutils.BeanUtils;

import tm.board.service.FreeBoardService;
import tm.board.service.IFreeBoardService;
import tm.comm.vo.BoardVO;
import tm.member.vo.MemberVO;



/**
 * Servlet implementation class BoardInsert
 */
@WebServlet("/FreeBoardInsert.do")
public class FreeBoardInsert extends HttpServlet {

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession loginSession = request.getSession(false);
		
		String title = request.getParameter("tm_b_title");
		String content = request.getParameter("tm_b_content");
		String tm_id = ((MemberVO)loginSession.getAttribute("memVO")).getTm_id();
		
		
		
		// 0. 데이터 가져오기
		BoardVO boardVO = new BoardVO();
		boardVO.setTm_id(tm_id);
		
		try {
			
			BeanUtils.populate(boardVO, request.getParameterMap());
			
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		
		
		
		// 1. 서비스객체 얻어오기
		IFreeBoardService service = FreeBoardService.getService();
		
		// 2. 서비스 메서드 호출 -> 결과 받기
		int cnt = service.insertBoard(boardVO);
		
		// 3. 결과값 저장
		request.setAttribute("cnt", cnt);
		
		// 4. 응답데이터 생성하기 -> jsp / forward
		//request.getRequestDispatcher("/WEB-INF/views/common/resultBoard.jsp").forward(request, response);
		request.getRequestDispatcher("/WEB-INF/views/common/resultBoard.jsp").forward(request, response);
		
	}

}
