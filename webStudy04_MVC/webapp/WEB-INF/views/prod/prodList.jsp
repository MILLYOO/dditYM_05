<%@page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<jsp:include page="/includee/preScript.jsp"/>
</head>
<body>
<table>
	<thead>
		<tr>
			<th>일련번호</th>
			<th>상품명</th>
			<th>분류명</th>
			<th>거래처명</th>
			<th>구매가</th>
			<th>마일리지</th>
		</tr>	
	</thead>
	<tbody id ="listBody">
	</tbody>
	<tfoot>
		<tr>
			<td colspan="6">
				<div id="pagingArea">
				${pagingVO.pagingHTML }
<%-- 				<%=pagingVO.getPagingHTML() %> 위의 el로 바꿈.--%> 
				</div>
				<div id="searchUI">
				<!-- 변경전 -->
<!-- 					<select name="searchType"> -->
<!-- 						<option value>전체</option>					 -->
<!-- 						<option value="name">상품명</option>					 -->
<!-- 						<option value="lprod">분류명</option>					 -->
<!-- 						<option value="buyer">거래처명</option>					 -->
<!-- 					</select> -->
<!-- 					<input type="text" name="searchWord" /> -->
				<!-- 변경 후 -->
					<select name="prodLgu" id="prodLgu">
						<option value>상품분류</option>
						<c:forEach items="${lprodList }" var="lprod">
							<option value="${lprod['lprodGu'] }">${lprod.lprodNm }</option>
						</c:forEach>
						<%--
							List<Map<String, Object>> lprodList = (List) request.getAttribute("lprodList");
							for(Map<String,Object> map : lprodList){
								--%>
<%-- 									<option value='<%=map.get("lprodGu")%>'><%=map.get("lprodNm")%></option> <!-- 동적으로 만들어야됨 --> --%>
								<%--
							}
						--%>
					</select>
					<select name = "prodBuyer" id="prodBuyer">
						<option value>거래처</option>
						<c:forEach items="${buyerList }" var="buyer">
							<option value="${buyer.buyerId}" class="${buyer.buyerLgu}">${buyer.buyerName}</option>
						</c:forEach>
						<%--
							List<BuyerVO> buyerList = (List) request.getAttribute("buyerList");
						for(BuyerVO vo : buyerList){
								--%>
<%-- 									<option value="<%=vo.getBuyerId()%>" class="<%=vo.getBuyerLgu()%>"> <%=vo.getBuyerName()%></option> <!-- 동적으로 만들어야됨 --> --%>
								<%--
							}
						--%>
					</select>
					<input type="text" name="prodName" />
					<input type="button" id="searchBtn" value="검색"/>
					<input type="button" class="linkBtn" data-gopage="${pageContext.request.contextPath}/prod/prodInsert.do" value="신규등록"/>
				</div>
			</td>
		</tr>
	</tfoot>
</table>
<form id="searchForm">
<!-- 	<input type="text" name="searchType" /> -->
<!-- 	<input type="text" name="searchWord" /> -->
	<input type="hidden" name="page" /> 
	<input type="hidden" name="prodLgu" />
	<input type="hidden" name="prodBuyer" />
	<input type="hidden" name="prodName" />
</form>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/custom/paging.js"></script>
<script type="text/javascript">
	$("[name='prodLgu']").val("${pagingVO.detailSearch.prodLgu}");
	$("[name='prodBuyer']").val("${pagingVO.detailSearch.prodBuyer}");
	$("[name='prodName']").val("${pagingVO.detailSearch.prodName}");
	let prodBuyer = $('#prodBuyer');
	$("#prodLgu").on("change", function(){
		let selectedLgu = $(this).val();
		if(!selectedLgu){
			
		}else { // 정상 option
			prodBuyer.find("option").hide();
			prodBuyer.find("option."+selectedLgu).show();
			prodBuyer.find("option:first").show();
		}
	});
	let listBody = $("#listBody");
	let pagingArea = $("#pagingArea");
	let pageTag = $("[name='page']");
	$("#searchForm").paging({
		searchUI : "#searchUI",
		searchBtn : "#searchBtn",
		pagingArea : "#pagingArea",
		pageKey  : "page",
		pageTag : "[name='page']"
	}).ajaxForm({
		dataType:"json",
		success:function(resp){
			listBody.empty();
			pagingArea.empty();
			pageTag.val("");
			let prodList = resp.dataList;
			let pagingHTML = resp.pagingHTML;
			let trTags = [];
			if(prodList){
				$.each(prodList, function(idx, prod){
					let trTag = $("<tr>").append(
						$("<td>").html(prod.rnum),		
						$("<td>").html(
							$("<a>").text(prod.prodName)
									.attr({
										href : "${pageContext.request.contextPath}/prod/prodView.do?what="+prod.prodId,
										title :"구매자수 : "+prod.memCount 
									})
						),		
						$("<td>").html(prod.lprodNm),		
						$("<td>").html(prod.buyerName),		
						$("<td>").html(prod.prodCost),		
						$("<td>").html(prod.prodMileage)		
					);
					trTags.push(trTag)
				});
			}else{
				let trTag = $("<tr>").html(
						$("<td>").attr({
						  colspan:"6"	
						})
					);
				trTags.push(trTag);
			}
			
			listBody.append(trTags);
			pagingArea.html(pagingHTML);
		}
	}).submit();
</script>
<jsp:include page="/includee/postScript.jsp"></jsp:include>
</body>
</html>