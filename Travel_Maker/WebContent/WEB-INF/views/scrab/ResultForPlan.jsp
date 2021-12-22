<%@page import="tm.scrab.vo.ScrabVO"%>
<%@page import="tm.comm.vo.PagingVO"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	List<ScrabVO> scrabList = (List<ScrabVO>)request.getAttribute("scrabList");
%>

	[
	 <%
		for(int i=0; i<scrabList.size(); i++){
			ScrabVO vo = scrabList.get(i);
			if(i > 0) out.print(",");
			
			System.out.println("리설트 테스트" + vo.getTm_search_name());
	%>
	
			{
				"user_id"		: "<%=vo.getTm_id() %>",
				"scrab_id"		: "<%=vo.getTm_scrab_id() %>",
				"search_id"		: "<%=vo.getTm_search_id() %>",
				"search_name"	: "<%=vo.getTm_search_name() %>",
				"search_addr"	: "<%=vo.getTm_search_addr() %>",
				"search_con"	: "<%=vo.getTm_search_con() %>",
				"img_id"		: "<%=vo.getImg_id() %>"
			}
	<% 	} %>

	]	
