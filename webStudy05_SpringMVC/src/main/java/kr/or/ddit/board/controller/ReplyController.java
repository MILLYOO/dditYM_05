package kr.or.ddit.board.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.fasterxml.jackson.databind.ObjectMapper;

import kr.or.ddit.board.service.ReplyService;
import kr.or.ddit.board.service.ReplyServiceImpl;
import kr.or.ddit.vo.PagingVO;
import kr.or.ddit.vo.ReplyVO;
import lombok.extern.slf4j.Slf4j;

// /reply/replyList.do
// /reply/replyInsert.do
// /reply/replyUpdate.do
// /reply/replyDelete.do

@Controller
@Slf4j
public class ReplyController {
	ReplyService service = new ReplyServiceImpl();
	
	
	@RequestMapping("/reply/replyList.do")
	public String replyList(
		@RequestParam("what") int boNo,
		@RequestParam(value="page", required=false, defaultValue="1") int currentPage,
		@RequestHeader(value="accept", required=false, defaultValue="html") String accept,
		HttpServletRequest req,
		HttpServletResponse resp
		) throws IOException {
		PagingVO<ReplyVO> pagingVO = new PagingVO<>(5,5);
		pagingVO.setCurrentPage(currentPage);
		
		ReplyVO detailSearch = new ReplyVO();
		detailSearch.setBoNo(boNo);
		pagingVO.setDetailSearch(detailSearch);
		
		service.retrieveReplyList(pagingVO);
		
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
			viewName = "board/boardView?What="+boNo;
		}
		return viewName;
	}
	
	@RequestMapping("/reply/replyInsert.do")
	public String replyInsert(
			) {
		String viewName = null;
		String messgae = null;
		return viewName;
	}
	
	@RequestMapping("/reply/replyUpdate.do")
	public String replyUpdate() {
		String viewName = null;
		String messgae = null;
		return viewName;
	}
	
	@RequestMapping("/reply/replyDelete.do")
	public String replyDelete() {
		String viewName = null;
		String messgae = null;
		return viewName;
	}
}
