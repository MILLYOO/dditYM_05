<%@page import="tm.search.vo.SearchVO"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	List<SearchVO> list = (List<SearchVO>)request.getAttribute("list");
%>

[
<%
	for(int i = 0; i < list.size(); i++){
		if(i > 0) out.print(",");
%>
		{
			"id" : "<%=list.get(i).getTm_search_id() %>",
			"cate" : "<%=list.get(i).getTm_search_cate() %>",
			"name" : "<%=list.get(i).getTm_search_name() %>",
			"addr" : "<%=list.get(i).getTm_search_addr().substring(0, 2) %>",
			"x" : "<%=list.get(i).getTm_search_x() %>",
			"y" : "<%=list.get(i).getTm_search_y() %>",
			"con" : "<%=list.get(i).getTm_search_con() %>",
			"tel" : "<%=list.get(i).getTm_search_tel() %>",
			"hit" : "<%=list.get(i).getTm_search_hit() %>",
			"imgId" : "<%=list.get(i).getImg_id() %>",
			"like" : "<%=list.get(i).getTm_search_like() %>"
		}
<%
	}
%>

]