package kr.or.ddit.board.controller;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.or.ddit.board.service.NoticeService;
import kr.or.ddit.board.vo.NoticeVO;
import kr.or.ddit.board.vo.PagingVO;
import kr.or.ddit.board.vo.SearchVO;

@Controller
public class NoticeRetrieveController {

	@Inject
	private NoticeService service;
	
	@RequestMapping(value="/board/noticeList.do",method=RequestMethod.GET)
	public String NoticeRetrieveList(
			@RequestParam(value="page", required=false, defaultValue="1") int currentPage
			, @ModelAttribute("searchVO") SearchVO searchVO
			, Model model
			) {
		PagingVO<NoticeVO> pagingVO = new PagingVO<>();
		pagingVO.setCurrentPage(currentPage);
		pagingVO.setSearchVO(searchVO);
		service.retrieveNoticeList(pagingVO);
		model.addAttribute("pagingVO", pagingVO);
		String viewName = "board/noticeList";
		return viewName;
	}

	@ResponseBody
	@RequestMapping(value="/board/noticeListMain.do",method=RequestMethod.GET)
	public List<NoticeVO> NoticeRetrieveListMain() {
		return service.retrieveNoticeListMain();
	}
	
	
	

	@RequestMapping(value="/board/noticeView.do",method=RequestMethod.GET)
	public String NoticeRetrieve(
			@RequestParam("what") int notiNum
			, Model model
			) {
		NoticeVO no = new NoticeVO();
		no.setNotiNum(notiNum);
		NoticeVO notice = service.retrieveNotice(no);
		model.addAttribute("notice", notice);
		String viewName = "board/noticeView";
		return viewName;
	}
}
