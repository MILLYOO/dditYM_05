package kr.or.ddit.servlet02;

import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//1. 요청 받고
// loadOnStartup=1
// Servlet이 객체화 되어 생성되는 시점은 사용자로부터 최초의 요청이 발생할 때. 
// 서블릿은 생성과정에서  init()메서드가 실행됩니다. 
// 사용자의 요청 여부에 관계없이 서블릿 컨테이너(톰캣)가 시작됨과 동시에 서블릿의 생성과 초기화를 진행하고 싶을 때 사용
// 0이상의 정수값으로 설정되어 있는 경우 해당 서블릿의 인스턴스를 생성하고 init()을 호출
@WebServlet(value="/imageForm.do")
public class ImageFormServlet extends HttpServlet{
    // 전체를 통틀어 싱글톤
	// ServletContext : 서블릿 데이터 보관소
	ServletContext application;

	@Override
	public void init() throws ServletException {
		super.init();
		application = getServletContext();
		
		// 폴더 위치 바뀌면 이것 만 바꾸면 됨, 
//		String folderPath = "d:/contents";
		
		// 컨텍스트에 파라미터로 넘겨줄 수  있다면? -> web.xml context-param
		// 어플리케이션 통틀어 딱 한번 전달
		String folderPath = application.getInitParameter("contentFolder");
		File folder = new File(folderPath);
		
		// 어디서든 꺼낼 수 있다.
		application.setAttribute("contentFolder", folder);
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//	      2. 요청 분석
		
//		  3. 모델 생성
		File folder = (File) application.getAttribute("contentFolder");
		// 1.7버전 이전
		// 이미지 파일만 걸러서 가져와 컨트롤 할 수 있도록
		File[] images = folder.listFiles(new FilenameFilter(){
			public boolean accept(File dir, String name){
				String mime = application.getMimeType(name);
				return mime!=null && mime.startsWith("image/");	//톰캣에매칭안된확장자는null이므로 null이 아닌 조건 걸어야 함
			}
		});	
//		4. 모델 공유(전달)
		req.setAttribute("images", images);
//		5. view 선택
		String view = "/WEB-INF/views/imageForm.jsp";
//		6. view로 이동
		req.getRequestDispatcher(view).forward(req, resp);
		
	}
	
}
