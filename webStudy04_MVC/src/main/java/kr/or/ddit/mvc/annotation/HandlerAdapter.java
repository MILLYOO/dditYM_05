package kr.or.ddit.mvc.annotation;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface HandlerAdapter {
	
	/**
	 * 리플렉션을 기반으로 command handler를 호출하는 역활
	 * 프론트로부터 resp를 받아옴.
	 * @param mappingInfo
	 * @param req
	 * @param resp
	 * @return
	 * @throws IOException TODO
	 * @throws ServletException TODO
	 */
	public String invokeHandler(RequestMappingInfo mappingInfo, HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException;
}
