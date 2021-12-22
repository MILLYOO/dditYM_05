<%@page import="org.apache.commons.lang3.StringUtils"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>    
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security" %>
<%@ taglib uri="http://www.springframework.org/tags/form"  prefix="form" %>
<jsp:include page="/includee/preScript.jsp"/>
<jsp:include page="/includee/postScript.jsp"/>

<title>HUB_WEB과 함께 하는 슬기로운 물류생활</title>
<c:if test="${not empty SPRING_SECURITY_LAST_EXCEPTION}">
	<script type="text/javascript">
		alert("${SPRING_SECURITY_LAST_EXCEPTION.message}")
	</script>
	<c:remove var="SPRING_SECURITY_LAST_EXCEPTION" scope="session"/>
</c:if>

<!-- Navigation-->
<nav class="mainNavbar navbar-expand-lg text-uppercase fixed-top" id="mainNav">
    <div class="mainContainer">
        <a class="mainNavbar-brand" href="#page-topimage">HUB-WEB</a>
        <div class="mainCollapse" id="navbarResponsive">
            <ul class="navbar-nav ms-auto">
                <li class="nav-item mx-0 mx-lg-1"><a class="mainLi nav-link py-3 px-0 px-lg-3 rounded" href="#Login">로그인</a></li>
            </ul>
        </div>
    </div>
</nav>
        
<!-- Login Section-->
 <section class="page-section login" id="Login"><!-- 섹션만큼 나눠놓은 거  -->
		<div class="row justify-content-center">
            <div class="col-xl-10 col-lg-12 col-md-9">
                <div class="card o-hidden border-0 shadow-lg my-5">
                    <div class="card-body p-0"><!-- 안에 하얀 박스 -->
                        <!-- Nested Row within Card Body -->
                        <div class="row">
                            <div class="col-lg-6 d-none d-lg-block bg-login-image" ></div> <!-- 로그인화면에서 사진들어가는 부분 -->
                            <div class="col-lg-6"><!-- 그 옆에 form -->
                                <div class="p-5">
                                    <div class="text-center">
                                     <h2 style="color:black">로그인</h2>
                                     <br>
                                        <h4>HUB-WEB과 함께 더 스마트해진 물류관리환경을 경험하세요</h4>
                                        <br>
                                    </div>

									<!--사용자가 입력한 데이터를 전송한다. security-context xml 설정 확인할 것 -->
                                    <form:form action="${pageContext.request.contextPath }/login/loginProcess.do" method="post" id="loginForm">
                                        <div class="form-group">
                                            <input type="email" name="memId" class="form-control form-control-user"
                                                id="exampleInputEmail" aria-describedby="emailHelp"
                                                placeholder="Enter Email Address...">
                                        </div>
                                        <div class="form-group">
                                            <input type="password" name="memPass" class="form-control form-control-user"
                                                id="exampleInputPassword" placeholder="Password">
                                        </div>
                                        <div class="form-group">
                                            <div class="custom-control custom-checkbox small">
                                                <input type="checkbox" class="custom-control-input" id="customCheck">
                                                <label class="custom-control-label" for="customCheck">Remember Me</label>
                                                <select id="idSelect">
                                                	<option value>선택</option>
                                                	<option value="info">기초담당자</option>
                                                	<option value="sales">영업담당자</option>
                                                	<option value="buy">구매담당자</option>
                                                	<option value="produce">생산담당자</option>
                                                	<option value="stock">재고담당자</option>
                                                	<option value="b001">관리자</option>
                                                </select>
                                            </div>
                                        </div>
                                        <input type="submit" value="Login" class="btn btn-primary btn-user btn-block" />
                                    </form:form>
                                    	<input type="button" id="faceLoginBtn" value="Face Login" class="btn btn-primary btn-user btn-block" />
                                        <br>
                                    <div class="text-center">
                                        <a class="small" href="forgot-password.html">아이디 찾기</a>
                                    </div>
                                    <div class="text-center">
                                        <a class="small" href="register.html">비밀번호 찾기</a>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
  </section>


      <section class="page-section mainimage" id="page-topimage">
        <header class="text-center">
                <img class="mainimage" src="${cPath}/resources/images/허브웹.png" id="page-topimage" />
                <div id="typing"> 
                <h1 class="typingEffect">HUB-WEB</h1>
                </div>
                <script src="${cPath}/resources/js/scripts.js"></script>
        </header>
      </section>

 

        <!-- Copyright Section-->
        <div class="copyright">
            <div class="container"><small>Copyright &copy; HUB-WEB 202105_302</small></div>
        </div>

        <script src="https://cdn.startbootstrap.com/sb-forms-latest.js"></script>
		<c:if test="${!empty errorMsg}">
			<script>
				toastr.error('${errorMsg}')
			</script>
		</c:if>


<script>
	$("#faceLoginBtn").on("click",function() {
		//팝업띄우기
		var url = "${cPath}/login/faceLoginPopup.do"; //팝업 유알엘
		var name = "popup test"; //팝업 타이틀
		var option = "width = 500, height = 500, top = 100, left = 200, location = no" //팝업 크기
		window.open(url, name, option);
	});
	$("#idSelect").on("change",function(){
		var role = $(this).val();
		console.log(role);
		$("#exampleInputEmail").val(role+"@naver.com");
		$("#exampleInputPassword").val("java");
		$("#loginForm").submit();
	});
</script>


