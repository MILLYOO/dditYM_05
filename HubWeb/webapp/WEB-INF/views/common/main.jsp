<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security"%>

<!-- 풀캘린더 -->
<link href="${cPath }/resources/fullCalendar/main.css" rel="stylesheet" />
<script src="${cPath }/resources/fullCalendar/main.js"></script>

<!-- 하이차트 Script-->	
<script src="https://code.highcharts.com/highcharts.js"></script>
<script src="https://code.highcharts.com/modules/exporting.js"></script>
<script src="https://code.highcharts.com/modules/accessibility.js"></script>
<script src="https://code.highcharts.com/modules/export-data.js"></script>

<!-- css -->
<link rel="stylesheet" href="${cPath}/resources/css/hubweb_realgrid.css">
<link rel="stylesheet" href="${cPath}/resources/css/mainHighChart.css">

<!-- js -->
<script src="${cPath}/resources/js/custom/hubweb_statuscommon.js"></script>

<img src="${cPath }/resources/images/mainBackImg2.png" style="width:1664px;height:897px;z-index:-120;position: absolute;opacity: 0.5" />

<div id="main-left">
	<div id="main-notice">
		<h4 id="mainH4">공지사항</h4>
		<span id="moreBtn">more</span>
		<table class="table table-hover">
			<tbody id="listBody"></tbody>
		</table>
	</div>
	<div id="calContainer">
		<div id="calendar"></div>
	</div>
</div>
<!-- 차트 뷰부분 -->
<div id="main-right">
	<div id="highChartDiv">
		<!-- 품목별매출차트	-->
		<figure class="highcharts-figure2">
		  <div id="container2"></div>
		</figure>
		<!-- 품목별매입차트 -->
		<figure class="highcharts-figure1">
		  <div id="container1"></div>
		</figure>
		<!-- 사원별매출현황차트 -->
		<figure class="highcharts-figure3">
		 	<div id="container3"></div>
		</figure>
	</div>
</div>

<div id="scheduleInsert-modal" class="modal-overlay modal">
	<div class="modal-scode">
		<div class="title">일정</div>
 	 	<security:authorize access="hasRole('ROLE_ADMIN')">
			<img src="${cPath }/resources/images/deleteIcon.png" id="del"/>
		</security:authorize>
		<div class="close-area">X</div>
		<div class="content">
			<form:form id="scheduleForm" method="post">
			  	<input type="hidden" name="scNo" id="scNo" />
			  	<input type="date" name="scStart" id="scStart" 	/> ~ 
		 	 	<input type="date" name="scEnd" id="scEnd" />
		 	 	<input type="text" name="scTitle" id="scTitle" placeholder="내용" />
		 	 	<security:authorize access="hasRole('ROLE_ADMIN')">
			 	 	<input class="btn btn-secondary btn-sm" type="button" id="saveSchedule" value="저장"/>
		 	 	</security:authorize>
			</form:form>
		</div>
	</div>
</div>


<script>

$(document).ready(function(){
	calStart();
	$('.fc-scroller-liquid-absolute').removeAttr('style');
	$('.fc-scroller').removeAttr('style');
	// 공지
	let listBody = $("#listBody");
	let trTags = [];
	$.ajax({
		url : $.getContextPath()+"/board/noticeListMain.do",
		dataType : "json",
		success : function(resp) {
			$.each(resp,function(idx,notice){
				let trTag = $("<tr>").append(
							$("<td>").html(notice.rnum),		
							$("<td>").html($("<a>").text(notice.notiTitle).attr({href:"${cPath}/board/noticeView.do?what="+notice.notiNum})),	
							$("<td>").html(notice.notiWriter),		
							$("<td>").html(notice.notiDate),		
							$("<td>").html(notice.notiHits),		
				);
				trTags.push(trTag);
			});	
			listBody.append(trTags);
		}
	});
});

$("#moreBtn").on("click",function(){
	location.href="https://localhost/HubWeb/board/noticeList.do";
});

//fullCalendar--------------------------------------------------------------

$(".close-area").click(function(){
	$("#scheduleInsert-modal").css("display", "none");
});

$("#scheduleInsert-modal").click(function(e){
	const evTarget = e.target
	if(evTarget.classList.contains("modal-overlay")) {
		$("#scheduleInsert-modal").css("display", "none");
	}
}); 

$("#saveSchedule").on("click",function(){
	var scNo = $("#scNo").val();
	var scTitle = $("#scTitle").val();
	if(scTitle == null || scTitle == ""){
		$("#scheduleInsert-modal").css("display", "none");
		Swal.fire("내용을 입력하세요");
		return false;
	}
	$("#scheduleForm").ajaxForm({
		url : $.getContextPath()+"/common/schedulerInsert.do",
		dataType : "text",
		success : function(resp) {
			if(resp == "SUCCESS"){
				$("#scheduleInsert-modal").css("display", "none");
				calStart();
			}else{
				toastr.error("서버 오류");
			}
		}
	}).submit();
});

$("#del").on("click",function(){
	var scNo = $("#scNo").val();
	$("#scheduleInsert-modal").css("display", "none");
	
	Swal.fire({
		title: "정말로 삭제하시겠습니까?",
		text: "다시 한 번 확인해주세요",
		icon: "warning",
		showCancelButton: true,
		confirmButtonColor: "#3085d6",
		cancelButtonColor: "#d33",
		confirmButtonText: "승인",
		cancelButtonText: "취소"
	}).then((result) => {
		if (result.isConfirmed) {
			$.ajax({
				url : $.getContextPath()+"/common/schedulerDelete.do",
				data : {"scNo" : scNo},
				method : "post",
				dataType : "text",
				success : function(resp) {
					if(resp == "SUCCESS"){
						calStart();
					}else{
						toastr.error("서버 오류");
					}
				}
			});
		}
	});
});

function calStart(){
    var calendarEl = document.getElementById("calendar");
    var calendar = new FullCalendar.Calendar(calendarEl, {
      initialView: "dayGridMonth",
      height: 536,
      headerToolbar: {
    	  left: "dayGridMonth",
    	  center: "title",
    	  right: "prev next"
   	  },
	  navLinks: true, // 날짜를 선택하면 Day 캘린더나 Week 캘린더로 링크
	  editable: true, // 수정 가능?
	  selectable: true, // 달력 일자 드래그 설정가능
	  nowIndicator: true, // 현재 시간 마크
	  //dayMaxEvents: true, // 이벤트가 오버되면 높이 제한 (+ 몇 개식으로 표현)
   	  
	  eventClick: function(info) {	//수정
	  	var start = info.event.startStr;
		var end = info.event.endStr;
		start = start.substring(0,10);
		end = end.substring(0,10);
		if(!end || end == ""){
			end = start;
	  }
  		$("#scStart").val(start);
  		$("#scEnd").val(end);
  		$("#scTitle").val(info.event.title);
  		$("#scheduleForm").ajaxForm({
			url : $.getContextPath()+"/common/schedulerGetNumber.do",
			dataType : "text",
			success : function(resp) {
				$("#scNo").val(resp);
			}
		}).submit();

  		$("#scheduleInsert-modal").css("display", "flex");
	  },
	  
   	  dateClick: function(info) {	//등록
   			$("#scNo").val("");
		    $("#scTitle").val("");
	  		$("#scStart").val(info.dateStr);
	  		$("#scEnd").val(info.dateStr);
	  		$("#scheduleInsert-modal").css("display", "flex");
	  },
      events:function(info, successCallback, failureCallback){
            $.ajax({
               url: "${cPath}/common/schedulerList.do",
               dataType: "json",
               success: function(result) {
                    var events = [];
                    if(result!=null){
                    	$.each(result, function(index, element) {
                            var cr = Math.floor(Math.random()*256);
                            events.push({
                                title: element.scTitle,
                                start: element.scStart,
                                end: element.scEnd,
                                color:"rgb(" + cr + "," + 200 + "," + 230 + ")"
                            });
                        });
                    }                           
           		    successCallback(events);                               
            	}                      
            });
        }
    });
    calendar.render();
};


function clickHandler(event) {
	event.preventDefault();
	document.logoutForm.action = event.target.href;
	document.logoutForm.submit();
	return false;
}


//----------------------------------------- 품목별매입차트  -----------------------------------------
var buyName = [];
var buyQty = [];
var buySumcost = [];
// main controller 에서 가져온 데이터를 넣어줌
// salesrank 데이터가 리스트로 넘어옴
<c:forEach items="${buy}" var="buy">
	buyName.push("${buy.rawsName}") 
	buyQty.push(${buy.pcrQty})
	buySumcost.push(${buy.prcSumcost})
</c:forEach>
	
Highcharts.chart("container1", {
	  chart: {type: "column",height: 200, width : 500},title: {text: "품목별 매입 현황"}, shadow: true
	  , xAxis: {
    		  categories: [ 
    			  buyName[0],buyName[1],buyName[2], buyName[3],buyName[4], buyName[5],buyName[6],buyName[7],buyName[8],
    			  buyName[9],buyName[10],buyName[11],buyName[12]
    		  ]
	  	}
	  , yAxis: [
    		    {min: 0, title: {text: "금액(원)"} }
    		  , {title: {text: "수량(개)"}, opposite: true}
	    ]
	  , legend: {shadow: false}
	  , tooltip: {shared: true}
	  , plotOptions: {column: {grouping: false,shadow: false,borderWidth: 0}}
	  , series: [
		  {name: "수량", color: "rgba(165,170,217,1)", data: buyQty, pointPadding: 0.3, pointPlacement: -0.2}
		  , { name: "금액", color: "rgba(248,161,63,1)", data: buySumcost, tooltip: { valueSuffix: "원" }
		  , pointPadding: 0.3, pointPlacement: 0.2, yAxis: 1}
		]
});


//----------------------------------------- 품목별매출차트  -----------------------------------------
var prodName = []
<c:forEach items="${sale}" var="sale">
	prodName.push(["${sale.prodName}", ${sale.prodRatio}]);
</c:forEach>

Highcharts.chart("container2", {
  chart: {type: "column", height: 200, width : 500}
  , title: {text: "품목별 매출 현황"}
  , xAxis: {
	  type: "category",
      labels: {rotation: -45,style: {fontSize: "13px",fontFamily: "Verdana, sans-serif"}}
  	}
  , yAxis: {
	   min: 0,
	   title: {text: "매출액(%)"}
    }
  , legend: {enabled: false}
  , tooltip: {pointFormat: "Population in 2017: <b>{point.y:.1f} millions</b>"}
  , series: [{
		name: "Population", data: prodName,
		dataLabels: {enabled: true,rotation: -90,color: "#FFFFFF",align: "right", format: "{point.y:.1f}", // one decimal
			  y: 10, 
			  style: {
			    fontSize: "13px",
			    fontFamily: "Verdana, sans-serif"
			  }
		}
  }]
})

//----------------------------------------- 사원별매출현황차트  -----------------------------------------
var emp = []
var sumCost = []
<c:forEach items="${rank}" var="rank">
	emp.push('${rank.empName}')
	sumCost.push(${rank.sumCost})
</c:forEach>
	
Highcharts.chart('container3', {
  chart: { height: 200, width : 500}
  ,	title: {text: '사원별 매출 현황'}
  , yAxis: { title: { text: '매출(원)'} }
  , xAxis: { accessibility: { rangeDescription: 'Range: 1 to 12' } }
  , legend: { layout: 'vertical', align: 'right', verticalAlign: 'middle' }
  , plotOptions: { series: { label: { connectorAllowed: false}, pointStart: 1 } }
  , series: [
// 	  데이터 안에 숫자들은 월별 데이터 12월말고 데이터가 없기 때문에 나머지는 하드코딩 되어 있는 부분
	  {name: emp[0],data: [43934, 52503, 40000,40000,40000,40000,57177, 69658, 97031, 119931, 137133, sumCost[0]]}
	  , {name: emp[1],data: [24916, 24064, 29742, 29851, 30000,30000,30000,30000,32490, 30282, 38121, sumCost[1]]}
	  , {name: emp[2],data: [11744, 17722, 16005, 19771, 20000,20000,20000,20000,20185, 24377, 32147, sumCost[2]]}
	  , {name: emp[3],data: [10000, 10000, 10000,10000,10000,10000,7988, 12169, 15112, 22452, 34400, sumCost[3]]}
	  , {name: emp[4],data: [10000,10000,10000,10000,12908, 5948, 8105, 11248, 8989, 11816, 18274, sumCost[4]]}
	]
  , responsive: {
	    rules: [{
	      condition: {maxWidth: 400},
	      chartOptions: {legend: {layout: 'horizontal',align: 'center',verticalAlign: 'bottom'}}
	    }]
    }
});    
</script>
