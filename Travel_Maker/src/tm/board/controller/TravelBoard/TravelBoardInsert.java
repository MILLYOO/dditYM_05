package tm.board.controller.TravelBoard;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.fileupload.FileUpload;

import tm.board.service.ITravelBoardService;
import tm.board.service.TravelBoardService;
import tm.comm.service.IImgService;
import tm.comm.service.ImgServiceImple;
import tm.comm.util.FileUploadRequestWrapper;
import tm.comm.vo.BoardVO;
import tm.comm.vo.ImgVO;
import tm.member.vo.MemberVO;

/**
 * Servlet implementation class TravelBoardInsert
 */
@WebServlet("/TravelBoardInsert.do")
public class TravelBoardInsert extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TravelBoardInsert() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	
		HttpSession loginSession = request.getSession(false);
		String tm_id = ((MemberVO)loginSession.getAttribute("memVO")).getTm_id();
		
		//요청 파라미터
		String tm_b_title = request.getParameter("tm_b_title");
		String tm_b_content = request.getParameter("tm_b_content");
		
		
		//-- 첨부파일 -----------------
		ImgVO imgVO = new ImgVO();
		
		//기존 http req 래퍼req로 캐스팅 		//멀티파트여부 체크하는 메서드(내가 만든 것. boolean타입)
		if(((FileUploadRequestWrapper )request).isMultipartContent()){
			
			IImgService imgService = ImgServiceImple.getInstacne();
			
			Map<String, Object[]> fileItemMap =
					((FileUploadRequestWrapper)request).getFileItemMap();
			
			//파일이 존재한다면
			if(fileItemMap.size() > 0) {
											//getter 쓸 객체 만들기!
				imgVO = imgService.saveAtchFileList(fileItemMap);
				System.out.println("파일 아이디:" + imgVO.getTm_bimg_id());
			
			Map<String, String[]> parameterMap = ((FileUploadRequestWrapper)request).getParameterMap();	
			System.out.println("parameterMap => " + parameterMap);
			String dd = ((FileUploadRequestWrapper)request).getParameter("dd");
			tm_b_title = ((FileUploadRequestWrapper)request).getParameter("tm_b_title");
			tm_b_content = ((FileUploadRequestWrapper)request).getParameter("tm_b_content");
			
			System.out.println("파일제목 :" + tm_b_title);
			System.out.println("파일내용 :" + tm_b_content);
			System.out.println("파일작성자 :" + tm_id);

			}
	
			
		}
		
		//서비스 객체
		ITravelBoardService service = TravelBoardService.getService();

		System.out.println("제목 :" + tm_b_title);
		System.out.println("내용 :" + tm_b_content);
		System.out.println("작성자 :" + tm_id);
		
		//보드 vo 세팅
		BoardVO boardVO = new BoardVO();
		boardVO.setTm_id(tm_id);
		boardVO.setTm_b_title(tm_b_title);
		boardVO.setTm_b_content(tm_b_content);
		boardVO.setTm_bimg_id(imgVO.getTm_bimg_id());
		
		int cnt = service.insertBoard(boardVO);
		
		request.setAttribute("cnt", cnt);
		
		System.out.println("cnt : " + cnt);
		
		// 4. 응답데이터 생성하기 -> jsp / forward
		request.getRequestDispatcher("/WEB-INF/views/common/resultBoardFile.jsp").forward(request, response);

		
	}

}
