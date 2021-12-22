<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>	
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<nav class="subHeader">
	<div id="subHeader-content">
		<h3 id="subHeader-name">재고정보입력</h3>
	</div>
</nav>

<div id="jspcontent">
<div class="centered">
<div style="display: flex;">
	<div>
	    <div class="upperBox">
	    	<div id="upperTitle">
	    		<h2 id="h2">품목정보</h2>
	   		</div>
		
		<table class="type05">
		  <tr>
		    <th scope="row">품목계정</th>
		    <td><input type="text" style="border:0;width:300px;" value="${stockTakingwarItem.icodeName}" disabled></td>
		  </tr>
		  <tr>
		    <th scope="row">품목코드</th>
		    <td><input type="text" style="border:0;width:300px;" value="${stockTakingwarItem.rawsCode}" disabled></td>
		  </tr>
		  <tr>
		    <th scope="row">품목명</th>
		    <td><input type="text" style="border:0;width:300px;" value="${stockTakingwarItem.rawsName}" disabled></td>
		  </tr>
		</table>
		<table class="type05">
		  <tr>
		    <th scope="row">규격</th>
		    <td><input type="text" style="border:0;width:300px;" value="${stockTakingwarItem.gcommName}" disabled></td>
		  </tr>
		  <tr>
		    <th scope="row">단위</th>
		    <td><input type="text" style="border:0;width:300px;" value="${stockTakingwarItem.ucommStname}" disabled></td>
		  </tr>
		  <tr>
		    <th scope="row">분류</th>
		    <td><input type="text" style="border:0;width:300px;" value="${stockTakingwarItem.ldivName} 
		    ${stockTakingwarItem.mdivName} ${stockTakingwarItem.sdivName}" disabled></td>
		  </tr>
		</table>
		    </div>
	</div>
	<div>
		<div class="lowerBox">
			<div id="lowerTitle">
			<h2 id="h2">재고정보</h2>
			</div>
		
		<table class="type05">
		  <tr>
		    <th scope="row">창고명</th>
		    <td><input type="text" style="border:0;width:300px;" value="${stockTakingwarItem.warName}" disabled></td>
		  </tr>
		  <tr>
		    <th scope="row">적정재고량</th>
		    <td><input type="text" style="border:0;width:300px;" value="${stockTakingwarItem.rawsAdqinv}" disabled></td>
		  </tr>
		</table>
		<table class="type05">
		  <tr>
		    <th scope="row">담당자</th>
		    <td><input type="text" style="border:0;width:300px;" value="${stockTakingwarItem.deptName} ${stockTakingwarItem.empName}" disabled></td>
		  </tr>
		  <tr>
		    <th scope="row">현재고량</th>
		    <td><input type="text" style="border:0;width:300px;" value="${stockTakingwarItem.wrQty}" disabled></td>
		  </tr>
		</table>

		</div>
	</div>   

</div>
			<div style="display: flex;">
				<form:form id="stockTakingForm" action="${cPath }/stock/stockTakingSave.do">
				<div style="position: relative; left: -400px; top: -17px;">
					<input type="hidden" name="prodCode" value="${stockTakingwarItem.rawsCode}" />
					<input type="hidden" name="prodName" value="${stockTakingwarItem.rawsName}" />
					<input type="hidden" name="warName" value="${stockTakingwarItem.warName}" />
					<div id="cal">
						<input type=button value="+" class="myButton" onClick="javascript:this.form.stotaQty.value++;">
						<input type=text value="${stockTakingwarItem.wrQty}" name="stotaQty" id="stotaQty"> 
						<input type=button value="-" class="myButton" onClick="javascript:this.form.stotaQty.value--;">
					</div>
				</div>
			
				<table class="type03">
				  <tr>
				    <th scope="row">비고</th>
				    <td colspan="4"><input type="text" style="border: 0;" name="stotaMemo"></td>
				  </tr>
				  <tr>
				    <th scope="row">수취인</th>
				    <td><input type="text" style="border: 0;" name="stotaRecv"></td>
				    <th scope="row">발신인</th>
				    <td><input type="text" style="border: 0;" name="stotaSend"></td>
				  </tr>
				  <tr>
				  </tr>
				</table>
			</form:form>
			<div style="position: relative; left: -742px; top: -12px;">
			<button class="btn btn-outline-primary btn-sm" id="cancel">취소</button>
			<button class="btn btn-outline-primary btn-sm" id="request">요청</button>
			</div>
		</div>
</div>
</div>


<!-- 취소 및 적용 버튼에 대한 스크립트 -->
<script>
	//취소
	$("#cancel").on("click",function(){
		//index.jsp 화면으로 이동
		window.history.back();
	})
	
	//요청
	$("#request").on("click",function(){
		// /stock/stockTaking.do <== 비동기 요청, 받는타입 text, form 태그 전송
		$("#stockTakingForm").ajaxForm({
			method : "post",
			dataType : "json",
			success : function(resp) {
				if(resp.result == 'OK'){
					toastr.success(resp.message);
				}else{
					for(var idx in resp.message){
						console.log(resp.message)
						toastr.error(resp.message[idx]);
					}
				}	
			},
			error : function(xhr, errorResp, error) {
				console.log(xhr);
				console.log(errorResp);
				console.log(error);
			}
				
		}).submit();
	})
</script>