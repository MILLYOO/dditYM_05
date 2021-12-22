package kr.or.ddit.board.controller;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import kr.or.ddit.board.service.NoticeService;
import kr.or.ddit.board.vo.NoticeVO;
import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.validate.groups.InsertGroup;

@Controller
public class NoticeInsertController {
	@Inject
	private NoticeService service;
	
	@RequestMapping(value="/board/noticeInsert.do",method=RequestMethod.GET)
	public String noticeInsertForm(
				@ModelAttribute("notice") NoticeVO notice
			) {
		return "board/noticeForm";
	}
	
	@RequestMapping(value="/board/noticeInsert.do",method=RequestMethod.POST)
	public String noticeInsert(
			@Validated(InsertGroup.class) @ModelAttribute("notice") NoticeVO notice
			, BindingResult errors
			, Model model
			) {
		
		String viewName = null;
		String message = null;
		if(!errors.hasErrors()) {
			
			ServiceResult result = service.createNotice(notice);
			switch(result) {
			case OK:
				viewName = "redirect:/board/noticeView.do?what="+notice.getNotiNum();
				break;
			default:
				viewName = "board/noticeForm";
				message = "서버 오류";
			}
		}else {
			viewName = "board/noticeForm";
		}
		model.addAttribute("message", message);
		
		return viewName;
	}
}
