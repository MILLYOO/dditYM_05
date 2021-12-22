package kr.or.ddit.servlet01;

import java.util.Calendar;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;

@WebServlet("/01/now.tmpl")
public class TimeServletWithTemplate extends AbstractTemplateServlet{

	@Override
	protected String getMimeType() {
		return "text/html;charset=UTF8";
	}

	@Override
	protected void makeDate(HttpServletRequest req) {
		Calendar cal = Calendar.getInstance();
		req.setAttribute("title", "짱쎈유밀");
		req.setAttribute("now", cal);
	}

}
