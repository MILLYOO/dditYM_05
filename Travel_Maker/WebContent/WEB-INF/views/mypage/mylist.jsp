<%@page import="tm.member.vo.MemberVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
    
    <%
    	MemberVO myList = (MemberVO)request.getAttribute("myList");    
    %>
 <!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link
	href="https://fonts.googleapis.com/css2?family=Do+Hyeon&display=swap"
	rel="stylesheet">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
<script src="../js/jquery.serializejson.min.js"></script>
<script
	src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
<link rel="stylesheet" type="text/css" href="../page/css/default.css">
<link rel="stylesheet" type="text/css" href="../page/css/style2.css">
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/page/css/default.css">
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/page/css/style2.css">


<style type="text/css">
h1 {
	margin: 20px 20px 20px 20px;
	text-align: center;
}

body {
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
</style>

<script type="text/javascript">

$(document).ready(function() {
	$("#header").load("<%=request.getContextPath()%>/views/MainHeader.jsp")
	$("#footers").load("<%=request.getContextPath()%>/views/footer.html")
});



	
$(function(){
	
	//이름 정규표현식	   
	  namereg = /^[가-힣]{2,6}$/
	   	   $('#name').on('keyup', function() {
				bb = $(this).val();
				
				if(!(namereg.test(bb))){
					$(this).css('border', '1px solid red');//틀리면 빨강
					return false;
				}
				$(this).css('border', '1px solid blue'); //맞으면 파랑
			})
			
	//이메일 정규표현식
	mailreg = /^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*.[a-zA-Z]{2,3}$/
	   	   $('#email').on('keyup', function() {
				cc = $(this).val();
				
				if(!(mailreg.test(cc))){
					$(this).css('border', '1px solid red');//틀리면 빨강
					return false;
				}
				$(this).css('border', '1px solid blue'); //맞으면 파랑
			})			
	
 //전화번호 정규표현식 -- 자동으로 하이픈 입력해준다.
 function autoHypenTel(str) {
  str = str.replace(/[^0-9]/g, '');
  var tmp = '';

    // 핸드폰 및 다른 지역 전화번호 일 경우
    if (str.length < 4) {
      return str;
    } else if (str.length < 7) {
      tmp += str.substr(0, 3);
      tmp += '-';
      tmp += str.substr(3);
      return tmp;
    } else if (str.length < 11) {
      tmp += str.substr(0, 3);
      tmp += '-';
      tmp += str.substr(3, 3);
      tmp += '-';
      tmp += str.substr(6);
      return tmp;
    } else {
      tmp += str.substr(0, 3);
      tmp += '-';
      tmp += str.substr(3, 4);
      tmp += '-';
      tmp += str.substr(7);
      return tmp;
    }

  return str;
}
	
	$('#telInput').keyup(function (event) {
		  event = event || window.event;
		  var _val = this.value.trim();
		  this.value = autoHypenTel(_val);
		});

	
	
	
	
	
	

	//전송

		//전체 form에서 공백확인	
		$('#send').on('click', function() {

			//입력한 아이디 값을 가져온다.
			formvalue = $('.form-control').val().trim();

			//공백체크 
			if (formvalue.length < 1) {
				alert("빈칸이 존재합니다. 빈칸을 확인해주세요");
				return false;
			}

		//전체 입력 확인

			$.ajax({
				url : '/Travel_Maker/JoinSend.do',
				type : 'post',
				data : $('form').serializeJSON(),
				success : function(res) {
					$('#spansend').html(res.msg)


				},
				dataType : 'json'
			})
		})

		// 수정버튼 누르면 (수정 창 뜨는 부분)
		$('#write').on('click', function() {
			
			$('#write').hide();
			$('#send').show();

			modiname = $(document).find('#namebox').text();
			$('#modifyname #name').val(modiname);
			$(document).find('#namebox').empty().append($('#modifyname'));
			$('#modifyname').show();

			moditel = $(document).find('#telbox').text();
			$('#modifytel #telInput').val(moditel);
			$(document).find('#telbox').empty().append($('#modifytel'));
			$('#modifytel').show();

			modizip = $(document).find('#zipbox').text();
			$('#modifyzip #sample4_postcode').val(modizip);
			$(document).find('#zipbox').empty().append($('#modifyzip'));
			$('#modifyzip').show();

			modiadd1 = $(document).find('#add1box').text();
			$('#modifyadd1 #sample4_roadAddress').val(modiadd1);
			$(document).find('#add1box').empty().append($('#modifyadd1'));
			$('#modifyadd1').show();

			modiadd2 = $(document).find('#add2box').text();
			$('#modifyadd2 #sample4_jibunAddress').val(modiadd2);
			$(document).find('#add2box').empty().append($('#modifyadd2'));
			$('#modifyadd2').show();

			modimail = $(document).find('#mailbox').text();
			$('#modifymail #email').val(modimail);
			$(document).find('#mailbox').empty().append($('#modifymail'));
			$('#modifymail').show();

			modibirth = $(document).find('#birthbox').html();
			$('#modifybirth #bir').val(modibirth);
			$(document).find('#birthbox').empty().append($('#modifybirth'));
			$('#modifybirth').show();

		})
		
		// 회원탈퇴
		$('#dismiss').on('click',function(){
			modiId = $(this).attr('idx');
			
			if(confirm("정말 탈퇴 하시겠습니까?") == true){
				$.ajax({
					url : '/Travel_Maker/MemManageDelete.do',
					type : 'post',
					data : {"modiId" : modiId},
					success : function(res) {
						alert('탈퇴' + res.res + ', 로그아웃됩니다.');
						location.href="/Travel_Maker/logout.do";
					},
					error : function(xhr){
						alert('상태 : ' + xhr.status);
					},
					dataType : 'json'
				})			
			}else{
				return;
			}
		})
		

	})

	function Zip() {
		new daum.Postcode(
				{
					oncomplete : function(data) {
						// 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분.

						// 도로명 주소의 노출 규칙에 따라 주소를 표시한다.
						// 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기 한다.
						var roadAddr = data.roadAddress; // 도로명 주소 변수
						var extraRoadAddr = ''; // 참고 항목 변수

						// 법정동명이 있을 경우 추가한다. (법정리는 제외)
						// 법정동의 경우 마지막 문자가 "동/로/가"로 끝난다.
						if (data.bname !== '' && /[동|로|가]$/g.test(data.bname)) {
							extraRoadAddr += data.bname;
						}
						// 건물명이 있고, 공동주택일 경우 추가한다.
						if (data.buildingName !== '' && data.apartment === 'Y') {
							extraRoadAddr += (extraRoadAddr !== '' ? ', '
									+ data.buildingName : data.buildingName);
						}
						// 표시할 참고항목이 있을 경우, 괄호까지 추가한 최종 문자열을 만든다.
						if (extraRoadAddr !== '') {
							extraRoadAddr = ' (' + extraRoadAddr + ')';
						}

						// 우편번호와 주소 정보를 해당 필드에 넣는다.
						document.getElementById('sample4_postcode').value = data.zonecode;
						document.getElementById("sample4_roadAddress").value = roadAddr;
						document.getElementById("sample4_jibunAddress").value = data.jibunAddress;

						// 참고항목 문자열이 있을 경우 해당 필드에 넣는다.
						if (roadAddr !== '') {
							document.getElementById("sample4_extraAddress").value = extraRoadAddr;
						} else {
							document.getElementById("sample4_extraAddress").value = '';
						}

						var guideTextBox = document.getElementById("guide");
						// 사용자가 '선택 안함'을 클릭한 경우, 예상 주소라는 표시를 해준다.
						if (data.autoRoadAddress) {
							var expRoadAddr = data.autoRoadAddress
									+ extraRoadAddr;
							guideTextBox.innerHTML = '(예상 도로명 주소 : '
									+ expRoadAddr + ')';
							guideTextBox.style.display = 'block';

						} else if (data.autoJibunAddress) {
							var expJibunAddr = data.autoJibunAddress;
							guideTextBox.innerHTML = '(예상 지번 주소 : '
									+ expJibunAddr + ')';
							guideTextBox.style.display = 'block';
						} else {
							guideTextBox.innerHTML = '';
							guideTextBox.style.display = 'none';
						}
					}
				}).open();
	}
</script>


<title>내 정보</title>
</head>
<body>
	<div id="header"></div>
<div class="container">
    <div class="row">
        <h1>내 정보 조회</h1>

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
                    	
                            <tbody id="box">
                            	<tr>
                            		<td class="name" id="id">아이디</td>
                            		<td class="data" id="idbox"><%= myList.getTm_id() %></td>
                            	</tr>
                            	<tr>
                            		<td class="name" id="pass">비밀번호</td>
                            		<td class="data" id="passbox"><%= myList.getTm_pass() %></td>
                            	</tr>
                            	
                            	
                            	<!-- 회원이 수정 가능한 부분 시작 -->
                            	<tr>
                            		<td class="name" id="name">이름</td>
                            		<td class="data" id="namebox"><%= myList.getTm_name() %></td>
                            	</tr>
                            	<tr>
                            		<td class="name" id="tel">번호</td>
                            		<td class="data" id="telbox"><%= myList.getTm_tel() %></td>
                            	</tr>
                            	<tr>
                            		<td class="name" id="zip">우편번호</td>
                            		<td class="data" id="zipbox"><%= myList.getTm_zip() %></td>
                            	</tr>
                            	<tr>
                            		<td class="name" id="add1">도로명주소</td>
                            		<td class="data" id="add1box"><%= myList.getTm_add1() %></td>
                            	</tr>
                            	<tr>
                            		<td class="name" id="add2">지번주소</td>
                            		<td class="data" id="add2box"><%= myList.getTm_add2() %></td>
                            	</tr>
                            	<tr>
                            		<td class="name" id="mail">메일</td>
                            		<td class="data" id="mailbox"><%= myList.getTm_email() %></td>
                            	</tr>
                            	<tr>
                            		<td class="name" id="birth">생일</td>
                            		<td class="data" id="birthbox"><%= myList.getTm_bir() %></td>
                            	</tr>
                            	<!-- 회원이 수정 가능한 부분 끝 -->
                            	
                            	
                            	<tr>
                            		<td class="name" id="date">가입일</td>
                            		<td class="data"><%= myList.getTm_date() %></td>
                            	</tr>
                            	
                            </tbody>
    			
                    	
                        </table>
                        <div class="write">
                        <div id = "optionbox">
                        <input  type="button" value="수정하기"  id="write" class="btn btn-primary">
                        <input  type="button" value="수정완료"  id="send" style="display: none" class="btn btn-primary">
                    	<input  type="button" value="탈퇴하기" idx="<%= myList.getTm_id() %>" id="dismiss" class="btn btn-primary">                   	
                    	<input  type="button" value="돌아가기"  id="back" onclick="location.href='/Travel_Maker/views/home.html'" class="btn btn-primary">
                    	</div>
                    	</div>
                    </div>
                    
                        
                    <!-- end project-list -->

                    <div class="pt-3">
                    	<div id = "pageList">

                    	</div>
                    </div>
                </div>
            </div>
        </div>
    </div>

		<!-- 회원 정보 수정 부분 -->
		<!-- 비밀번호 -->
		<div class="form-group" style="display:none" id="modifypass">
			<div class="col-sm-3">
				<input type="text" class="form-control" id="pass"
					placeholder="비밀번호" name="tm_pass">
			</div>
		</div>
		
		<!-- 이름 -->
		<div class="form-group" style="display:none" id="modifyname">
			<div class="col-sm-3">
				<input type="text" class="form-control" id="name" name="tm_name" placeholder="이름">
			</div>
		</div>

		<!-- 번호 -->
		<div class="form-group"  style="display:none" id="modifytel">
			<div class="col-sm-3" >
				<input type="tel" class="form-control m-input" name="tm_tel" placeholder="폰 번호"
					id="telInput" required pattern="[0-9]{2,3}-[0-9]{3,4}-[0-9]{4}"
					maxlength="13" />
			</div>
		</div>

		<!-- 우편번호 -->
		<div class="form-group" style="display:none" id="modifyzip">
			<div class="col-sm-3"  >
				<input type="text" class="form-control" id="sample4_postcode" name="tm_zip"  placeholder="우편 번호"readonly>
			</div>
			<button type="button" class="btn btn-success btn-sm" onclick="Zip()" id="zip">우편번호 찾기</button>
		</div>
		
		<!-- 도로명 주소 -->
		<div class="form-group" style="display:none" id="modifyadd1">
			<div class="col-sm-5">
				<input type="text" class="form-control" id="sample4_roadAddress"
					name="tm_add1" placeholder="도로명주소" readonly>
			</div>
		</div>
		
		<!-- 지번 주소 -->
		<div class="form-group" style="display:none" id="modifyadd2">
			<div class="col-sm-5">
				<input type="text" class="form-control" id="sample4_jibunAddress"
					placeholder="지번주소" disabled>
			</div>
		</div>
		
		<!-- 이메일 -->
		<div class="form-group" style="display:none" id="modifymail">
			<div class="col-sm-3">
				<input type="email" class="form-control" id="email"
					placeholder="이메일" name="tm_email">
			</div>
		</div>
		
		<!-- 생일 -->
		<div class="form-group" style="display: none" id="modifybirth">
			<div class="col-sm-3">
				<input type="date" class="form-control" id="bir" placeholder="생년월일"
					name="tm_bir">
			</div>



			<div class="form-group" style="display: none" >
				<label class="control-label col-sm-2" for="extra">참고항목:</label>
				<div class="col-sm-5">
					<input type="text" class="form-control" id="sample4_extraAddress"
						placeholder="참고항목" disabled>
				</div>
			</div>

			<div class="form-group" style="display: none" >
				<label class="control-label col-sm-2" for="auth"></label>
				<div class="col-sm-3">
					<input type="hidden" class="form-control" id="auth"
						name="tm_author">
				</div>
			</div>

		</div>
		</div>
			<div id="footers"></div>
		</body>
</html>