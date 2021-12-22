package kr.or.ddit.board.controller;

import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import kr.or.ddit.board.service.BoardService;
import kr.or.ddit.board.service.BoardServiceImpl;
import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.mvc.annotation.RequestMethod;
import kr.or.ddit.mvc.annotation.resolvers.ModelAttribute;
import kr.or.ddit.mvc.annotation.resolvers.RequestParam;
import kr.or.ddit.mvc.annotation.resolvers.RequestPart;
import kr.or.ddit.mvc.annotation.stereotype.Controller;
import kr.or.ddit.mvc.annotation.stereotype.RequestMapping;
import kr.or.ddit.mvc.fileupload.MultipartFile;
import kr.or.ddit.util.ValidateUtils;
import kr.or.ddit.validate.groups.UpdateGroup;
import kr.or.ddit.vo.AttatchVO;
import kr.or.ddit.vo.BoardVO;

@Controller
public class BoardUpdateController {
	BoardService service = new BoardServiceImpl();
	@RequestMapping("/board/boardUpdate.do")
	public String form(
			@RequestParam("what") int boNo,
			HttpServletRequest req
			) {
		BoardVO board = service.retrieveBoard(boNo);
		req.setAttribute("board", board);
		return "/board/boardEdit";
	}
	
	@RequestMapping(value="/board/boardUpdate.do", method=RequestMethod.POST)
	public String boardUpdate(
			@ModelAttribute("boardVO") BoardVO boardVO, // 커맨드 오브젝트
			@RequestPart(value="boFiles", required=false) MultipartFile[] boFiles,
			HttpServletRequest req
			) {
		boardVO.setBoFiles(boFiles);
		Map<String, List<String>> errors = new LinkedHashMap<>();
		req.setAttribute("errors", errors);
		boolean valid = ValidateUtils.validate(boardVO, errors, UpdateGroup.class);
	
		String viewName = null;
		String message = null;
		req.setAttribute("message", message);
		if(valid) {
			ServiceResult result = service.modifyBoard(boardVO);
			switch (result) {
			case OK:
				viewName ="redirect:/board/boardView.do?what="+boardVO.getBoNo();
				break;
			case INVALIDPASSWORD:
				viewName ="board/boardEdit";
				message = "비밀번호 오류, 다시 시도 하셈";
				break;
			default:
				viewName = "board/boardEdit";
				message = "서버 오류, 잠시뒤 다시 해보셈.";
				break;	
			}
		}else {
			viewName = "board/boardEdit";	
		}
		
		return viewName;
	}
}
