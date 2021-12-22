package kr.or.ddit.servlet03;

import java.io.IOException;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/sampleProcess.do")
public class SampleProcessServlet extends HttpServlet 
{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("get callback");
//		req.setCharacterEncoding("UTF-8");
		String textParam = req.getParameter("textParam");
		String numberParam = req.getParameter("numberParam");
		String radioParam = req.getParameter("radioParam");
		String[] checkParam = req.getParameterValues("checkParam");
		String singleSelect = req.getParameter("singleSelect");
		String[] multiSelect = req.getParameterValues("multiSelect");
		
		System.out.printf("textParam : %s\n", textParam);
		System.out.printf("numberParam : %s\n", numberParam);
		System.out.printf("radioParam : %s\n", radioParam);
		System.out.printf("checkParam : %s\n", Arrays.toString(checkParam));
		System.out.printf("singleSelect : %s\n", singleSelect);
		System.out.printf("multiSelect : %s\n", Arrays.toString(multiSelect));
		
		Map<String, String[]> parameterMap = req.getParameterMap();
		for(Entry<String, String[]> entry : parameterMap.entrySet()) {
			String name = entry.getKey();
			String[] values = entry.getValue();
			System.out.printf("%s : %s\n", name, Arrays.toString(values));
		}
		
		Enumeration<String> names =  req.getParameterNames();
		while (names.hasMoreElements()) {
			String name = (String) names.nextElement();
			req.getParameterValues(name);
			String[] values = req.getParameterValues(name);
			System.out.printf("%s : %s\n", name, Arrays.toString(values));
		}
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("post callback");

	}
	
}
