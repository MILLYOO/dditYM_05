<%@page import="tm.comm.vo.CartVO"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	// 장바구니
	List<CartVO> list = (List<CartVO>)request.getAttribute("list");
	
	// 총개수, 총가격
	CartVO vo = (CartVO)request.getAttribute("vo");
	
	
// 	String[] card = {"농협카드", "신한카드", "국민카드", "하나카드"};
// 	int random = (int)Math.random() * 3;
// 	int ranPront = (int)(Math.random() * 10000) + 1000;
// 	int ranBack = (int)(Math.random() * 10000) + 1000;
	int ranPay = (int)(Math.random() * 100000) + 10000;
	
// 	String catdName = card[random];
// 	String payMethod = "카드";
// 	String cardNum = ranPront + "*********" + ranBack;
	String payNum = "p" + ranPay;
	
%>   
 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>결제내역</title>

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

<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/page/css/default.css"> 
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/page/css/style2.css">
<script>

$(document).ready(function() {
	$("#header").load("<%=request.getContextPath() %>/views/MainHeader.jsp")
	$("#footers").load("<%=request.getContextPath() %>/views/footer.html")
});


$(function(){

	$('.btnRefund').on('click',function(){
		if(confirm("정말 취소하시겠습니까?") == true){
			$(this).attr('disabled','disabled');
			$(this).attr('value','취소신청됨');			
		}else{
			return;
		}
	})

	
})
</script>


 <style type="text/css">
h1{
    margin: 20px 20px 20px 20px;
    text-align: center;
    font-family: 'Do Hyeon', sans-serif;
}
#bodyDiv{
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
.container{
	height: 500px;
	margin-top : 50px;
}
</style>
</head>
<body>
	<div id="header"></div>
	
<div class="container">
<div class="row">
        <h1>결제내역</h1>
    </div>
<div id="bodyDiv">
    <div class="row">
        <div class="col-lg-12">
         <div class="card-body" id="showList">
        	<div class="table-responsive project-list">
	               <%
	               	if(list.size() <= 0 || list == null){
	               %>
	               <h3>결제내역이 없습니다.</h3>
	               <% 
	               	}else{
	               %>
			</div>
        </div>
     </div>
</div>
<!--             <div class="card"> -->
<!--                 <div class="card-body"> -->
<!--                     <div class="table-responsive project-list"> -->
                        <table class="table project-table table-centered table-nowrap">
                            <thead>
                                <tr>
                                    <th scope="col">주문번호</th>
                                    <th scope="col">주문내용</th>
                                    <th scope="col">결제금액</th>
                                    <th scope="col">취소/환불</th>
                                </tr>
                            </thead>
                            
                            <tbody id="tbody">
                                <tr>
                                	<td><%=payNum %></td>
                                	<%
                                		if(list.size() > 0){
                                	%>
                                	<td><%=list.get(0).getTm_prod_comp() %><%=list.get(0).getTm_prod_name() %>
                                	<br>외 <%=vo.getTotalCount()-1 %> 건</td>
                                	<%
                                		}else{
                                	%>
                                	<td><%=list.get(0).getTm_prod_comp() %><%=list.get(0).getTm_prod_name() %></td>
                                	<%
                                		}
                                	%>
                                	<td><%=vo.getTotalPrice() %>원</td>
                                	<td><input class="btn btn-warning btnRefund" type="button" value="취소"></td>
                                </tr>
                                
                                
                                
                            </tbody>
                        </table>
                    	   <%
			               		}
			               %>
                    </div>
                    <!-- end project-list -->
				</div>
<!--                 </div> -->
<!--             </div> -->
<!--         </div> -->
<!-- end row -->

       	<div id="footers"></div>
</body>
</html>