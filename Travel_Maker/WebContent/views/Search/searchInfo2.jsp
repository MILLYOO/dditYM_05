<%@page import="tm.search.vo.SearchVO"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
 // 서블릿에서 수행결과 가져오기
 SearchVO vo = (SearchVO)request.getAttribute("vo");

%>

   {
      "tm_search_id" : "<%= vo.getTm_search_id() %>",
      "tm_search_cate" : "<%= vo.getTm_search_cate() %>",
      "tm_search_name" : "<%= vo.getTm_search_name() %>",
      "tm_search_addr" : "<%= vo.getTm_search_addr() %>",
      "tm_search_x" : "<%= vo.getTm_search_x() %>",
      "tm_search_y" : "<%= vo.getTm_search_y() %>",
      "tm_search_con" : "<%= vo.getTm_search_con() %>",
      "tm_search_tel" : "<%= vo.getTm_search_tel() %>",
      "img_id" : "<%= vo.getImg_id() %>"
   }   
    
<%
 
%>