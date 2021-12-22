package tm.comm.util;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

/*
 	테스트 방법 : 
 	겟방식은 이클립스가 알아서 해주니까 포스트방식(3번파일로 해야 함)
 	3번 파일에서 인코딩 안 하면 한글이 깨지는데, web.xml에 필터 적용하니까 걸러져서 알아서 인코딩 된다
 	로그인 한 사람만 쓸 수 있는 기능 (로그인 체크 필터) --> 세션 넘버 체크 / 아니면 비회원페이지로 리턴
 */
public class T10_CharacterEncoding implements Filter{
	
	private String encoding;	//인코딩 정보 담을 변수
	
	@Override
	public void destroy() {
		
		
	}

	@Override				//http 객체 아님
	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain fc)
			throws IOException, ServletException {
		
		System.out.println("인코딩 정보 설정 : " + encoding);
		
		req.setCharacterEncoding(encoding);
		resp.setCharacterEncoding(encoding);
		
		//다음 필터 있으면 실행하라는 메서드
		fc.doFilter(req, resp);
	}

	@Override
	public void init(FilterConfig config) throws ServletException {
		
		//web.xml에서 설정하지 않은 경우 (default 설정)
		if(config.getInitParameter("encoding") == null) {
			this.encoding = "utf-8";
		}else {
			this.encoding = config.getInitParameter("encoding");
		}
	}
	
}
