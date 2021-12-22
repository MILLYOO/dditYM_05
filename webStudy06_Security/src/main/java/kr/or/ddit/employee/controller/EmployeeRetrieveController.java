package kr.or.ddit.employee.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;

import com.fasterxml.jackson.databind.ObjectMapper;

import kr.or.ddit.employee.service.EmployeeService;
import kr.or.ddit.employee.service.EmployeeServiceImpl;
import kr.or.ddit.mvc.annotation.resolvers.ModelAttribute;
import kr.or.ddit.mvc.annotation.resolvers.RequestHeader;
import kr.or.ddit.mvc.annotation.resolvers.RequestParam;
import kr.or.ddit.mvc.annotation.stereotype.Controller;
import kr.or.ddit.mvc.annotation.stereotype.RequestMapping;
import kr.or.ddit.vo.EmployeeVO;
import kr.or.ddit.vo.PagingVO;

// /emp/employeeList.do
@Controller
public class EmployeeRetrieveController {
	private EmployeeService service = new EmployeeServiceImpl();
	
	@RequestMapping("/emp/employeeList.do")
	public String empList(
//			@RequestParam(value="page", required=false, defaultValue="1") int currentPage,
//			@ModelAttribute("detailSearch") EmployeeVO detailSearch,
			@RequestHeader(value="accept", required=false, defaultValue="html") String accept
			, HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException{
		
		PagingVO<EmployeeVO> pagingVO = new PagingVO<>();
//		pagingVO.setCurrentPage(currentPage);
//		pagingVO.setDetailSearch(detailSearch);
		List<EmployeeVO> empList = service.retrieveEmployeeList(pagingVO);
		String viewName = null;
		if(StringUtils.containsIgnoreCase(accept, "json")) {
			resp.setContentType("application/json;charset=UTF-8");
			try(
				PrintWriter out = resp.getWriter();
			){
				ObjectMapper mapper = new ObjectMapper();
				mapper.writeValue(out, pagingVO);
			}
			return null;
		}else {
			req.setAttribute("pagingVO", pagingVO);
			viewName = "employee/employeeList";
			return viewName;
		}
	}
}
