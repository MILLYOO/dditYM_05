package kr.or.ddit.board.controller;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import kr.or.ddit.board.service.BoardService;
import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.validate.groups.UpdateGroup;
import kr.or.ddit.vo.BoardVO;

@Controller
@RequestMapping("/board")
public class BoardUpdateController {
	@Inject
	BoardService service;

	@RequestMapping("boardUpdate.do")
	public String form(@RequestParam("what") int boNo, Model model) {
		BoardVO board = service.retrieveBoard(boNo);
		model.addAttribute("board", board);
		return "/board/boardEdit";
	}

	@RequestMapping(value = "/board/boardUpdate.do", method = RequestMethod.POST)
	public String boardUpdate(@Validated(UpdateGroup.class) @ModelAttribute("boardVO") BoardVO boardVO, // 커맨드 오브젝트
			Errors errors, Model model) {
		String viewName = null;
		String message = null;
		if (!errors.hasErrors()) {
			ServiceResult result = service.modifyBoard(boardVO);
			switch (result) {
			case OK:
				viewName = "redirect:/board/boardView.do?what=" + boardVO.getBoNo();
				break;
			case INVALIDPASSWORD:
				viewName = "board/boardEdit";
				message = "비밀번호 오류, 다시 시도 하셈";
				break;
			default:
				viewName = "board/boardEdit";
				message = "서버 오류, 잠시뒤 다시 해보셈.";
				break;
			}
		} else {
			viewName = "board/boardEdit";
		}
		model.addAttribute("message", message);

		return viewName;
	}
}
