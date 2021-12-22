package kr.or.ddit.board.controller;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import kr.or.ddit.board.service.NoticeService;
import kr.or.ddit.board.vo.NoticeVO;
import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.validate.groups.UpdateGroup;

@Controller
public class NoticeUpdateController {
	@Inject
	private NoticeService service;
	
	@RequestMapping(value="/board/noticeUpdate.do",method=RequestMethod.GET)
	public String NoticeUpdate(@RequestParam("what") int notiNum
			,Model model) {
		NoticeVO not = new NoticeVO();
		not.setNotiNum(notiNum);
		NoticeVO notice = service.retrieveNotice(not);
		
		model.addAttribute("notice",notice);
		String viewName = "board/noticeEdit";
		return viewName;
	}

	@RequestMapping(value="/board/noticeUpdate.do",method=RequestMethod.POST)
	public String NoticeUpdatePost(
			@Validated(UpdateGroup.class) @ModelAttribute("notice") NoticeVO notice,
			BindingResult errors,
			Model model
			) {
		String viewName = null;
		String message = null;
		if(!errors.hasErrors()) {
			ServiceResult result = service.modifyNotice(notice);
			switch(result) {
			case OK:
				viewName = "redirect:/board/noticeView.do?what="+notice.getNotiNum();
				break;
			default:
				viewName = "board/noticeEdit";
				message = "서버 오류";
			}
		}else {
			viewName = "board/noticeEdit";
		}
		model.addAttribute("message", message);
		return viewName;
	}
}
