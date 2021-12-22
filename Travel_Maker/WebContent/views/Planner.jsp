<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
 <meta name="viewport" content="width=device-width, initial-scale=1">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<!-- api -->
<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=e08d921eaf92b6587ac46f3f1255b6a3&libraries=services"></script>

<!-- css -->
<link rel="stylesheet" type="text/css" href="https://cdn.jsdelivr.net/npm/daterangepicker/daterangepicker.css" />
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css">
<link rel="stylesheet" type="text/css" href="../page/css/default.css">
<link rel="stylesheet" type="text/css" href="../page/css/style2.css">
<link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.13.0/css/all.min.css" rel="stylesheet">

<!-- font -->
<link href="https://fonts.googleapis.com/css2?family=Do+Hyeon&display=swap" rel="stylesheet">


<!-- js -->
<script type="text/javascript" src="https://cdn.jsdelivr.net/jquery/latest/jquery.min.js"></script>
<script type="text/javascript" src="https://cdn.jsdelivr.net/momentjs/latest/moment.min.js"></script>
<script type="text/javascript" src="https://cdn.jsdelivr.net/npm/daterangepicker/daterangepicker.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js"></script>
<script src="<%=request.getContextPath() %>/js/planner.js"></script>

 <style>
html, body {
	width: 100%;
	height: 100%;
	overflow: hidden;
}

body {
	font-family: 'Do Hyeon', sans-serif;
}

#pl_wrapper, #pl_content_wrap, #pl_content {
	height: 100%;
}

#pl_wrapper {
	height: 80%;
}

#pl_map_area {
	width: 100%;
	height: 100%;
}

nav {
	display: block;
}

#pl_main_container {
	height: 100%;
	padding: 0;
	margin: 0;
	background-color: #f9f9f9;
	display: flex;
}
/* 맵 둘러싸는 영역 */
#pl_map_container {
	height: 100%;
	position: relative;
	padding: 0;
	margin: 0;
	padding: 0
}

#footers {
	position: re;
	bottom: 0;
}

#pl_dside {
	padding: 0;
	margin: 0;
	z-index: 100;
	background: #334257;
}

#pl_rSide {
	padding: 0;
	margin: 0;
	overflow-x: hidden;
	position: absolute;
	right: 0px;
	top: -1px;
	height: 100%;
	z-index: 100;
	background-color: #ffffff;
	background-color: rgba(255, 255, 255, 0.5);
	top: -1px;
}

.card {
	cursor: pointer;
}

.card-title {
	text-align: center;
}

.card-body {
	height: 100px;
}

.col-4 {
	margin: 10px 0px;
}

.card-text {
	height: 50px;
}

.fa-angle-double-right {
	margin: 40px 10px;
	color: #EEEEEE;
}

#plDate {
	background-color: #8091B0;
	padding: 20px 10px;
}

#plDate span {
	text-align: center;
	display: table-cell;
	vertical-align: middle;
	color: red;
	margin: 0 auto;
}

#pl_rSide::-webkit-scrollbar {
	width: 10px;
}

#pl_rSide::-webkit-scrollbar-thumb {
	background-color: #2f3542;
	border-radius: 10px;
	background-clip: padding-box;
	border: 2px solid transparent;
}

#pl_rSide::-webkit-scrollbar-track {
	background-color: grey;
	border-radius: 10px;
	box-shadow: inset 0px 0px 5px white;
}

#sendForm {
	width: 300px;
}
.modal-content{
	margin-top: 40vh;
}
.modal {
	z-index: 9999;
}
.modal-label{
	width: 150px;
    text-align: center;
    border: 1px solid #a2acb5;
}
.modal_pl_title{
	margin-left: 15px;
    text-align: center;
    border: 1px solid #a2acb5;
}
#plSubmit{

}
</style>
<script>
	$(function() {
		$("#header").load("MainHeader.jsp")
		$("#footers").load("footer.html")
		let today = new Date();
		$('#plDate').daterangepicker(
				{
					timePicker : false,
					drops : 'up',
					locale : {
						"separator" : " ~ ", // 시작일시와 종료일시 구분자
						"format" : 'YYYY-MM-DD', // 일시 노출 포맷
						"applyLabel" : "확인", // 확인 버튼 텍스트
						"cancelLabel" : "취소", // 취소 버튼 텍스트
						"daysOfWeek" : [ "일", "월", "화", "수", "목", "금", "토" ],
						"monthNames" : [ "1월", "2월", "3월", "4월", "5월", "6월",
								"7월", "8월", "9월", "10월", "11월", "12월" ]
					},
				},
				function(start, end, label) {
					$('#startDate').val(start.format('YYYY-MM-DD'));
					$('#endDate').val(end.format('YYYY-MM-DD'));
					$('#plDate').empty().html(
							start.format('YYYY-MM-DD') + " ~ "
									+ end.format('YYYY-MM-DD'))
				});

		$(document)
				.on(
						'click',
						'.cardcon',
						function() {
							$
									.ajax({
										url : '/Travel_Maker/searchInfo2.do',
										type : 'post',
										data : {
											"data" : $(this).find('.card2')
													.prop('id')
										},
										dataType : 'json',
										success : function(res) {
											var id = res.tm_search_id;
											var title = res.tm_search_name;
											var latlng = new kakao.maps.LatLng(
													res.tm_search_y,
													res.tm_search_x);
											var img = res.img_id;
											var addr = res.tm_search_addr;
											var tel = res.tm_search_tel;
											var cate = res.tm_search_cate;
											var con = res.tm_search_con;
											var content = '<div class="col-12">'
													+ '<div class="card" id="'+id+'">'
													+ '<div class="row no-gutters">'
													+ '<div class="col-4">'
													+ '<img src="images/card-image.png" alt="" class="card-img">'
													+ '</div>'
													+ '<div class="col-8" id="'+title+'">'
													+ '<div class="card-body">'
													+ '<p class="card-text">장소명 : '
													+ title
													+ '<br>주소 : '
													+ addr
													+ '</p>'
													+ '<div class="card-X" style="display:none;">'
													+ res.tm_search_x
													+ '</div>'
													+ '<div class="card-Y" style="display:none;">'
													+ res.tm_search_y
													+ '</div>'
													+ '</div>'
													+ '</div>'
													+ '</div>'
													+ '</div>' + '</div>'
											$('.cardinfo').append(content);
										},
										error : function(xhr) {
											console.log("상태: " + xhr.status);
										}
									});
							$(this).next().remove();
							$(this).remove();
							var index = select.indexOf($(this).find(".card2")
									.attr("id"));
							select.splice(index, 1);
							markers[index].setMap(null);
							markers.splice(index, 1)
						})

		$(document).on('click', '#plSubmit', function() {
			var list = $('.card2').get();
			var value = "";
			for (var i = 0; i < list.length; i++) {
				if (i != 0)
					value += ",";
				value += list[i].id;
			}
			$('#Pid').val(value);
			$("#myModal").modal("show");
		})

		$(document).on(
				'click',
				'#send',
				function() {
					if ($('#Pid').val() == null || $('#Pid').val() == "") {
						alert("일정을 먼저 추가해주세요!")
						$("#myModal").modal("hide");
					} else if ($('#endDate').val() == null
							|| $('#endDate').val() == "") {
						alert("여행일을 추가해주세요!")
						$("#myModal").modal("hide");
					} else {
						$('#Ptitle').val($('#modal_pl_title').val());
						console.log($('#Ptitle').val())
						$("#myModal").modal("hide");
						alert("일정이 저장되었습니다.")

						formdata = $('form').serializeArray();
						$.ajax({
							url : '/Travel_Maker/PlanInsert.do',
							type : 'post',
							data : formdata,
							success : function(res) {
								location.reload();
							},
							error : function(xhr) {
								console.log('상태 : ' + xhr.status);
							}
						})
					}
				})
	});
</script>


</head>
<body>
<div id="header"></div>
	<div id="pl_wrapper">
<!-- 		<nav id="pl_header" class="navbar navbar-default  navbar-fixed-top"></nav>	네비게이션 -->

		<div id="pl_content_wrap" class="container-fluid">
			<!-- 콘텐츠 영역 -->
			<div id="pl_content" class=row>
				<div id="pl_main_container" class="col-md-12">
					<!-- 맵컨테이너 -->
					<div id="pl_map_container" class="col-md-12">
						<div id="pl_map_area"></div>
					<div id="pl_rSide" class="col-md-3">	<!-- 오른쪽 사이드바 -->
							<div class="row cardinfo">
						</div>
					</div>
					</div>
				</div>
			</div>
		</div>
	</div>
		<div  id="pl_dside" class = "pl_dside_container col-md-12 row container" style="height: 152px; width: 100%;">
			<div id="plDate" class="col-md-2" style="color:white;height: 48%;margin: 38px auto 15px 13px;font-size: 23.2px;border-radius: -3%;text-align: center;">기간을 입력하세요</div>
			<div class = "col-md-9 row makeinfo" style="height: 100%;" >
			</div>
			<input type="button" class="col-md-1" id = "plSubmit" style="font-family: 'Do Hyeon'; font-size:1.2em"; value ="일정 만들기">
		</div>
	<form>
    	<input type="text" id = "startDate" name= "startDate" style="disply: none;"><br>
    	<input type="text" id = "endDate" name= "endDate" style="disply: none;"><br>
    	<input type="text" id = "Pid" name = "Pid" style="disply: none;"><br>
    	<input type="text" id = "Ptitle" name = "Ptitle" style="disply: none;"><br>
	</form>
	<div id="myModal" class="modal fade" role="dialog">
			<div class="modal-dialog">
				<!-- Modal content-->
				<div class="modal-content">
					<div class="modal-body">
						<form>
							<label class="modal-label">일정이름</label>
							<input type ="text" id ="modal_pl_title" placeholder="일정 이름을 입력해 주세요!!" >
						</form>
					</div>
					<div class="modal-footer">
						<input type="button" class="btn btn-default" value="저장" id="send">
						<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
					</div>
				</div>
			</div>
		</div>
</body>
</html>