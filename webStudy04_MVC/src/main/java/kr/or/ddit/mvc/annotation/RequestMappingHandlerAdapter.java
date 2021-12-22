package kr.or.ddit.mvc.annotation;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.ddit.mvc.annotation.resolvers.BadRequestException;
import kr.or.ddit.mvc.annotation.resolvers.HandlerMethodArgumentResolver;
import kr.or.ddit.mvc.annotation.resolvers.ModelAttributeArgumentResolver;
import kr.or.ddit.mvc.annotation.resolvers.RequestHeaderArgumentResolver;
import kr.or.ddit.mvc.annotation.resolvers.RequestParamArgumentResolver;
import kr.or.ddit.mvc.annotation.resolvers.RequestPartArgumentResolver;
import kr.or.ddit.mvc.annotation.resolvers.ServletSpecArgumentResolver;

// 10/08 Adapter가 제대로 동작을 안하면 뒷단을 불러올 수 없음.
// 핸들러매핑이 req, resp를 다 넘겨 받는데, 이것이 불편하여 해결하고자 함.
// 핸들러 어댑터가 호출해야할 메소드 중 하나는 파라미터가 아예 없을수 있음 (get메소드 같은 경우)
// 두번째는 파라미터가 필요함(파라미터 갯수, 타입이 다를 수 있음).
// 메소드가 시그니처를 가지고 있는지 없는지를 체크하여 넘겨줘야함.
public class RequestMappingHandlerAdapter implements HandlerAdapter {
	List<HandlerMethodArgumentResolver> argumentResolvers;

	public RequestMappingHandlerAdapter() {
		super();
		argumentResolvers = new ArrayList<>();
		argumentResolvers.add(new ModelAttributeArgumentResolver());
		argumentResolvers.add(new ServletSpecArgumentResolver());
		argumentResolvers.add(new RequestParamArgumentResolver());
		argumentResolvers.add(new RequestHeaderArgumentResolver());
		argumentResolvers.add(new RequestPartArgumentResolver());
	}

	// 설계도에는 없는 메소드
	// 사용할 ArgumentResolver를 찾음.
	private HandlerMethodArgumentResolver findArgumentResolver(Parameter parameter) {
		HandlerMethodArgumentResolver finded = null;
		for (HandlerMethodArgumentResolver resolver : argumentResolvers) {
			if (resolver.isSupported(parameter)) {
				finded = resolver;
				break;
			}
		}
		return finded;
	}

	@Override
	public String invokeHandler(RequestMappingInfo mappingInfo, HttpServletRequest req, HttpServletResponse resp)
			throws IOException, ServletException {
		Object commandHandler = mappingInfo.getCommandHandler();
		Method handlerMethod = mappingInfo.getHandlerMethod();
		Parameter[] parameters = handlerMethod.getParameters(); // 파라미터가 없으면 size = 0인 어레이가 넘어온다.

		try {
			Object[] parameterValues = null;
			// 파라미터가 있으면 있는것 만큼 만들어야 한다.
			if (parameters.length > 0) {
				parameterValues = new Object[parameters.length];
				for(int i = 0 ; i <= parameterValues.length-1 ; i++) {
					HandlerMethodArgumentResolver finded = findArgumentResolver(parameters[i]); // 리졸버를 찾음;
					if(finded!=null) {
						parameterValues[i] = finded.argumentResolve(parameters[i], req, resp);
					}else {
						throw new RuntimeException(String.format("현재 파라미터[%s]는 처리할 수 없는 형태", parameterValues[i]).toString());
					}
				}
			}
			String viewName = (String) handlerMethod.invoke(commandHandler, parameterValues);
			return viewName;
		} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
			throw new ServletException(e);
		} catch (BadRequestException e) {
			resp.sendError(400, e.getMessage());
			return null;
		}
	}

}
