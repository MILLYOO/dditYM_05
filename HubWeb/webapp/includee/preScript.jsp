<%@ page import="org.apache.commons.lang3.StringUtils"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
<script src="${pageContext.request.contextPath}/resources/js/jquery-3.6.0.min.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/jquery.form.min.js"></script>

<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<!-- 부트스트랩 -->
   <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>
   
   <!-- 메뉴 폰트 -->
    <link href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i"
          rel="stylesheet">
    <!-- css경로 -->
    <link rel="stylesheet" href="${cPath}/resources/css/sb-admin-2.css">
    
    <!-- 폰트어썸아이콘 갖다붙이는 link -->
    <script src="https://use.fontawesome.com/releases/v5.2.0/js/all.js"></script>
    
    <!-- realgrid -->
    <link href="${cPath}/resources/js/realgrid/realgrid-style.css" rel="stylesheet"/>
    <link href="${cPath}/resources/css/hubweb_realgrid.css" rel="stylesheet"/>
	<script type="text/javascript" src="${cPath}/resources/js/realgrid/realgrid-lic.js"></script>
	<script src="${cPath}/resources/js/realgrid/realgrid.min.js"></script>
    <script src="${cPath}/resources/js/realgrid/libs/jszip.min.js"></script>
    
	<!-- 경고창, confirm 모달 -->
	<script src="https://cdn.jsdelivr.net/npm/sweetalert2@10"></script>

	<!-- Toastr --> 
	<link rel="stylesheet" href="${cPath }/resources/css/toastr.min.css">
<!-- 	 SweetAlert2 --> 
<%-- 	<link rel="stylesheet" href="${cPath }/resources/css/bootstrap-4.min.css"> --%>
	<!-- SweetAlert2 -->
	<script src="${cPath }/resources/js/sweetalert2.min.js"></script>
	<!-- Toastr --> 
	<script src="${cPath }/resources/js/toastr.min.js"></script>

	<!-- dateRangePicker -->
	<script src="${cPath }/resources/js/daterangepicker/moment.min.js"></script>
	<script src="${cPath }/resources/js/daterangepicker/daterangepicker.js"></script>
	<link rel="stylesheet" type="text/css" href="${cPath }/resources/js/daterangepicker/daterangepicker.css" />
	<!-- inputMask -->
	<script	src="${cPath }/resources/js/Inputmask/dist/jquery.inputmask.min.js"></script>
	<!-- Daum우편API -->
	<script	src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
	
<script>
	$.getContextPath=function(){
		return "${cPath}";
	}
	
    toastr.options = {
    		  "positionClass": "toast-bottom-right",
    		  "preventDuplicates" : true
    };
</script>
<script type="text/javascript">
$(document).ready(function(){
        $(".loading").fadeOut();
    });
</script>
<c:if test="${not empty message} ">
	<script>
		alert("${message}");
	</script>
	<c:remove var="message" scope="session"/>
</c:if>


