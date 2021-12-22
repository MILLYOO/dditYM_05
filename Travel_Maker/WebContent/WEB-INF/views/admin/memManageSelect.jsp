<%@page import="tm.member.vo.MemberVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	MemberVO vo = (MemberVO) request.getAttribute("vo");
%>    
    
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>회원 정보 조회</title>

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

<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>

<script src="<%=request.getContextPath() %>/js/admin.js"></script>
<script src="<%=request.getContextPath() %>/js/jquery.serializejson.min.js"></script>

<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/page/css/default.css">
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/page/css/style2.css">

<style>
#bodyDiv{
	height : 700px;
	font-family: 'Do Hyeon', sans-serif;
	font-size : 1.3em;
}
h1{
	text-align : center;
	margin : 50px 0px;
}
label{
	display : inline-block;
	width : 100px;
	margin : 10px;
}
input[type="text"]{
	border : 1px solid lightgray;
	border-radius: 5px;
}
</style>

<script>
$(document).ready(function() {
	$("#header").load("<%=request.getContextPath()%>/views/MainHeader.jsp")
	$("#footers").load("<%=request.getContextPath()%>/views/footer.html")
});

$(function(){
	
	$('#zip').on('click',function(){
		
	    new daum.Postcode({
	    	oncomplete: function(data) {
            // 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분.

            // 도로명 주소의 노출 규칙에 따라 주소를 표시한다.
            // 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기 한다.
            var roadAddr = data.roadAddress; // 도로명 주소 변수
            var extraRoadAddr = ''; // 참고 항목 변수

            // 법정동명이 있을 경우 추가한다. (법정리는 제외)
            // 법정동의 경우 마지막 문자가 "동/로/가"로 끝난다.
            if(data.bname !== '' && /[동|로|가]$/g.test(data.bname)){
                extraRoadAddr += data.bname;
            }
            // 건물명이 있고, 공동주택일 경우 추가한다.
            if(data.buildingName !== '' && data.apartment === 'Y'){
               extraRoadAddr += (extraRoadAddr !== '' ? ', ' + data.buildingName : data.buildingName);
            }
            // 표시할 참고항목이 있을 경우, 괄호까지 추가한 최종 문자열을 만든다.
            if(extraRoadAddr !== ''){
                extraRoadAddr = ' (' + extraRoadAddr + ')';
            }

            // 우편번호와 주소 정보를 해당 필드에 넣는다.
            document.getElementById('sample4_postcode').value = data.zonecode;
            document.getElementById("sample4_roadAddress").value = roadAddr;
            document.getElementById("sample4_jibunAddress").value = data.jibunAddress;
            
            // 참고항목 문자열이 있을 경우 해당 필드에 넣는다.
            if(roadAddr !== ''){
                document.getElementById("sample4_extraAddress").value = extraRoadAddr;
            } else {
                document.getElementById("sample4_extraAddress").value = '';
            }

            var guideTextBox = document.getElementById("guide");
            // 사용자가 '선택 안함'을 클릭한 경우, 예상 주소라는 표시를 해준다.
            if(data.autoRoadAddress) {
                var expRoadAddr = data.autoRoadAddress + extraRoadAddr;
                guideTextBox.innerHTML = '(예상 도로명 주소 : ' + expRoadAddr + ')';
                guideTextBox.style.display = 'block';

            } else if(data.autoJibunAddress) {
                var expJibunAddr = data.autoJibunAddress;
                guideTextBox.innerHTML = '(예상 지번 주소 : ' + expJibunAddr + ')';
                guideTextBox.style.display = 'block';
            } else {
                guideTextBox.innerHTML = '';
                guideTextBox.style.display = 'none';
            }
        }
      }).open();
	})

	
	
	
	
	//수정하기 눌렀을 때
	$('#btnUpdate').on('click', function(){
		$('#myModal').modal('show');
	})
	
	// 회원정보 수정 등록 버튼
	$('#send').on('click',function(){
		
		formdata = $('form').serialize();
		
		memInfoUpdate();
		
		$('#myModal').modal('hide');
	})
	
	// 회원탈퇴버튼
	$('#btnDelete').on('click',function(){
		
		if(confirm("선택한 회원을 탈퇴 하시겠습니까?") == true){
			modiId = $(this).attr('idx');
			modiId = modiId.trim();
			memInfoDelete();			
		}else{
			return;
		}
		
	})
	
})
</script>

</head>
<body>
	<div id="header"></div>
	<input type="hidden" name="pageNo">
	<div class="container">
		<div class="row">
			<h1>회원정보 조회</h1>
		</div>
<div id="bodyDiv">
<div class="table-responsive project-list">
  <h4>&lt;&lt;<%=vo.getTm_id() %>&gt;&gt; 님 회원정보</h4><br>
   <table class="table project-table table-centered table-nowrap">
       
       <tbody id="tbody">
     	   	<tr>
		  		<td class="name">구분</td>
		  		<td class="data">
	                <%
	               	String auth = "";
	               	int author = vo.getTm_author();
	               	if(author == 0){
	               		auth = "관리자";
	               	}else if(author == 1){
	               		auth = "일반회원";
	               	}else if(author == 2){
	               		auth = "블랙리스트";
	               	}else if(author == -1){
	               		auth = "탈퇴회원";
	               	}
	               %>
	               <%=auth %>
		  		
		  		</td>
		    </tr>
           <tr>
       		<td class="name">아이디</td>
       		<td class="data"><%= vo.getTm_id() %></td>
  		   </tr>
		  	<tr>
		  		<td class="name">이름</td>
		  		<td class="data"><%= vo.getTm_name() %></td>
		  	</tr>
		  	<tr>
		  		<td class="name">전화번호</td>
		  		<td class="data"><%= vo.getTm_tel()%></td>
		  	</tr>
		  	<tr>
		  		<td class="name">우편번호</td>
		  		<td class="data"><%= vo.getTm_zip()%></td>
		  	</tr>
		  	<tr>
		  		<td class="name">도로명주소</td>
		  		<td class="data">
		  		<%
					String maddr1 = vo.getTm_add1();
					if(maddr1 == null){
						maddr1 = "-";
					}
				%>                                    
                <%= maddr1 %>
		  		</td>
		  	</tr>
		  	<tr>
		  		<td class="name">지번주소</td>
		  		<td class="data">
		  		<%
					String maddr2 = vo.getTm_add2();
					if(maddr2 == null){
						maddr2 = "-";
					}
				%>                                    
                <%= maddr2 %>
		  		</td>
		    </tr>
		  	<tr>
		  		<td class="name">이메일</td>
		  		<td class="data">
		  		<%
					String memail = vo.getTm_email();
					if(memail == null){
						memail = "-";
					}
				%>                                    
                <%= memail %>
		  		</td>
		    </tr>
		  	<tr>
		  		<td class="name">생일</td>
		  		<td class="data">
		  		<%
					String mbir= vo.getTm_bir();
					if(mbir == null){
						mbir = "-";
					}
				%>                                    
                <%= mbir %>
		  		</td>
		    </tr>
		  	<tr>
		  		<td class="name">가입일</td>
		  		<td class="data">
		  		<%
					String mdate = vo.getTm_date();
					if(mdate == null){
						mdate = "-";
					}
				%>                                    
                <%= mdate %>
		  		</td>
		    </tr>
		  	<tr>
		  		<td class="name">탈퇴여부</td>
		  		<td class="data">
		  		<%
	               	String diss = vo.getTm_diss();
	               	if(diss == null || diss.equals("X")){
	               		diss = "-";
	               	}
	            %>
	            <%=diss %>
		  		</td>
		    </tr>
        </tbody>
    </table>
</div>

<!-- 회원 정보 수정/삭제 버튼 -->
	<div class="pt-3">
      <button type="button" idx="<%= vo.getTm_id() %>" class="btn btn-info" id="btnUpdate">수정하기</button>
      <button type="button" idx="<%= vo.getTm_id() %>" class="btn btn-warning" id="btnDelete">탈퇴하기</button>
      <input type="button" class="btn btn-outline-secondary" value="돌아가기" style="border:1px solid lightgray" id="back" onclick="location.href='/Travel_Maker/MemManage.do'">
    </div>
    
    
    
  <!-- Modal -->
		<div id="myModal" class="modal fade" role="dialog">
		  <div class="modal-dialog modal-lg">
		
		    <!-- Modal content-->
		    <div class="modal-content">
		      <div class="modal-header">
		        <h3 class="modal-title">회원정보수정</h3>
		        <button type="button" class="close" data-dismiss="modal">&times;</button>
		      </div>
		      <div class="modal-body">
		        <form id="modi">
			        <label>이름</label>
			        <input id="modiName" class="text" type="text" name="tm_name" value="<%=vo.getTm_name()%>"><br>
			        <label>전화번호</label>
			        <input id="modiTel" class="text" type="text" name="tm_tel" value="<%=vo.getTm_tel()%>"><br>
	                <label>우편번호</label>
	                <input type="text" id="sample4_postcode" name="tm_zip" readonly>  
	                <button type="button" class="btn btn-success btn-sm" id="zip">우편번호 찾기</button><br>
	                <label>도로명주소</label>
	                <input type="text" id="sample4_roadAddress" name="tm_add1" readonly><br>
	                <label>지번주소</label>
	                <input type="text" id="sample4_jibunAddress" name="tm_add2" disabled><br>
	                <label>참고항목</label>
	                <input type="text" id="sample4_extraAddress" disabled><br>
			        <label>이메일</label>
			        <input id="modiEmail" class="text" type="text" name="tm_email" value="<%=vo.getTm_email()%>"><br>
			        <span id="guide" style="color:red;display:none"></span><br>
			        <input type="hidden" name="tm_id" value="<%=vo.getTm_id() %>"  >
		        </form>
		      </div>
		      <div class="modal-footer">
		        <input type="button" class="btn btn-primary" value="등록" id="send">
		        <button type="button" class="btn btn-default" data-dismiss="modal">닫기</button>
		      </div>
		    </div>
		  </div>
		</div>   
    </div>
    </div>
    <br><br>
   	<div id="footers"></div>
</body>
</html>