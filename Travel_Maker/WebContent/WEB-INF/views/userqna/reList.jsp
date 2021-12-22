<%@page import="tm.comm.vo.ReplyVO"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	List<ReplyVO> list = (List<ReplyVO>)request.getAttribute("list");
%>

[
	<%
	for(int i=0; i<list.size(); i++){
		ReplyVO vo = list.get(i);
		if(i > 0) out.print(",");
	%>
	{
		"TM_B_NO"  		: "<%= vo.getTm_b_no() %>",
		"TM_BC_NO" 		: "<%=vo.getTm_bc_no() %>",
		"TM_ID" 		: "<%=vo.getTm_id() %>",
		"TM_BC_CONTENT" : "<%=vo.getTm_bc_content() %>",
		"TM_BC_DATE"	: "<%=vo.getTm_bc_date() %>",
		"TM_BC_MODATE"	: "<%=vo.getTm_bc_modate() %>"
	}

<%
	}
%>
]