<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>	
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<link rel="stylesheet" href="${cPath}/resources/css/hubweb_realgrid.css">
<div id="jspcontent">
<h3>생산지시대비출고현황</h3>

	<ul class="nav nav-tabs" id="myTab" role="tablist">
		<li class="nav-item" role="presentation">
			<a class="nav-link active" id="byInstNum" data-bs-toggle="tab" href="#byInstNumDiv" role="tab" aria-controls="byInstNumDiv" aria-selected="true">지시번호별</a>
		</li>
		<li class="nav-item" role="presentation">
			<a class="nav-link"	id="byDate" data-bs-toggle="tab" href="#byDateDiv" role="tab" aria-controls="byDateDiv" aria-selected="false">일자별</a>
		</li>
		<li class="nav-item" role="presentation">
			<a class="nav-link"	id="byItem" data-bs-toggle="tab" href="#byItemDiv" role="tab" aria-controls="byItemDiv" aria-selected="false">품목별</a>
		</li>
	</ul>

	<div class="tab-content" id="myTabContent">
	
		<div class="tab-pane fade show active" id="byInstNumDiv" role="tabpanel" aria-labelledby="byInstNum">
		<div class="container">
		  	<form:form id="searchForm" action="${cPath }/produce/컨트롤러.do" method="post">
			  	<input type="date" name="dateStart" id="sDateInstNum" />
			  	<input type="date" name="dateEnd" id="eDateInstNum" />
	  		  	공정<input type="text" name="kcommName" id="kNameInstNum" />
			  	품목<input type="text" name="prodName" id="pNameInstNum" />
			  	<input class="btn btn-outline-primary btn-sm" type="button" id="selectInstNum" value="조회"/>
			</form:form>
			<div class="row">
				<div id="realgrid_byInstNumMaster" class="hubWeb_standard"></div>
				<div id="realgrid_byInstNumDetail" class="hubWeb_standard"></div>
			</div>
		</div>
		</div>
		<div class="tab-pane fade show active" id="byDateDiv" role="tabpanel" aria-labelledby="byDate">
		<div class="container">
		  	<input type="date" name="dateStart" id="sDateByDate" />
		  	<input type="date" name="dateEnd" id="eDateByDate" />
  		  	공정<input type="text" name="kcommName" id="kNameDate" />
		  	품목<input type="text" name="prodName" id="pNameDate" />
		  	<input class="btn btn-outline-primary btn-sm" type="button" id="selectByDate" value="조회"/>
			<div class="row">
				<div class="col">
					<div id="realgrid_byDateMaster" class="hubWeb_standard"></div>
				</div>
				<div class="col">
					<div id="realgrid_byDateDetail" class="hubWeb_standard"></div>
				</div>
			</div>
		</div>
		</div>
		<div class="tab-pane fade show active" id="byItemDiv" role="tabpanel" aria-labelledby="byItem">
		<div class="container">
			<input type="date" name="dateStart" id="sDateByItem" />
		  	<input type="date" name="dateEnd" id="eDateByItem" />
	 		공정<input type="text" name="kcommName" id="kNameItem" />
		  	품목<input type="text" name="prodName" id="pNameItem" />
		  	<input class="btn btn-outline-primary btn-sm" type="button" id="selectByItem" value="조회"/>
		  	<div class="row">
				<div id="realgrid_byItemMaster" class="hubWeb_standard"></div>
				<div id="realgrid_byItemDetail" class="hubWeb_standard"></div>
			</div>
		</div>
		</div>
		
	</div>
	
</div>