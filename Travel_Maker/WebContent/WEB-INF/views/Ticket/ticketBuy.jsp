<%@page import="tm.member.vo.MemberVO"%>
<%@page import="tm.comm.vo.CartVO"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	List<CartVO> list = (List<CartVO>)request.getAttribute("list");
	CartVO cartVO = (CartVO)request.getAttribute("cartVO");
	MemberVO memVO = (MemberVO)request.getAttribute("memVO");

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
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<script type="text/javascript" src="https://cdn.iamport.kr/js/iamport.payment-1.1.5.js"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>

<script>
		IMP.init('imp31931496');
	
		IMP.request_pay({
		    pg : 'inicis', // version 1.1.0부터 지원.
		    pay_method : 'card',
		    merchant_uid : 'merchant_' + new Date().getTime(),
		    name : '<%=buyName%>',
		    amount : <%=cartVO.getTotalPrice()%>, //판매 가격
		    buyer_email : '<%=memVO.getTm_email()%>',
		    buyer_name : '<%=memVO.getTm_name()%>',
		    buyer_tel : '<%=memVO.getTm_tel()%>',
		    buyer_addr : '<%=memVO.getTm_add1()%>',
		    buyer_postcode : '<%=memVO.getTm_zip()%>'
		}, function(rsp) {
		    if ( rsp.success ) {
		        var msg = '결제가 완료되었습니다.';
		        msg += '고유ID : ' + rsp.imp_uid;
		        msg += '상점 거래ID : ' + rsp.merchant_uid;
		        msg += '결제 금액 : ' + rsp.paid_amount;
		        msg += '카드 승인번호 : ' + rsp.apply_num;
		        alert(msg);
		        
// 		        $.ajax({
// 		    		url : '/Travel_Maker/BasketDelete.do',
// 		    		type : 'post',
// 		    		data : {"cartNo" : cartNo,
// 		    				"prod" : prod},
// 		    		success : function(res){
// 		    			alert('삭제되었습니다.');
// 		    			location.reload();
// 		    		},
// 		    		error : function(xhr){
// 		    			console.log("상태  " + xhr.status);
// 		    		},
// 		    		dataType : 'json'	
// 		    	})
		        
		    } else {
		        var msg = '결제에 실패하였습니다.';
		        msg += '에러내용 : ' + rsp.error_msg;
		    }
		});

</script>
<title>결제창</title>
</head>
<body>


</body>
</html>