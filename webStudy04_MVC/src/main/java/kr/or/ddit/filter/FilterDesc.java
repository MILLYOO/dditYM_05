package kr.or.ddit.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class FilterDesc implements Filter {

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		log.info(getClass().getSimpleName()+ " 초기화 되었음");
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		long start = System.currentTimeMillis();
		HttpServletRequest req = (HttpServletRequest) request;
		String url = req.getRequestURI();
		String method = req.getMethod();
		log.info("url : {}, method : {}", url, method);
		// request가 필터링 된다. ( request filtering )
		chain.doFilter(request, response); // 다음번 필터로 제어권을 넘긴다, 만약 다음 필터가 없다면 프론트로 넘어간다.
		// response가 필터링 된다 ( response filtering )
		long end = System.currentTimeMillis();
		
		log.info("url : {}, method : {}, 소요시간 : {}", url, method, ( end - start ));
	}

	@Override
	public void destroy() {
		log.info(getClass().getSimpleName()+ " 소멸 되었음");		
	}

}
