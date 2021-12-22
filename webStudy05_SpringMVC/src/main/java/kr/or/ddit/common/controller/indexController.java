package kr.or.ddit.common.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class indexController{
	@RequestMapping("/index.do")
	public String doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//		String destination = "/WEB-INF/views/template.jsp";
//		req.getRequestDispatcher(destination).forward(req, resp);;
		return "index";
	}
}
