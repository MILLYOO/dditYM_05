package kr.or.ddit.board.controller;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import kr.or.ddit.board.service.NoticeService;
import kr.or.ddit.board.vo.AttatchVO;

@Controller
public class NoticeAttatchDownloadController {
	@Inject
	private NoticeService service;
	
	@RequestMapping("/board/noticeAttatchDownload.do")
	public String NoticeAttatchDownload(@RequestParam("what") int attNo, Model model) {
		AttatchVO atcVO = new AttatchVO();
		atcVO.setAttNo(attNo);
		AttatchVO atch = service.download(atcVO);
		model.addAttribute("atch", atch);
		String viewName = "downloadView";
		return viewName;
	}
}
