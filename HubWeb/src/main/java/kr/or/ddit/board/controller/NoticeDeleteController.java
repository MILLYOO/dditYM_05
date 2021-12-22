package kr.or.ddit.board.controller;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import kr.or.ddit.board.service.NoticeService;
import kr.or.ddit.board.vo.NoticeVO;
import kr.or.ddit.enumpkg.ServiceResult;

@Controller
public class NoticeDeleteController {
	@Inject
	private NoticeService service;
	
	@RequestMapping(value="/board/noticeDelete.do",method=RequestMethod.POST)
	public String NoticeDelete(
			@RequestParam("notiNum") int notiNum,
			RedirectAttributes redirectAttributes
			) {
		
		NoticeVO notice = new NoticeVO();
		notice.setNotiNum(notiNum);
		ServiceResult result = service.removeNotice(notice);
		
		String message = null;
		String viewName = null;
		switch (result) {
		case OK:
			viewName = "redirect:/board/noticeList.do";
			break;
		default:
			// 유지 할 데이터 없으므로 redirect
			viewName = "redirect:/board/noticeView.do?what="+notice.getNotiNum();
			message = "서버 오류";
			break;
		}
		redirectAttributes.addAttribute("message", message);
		return viewName;
	}
}
