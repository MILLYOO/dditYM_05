<%@page import="tm.comm.vo.BoardVO"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>


    <%
    	// 서블릿에서 저장된 값 가져오기
    	List<BoardVO> list = (List<BoardVO>)request.getAttribute("selectAll");
    %>
    [
    <%
    	for(int i = 0; i < list.size(); i++){
    		BoardVO vo = list.get(i);
    		if(i > 0) out.print(",");
    		%>
    		{
    		
    			"TM_B_NO" : "<%= vo.getTm_b_no() %>",
    			"TM_B_TITLE" : "<%= vo.getTm_b_title() %>",
    			"TM_ID" : "<%= vo.getTm_id() %>",
    			"TM_B_CONTENT" : "<%= vo.getTm_b_content() %>",
    			"TM_B_HIT" : "<%= vo.getTm_b_hit() %>",
    			"TM_B_DATE" : "<%= vo.getTm_b_date() %>",
    			"TM_B_OX" : "<%= vo.getTm_b_ox() %>",
    			"TM_BIMG_ID" : "<%= vo.getTm_bimg_id() %>",
    			"TM_B_MODATE" : "<%= vo.getTm_b_modate() %>"
    			
    		}
    		<%		
    	}
    %>
    ]
    
    