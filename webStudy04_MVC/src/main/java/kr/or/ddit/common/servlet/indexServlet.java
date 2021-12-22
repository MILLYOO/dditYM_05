package kr.or.ddit.common.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.ddit.mvc.annotation.stereotype.Controller;
import kr.or.ddit.mvc.annotation.stereotype.RequestMapping;

@Controller
public class indexServlet{
	@RequestMapping("/index.do")
	public String doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//		String destination = "/WEB-INF/views/template.jsp";
//		req.getRequestDispatcher(destination).forward(req, resp);;
		return "template";
	}
}
