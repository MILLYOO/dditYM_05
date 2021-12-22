package tm.search.controller;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;

import tm.comm.service.IImgService;
import tm.comm.service.ImgServiceImple;
import tm.comm.util.FileUploadRequestWrapper;
import tm.comm.vo.ImgVO;
import tm.search.service.ISearchService;
import tm.search.service.SearchService;
import tm.search.vo.SearchVO;

@WebServlet("/InsertSearch.do")
public class InesrtSearchServlet extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		RequestDispatcher dispatcher = req.getRequestDispatcher("/views/Search/kakaoMap.jsp");
		dispatcher.forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		
		
		String tm_search_addr = "";
		String tm_search_cate = "";
		String tm_search_con = "";
		String tm_search_name = "";
		String tm_search_x = "";
		String tm_search_y = "";
		String tm_search_tel = "";
		
		
		//---첨부파일-----------------------
		ImgVO imgVO = new ImgVO();
		
		//기존 http req 래퍼req로 캐스팅 		//멀티파트여부 체크하는 메서드(내가 만든 것. boolean타입)
		if(((FileUploadRequestWrapper )req).isMultipartContent()){
			
			IImgService imgService = ImgServiceImple.getInstacne();
			
			Map<String, Object[]> fileItemMap =
					((FileUploadRequestWrapper)req).getFileItemMap();
			
			//파일이 존재한다면
			if(fileItemMap.size() > 0) {
											//getter 쓸 객체 만들기!
				imgVO = imgService.saveAtchFileList(fileItemMap);
				System.out.println("파일 아이디 확인용:" + imgVO.getTm_bimg_id());
			
			Map<String, String[]> parameterMap = ((FileUploadRequestWrapper)req).getParameterMap();	
			System.out.println("parameterMap => " + parameterMap);
			
			tm_search_addr = ((FileUploadRequestWrapper)req).getParameter("tm_search_addr");
			tm_search_cate = ((FileUploadRequestWrapper)req).getParameter("tm_search_cate");
			tm_search_con = ((FileUploadRequestWrapper)req).getParameter("tm_search_con");
			tm_search_name = ((FileUploadRequestWrapper)req).getParameter("tm_search_name");
			tm_search_x = ((FileUploadRequestWrapper)req).getParameter("tm_search_x");
			tm_search_y = ((FileUploadRequestWrapper)req).getParameter("tm_search_y");
			tm_search_tel = ((FileUploadRequestWrapper)req).getParameter("tm_search_tel");
			
			}
		}
		
		ISearchService service = SearchService.getInstance();
		
		SearchVO vo = new SearchVO();
		
		vo.setTm_search_addr(tm_search_addr);
		vo.setTm_search_cate(tm_search_cate);
		vo.setTm_search_con(tm_search_con);
		vo.setTm_search_name(tm_search_name);
		vo.setTm_search_x(tm_search_x);
		vo.setTm_search_y(tm_search_y);
		vo.setTm_search_tel(tm_search_tel);
		vo.setTm_search_hit("0");
		
		int result = service.setInfo(vo);

		req.setAttribute("result", result);
		req.getRequestDispatcher("views/Search/resultSearchInsert.jsp").forward(req, resp);

	}
}
