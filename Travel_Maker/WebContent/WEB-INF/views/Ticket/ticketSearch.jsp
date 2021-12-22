<%@page import="tm.comm.vo.ProdVO"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	List<ProdVO> list = (List<ProdVO>)request.getAttribute("list");
%>
[
<%
	for(int i = 0; i < list.size(); i++){
		if(i > 0) out.print(",");
%>
{
	"id" : "<%=list.get(i).getTm_prod_id()%>",
	"com" : "<%=list.get(i).getTm_prod_comp() %>",
	"name" : "<%=list.get(i).getTm_prod_name() %>",
	"price" : "<%=list.get(i).getTm_prod_price() %>"
}
<%
	}
%>
]