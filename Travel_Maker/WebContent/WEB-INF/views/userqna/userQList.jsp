<%@page import="tm.comm.vo.BoardVO"%>
<%@page import="java.util.List"%>
<%@page import="tm.comm.vo.PagingVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	PagingVO pagingVO = (PagingVO)request.getAttribute("pagingVO");
	List<BoardVO> boardList = (List<BoardVO>)request.getAttribute("boardList");
%>

{
	"startPage"	: "<%= pagingVO.getFirstPageNo() %>",
	"endPage"	: "<%= pagingVO.getLastPageNo() %>",
	"totalPage"	: "<%= pagingVO.getTotalCount() %>",
	"datas"		: [
	<% 
		for(int i=0; i<boardList.size(); i++){
			BoardVO vo = boardList.get(i);
			if(i > 0) out.print(",");
	%>
			{
				"bonum"	: "<%= vo.getTm_b_no() %>",
				"title"	: "<%= vo.getTm_b_title() %>",
				"date"	: "<%= vo.getTm_b_date() %>",
				"id"	: "<%= vo.getTm_id() %>"
			}
	<% 
		}
	%>
				  ]
}