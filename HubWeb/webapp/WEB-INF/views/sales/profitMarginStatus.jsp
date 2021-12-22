<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>	
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>


<style>
.hubWeb_code1 {
  width: 1590px;
  height: 550px;
}

.row{
	margin-top: 35px;
}

.container{
	margin : 0px;
	padding-left: 0px;
}

</style>

<!-- 서브헤더 -->
<nav class="subHeader">
<div id="subHeader-content">
<h3 id="subHeader-name">매출이익현황</h3>
  	<div class="main-icon">
	  <!-- pdf, excel -->
		<i class="far fa-file-pdf fa-2x"></i>
	    <i class="fas fa-file-import fa-2x"></i>
	    <i class="fas fa-file-export fa-2x"></i>
	</div>
</div>
</nav>



<ul class="nav nav-tabs" id="myTab" role="tablist">
	<li class="nav-item" role="presentation">
		<a class="nav-link active" id="common1-tab" data-bs-toggle="tab" href="#common1" role="tab" aria-controls="common1" aria-selected="true">거래처별</a></li>
	<li class="nav-item" role="presentation">
		<a class="nav-link"	id="common2-tab" data-bs-toggle="tab" href="#common2" role="tab" aria-controls="common2" aria-selected="false">부서별</a></li>
	<li class="nav-item" role="presentation">
		<a class="nav-link"	id="common3-tab" data-bs-toggle="tab" href="#common3" role="tab" aria-controls="common3" aria-selected="false">사원별</a></li>
</ul>

<div id="jspcontent">
<div class="tab-content" id="myTabContent">
	<div class="tab-pane fade show active" id="common1" role="tabpanel" aria-labelledby="common1-tab">
  	<form id="searchFormPC" action="${cPath}/sales/salesRankList.do">
		<input type="date" id="dateStartPC" name="dateStart" />
		<input type="date" id="dateEndPC" name="dateEnd" />&nbsp;
	  	<input type="button" id="selectPC" class="btn btn-outline-primary btn-sm" value="조회"/>
	</form>
		<div class="container">
			<div class="row">
				<div class="col">  <!-- row랑 col이렇게 줘야 표가 세로로 내려가게 sb어쩌고css에 저장해놨음 -->
					<div id="realgrid_masterPC" class="hubWeb_code1"></div>  <!-- .hubWeb_code는 realGrid.css에 사이즈 넣어놨으 -->
				</div>
			</div>
		</div>
	  </div>

	<div class="tab-pane fade" id="common2" role="tabpanel" aria-labelledby="common2-tab">
  	<form id="searchFormDP" action="${cPath}/sales/salesRankList.do">
		<input type="date" id="dateStartDP" name="dateStart" />
		<input type="date" id="dateEndDP" name="dateEnd" />&nbsp;
	  	<input type="button" id="selectDP" class="btn btn-outline-primary btn-sm" value="조회"/>
	</form>
		<div class="container">
			<div class="row">
				<div class="col">
					<div id="realgrid_masterDP" class="hubWeb_code1"></div>
				</div>	
			</div>
		</div>
	</div>
	<div class="tab-pane fade" id="common3" role="tabpanel" aria-labelledby="common3-tab">
  	<form id="searchFormEMP" action="${cPath}/sales/salesRankList.do">
		<input type="date" id="dateStartEMP" name="dateStart" />
		<input type="date" id="dateEndEMP" name="dateEnd" />&nbsp;
	  	<input type="button" id="selectEMP" class="btn btn-outline-primary btn-sm" value="조회"/>
	</form>
		<div class="container">
			<div class="row">
				<div class="col">
					<div id="realgrid_masterEMP" class="hubWeb_code1"></div>
				</div>	
			</div>
		</div>
	</div>
</div>
</div>
 <script src="${cPath}/resources/js/sales/profitmarginstatus.js"></script>