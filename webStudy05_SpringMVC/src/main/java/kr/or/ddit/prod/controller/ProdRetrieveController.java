package kr.or.ddit.prod.controller;

import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import kr.or.ddit.prod.dao.OthersDAO;
import kr.or.ddit.prod.service.ProdService;
import kr.or.ddit.vo.BuyerVO;
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
@RequestMapping("/prod")
public class ProdRetrieveController {
	@Inject
	private ProdService service ;
	@Inject
	private OthersDAO othersDAO;
	
	@ModelAttribute("lprodList")
	public List<Map<String, Object>> getLprodList(){
		return othersDAO.selectLprodList();
	}
	@ModelAttribute("buyerList")
	public List<BuyerVO> getBuyerList(){
		return othersDAO.selectBuyerList(null);
	}
	
//	private void addAttribute(HttpServletRequest req) {
//		req.setAttribute("lprodList", othersDAO.selectLprodList());
//		req.setAttribute("buyerList", othersDAO.selectBuyerList(null));
//	}
	
	@RequestMapping("prodList.do")
	public String prodList(
			@RequestParam(value="page", required=false, defaultValue="1") int currentPage,
			@ModelAttribute("detailSearch") ProdVO detailSearch,
			Model model
			) {
		PagingVO<ProdVO> pagingVO = prodListForData(currentPage, detailSearch);
		model.addAttribute("pagingVO",pagingVO);
		return "prod/prodList";
	}
	
	@RequestMapping("prodView.do")
	// 처음부터 prodId가 여기 있다면?
	public ModelAndView form(@RequestParam(value="what", required=true) String prodId){
		ProdVO prod = service.retrieveProd(prodId);
		ModelAndView mav = new ModelAndView();
		mav.addObject("prod", prod);       // model.addAttribute("prod", prod); 과 같은 뜻
		mav.setViewName("prod/prodView");
		return mav;
	}
	
	// 뷰가 필요없고 넌 지금 마샬링과 직렬화만 하면 된다는 것을 어댑터에게 알려줌
	@RequestMapping(value="prodList.do", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public PagingVO<ProdVO> prodListForData(
			@RequestParam(value="page", required=false, defaultValue="1") int currentPage,
			@ModelAttribute("detailSearch") ProdVO detailSearch
			){
		PagingVO<ProdVO> pagingVO = new PagingVO<>();
		pagingVO.setCurrentPage(currentPage);
		pagingVO.setDetailSearch(detailSearch);
		
		service.retrieveProdList(pagingVO);
		return pagingVO;
	}
	
}

