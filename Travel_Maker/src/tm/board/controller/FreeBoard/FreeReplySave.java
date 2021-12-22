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
import tm.comm.vo.ReplyVO;
import tm.member.vo.MemberVO;



/**
 * Servlet implementation class ReplySave
 */
@WebServlet("/FreeReplySave.do")
public class FreeReplySave extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		
		HttpSession loginSession = request.getSession(false);
		
		String tm_id = ((MemberVO)loginSession.getAttribute("memVO")).getTm_id();
		String content = request.getParameter("tm_b_content");
		
		//0
		ReplyVO vo = new ReplyVO();
		vo.setTm_id(tm_id);

		try {
			BeanUtils.populate(vo,  request.getParameterMap());
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		
		//1
		IFreeBoardService service = FreeBoardService.getService();
		System.out.println("no="+vo.getTm_b_no());
		System.out.println("cont="+vo.getTm_bc_content());
		System.out.println("id="+vo.getTm_id());
		
		//2
		int cnt = service.replySave(vo);
		
		
		
		//3
		request.setAttribute("cnt", cnt);
		
		
		//4
		request.getRequestDispatcher("/WEB-INF/views/common/resultBoard.jsp").forward(request, response);
	}

}
