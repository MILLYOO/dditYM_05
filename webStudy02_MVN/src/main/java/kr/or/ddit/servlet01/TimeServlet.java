// context path : /time
// service url : time/now.do

// content 형태 :
// <h4>당신의 접속 시간 : ...입니다.</h4>

package kr.or.ddit.servlet01;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import javax.servlet.*;
import java.io.*;
import java.util.*;
//import java.text.*;




public class TimeServlet extends HttpServlet{
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
		// 1. Mime 설정
		resp.setContentType("text/html;charset=UTF8");
		
		// 2. tmpl 파일 읽기
		String tmplpath = req.getServletPath();
		InputStream is = getServletContext().getResourceAsStream(tmplpath);
		StringBuffer tmplSrc = new StringBuffer();
		InputStreamReader isr = new InputStreamReader(is,"UTF-8");
		BufferedReader reader = new BufferedReader(isr);
		String temp = null;
		
		while ((temp = reader.readLine())!= null) {
			tmplSrc.append(String.format("%s\n", temp));
		}
		// 3. 데이터 생성
		Calendar cal = Calendar.getInstance();
		Object data = String.format("%tc", cal); // 날짜데이터를 format해서 데이터를 만듬.
		
		// 4. 데이터 치환
		String html = tmplSrc.toString().replace("%now%", data.toString());
		
		// 5. 응답 전송
		PrintWriter out = null;
		try{
			out = resp.getWriter();
			out.println(html);
		}finally{
			if(out!=null){	
				out.close();	
			}
		}
	}
}























