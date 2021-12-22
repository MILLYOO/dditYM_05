package kr.or.ddit.board.controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import kr.or.ddit.board.service.BoardService;
import kr.or.ddit.board.service.BoardServiceImpl;
import kr.or.ddit.vo.AttatchVO;

@Controller
public class DownloadController {
	@Inject
	BoardService boardService;

	
	@RequestMapping("/board/download.do")
	public String download(
			@RequestParam("what") int attNo, Model model
			) throws IOException {
		
		AttatchVO atch = boardService.download(attNo);
		model.addAttribute("atch", atch);
		return "downloadView";
	}
}
