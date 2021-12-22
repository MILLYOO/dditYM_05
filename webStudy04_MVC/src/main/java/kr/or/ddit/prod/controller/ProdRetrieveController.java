package kr.or.ddit.prod.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.InvocationTargetException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang3.StringUtils;

import com.fasterxml.jackson.databind.ObjectMapper;

import kr.or.ddit.mvc.annotation.resolvers.ModelAttribute;
import kr.or.ddit.mvc.annotation.resolvers.RequestHeader;
import kr.or.ddit.mvc.annotation.resolvers.RequestParam;
import kr.or.ddit.mvc.annotation.stereotype.Controller;
import kr.or.ddit.mvc.annotation.stereotype.RequestMapping;
import kr.or.ddit.prod.dao.OthersDAO;
import kr.or.ddit.prod.dao.OthersDAOImpl;
import kr.or.ddit.prod.service.ProdService;
import kr.or.ddit.prod.service.ProdServiceImpl;
import kr.or.ddit.vo.PagingVO;
import kr.or.ddit.vo.ProdVO;

/**
 * 검색 조건 : 상품명, 거래처명, 상품의 분류 명 세가지 검색 타입으로 검색 가능. 
 *  1. 세가지 검색 조건 중 하나만을 사용해서 검색
 *  	동기요청
 *  2. 두가지 이상의 검색 조건을 동시 사용.
 *  	비동기 요청
 * 
 */
@Controller
public class ProdRetrieveController {
	private ProdService service = new ProdServiceImpl();
	private OthersDAO othersDAO = new OthersDAOImpl();
	
	private void addAttribute(HttpServletRequest req) {
		req.setAttribute("lprodList", othersDAO.selectLprodList());
		req.setAttribute("buyerList", othersDAO.selectBuyerList(null));
	}
	
	@RequestMapping("/prod/prodList.do")
		public String form(@RequestParam(value="page", required=false, defaultValue="1") int currentPage
				, @ModelAttribute("detailSearch") ProdVO detailSearch
				, @RequestHeader(value="accept", required=false, defaultValue="html") String accept
				, HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//			String accept = req.getHeader("Accept");
			
			addAttribute(req);
			
//			int currentPage = 1;
//			String pageParam = req.getParameter("page");
//			if(StringUtils.isNumeric(pageParam)) {
//				currentPage = Integer.parseInt(pageParam);
//			}
			
//			String searchType = req.getParameter("searchType");
//			String searchWord = req.getParameter("searchWord");
//			SearchVO searchVO = new SearchVO(searchType, searchWord);
//			ProdVO detailSearch = new ProdVO();
//			try {
//				BeanUtils.populate(detailSearch, req.getParameterMap());
//			} catch (IllegalAccessException | InvocationTargetException e) {
//				throw new RuntimeException(e);
//			}
			
			PagingVO<ProdVO> pagingVO = new PagingVO<>();
			pagingVO.setCurrentPage(currentPage);
			pagingVO.setDetailSearch(detailSearch);
//			pagingVO.setSearchVO(searchVO);
			service.retrieveProdList(pagingVO);
			String viewName = null;
			if(StringUtils.containsIgnoreCase(accept, "json")) {
				resp.setContentType("application/json;charset=UTF-8");
				try(
						PrintWriter out = resp.getWriter();
				){
					ObjectMapper mapper = new ObjectMapper();
					mapper.writeValue(out, pagingVO);
				}
			}else {
				req.setAttribute("pagingVO", pagingVO);
				viewName = "prod/prodList";
			}
			return viewName;
		}
	
	@RequestMapping("/prod/prodView.do")
	// 처음부터 prodId가 여기 있다면?
	public String form(@RequestParam(value="what", required=true) String prodId, HttpServletRequest req) throws ServletException, IOException {
//		String prodId = req.getParameter("what");
//		if(StringUtils.isBlank(prodId)) {
//			resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "필수파라미터 누락");
//			return null;
//			// 반복되어 오류 발생함.
//		}
		
		ProdVO prod = service.retrieveProd(prodId);
		req.setAttribute("prod", prod);
		
		return "prod/prodView";
	}
}

