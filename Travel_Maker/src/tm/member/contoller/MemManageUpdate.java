package tm.member.contoller;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;

import tm.member.service.IMemManageService;
import tm.member.service.MemManageService;
import tm.member.vo.MemberVO;

@WebServlet("/MemManageUpdate.do")
public class MemManageUpdate extends HttpServlet{
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		MemberVO vo = new MemberVO();
		
		req.getParameter("formdata");
		
		try {
			BeanUtils.populate(vo, req.getParameterMap());
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		
		
		IMemManageService service = MemManageService.getservice();
		
		int res = service.memUpdate(vo);
		
		req.setAttribute("res", res);
		
		req.getRequestDispatcher("WEB-INF/views/admin/memManageRes.jsp").forward(req, resp);
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}
}
