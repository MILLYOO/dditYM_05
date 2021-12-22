<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!-- 서브헤더 -->
<nav class="subHeader">
<div id="subHeader-content">
<h3 id="subHeader-name">매입순위표</h3>
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
<style>
.container{
	margin : 0px;
	max-width: 100%;
	
}	
.col{
	width : 1000px;
}

</style>
<div id="jspcontent">

<!-- 거래처별 -->
<div class="tab-content" id="myTabContent">
	<div class="tab-pane fade show active" id="common1" role="tabpanel" aria-labelledby="common1-tab">
  	<form id="searchFormPC" action="${cPath}/buy/purchRankListPC.do">
  	<fieldset>
			<div class="searchDate"> 
				<input type="date" class="dateStart" name="dateStart" />
				<input type="date" class="dateEnd" name="dateEnd" />&nbsp;
			</div>
		  	<input type="button" id="selectPC" class="btn btn-outline-primary btn-sm" value="조회"/>
	</fieldset>
	</form>
		<br>
		<div class="container">
			<div class="row">
				<div class="col">  <!-- row랑 col이렇게 줘야 표가 세로로 내려가게 sb어쩌고css에 저장해놨음 -->
					<span class="rightAlign">단위 : (원)</span>
					<div id="realgrid_masterPC" class="hubWeb_code1"></div>  <!-- .hubWeb_code는 realGrid.css에 사이즈 넣어놨으 -->
				</div>
				<div class="col">	
					<span class="rightAlign">단위 : (원)</span>
					<div id="realgrid_detailPC" class="hubWeb_code1"></div>
				</div>
			</div>
		</div>
	  </div>

<!-- 부서별 -->	  
	<div class="tab-pane fade" id="common2" role="tabpanel" aria-labelledby="common2-tab">
	<form id="searchFormDP" action="${cPath}/buy/purchRankListDP.do">
		<input type="date" class="dateStart" name="dateStart" />
		<input type="date" class="dateEnd" name="dateEnd" />&nbsp;
	  	<input type="button" id="selectDP" class="btn btn-outline-primary btn-sm" value="조회"/>
	</form>
		<br>
		<div class="container">
			<div class="row">
				<div class="col">
					<span class="rightAlign">단위 : (원)</span>
					<div id="realgrid_masterDP" class="hubWeb_code1"></div>
				</div>	
				<div class="col">	
					<span class="rightAlign">단위 : (원)</span>			
					<div id="realgrid_detailDP" class="hubWeb_code1"></div>
				</div>
			</div>
		</div>
	</div>
	
<!-- 사원별 -->	
	<div class="tab-pane fade" id="common3" role="tabpanel" aria-labelledby="common3-tab">
	<form id="searchFormEMP" action="${cPath}/buy/purchRankListEMP.do">
		<input type="date" class="dateStart" name="dateStart" />
		<input type="date" class="dateEnd" name="dateEnd" />&nbsp;
	  	<input type="button" id="selectEMP" class="btn btn-outline-primary btn-sm" value="조회"/>
	</form>
		<br>
		<div class="container">
			<div class="row">
				<div class="col">
					<span class="rightAlign">단위 : (원)</span>
					<div id="realgrid_masterEMP" class="hubWeb_code1"></div>
				</div>	
				<div class="col">
					<span class="rightAlign">단위 : (원)</span>
					<div id="realgrid_detailEMP" class="hubWeb_code1"></div>
				</div>
			</div>
		</div>
	</div>
</div>
</div>
 <script src="${cPath}/resources/js/buy/purchaseleaderboard.js"></script>
<%--  <script src="${cPath}/resources/js/custom/hubweb_managecommon.js"></script> --%>
<script>
 masterPCGrid.header.height = 50;
</script>
 <script>
 detailPCGrid.header.height = 50;
</script>
 <script>
 masterDPGrid.header.height = 50;
</script>
 <script>
 detailDPGrid.header.height = 50;
</script>
 <script>
 masterEMPGrid.header.height = 50;
</script>
 <script>
 detailEMPGrid.header.height = 50;
</script>


