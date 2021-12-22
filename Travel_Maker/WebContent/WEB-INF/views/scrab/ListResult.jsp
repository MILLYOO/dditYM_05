<%@page import="tm.scrab.vo.ScrabVO"%>
<%@page import="tm.comm.vo.PagingVO"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	List<String> imgList = (List<String>) request.getAttribute("imgList");
	List<ScrabVO> scrabList = (List<ScrabVO>)request.getAttribute("scrabList");
	ScrabVO scVO = (ScrabVO)request.getAttribute("scVO");
%>

{
	"startPage"	: "<%=scVO.getFirstPageNo() %>",
	"endPage"	: "<%=scVO.getLastPageNo() %>",
	"totalPage"	: "<%=scVO.getTotalCount() %>",
	"datas"		: [
	<%
		for(int i=0; i<scrabList.size(); i++){
			ScrabVO vo = scrabList.get(i);
			if(i > 0) out.print(",");
	%>
			{
				"user_id"		: "<%=vo.getTm_id() %>",
				"scrab_id"		: "<%=vo.getTm_scrab_id() %>",
				"search_id"		: "<%=vo.getTm_search_id() %>",
				
				"search_name"	: "<%=vo.getTm_search_name() %>",
				"search_addr"	: "<%=vo.getTm_search_addr() %>",
				"img_id"		: "<%=vo.getImg_id() %>",
				"search_tel"	: "<%=vo.getTm_search_tel() %>",
				"search_cate"	: "<%=vo.getTm_search_cate() %>",
				"search_con"	: "<%=vo.getTm_search_con() %>",
				"img_name" 		: "<%=imgList.get(i) %>"
			}
	<% 	} %>
	
				  ]
		
}