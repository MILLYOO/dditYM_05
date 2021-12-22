<%@page import="tm.comm.vo.PagingVO"%>
<%@page import="tm.comm.vo.BoardVO"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%
	List<BoardVO> list = (List<BoardVO>)request.getAttribute("list");
%>
[
<%
	int listSize = list.size();
	if(listSize > 0){
		for(int i = 0; i < listSize; i++){
			if(i > 0) out.print(",");
%>
{
	"no" : "<%=list.get(i).getTm_b_no() %>",
	"title" : "<%=list.get(i).getTm_b_title() %>",
	"writer" : "<%=list.get(i).getTm_id() %>",
<%
	if(list.get(i).getTm_b_modate() != null){
%>
	"date" : "<%= list.get(i).getTm_b_modate()%>",
<%
	}else{
%>
	"date" : "<%= list.get(i).getTm_b_date()%>",
<%
	}
%>
	"hit" : "<%=list.get(i).getTm_b_hit() %>"
}
<%
		}
	}
%>
]