<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security" %>    
<!-- 사이드 메뉴 -->
<ul class="navbar-nav bg-gradient-primary sidebar sidebar-dark accordion" id="accordionSidebar">

<div class="sidebar-brand d-flex align-items-center justify-content-center">
	<img alt="" src="${cPath}/resources/images/HUB-WEB.png" onclick="location.href='${cPath}/common/main.do'" style="width: 100px; height: 100px; cursor: pointer;">
</div>

<!-- 관리 / 현황 메뉴들 -->

<li class="nav-item">
<div class="accordion accordion-flush" id="accordionFlushExample">

<!--=========================================================영업관리======================================================================= -->
<security:authorize access="hasAnyRole('ROLE_ADMIN', 'ROLE_SALE')">    
    <hr class="sidebar-divider my-0">
	<div class="accordion-item">
   		<h6 class="accordion-header" id="flush-headingOne">
	     	<a class="nav-link collapsed">
		    	<i class="fas fa-piggy-bank"></i>
		      	<button class="btn btn-primary" type="button" data-bs-toggle="collapse" data-bs-target="#flush-collapseOne" aria-expanded="false" aria-controls="flush-collapseOne" style="display: inline-flex">
		        	영업관리
		     	</button>
		     	<label>
		          <c:if test="${unitMap.ES gt 0||unitMap.OR gt 0||unitMap.RE gt 0||unitMap.RA gt 0||unitMap.SA gt 0}">
				    <i class="fas fa-bell" style="color:orange;"></i>
				  </c:if>
		        </label>
		<!--       / -->
		<!--       <button class="btn btn-primary" type="button" data-bs-toggle="collapse" data-bs-target="#flush-collapseHOne" aria-expanded="false" aria-controls="flush-collapseHOne"> -->
		<!--         영업현황 -->
		<!--       </button> -->
	   		</a>
    	</h6>
    	
		<security:authorize access="hasRole('ROLE_ADMIN')">
		     <div id="flush-collapseOne" class="accordion-collapse collapse" aria-labelledby="flush-headingOne" data-bs-parent="#accordionFlushExample">
		</security:authorize>
		<security:authorize access="hasRole('ROLE_SALE')">
		     <div id="flush-collapseOne" class="accordion-collapse collapse show" aria-labelledby="flush-headingOne" data-bs-parent="#accordionFlushExample">
		</security:authorize>
        <div class="card card-body">
          <a class="collapse-item" href="${cPath}/sales/EstimateRetrieveView.do">
          	견적서
          	<label>
	          <c:if test="${unitMap.ES gt 0}">
			    <i class="fas fa-bell" style="color:orange;"></i>
			  </c:if>
	        </label>
          </a>
	          
          <a class="collapse-item" href="${cPath}/sales/OrderbookRetrieveView.do">
          	수주서
          	<label>
	          <c:if test="${unitMap.OR gt 0}">
			    <i class="fas fa-bell" style="color:orange;"></i>
			  </c:if>
	        </label>
          </a>
          <a class="collapse-item" href="${cPath}/sales/ReleaseOrderRetrieveView.do">
          	출고지시서
          	<label>
	          <c:if test="${unitMap.RE gt 0}">
			    <i class="fas fa-bell" style="color:orange;"></i>
			  </c:if>
	        </label>
          </a>
          <a class="collapse-item" href="${cPath}/sales/ReleaseProcessingRetrieveView.do">
          	출고처리
          	<label>
	          <c:if test="${unitMap.RA gt 0}">
			    <i class="fas fa-bell" style="color:orange;"></i>
			  </c:if>
	        </label>
          </a>
          <a class="collapse-item" href="${cPath}/sales/SalesCloseRetrieveView.do">
          	매출마감
          	<label>
	          <c:if test="${unitMap.SA gt 0}">
			    <i class="fas fa-bell" style="color:orange;"></i>
			  </c:if>
	        </label>
          </a>
          <a class="collapse-item" href="${cPath}/sales/salesRankListView.do">매출순위표</a>
          <a class="collapse-item" href="${cPath}/sales/profitMarginStatusRetrieve.do">매출이익현황</a>
        </div>  
     </div>
<!--     <div id="flush-collapseHOne" class="accordion-collapse collapse" aria-labelledby="flush-headingHOne" data-bs-parent="#accordionFlushExample"> -->
<!-- 		<div class="card card-body"> -->
<!--           <a class="collapse-item" href="">견적현황</a> -->
<!--           <a class="collapse-item" href="">수주현황</a> -->
<!--           <a class="collapse-item" href="">출고지시현황</a> -->
<!--           <a class="collapse-item" href="">출고현황</a> -->
<!--           <a class="collapse-item" href="">매출마감현황</a> -->
<!--           <a class="collapse-item" href="">매출순위표</a> -->
<!--           <a class="collapse-item" href="">매출이익현황</a> -->
<!--           <a class="collapse-item" href="">매출비교현황</a> -->
<!--         </div>   -->
<!--     </div> -->
  </div>
</security:authorize>   
<!--=========================================================구매관리======================================================================= -->
<security:authorize access="hasAnyRole('ROLE_ADMIN', 'ROLE_BUY')">
  <hr class="sidebar-divider my-0">
  <div class="accordion-item">
  	  <h6 class="accordion-header" id="flush-headingTwo">
      	<a class="nav-link collapsed">
	    	<i class="fas fa-shopping-cart"></i>
	        <button class="btn btn-primary" type="button" data-bs-toggle="collapse" data-bs-target="#flush-collapseTwo" aria-expanded="false" aria-controls="flush-collapseTwo" style="display: inline-flex">
	       	  구매관리
	        </button>
	        <label>
	          <c:if test="${unitMap.OD gt 0||unitMap.IC gt 0||unitMap.PC gt 0}">
			    <i class="fas fa-bell" style="color:orange;"></i>
			  </c:if>
	        </label>
	        
	<!--       / -->
	<!--       <button class="btn btn-primary" type="button" data-bs-toggle="collapse" data-bs-target="#flush-collapseHTwo" aria-expanded="false" aria-controls="flush-collapseHTwo"> -->
	<!--        구매현황 -->
	<!--       </button> -->
   	    </a>
   	 </h6>
		<security:authorize access="hasRole('ROLE_ADMIN')">
		    <div id="flush-collapseTwo" class="accordion-collapse collapse" aria-labelledby="flush-headingTwo" data-bs-parent="#accordionFlushExample">
		</security:authorize>
		<security:authorize access="hasRole('ROLE_BUY')">
		    <div id="flush-collapseTwo" class="accordion-collapse collapse show" aria-labelledby="flush-headingTwo" data-bs-parent="#accordionFlushExample">
		</security:authorize>
	      <div class="card card-body">
	          <a class="collapse-item" href="${cPath}/buy/orderList.do">발주서
		          <c:if test="${unitMap.OD gt 0}">
				    <i class="fas fa-bell" style="color:orange;"></i>
				  </c:if>
			  </a>
	          <a class="collapse-item" href="${cPath}/buy/incomingList.do">
	          	입고처리
	          	<label>
		          <c:if test="${unitMap.IC gt 0}">
				    <i class="fas fa-bell" style="color:orange;"></i>
				  </c:if>
		        </label>
	          </a>
	          <a class="collapse-item" href="${cPath}/buy/purchCloseList.do">
	          	매입마감
	          	<label>
		          <c:if test="${unitMap.PC gt 0}">
				    <i class="fas fa-bell" style="color:orange;"></i>
				  </c:if>
		        </label>
	          </a>
	          <a class="collapse-item" href="${cPath}/buy/purchRankList.do">매입순위표</a>
	      </div>
	    </div>
<!--     <div id="flush-collapseHTwo" class="accordion-collapse collapse" aria-labelledby="flush-headingHTwo" data-bs-parent="#accordionFlushExample"> -->
<!--       <div class="card card-body"> -->
<!--           <a class="collapse-item" href="">발주현황</a> -->
<!--           <a class="collapse-item" href="">입고현황</a> -->
<!--           <a class="collapse-item" href="">매입마감현황</a> -->
<!--           <a class="collapse-item" href="">매입순위표</a> -->
<!--           <a class="collapse-item" href="">매출비교현황</a> -->
<!--           <a class="collapse-item" href="">발주대비입고현황</a> -->
<!-- 	  </div> -->
<!--     </div> -->
  </div>
</security:authorize>
<!--=========================================================생산관리======================================================================= -->
<security:authorize access="hasAnyRole('ROLE_ADMIN', 'ROLE_PRODUCE')"> 
  <hr class="sidebar-divider my-0">
  <div class="accordion-item">
    <h6 class="accordion-header" id="flush-headingThree">
    <a class="nav-link collapsed">
    <i class="fas fa-industry"></i>
      <button class="btn btn-primary" type="button" data-bs-toggle="collapse" data-bs-target="#flush-collapseThree" aria-expanded="false" aria-controls="flush-collapseThree" style="display: inline-flex">
       	생산관리
      </button>
      <label>
         <c:if test="${unitMap.ME gt 0||unitMap.PR gt 0||unitMap.ID gt 0}">
	    	<i class="fas fa-bell" style="color:orange;"></i>
	  	</c:if>
      </label>
      
<!--       / -->
<!--       <button class="btn btn-primary" type="button" data-bs-toggle="collapse" data-bs-target="#flush-collapseHThree" aria-expanded="false" aria-controls="flush-collapseHThree"> -->
<!--        생산현황 -->
<!--       </button> -->
   </a>
    </h6>
   		<security:authorize access="hasRole('ROLE_ADMIN')">
		    <div id="flush-collapseThree" class="accordion-collapse collapse" aria-labelledby="flush-headingThree" data-bs-parent="#accordionFlushExample">
		</security:authorize>
		<security:authorize access="hasRole('ROLE_PRODUCE')">
		    <div id="flush-collapseThree" class="accordion-collapse collapse show" aria-labelledby="flush-headingThree" data-bs-parent="#accordionFlushExample">
		</security:authorize>
    
      <div class="card card-body">
          <a class="collapse-item" href="${cPath}/produce/productionInstructionList.do">
          	생산지시서
          	<label>
	          <c:if test="${unitMap.ID gt 0}">
			    <i class="fas fa-bell  " style="color:orange;"></i>
			  </c:if>
	        </label>
          </a>
          <a class="collapse-item" href="${cPath}/produce/materialReleaseList.do">
          	자재출고처리
          	<label>
	          <c:if test="${unitMap.ME gt 0}">
			    <i class="fas fa-bell" style="color:orange;"></i>
			  </c:if>
	        </label>
          </a>
          <a class="collapse-item" href="${cPath}/produce/productIncomingList.do">
          	생산품입고처리
          	<label>
	          <c:if test="${unitMap.PR gt 0}">
			    <i class="fas fa-bell" style="color:orange;"></i>
			  </c:if>
	        </label>
          </a>
      </div>
    </div>
<!--     <div id="flush-collapseHThree" class="accordion-collapse collapse" aria-labelledby="flush-headingHThree" data-bs-parent="#accordionFlushExample"> -->
<!--       <div class="card card-body"> -->
<!--           <a class="collapse-item" href="">생산지시현황</a> -->
<!--           <a class="collapse-item" href="">자재출고현황</a> -->
<!--           <a class="collapse-item" href="">생산입고현황</a> -->
<!--           <a class="collapse-item" href="">생산지시대비출고현황</a> -->
<!--           <a class="collapse-item" href="">생산지시대비입고현황</a> -->
<!--           <a class="collapse-item" href="">BOM등록현황</a> -->
<!--           <a class="collapse-item" href="">제품별자재투입현황</a> -->
<!-- 	  </div> -->
<!--     </div> -->
  </div>
</security:authorize>

<!--=========================================================재고관리======================================================================= -->
<security:authorize access="hasAnyRole('ROLE_ADMIN', 'ROLE_STOCK')"> 
  <hr class="sidebar-divider my-0">
  <div class="accordion-item">
    <h6 class="accordion-header" id="flush-headingFour">
    <a class="nav-link collapsed">
    <i class="fas fa-cubes"></i>
      <button class="btn btn-primary" type="button" data-bs-toggle="collapse" data-bs-target="#flush-collapseFour" aria-expanded="false" aria-controls="flush-collapseFour" style="display: inline-flex">
       재고관리
      </button>
      <label>
         <c:if test="${unitMap.AT gt 0||unitMap.SE gt 0||unitMap.SU gt 0}">
	    	<i class="fas fa-bell" style="color:orange;  margin-right: 12px;"></i>
	  	</c:if>
      </label>
      
      <button class="btn btn-primary" type="button" data-bs-toggle="collapse" data-bs-target="#flush-collapseHFour" aria-expanded="false" aria-controls="flush-collapseHFour"  style="margin-left: 21%;">
       재고현황
      </button>
   </a>
    </h6>
     	<security:authorize access="hasRole('ROLE_ADMIN')">
		    <div id="flush-collapseFour" class="accordion-collapse collapse" aria-labelledby="flush-headingFour" data-bs-parent="#accordionFlushExample">
		</security:authorize>
		<security:authorize access="hasRole('ROLE_STOCK')">
		    <div id="flush-collapseFour" class="accordion-collapse collapse show" aria-labelledby="flush-headingFour" data-bs-parent="#accordionFlushExample">
		</security:authorize>
      <div class="card card-body">
          <a class="collapse-item" href="${cPath}/stock/stockTakingIndex.do">재고실사</a>
          <a class="collapse-item" href="${cPath}/stock/inOutAdjustmentRetrieve.do">
          	입출고조정
          	<label>
	          <c:if test="${unitMap.AT gt 0}">
			    <i class="fas fa-bell" style="color:orange;"></i>
			  </c:if>
	        </label>
          </a>
          <a class="collapse-item" href="${cPath}/stock/stockMoveRetrieve.do">
          	재고이동
          	<label>
	          <c:if test="${unitMap.SE gt 0}">
			    <i class="fas fa-bell" style="color:orange;"></i>
			  </c:if>
	        </label>
          </a>
          <a class="collapse-item" href="${cPath}/stock/inventoryValuationRetrieve.do">
          	재고평가
          	<label>
	          <c:if test="${unitMap.SU gt 0}">
			    <i class="fas fa-bell" style="color:orange;" ></i>
			  </c:if>
	        </label>
          </a>
      </div>
    </div>
    <div id="flush-collapseHFour" class="accordion-collapse collapse" aria-labelledby="flush-headingHFour" data-bs-parent="#accordionFlushExample">
      <div class="card card-body">
          <a class="collapse-item" href="${cPath}/stock/stockOverallStatusRetrieve.do">현재고총괄현황</a>
          <a class="collapse-item" href="${cPath}/stock/stockStatusByWarehouseRetrieve.do">창고별재고현황</a>
          <a class="collapse-item" href="${cPath}/stock/itemEnrollmentStatusRetrieve.do">품목등록현황</a>
          <a class="collapse-item" href="${cPath}/stock/defectiveItemStockStatusRetrieve.do">불량품재고현황</a>
<%--           <a class="collapse-item" href="${cPath}">규격군별재고현황</a> --%>
<%--           <a class="collapse-item" href="${cPath}/stock/StockValuationStatusRetrieve.do">재고평가현황</a> --%>
          <a class="collapse-item" href="${cPath}/stock/stockAssetPaymentStatusRetrieve.do">재고자산수불부현황</a>
	  </div>
    </div>
  </div>
</security:authorize>
<!--=========================================================기초정보관리======================================================================= -->
<security:authorize access="hasAnyRole('ROLE_ADMIN', 'ROLE_INFO')">
  <hr class="sidebar-divider my-0">
  <div class="accordion-item">
    <h6 class="accordion-header" id="flush-headingFive">
    <a class="nav-link collapsed">
    <i class="fas fa-fw fa-cog"></i>
      <button class="btn btn-primary" type="button" data-bs-toggle="collapse" data-bs-target="#flush-collapseFive" aria-expanded="false" aria-controls="flush-collapseFive" style="display: inline-flex">
        기초정보관리 I
      </button>
      <button class="btn btn-primary" type="button" data-bs-toggle="collapse" data-bs-target="#flush-collapseHFive" aria-expanded="false" aria-controls="flush-collapseHFive" style="margin-left: 22.5%">
        기초정보관리 II
      </button>
   </a>
    </h6>
      	<security:authorize access="hasRole('ROLE_ADMIN')">
		    <div id="flush-collapseFive" class="accordion-collapse collapse" aria-labelledby="flush-headingFive" data-bs-parent="#accordionFlushExample">
		</security:authorize>
		<security:authorize access="hasRole('ROLE_INFO')">
		    <div id="flush-collapseFive" class="accordion-collapse collapse show" aria-labelledby="flush-headingFive" data-bs-parent="#accordionFlushExample">
		</security:authorize>
      <div class="card card-body">
         <security:authorize access="hasRole('ROLE_ADMIN')">
          <a class="collapse-item" href="${cPath}/info1/authManageRetrieve.do">권한등록</a>
         </security:authorize>
          <a class="collapse-item" href="${cPath}/info1/buyerRetrieve.do">거래처등록</a>
          <a class="collapse-item" href="${cPath}/info1/projectRetrieve.do">프로젝트등록</a>
          <a class="collapse-item" href="${cPath}/info1/departmentManageRetrieve.do">부서등록</a>
          <a class="collapse-item" href="${cPath}/info1/employeeManageRetrieve.do">사원등록</a>
      </div>
    </div>
    <div id="flush-collapseHFive" class="accordion-collapse collapse" aria-labelledby="flush-headingHFive" data-bs-parent="#accordionFlushExample">
      <div class="card card-body">
          <a class="collapse-item" href="${cPath}/info2/itemManageRetrieve.do">품목등록</a>
          <a class="collapse-item" href="${cPath}/info2/warehouseRetrieve.do">창고등록</a>
          <a class="collapse-item" href="${cPath}/info2/bomManageRetrieve.do">BOM등록</a>
          <a class="collapse-item" href="${cPath}/info2/codeManageRetrieve.do">규격/분류/단위/공정 <br>등록</a>
<%--           <a class="collapse-item" href="${cPath}/info2/ItemDeadlineTransferRetrieve.do">품목마감후이월</a> --%>
      </div>
    </div>
  </div>
  
  <hr class="sidebar-divider my-0">
</security:authorize>
            <div class="sidebar-card d-none d-lg-flex">
                <i class="fas fa-history fa-2x" style="margin-bottom: 10px;"></i>
                <input type="button" id="historyBtn" class="btn btn-success btn-sm" value="History"/>
            </div>


  </div>
              
</li>
    
</ul>


<!-- ======================================================================================== -->
	<!-- 모달창 jsp Tag-->
	<div id="history-modal" class="modal-overlay modal">
		<div class="modal-scode" style="height : 676px;">
			<div class="title">History</div>
			<div class="close-area">X</div>
			<div class="historyContainer" style="overflow:auto; height:596px;">
				<c:if test="${empty history}">
					<p>저장된 내역을 모두 확인했습니다</p>
				</c:if>
<!-- 				<div class="content"> -->
					<c:set var="DocCheckList" value="${history}" />
					<c:forEach var="doCheck" items="${DocCheckList}">
						<div id="abc">
						<p class="chkNum">${doCheck.chkNum}</p>
						<p class="chkDate">${doCheck.chkDate}</p>
						</div>
						<br>
					</c:forEach>
<!-- 				</div> -->
			</div>
		</div>
	</div>
	
<!-- ======================================================================================== -->
<!-- 모달창 Script-->	
<script>
$("#historyBtn").on("click",function(){
	$("#history-modal").css('display', 'flex')
	$.ajax({
	})
}) 
//모달 열기
$("#history-modal").click(function(e){
	const evTarget = e.target
	if(evTarget.classList.contains("modal-overlay")) {
		$("#history-modal").css('display', 'none')
		$("#history-modal").find(':input[name]').prop('value','');
	}
})  
//x표 눌러서 모달 닫기
$(".close-area").click(function(){
	$("#history-modal").css('display', 'none')
	$("#history-modal").find(':input[name]').prop('value','');
})
</script>