package kr.or.ddit.mvc.annotation.resolvers;

import java.io.IOException;
import java.lang.reflect.Parameter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class ServletSpecArgumentResolver implements HandlerMethodArgumentResolver {

	@Override
	public boolean isSupported(Parameter parameter) {
		Class<?> parameterType = parameter.getType();
		return HttpServletRequest.class.isAssignableFrom(parameterType)||
				HttpServletResponse.class.isAssignableFrom(parameterType)||
				HttpSession.class.isAssignableFrom(parameterType);

	}

	@Override
	public Object argumentResolve(Parameter parameter, HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		Class<?> parameterType = parameter.getType();
		Object parmeterValue = null;
		if(HttpServletRequest.class.isAssignableFrom(parameterType)) {
			parmeterValue = req;
		}else if(HttpServletResponse.class.isAssignableFrom(parameterType)){
			parmeterValue = resp;
		}else {
			parmeterValue = req.getSession();
		}
		return parmeterValue;
	}

}
