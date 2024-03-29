package kr.or.ddit.servlet01;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public abstract class AbstractTemplateServlet extends HttpServlet{
	
	protected abstract String getMimeType();
	
	private StringBuffer readTmpl(HttpServletRequest req) throws IOException {
		String tmplpath = req.getServletPath();
		InputStream is = getServletContext().getResourceAsStream(tmplpath);
		StringBuffer tmplSrc = new StringBuffer();
		InputStreamReader isr = new InputStreamReader(is,"UTF-8");
		BufferedReader reader = new BufferedReader(isr);
		String temp = null;
		while ((temp = reader.readLine())!= null) {
			tmplSrc.append(String.format("%s\n", temp));
		}
		return tmplSrc;
	}
	
	protected abstract void makeDate(HttpServletRequest req);
	
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//		1. Mime
		String mime = getMimeType();
		resp.setContentType(mime);
		
//		2. read tmpl
		StringBuffer tmplSrc = readTmpl(req);
		
//		3. 데이터생성
		makeDate(req);
		
//		4. 데이터치환
		StringBuffer html = replaceDate(req, tmplSrc);
		
//		5. 응답 전송
		PrintWriter out = resp.getWriter();
		out.print(html);
		
		
	}

	private StringBuffer replaceDate(HttpServletRequest req, StringBuffer tmplSrc) {
		String regex = "%(\\w+)%";
		Pattern regexPtrn = Pattern.compile(regex);
		Matcher matcher = regexPtrn.matcher(tmplSrc);
		StringBuffer html = new StringBuffer();
		while(matcher.find()){
			String dataName = matcher.group(1); // 소괄호() 그룹안의 내용을 가져옴
			Object value = req.getAttribute(dataName);
			String valueStr = Objects.toString(value, "");
			matcher.appendReplacement(html, valueStr);
		}
		matcher.appendTail(html);
		return html;
	}
}
