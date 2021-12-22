package kr.or.ddit.board.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;

import com.fasterxml.jackson.databind.ObjectMapper;

import kr.or.ddit.board.service.BoardService;
import kr.or.ddit.board.service.BoardServiceImpl;
import kr.or.ddit.mvc.annotation.resolvers.ModelAttribute;
import kr.or.ddit.mvc.annotation.resolvers.RequestHeader;
import kr.or.ddit.mvc.annotation.resolvers.RequestParam;
import kr.or.ddit.mvc.annotation.stereotype.Controller;
import kr.or.ddit.mvc.annotation.stereotype.RequestMapping;
import kr.or.ddit.vo.BoardVO;
import kr.or.ddit.vo.PagingVO;
import kr.or.ddit.vo.SearchVO;
import lombok.extern.slf4j.Slf4j;

// /board/boardView.do?what=34
@Controller
@Slf4j
public class BoardRetrieveController {
	private BoardService service = new BoardServiceImpl();
	
	@RequestMapping("/board/boardList.do")
	public String boardList(@RequestParam(value="page", required=false, defaultValue="1") int currentPage
			, @ModelAttribute("searchVO") SearchVO searchVO
			, @RequestHeader(value="accept", required=false, defaultValue="html") String accept
			, HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		PagingVO<BoardVO> pagingVO = new PagingVO<>();
		pagingVO.setCurrentPage(currentPage);
		pagingVO.setSearchVO(searchVO);
		service.retrieveBoardList(pagingVO);
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
			viewName = "board/boardList";
		}
		return viewName;
	}
	
	@RequestMapping("/board/boardView.do")
	public String boardSelect(@RequestParam("what") int boNo, HttpServletRequest req ) {
		BoardVO board = service.retrieveBoard(boNo);
		req.setAttribute("board", board);
		log.info(":::::::::::::{}", board);
		return "board/boardView";
	}
	
}
