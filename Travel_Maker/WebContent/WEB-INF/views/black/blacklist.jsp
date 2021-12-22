<%@page import="tm.comm.vo.PagingVO"%>
<%@page import="tm.black.vo.BlackMemVO"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
	<%
	List<BlackMemVO> blackList = (List<BlackMemVO>)request.getAttribute("blackList");
	PagingVO pagingVO = (PagingVO)request.getAttribute("pagingVO");
	%>

{
	"startPage"	: "<%=pagingVO.getFirstPageNo() %>",
	"endPage"	: "<%=pagingVO.getLastPageNo() %>",
	"totalPage"	: "<%=pagingVO.getTotalCount() %>",
	"datas"		: [
	<%
		for(int i=0; i<blackList.size(); i++){
			BlackMemVO vo = blackList.get(i);
			if(i > 0) out.print(",");
	%>
			{
				"id"		: "<%=vo.getTm_id() %>",
				"rs"		: "<%=vo.getTm_bl_rs() %>",
				"date"		: "<%=vo.getTm_bl_date() %>",
				"admn"		: "<%=vo.getTm_bl_admn() %>"
			}
	<% 	} %>
	
				  ]
		
}
