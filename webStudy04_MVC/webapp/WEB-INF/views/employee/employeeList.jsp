<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<jsp:include page="/includee/preScript.jsp" />
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/resources/js/DataTables/datatables.min.css"/>
<script type="text/javascript" src="${pageContext.request.contextPath }/resources/js/DataTables/datatables.min.js"></script>
</head>
<body>
<table id="example" class="display" style="width:100%">
        <thead>
            <tr>
                <th>직원아이디</th>
                <th>성</th>
                <th>이름</th>
                <th>폰넘버</th>
                <th>상사아이디</th>
                <th>부서명</th>
                <th>고용일</th>
                <th>부하직원수</th>
            </tr>
        </thead>
        <tfoot>
        </tfoot>
    </table>
    <script type="text/javascript">
    $.fn.dataTable.ext.errMode = 'none';
    $("#example").DataTable({
    	   "ajax": {
    		   "url": "${pageContext.request.contextPath }/emp/employeeList_FT.do",
               "dataSrc": "dataList"
    	   },
   	    "columns": [
            { "data": "employeeId" },
            { "data": "firstName" },
            { "data": "lastName" },
            { "data": "phoneNumber" },
            { "data": "managerId" },
            { "data": "department.departmentName"},
            { "data": "hireDate" },
            { "data": "childCount" }
        ]
    });
    </script>
<jsp:include page="/includee/postScript.jsp" />
</body>
</html>