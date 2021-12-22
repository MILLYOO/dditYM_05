package kr.or.ddit.servlet02;
import javax.servlet.http.*;
import javax.servlet.*;
import java.io.*;
import javax.servlet.annotation.*;


@WebServlet("/image.do")
public class ImageServlet extends HttpServlet{
	public void doGet(HttpServletRequest req, HttpServletResponse resp) 
	throws IOException, ServletException
	{
		// D드라이브의 컨텐츠 폴더를 하드코딩하지 않고 동적으로 적용
		//File folder = new File("d:/contents");
		File folder = (File) getServletContext().getAttribute("contentFolder");
		String file = (String) req.getParameter("image");
		
		if(file == null) {		// 꼭 필요한 파라미터가 넘어오지 않았을 때
			resp.sendError(HttpServletResponse.SC_BAD_REQUEST); // 400 : 잘못된 요청 (클라이언트의 문제)
			return;		// 요청이 잘못되면 return
		}
		
		File imageFile = new File(folder,file);
		
		if(!imageFile.exists()) {
			// 없는 파일 요청하는 클라이언트의 문제
			resp.sendError(HttpServletResponse.SC_NOT_FOUND); // 404 : FileNotFound
			return;		
		}
		
		String mime = getServletContext().getMimeType(imageFile.getName());
		
		if(mime == null || !mime.startsWith("image/")) {
			resp.sendError(HttpServletResponse.SC_BAD_REQUEST); // 400 : 잘못된 요청 (클라이언트의 문제) 파일형태잘못됨
			return;		// 요청이 잘못되면 return
		}
		
		resp.setContentType(mime);
		
		
		
		FileInputStream fis = new FileInputStream(imageFile);
		byte[] buffer = new byte[1024];
		int count = -1;
		OutputStream os = resp.getOutputStream();
		
		while((count = fis.read(buffer)) != -1){
			os.write(buffer, 0, count);
		}
		
		fis.close();
		os.flush();
		os.close();
	}
}
