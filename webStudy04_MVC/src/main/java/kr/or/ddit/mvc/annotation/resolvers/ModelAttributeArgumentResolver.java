package kr.or.ddit.mvc.annotation.resolvers;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Parameter;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang3.ClassUtils;

public class ModelAttributeArgumentResolver implements HandlerMethodArgumentResolver {

	@Override
	public boolean isSupported(Parameter parameter) {
		// 파라미터의 타입을 가져옴
		Class<?> parameterType = parameter.getType();
		//  어노테이션을 가져와서 null인지 체크
		ModelAttribute annotation = parameter.getAnnotation(ModelAttribute.class);
		
		// 기본형인지, Wrapper인지 확인 후 True일 경우 리턴 X
		return !ClassUtils.isPrimitiveOrWrapper(parameterType) && annotation!=null;
	}

	@Override
	public Object argumentResolve(Parameter parameter, HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		Class<?> parameterType = parameter.getType();
		ModelAttribute annotation = parameter.getAnnotation(ModelAttribute.class);
		try {
			Object model = parameterType.newInstance(); // 자바빈규약을 맞춰야지만 생성이 된다(기본생성자)
			String attributerName = annotation.value();
			req.setAttribute(attributerName, model);
			
			Map<String, String[]> parameterMap = req.getParameterMap();
			BeanUtils.populate(model, parameterMap);
			return model;
		} catch (InstantiationException | IllegalAccessException | InvocationTargetException e) {
			throw new ServletException(e);
		} 
//		1. 가입을 하기위한 파라미터 확보 -> MemberVO (빈유틸을 사용하기 위해 name을 vo의 파라미터와 맞추자)
//		MemberVO member = new MemberVO();
//		req.setAttribute("member", member);
////		String memId = req.getParameter("memId");
////		member.setMemId(memId);
//		
//		// req에 있는 파라미터를 맵형식으로 만들어 담은 후 populate를 통해 memberVO와 일치하는 파라미터 복사.
//		Map<String, String[]> paramterMap = req.getParameterMap();
//		try {
//			BeanUtils.populate(member, paramterMap);
//		} catch (IllegalAccessException | InvocationTargetException e) {
//			throw new ServletException(e);
//		}
	}

}
