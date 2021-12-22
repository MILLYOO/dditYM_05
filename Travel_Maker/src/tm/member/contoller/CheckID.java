package tm.member.contoller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import tm.member.service.IMemberService;
import tm.member.service.MemberService;

/**
 * Servlet implementation class CheckID
 */
@WebServlet("/CheckID.do")
public class CheckID extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CheckID() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
				String inputId = request.getParameter("id");
				String inputPass = request.getParameter("pass");//
				
				IMemberService service = MemberService.getservice();
				
				String chkId = service.searchId(inputId);
				String chkPass = service.searchId(inputPass);//
				
				request.setAttribute("chkI", chkId); 
				request.setAttribute("chkP", chkPass); 
				
				
				RequestDispatcher disp = request.getRequestDispatcher("views/checkId.jsp");
				disp.forward(request, response);
						
	
	}

}
