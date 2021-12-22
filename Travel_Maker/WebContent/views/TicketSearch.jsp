<%@page import="tm.board.service.FreeBoardService"%>
<%@page import="tm.board.service.IFreeBoardService"%>
<%@page import="tm.member.vo.MemberVO"%>
<%@page import="tm.comm.vo.ProdVO"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Ticket</title>
 
 <meta name="viewport" content="width=device-width, initial-scale=1">

<script src="https://code.jquery.com/jquery-1.10.2.min.js"></script>

<link href="https://cdn.jsdelivr.net/npm/bootstrap@4.4.1/dist/css/bootstrap.min.css" rel="stylesheet">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.4.1/dist/js/bootstrap.bundle.min.js"></script>
<link href="https://maxcdn.bootstrapcdn.com/font-awesome/4.3.0/css/font-awesome.min.css" rel="stylesheet">

<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link href="https://fonts.googleapis.com/css2?family=Do+Hyeon&display=swap" rel="stylesheet">

<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
<link href="https://maxcdn.bootstrapcdn.com/font-awesome/4.3.0/css/font-awesome.min.css" rel="stylesheet">

<script type="text/javascript" src="../js/ticket.js"></script>
<link rel="stylesheet" type="text/css" href="../page/css/default.css">
<link rel="stylesheet" type="text/css" href="../page/css/style2.css">   

<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.6.3/css/all.css" integrity="sha384-UHRtZLI+pbxtHCWp1t77Bi1L4ZtiqrqD80Kn4Z8NTSRyMA2Fd33n5dQ8lWUE00s/" crossorigin="anonymous">
</head>
<style>
.container{
	font-family: 'Do Hyeon', sans-serif;
}
.modal-title{
	text-align: center;
}
#pagelist{
	width : 200px;
}
.pager, .pagination{
		width : 100px;
		float : left;
	}
h1 {
    margin: 20px 20px 20px 20px;
    text-align: center;
}
.pt-3{
 	text-align : center; 
}
#btnWrite{ 
 	float : right;
 	vertical-align : bottom;
}
.pagination{
 	 width : 100px;
	 margin-left: auto;
	 margin-right: auto;
}
#pagelist #btnPre{
	display : inline-block;
	width : 30px; 
	margin-right : 10px;
}
#pagelist #btnNe{
	display : inline-block;
	width : 30px;
	margin-left : 10px; 
}
 #pagelist ul{ 
 	float : left;
 } 
#btnPage{
	 width : 100px;
	 margin-left: auto;
	 margin-right: auto;
}
#pagelist{
	width : 300px;
	margin-left: auto;
	margin-right: auto;
}
#bodyDiv{
	height : 800px;
	font-size : 1.3em;
	font-family: 'Do Hyeon', sans-serif;
}

.card-body{
    flex: 1 1 auto;
    min-height: 1px;
    padding: 1.25rem;
      -webkit-box-shadow: 0px 10px 20px -12px rgba(0, 0, 0, 0.18);
  -moz-box-shadow: 0px 10px 20px -12px rgba(0, 0, 0, 0.18);
  box-shadow: 0px 10px 20px -12px rgba(0, 0, 0, 0.18);
}
</style>

<script>

$(document).ready(function() {
	$("#header").load("<%=request.getContextPath() %>/views/MainHeader.jsp")
	$("#footers").load("<%=request.getContextPath() %>/views/footer.html")
});

	$(function () {
		
		$("form").submit(function() {
			$('#ticketTbody').empty();
			data = $(this).find('#inputSearch').val();
			
			 $.ajax({
		           type: "post",
		           url: "<%=request.getContextPath()%>/TicketSearch.do",
		           data: {"inValue" : data}, 
		           success: function(res){
		        		str = '<thead><tr>';
                        str += '<th scope="col">#</th>';
                        str += '<th scope="col">구분</th>';
                        str += '<th scope="col">이름</th>';
                        str += '<th scope="col">가격</th>';
                        str += '<th scope="col">수량</th>';
                        str += '<th scope="col">합계</th></tr></thead>';
		   				$.each(res,function(i,v){
	                        str += "<tr class='prodTr'>";
							str += ' <td><input class="p_check" type="checkbox" name="buy" value="'+ v.id +'" onclick="javascript:basket.checkItem(this);"></td>';
							str += " <td class='comC' idx='" + v.com + "'>" + v.com +"</td>";
							str += " <td class='nameC'>" + v.name + "</td>";
							str += " <td idx='"+v.price+"'>" + v.price + "원" + "</td>";
	                        str += ' <td>';
	                        str +=   '<div class="updown">';
	                        str +=    '<input type="text" name="p_num'+ i +'" id="p_num'+ i +'" size="2" maxlength="10" class="p_num" value="1" onkeyup="javascript:basket.changePNum('+ i +');">';
	                        str +=    '<span onclick="javascript:basket.changePNum('+ i +');"><i class="fas fa-arrow-alt-circle-up up"></i></span>';
	                        str +=    '<span onclick="javascript:basket.changePNum('+ i +');"><i class="fas fa-arrow-alt-circle-down down"></i></span>';
	                        str +=   '</div>';
	                        str +=  '</td>';
	                        str += '<td>'+ v.price + '원' +'<div class="sum sum_p_price"></div></td></tr>';
	                                                                                                                                                                                    
			   			})
			   			$('#ticketTbody').append(str);
		           },
		           error : function(xhr){
		        	   console.log('상태 ' + xhr.status);
		           },
		           dataType : 'json'		           
		     });
			
		})
	})
	
</script>

<body>

<div id="header"></div>

 <div class="container">
 
   <div class="row">
     <h1>티켓검색</h1>
   </div>
   
       <div class="col-lg-12">
              <div class="card-body">
                   <form onsubmit="return false;">
                        <div class="form-group mb-0">
                        	<div>
								<label><input type="radio" name="Seoul" value="Seoul"> 서울</label>
								<label><input type="radio" name="Gyungki" value="Gyungki"> 경기</label>
								<label><input type="radio" name="Chungcheong" value="Chungcheong"> 충청</label>
								<label><input type="radio" name="Junra" value="Junra"> 전라</label>
								<label><input type="radio" name="Gyungsang" value="Gyungsang"> 경상</label>
								<label><input type="radio" name="Gangwon" value="Gangwon"> 강원</label>
								<label><input type="radio" name="Jeju" value="Jeju"> 제주</label>
                        	</div>
                        	<div>
	                           <div class="input-group mb-0" id="searchText">
	                               <input id="inputSearch" type="text" name="inputSearch" class="form-control" placeholder="Search..." aria-describedby="project-search-addon" style="width: 989px; margin: 0 10px"/>
	                     
	                               <button class="btn btn-danger" type="submit" id="project-search-addon"><i class="fa fa-search search-icon font-12"></i></button>
	                           </div>
                            </div>
                        </div>
                    </form>
                </div>
        </div>
    
    
    <div class="row">
        <div class="col-lg-12">
                <div class="card-body" id="showList">
                    <div class="table-responsive project-list">
                    <form name="listForm">
						<!-- 수정 -->
                        <input type="hidden" name="pageNo">
						<!-- 수정 -->
                        <table class="table project-table table-centered table-nowrap" id="ticketTbody">
                            
			               <tbody>
			
			                   
			
			               </tbody>
                        </table>
                        </form>
                    </div>
                </div>
                <br><br>
                <div style="margin-left: 70vh;">
                <div id="sum_p_num">상품개수 &gt;&gt;</div>
                <div id="sum_p_price">합계금액 &gt;&gt; </div>
                <%	
                MemberVO memvo = (MemberVO)session.getAttribute("memVO");
            	int tm_author = -1; 
            	IFreeBoardService service = FreeBoardService.getService();
            	if(memvo!=null){
            		tm_author = service.checkNotUser(memvo.getTm_id());
            	} 
            	if(tm_author == -1){
            	%>
            		<br>
            		<input type="button" value="장바구니담기" class="btn btn-primary" onclick="javascript:basket.nonUser();">
            	<%}else{ %>
            		<input type="button" value="장바구니담기" class="btn btn-primary" onclick="javascript:basket.shopping();">
                <%} %>
                <br><br><br>
                </div>
                
                
            </div>
        </div>
    </div>
    
    
    
 </div>
<div id="footers"></div>
</body>
</html>