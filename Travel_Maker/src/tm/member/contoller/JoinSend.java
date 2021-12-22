package tm.member.contoller;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;

import tm.member.service.IMemberService;
import tm.member.service.MemberService;
import tm.member.vo.MemberVO;

/**
 * Servlet implementation class JoinSend
 */
@WebServlet("/JoinSend.do")
public class JoinSend extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public JoinSend() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");
		
		  String stm_id = request.getParameter("tm_id"); 					
	      String stm_pass = request.getParameter("tm_pass");
	      String stm_name = request.getParameter("tm_name");
	      String stm_tel = request.getParameter("tm_tel");
	      String stm_zip = request.getParameter("tm_zip");
	      String stm_add1 = request.getParameter("tm_add1");
	      String stm_add2 = request.getParameter("tm_add2");
	      String stm_email = request.getParameter("tm_email");
	      String stm_bir = request.getParameter("tm_bir");
	      String stm_diss = request.getParameter("tm_diss");
	      String stm_date = request.getParameter("tm_date");
	      //int stm_author = Integer.parseInt(request.getParameter("tm_author"));
	      int stm_author = 1;
	      
	      MemberVO vo = new MemberVO();
	      
	      vo.setTm_id(stm_id);
	      vo.setTm_name(stm_name);
	      vo.setTm_pass(stm_pass);
	      vo.setTm_tel(stm_tel);
	      vo.setTm_zip(stm_zip);
	      vo.setTm_add1(stm_add1);
	      vo.setTm_add2(stm_add2);
	      vo.setTm_email(stm_email);
	      vo.setTm_bir(stm_bir);
	      vo.setTm_diss(stm_diss);
	      vo.setTm_date(stm_date);
	      vo.setTm_author(stm_author);
	         
		
	//	MemberVO vo = new MemberVO(); //valueObject, Bean, DTO
		  
/*		  try {
			BeanUtils.populate(vo, request.getParameterMap()); 
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} */
		  
	      // 1. 서비스객체 얻어오기
	      IMemberService service = MemberService.getservice();
	    		  
	      // 2. 서비스 메서드 호출하기
	      String id = service.insertMember(vo);
	    		  
	      // 3. 결과값을 request 에 저장하기
	      request.setAttribute("joinSend1", id); 
	      request.setAttribute("joinSend2", vo.getTm_id()); 
	    		  
	      // 4. 응답데이터를 생성 - jsp로 위임 (forward방식)
	      request.getRequestDispatcher("views/join.jsp").forward(request, response); 
	   }

	}
