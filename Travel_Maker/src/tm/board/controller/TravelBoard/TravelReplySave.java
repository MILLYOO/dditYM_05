package tm.board.controller.TravelBoard;

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
import tm.board.service.ITravelBoardService;
import tm.board.service.TravelBoardService;
import tm.comm.vo.ReplyVO;
import tm.member.vo.MemberVO;

/**
 * Servlet implementation class TravelReplySave
 */
@WebServlet("/TravelReplySave.do")
public class TravelReplySave extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TravelReplySave() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		HttpSession loginSession = request.getSession(false);
		
		String tm_id = ((MemberVO)loginSession.getAttribute("memVO")).getTm_id();
		
		//int tm_b_no = Integer.parseInt(req.getParameter("tm_b_no"));
		String content = request.getParameter("tm_b_content");


		//0
		ReplyVO reVO = new ReplyVO();
		reVO.setTm_id(tm_id);

		try {
			BeanUtils.populate(reVO,  request.getParameterMap());
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		
		//1
		ITravelBoardService service = TravelBoardService.getService();
		System.out.println("no="+reVO.getTm_b_no());
		System.out.println("cont="+reVO.getTm_bc_content());
		System.out.println("id="+reVO.getTm_id());
		
		//2
		int cnt = service.replySave(reVO);

		//3
		request.setAttribute("cnt", cnt);	
		
		//4
		request.getRequestDispatcher("/WEB-INF/views/common/resultBoard.jsp").forward(request, response);
	}

}
