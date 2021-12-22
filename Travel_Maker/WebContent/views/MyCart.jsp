<%@page import="tm.member.vo.MemberVO"%>
<%@page import="tm.comm.vo.CartVO"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
// 	HttpSession cartSession = request.getSession();

	List<CartVO> list = (List<CartVO>)request.getAttribute("list");
	CartVO vo = (CartVO)request.getAttribute("vo");
	MemberVO memvo = (MemberVO)request.getAttribute("memVO");
	
	String buyName = "";
	if(list.size() > 0){
		buyName += list.get(0).getTm_prod_comp();
		buyName += list.get(0).getTm_prod_name();
		buyName += " 외 ";
		buyName += list.size()-1;
		buyName += "건 결제";
	}else if(list.size() == 1){
		buyName = list.get(0).getTm_prod_comp() + " " + list.get(0).getTm_prod_name() + " 결제";
	}
%>
<!DOCTYPE html>
<html>
<meta charset="UTF-8">
<title>장바구니</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
<link
	href="https://fonts.googleapis.com/css2?family=Do+Hyeon&display=swap"
	rel="stylesheet">
<script
	src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/page/css/default.css">
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/page/css/style2.css">

<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.6.3/css/all.css" integrity="sha384-UHRtZLI+pbxtHCWp1t77Bi1L4ZtiqrqD80Kn4Z8NTSRyMA2Fd33n5dQ8lWUE00s/" crossorigin="anonymous">

<script type="text/javascript" src="https://cdn.iamport.kr/js/iamport.payment-1.1.5.js"></script>

<script type="text/javascript">
	$(document).ready(function() {
		$("#header").load("<%=request.getContextPath() %>/views/MainHeader.jsp")
		$("#footers").load("<%=request.getContextPath() %>/views/footer.html")
	});

</script>
<style type="text/css">
.row {
	margin-right: -15px;
	margin-left: -15px;
	display: -ms-flexbox;
	display: flex;
	-ms-flex-wrap: wrap;
	flex-wrap: wrap;
	margin-right: -15px;
	margin-left: -15px;
}

h1 {
	margin: 20px 20px 20px 20px;
}

.container {
	font-family: 'Do Hyeon', sans-serif;
}

.modal-title {
	text-align: center;
}

#pagelist {
	width: 200px;
}

.page-link {
	width: 50px;
}

.pager, .pagination {
	width: 100px;
	float: left;
}

.card-body {
	-ms-flex: 1 1 auto;
	flex: 1 1 auto;
	min-height: 1px;
	padding: 1.25rem;
	color: black;
}

.card {
	border: none;
	margin-bottom: 24px;
	-webkit-box-shadow: 0 0 13px 0 rgba(236, 236, 241, .44);
	box-shadow: 0 0 13px 0 rgba(236, 236, 241, .44);
}

.avatar-xs {
	height: 2.3rem;
	width: 2.3rem;
}
h3{
	text-align : center;
	margin : 200px 0px;
}
</style>
<script type="text/javascript">

$(function () {
	$.fn.changeCount = function(pos) {
		var item = document.querySelector('input[name=p_num'+pos+']');
		var p_num = parseInt(item.getAttribute('value'));
		var newval = event.target.classList.contains('up') ? p_num+1 : event.target.classList.contains('down') ? p_num-1 : event.target.value;
		
		if (parseInt(newval) < 1 || parseInt(newval) > 99) { return false; }
		
		item.setAttribute('value', newval);
		item.value = newval;
		
		prodId = $(this).parent('.updown').attr('idx');
		
		$.ajax({
    		url : '/Travel_Maker/BasketUpdate.do',
    		type : 'post',
    		data : {"newval" : newval,
    				"prodId" : prodId},
    		success : function(res){
    			location.reload();
    		},
    		error : function(xhr){
    			console.log("상태  " + xhr.status);
    		},
    		dataType : 'json'	
    	})
	}
	
	$('.btnDel').on('click',function(){
		cartNo = $(this).attr('idx');
		prod = $(this).parents('.prodTr').find('.updown').attr('idx');
		$.ajax({
    		url : '/Travel_Maker/BasketDelete.do',
    		type : 'post',
    		data : {"cartNo" : cartNo,
    				"prod" : prod},
    		success : function(res){
    			alert('삭제되었습니다.');
    			location.reload();
    		},
    		error : function(xhr){
    			console.log("상태  " + xhr.status);
    		},
    		dataType : 'json'	
    	})
	})
	
	$('#btnBuy').on('click',function(){
		
// 		location.href="/Travel_Maker/BasketBuy.do";
		
		IMP = window.IMP;
		IMP.init('imp31931496');
				
		IMP.request_pay({
		    pg : 'inicis', 
		    merchant_uid : 'merchant_' + new Date().getTime(),
		    name : '<%=buyName%>',
		    amount : <%=vo.getTotalPrice()%>,
		    buyer_email : '<%=memvo.getTm_email()%>',
		    buyer_name : '<%=memvo.getTm_name()%>',
		    buyer_tel : '<%=memvo.getTm_tel()%>',
		    buyer_addr : '<%=memvo.getTm_add1()%>',
		    buyer_postcode : '<%=memvo.getTm_zip()%>'
		}, function(rsp) {
		    if(rsp.success) {
		    	var msg = '결제가 완료되었습니다.';
		    	msg += rsp.card_name;
		        msg += rsp.card_number;
		     	alert("결제내역 페이지로 이동합니다.");
		        location.href="/Travel_Maker/buyList.do";
		    } else {
		        var msg = '결제에 실패하였습니다.';
		        msg += '에러내용 : ' + rsp.error_msg;
		        location.reload;
		    }
		    alert(msg);
		});
		
	})
	
})


</script>
<title>매출 관리</title>
</head>
<body>
<div id="header"></div>
<div class="container">
<h1>장바구니</h1>
<div class="row">
	<div class="col-lg-12">
        <div class="card-body" id="showList">
        	<div class="table-responsive project-list">
	               <%
	               	if(list.size() <= 0 || list == null){
	               %>
	               <h3>장바구니 내역이 없습니다.</h3>
	               <% 
	               	}else{
	               %>
			</div>
        </div>
    </div>
</div>
                    <form name="listForm">
						<!-- 수정 -->
                        <input type="hidden" name="pageNo">
						<!-- 수정 -->
                        <table class="table project-table table-centered table-nowrap">
                            <thead>
                                <tr>
                                    <th scope="col">#</th>
                                    <th scope="col">구분</th>
                                    <th scope="col">이름</th>
                                    <th scope="col">가격</th>
                                    <th scope="col">수량</th>
                                    <th scope="col">합계</th>
                                    <th scope="col">삭제</th>
                                </tr>
                            </thead>
			               <tbody id="ticketTbody">
			               <%
			               	for(int i = 0 ; i < list.size(); i++){
			               %>			               
	                        <tr class='prodTr'>
							 <td><input type="checkbox" checked="checked"></td>
							 <td><%=list.get(i).getTm_prod_comp()%></td>
							 <td><%=list.get(i).getTm_prod_name() %></td>
							 <td><%=list.get(i).getTm_cart_price()%>원</td>
	                         <td>
	                          <div class="updown" idx="<%=list.get(i).getTm_prod_id()%>">
	                          <input type="text" name="p_num<%=i+1 %>" id="p_num<%=i+1 %>" size="2" maxlength="10" class="p_num" value="<%=list.get(i).getTm_cart_qty() %>">
	                          <span onclick="$(this).changeCount('<%=i+1 %>');"><i class="fas fa-arrow-alt-circle-up up"></i></span>
	                          <span onclick="$(this).changeCount('<%=i+1 %>');"><i class="fas fa-arrow-alt-circle-down down"></i></span>
	                          </div>
	                         </td>
	                         <td><%=list.get(i).getTm_cart_price()%>원<div class="sum sum_p_price"></div></td>
	                         <td><input type="button" value="삭제" class="btn btn-defalt btnDel" idx="<%= list.get(i).getTm_cart_no()%>"></td>
	                       </tr>
			               <%
			               	}
			               	%>
			
			               </tbody>
                        </table>
                        </form>
               
                <br><br>
                <div id="pQty">상품갯수: <%=vo.getTotalCount() %></div>
                <div id="tPrice">합계금액: <%=vo.getTotalPrice() %></div>
                
					<div class="write">
						<input class="btn btn-primary" id="btnBuy" type="button" value="구매하기">&nbsp;
					</div>
					<br><br><br><br>
			               <%
			               		}
			               %>
		</div>			
<div id="footers"></div>

</body>
</html>