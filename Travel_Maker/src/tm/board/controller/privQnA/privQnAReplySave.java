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
import tm.comm.vo.ReplyVO;
import tm.member.vo.MemberVO;



/**
 * Servlet implementation class ReplySave
 */
@WebServlet("/privQnAReplySave.do")
public class privQnAReplySave extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		
		HttpSession loginSession = request.getSession(false);
		
		String content = request.getParameter("tm_b_content");
		
		String tm_id = ((MemberVO)loginSession.getAttribute("memVO")).getTm_id();
		

		
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
		IprivQnABoardService service = privQnABoardService.getService();
		System.out.println("no="+vo.getTm_b_no());
		System.out.println("cont="+vo.getTm_bc_content());
		System.out.println("id="+vo.getTm_id());
		
		//2
		int save = service.replySave(vo);
		
		
		
		//3
		request.setAttribute("insertNo", save);
		
		
		//4
		
		request.getRequestDispatcher("/WEB-INF//views/privQnA/privQnAresult.jsp").forward(request, response);
	}

}
