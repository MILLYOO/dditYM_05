<%@page import="tm.plan.service.PlanService"%>
<%@page import="tm.plan.service.IPlanService"%>
<%@page import="tm.plan.vo.planDetailVO"%>
<%@page import="java.util.HashMap"%>
<%@page import="java.util.Map"%>
<%@page import="tm.board.service.FreeBoardService"%>
<%@page import="tm.board.service.UserQnaServiceImple"%>
<%@page import="tm.board.service.IFreeBoardService"%>
<%@page import="tm.comm.vo.ReplyVO"%>
<%@page import="java.util.List"%>
<%@page import="tm.member.vo.MemberVO"%>
<%@page import="tm.comm.vo.BoardVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%
    List<planDetailVO> list = (List<planDetailVO>) request.getAttribute("selectplanList");
    
    MemberVO memvo = (MemberVO)session.getAttribute("memVO");
	
	IPlanService service = PlanService.getService();
	

    %>
    
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script src="https://code.jquery.com/jquery-1.10.2.min.js"></script>

<link href="https://cdn.jsdelivr.net/npm/bootstrap@4.4.1/dist/css/bootstrap.min.css" rel="stylesheet">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.4.1/dist/js/bootstrap.bundle.min.js"></script>
<link href="https://maxcdn.bootstrapcdn.com/font-awesome/4.3.0/css/font-awesome.min.css" rel="stylesheet">

<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link href="https://fonts.googleapis.com/css2?family=Do+Hyeon&display=swap" rel="stylesheet">
<link rel="stylesheet" type="text/css" href="../page/css/default.css">
<link rel="stylesheet" type="text/css" href="../page/css/style2.css">

<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>

<script src="<%=request.getContextPath() %>/js/freeboard.js"></script>
<script src="<%=request.getContextPath() %>/js/LikeHate.js"></script>

<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.0/dist/css/bootstrap.min.css">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.0/dist/js/bootstrap.bundle.min.js"></script>

<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/page/css/default.css">
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/page/css/style2.css">

<style>
h1{
    margin: 20px 20px 20px 20px;
    text-align: center;
}
body{
	font-family: 'Do Hyeon', sans-serif;
}
.modal-title{
	text-align: center;
}
#pagelist{
	width : 200px;
}
.page-link{
	width : 50px;
}
.pager, .pagination{
	width : 100px;
	float : left;
	}
.card-body {
	-ms-flex: 1 1 auto;
	flex: 1 1 auto;
	min-height: 1px;
	padding: 1.25rem;

}
.card {
    border: none;
    margin-bottom: 24px;
    -webkit-box-shadow: 0 0 13px 0 rgba(236,236,241,.44);
    box-shadow: 0 0 13px 0 rgba(236,236,241,.44);
}

.avatar-xs {
    height: 2.3rem;
    width: 2.3rem;
}

#modiform{
	width : 50px;
}
#btnWrite{ 
 	float : right;
 	vertical-align : bottom;
}
.card:hover{
	box-shadow: 1px 1px 20px #ddd;
}

    /* 정보를 담는 폼 스타일*/
    .TM_ML{
	display : inline-block;
	width: 100px;
	}
	.card{
		cursor: pointer;
	}
	.card-title{
		text-align: center;
	}
	
	.card-body {
    	height: 200px;
	}
	.card-body {
    	height: auto;
	}
	.card-text{
		height: 80px;
		text-align: center;
	}
	.container{
		margin : 10px;
	}
	#plandetail{
		height : 500px;
	}
	.detailcont{
		border : 0.5px solid lightblue;
		height: 200px;
	}
	.fa-angle-double-right{
		margin-top: 80px;
		
	}
	.col-3:hover{
		box-shadow: 1px 1px 20px #ddd;
	}
	.container{
		margin: 0 auto;
	}
	
</style>




<script>

 $(document).ready(function() {
	$("#header").load("<%=request.getContextPath() %>/views/MainHeader.jsp") 
 	$("#footers").load("<%=request.getContextPath() %>/views/footer.html") 
 });
 

 	<%
	for(int i =0; i<list.size(); i++){
		String detail = list.get(i).getTm_plan_detail();
		System.out.println("번호" + detail);
	%>
	
	
	$.ajax({
		url : '/Travel_Maker/searchInfo2.do',
		type : 'post',
		data : {"data" : '<%=detail%>'},
		dataType : 'json',
		success : function(res) {
			var id = res.tm_search_id;
			var title = res.tm_search_name;
			var img = res.img_id;
			var addr = res.tm_search_addr;
			var tel = res.tm_search_tel;
			var cate = res.tm_search_cate;
			var con = res.tm_search_con;
			
				str = 
					<%if(i > 0){%>
					'<i class="fas fa-angle-double-right fa-3x"></i>'+
					<%}%>
					'<div class="col-3">'+
					'<h3>'+<%=i+1%>+'번째 일정</h3>'+
						'<div class="card" id="'+id+'">'+
							'<img src="../page/images/여행지1.jpg" alt="" class="card-img-top" />'+
							'<div class="card-body detailcont">'+ 
							'<h2 class="card-title">'+title+'</h2>'+
							'<p class="card-text">'+addr+'</p>'+
							'</div>'+
						'</div>'+
					'</div>';

				$('#AA').append(str);
			
		},
		error : function(xhr){
			console.log("상태 : " + xhr.status);
		},

	})
		
		
	<%
	}
	%> 




</script>
</head> 




<body>
	<div id="header"></div>
	<input type="hidden" name="pageNo">
	<div class="container">
		<div class="row">
			<h1 style="font-size:2.0em;">일정상세</h1>
		</div>


    <!-- end row -->

    <div class="row">
        <div class="col-lg-12">
            <div class="card">
                <div class="card-body">
                    <div class="table-responsive project-list">
                        <table class="table project-table table-centered table-nowrap">
                            <thead>
                                <tr>
                                    <th scope="col"></th>
                                    <th scope="col"></th>


                                </tr>
                            </thead>
    	
                        </table>

                    </div>
                    
                            <tbody id="plandetail">
                            	<table class="table project-table table-centered table-nowrap">



								<div class="container">
									<div class="row cardinfo" id="AA">
										<!-- 카드 정보가 들어가는 곳 -->
									</div>
								</div>

    
                        </table>
                      </tbody>
                    <!-- end project-list -->

                </div>
            </div>
        </div>
    </div>


		<!-- end row -->
</div>    			
   			
    			
   	<div id="footers"></div> 			

</body>
</html>	




















