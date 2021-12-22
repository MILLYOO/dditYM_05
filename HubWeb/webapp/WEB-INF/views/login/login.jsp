<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>   
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form"  prefix="form" %>

 <link rel="stylesheet" href="${cPath}/resources/css/sb-admin-2.css">

<div class="jspcontent">
	<div class="row justify-content-center">
	    <div class="col-xl-10 col-lg-12 col-md-9">
	        <div class="card o-hidden border-0 shadow-lg my-5">
	            <div class="card-body p-0">
	                <div class="row">
	                    <div class="col-lg-6 d-none d-lg-block bg-login-image" ></div>
	                    <div class="col-lg-6">
	                        <div class="p-5">
	                            <div class="text-center">
	                                <h1 class="h4 text-gray-900 mb-4">HUB-WEB</h1>
	                                	물류관리를 쉽고 빠르고 자신있게! HUB-WEB과 함께 하세요<br><br>
	                            </div>
	                             <form:form action="${pageContext.request.contextPath }/login/login.do" method="post">
	                                 <div class="form-group">
	                                     <input type="email" class="form-control form-control-user"
	                                         id="exampleInputEmail" aria-describedby="emailHelp"
	                                         placeholder="Enter Email Address...">
	                                 </div>
	                                 <div class="form-group">
	                                     <input type="password" class="form-control form-control-user"
	                                         id="exampleInputPassword" placeholder="Password">
	                                 </div>
	                             </form:form>
	                                <div class="form-group">
	                                    <div class="custom-control custom-checkbox small">
	                                        <input type="checkbox" class="custom-control-input" id="customCheck">
	                                        <label class="custom-control-label" for="customCheck">Remember Me</label>
	                                    </div>
	                                </div>
	                                <a href="index.html" class="btn btn-primary btn-user btn-block">
	                                    Login
	                                </a>
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
</div>