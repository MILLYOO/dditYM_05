<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<!-- 
   codeScript.jsp
   By 이원균_211202(최종수정)
   코드도움 모달창 구현에 있어서 공통적인 부분을 모아놓았습니다.
   1. 코드도움 모달 CSS
   2. 개별 코드도움 모달 HTML(div) 
   3. 모달 HTML에 적용하는 script 로딩
   4. 모달 데이터 적용 시 클릭한 태그 안에 데이터를 넣기 위한 전역변수 선언
-->


<!-- ======================================================================================== -->
<!-- 1. 코드도움 모달 CSS-->
<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link href="https://fonts.googleapis.com/css2?family=Noto+Sans+KR:wght@500&display=swap" rel="stylesheet">
<style>
.modal, .modal-overlay, #dept-modal, #buyer-modal, #emp-modal,
   #item-modal, #war-modal, #proj-modal, #kcomm-modal, #gcomm-modalM,
   #gcomm-modalD, #ucomm-modalM, #ucomm-modalD, #item-modalM,
   #kcomm-modalM, #ldiv-modal, #mdiv-modal, #sdiv-modal, 
   #stockValuation-modal, #stockValInsert-modal, #war-modalM,
   #inOutReq-modal, #history-modal, #excelImport-modal {
   width: 100%;
   height: 100%;
   position: absolute;
   left: 0;
   top: 0;
   display: none;
   flex-direction: column;
   align-items: center;
   justify-content: center;
   background: rgba(255, 255, 255, 0.25);
/*    box-shadow: 0 8px 32px 0 rgba(31, 38, 135, 0.37); */
   backdrop-filter: blur(1.5px);
   -webkit-backdrop-filter: blur(1.5px);
   border-radius: 10px;
   border: 1px solid rgba(255, 255, 255, 0.18);
}
.modal-windows{
   background: rgba(174, 182, 204);
   backdrop-filter: blur(13.5 px);
   -webkit-backdrop-filter: blur(13.5px);
   border-radius: 10 px;
   border: 1 px solid rgba(255, 255, 255, 0.18);
   width: 40%;
   position: relative;
   top: -100 px;
   padding: 10 px;
   color: #5C5F68;
}
.modal-apply{
   background: rgba(174, 182, 204);
   backdrop-filter: blur(13.5 px);
   -webkit-backdrop-filter: blur(13.5px);
   border-radius: 10 px;
   border: 1 px solid rgba(255, 255, 255, 0.18);
   width: 50%;
   position: relative;
   top: -100 px;
   padding: 10 px;
   color: #5C5F68;
}
.modal-applyLg{
   background: rgba(174, 182, 204);
   backdrop-filter: blur(13.5 px);
   -webkit-backdrop-filter: blur(13.5px);
   border-radius: 10 px;
   border: 1 px solid rgba(255, 255, 255, 0.18);
   width: 70%;
   position: relative;
   top: -100 px;
   padding: 10 px;
   color: #5C5F68;
}
.modal-scode{
   background: rgba(174, 182, 204);
   backdrop-filter: blur(13.5 px);
   -webkit-backdrop-filter: blur(13.5px);
   border-radius: 10 px;
   border: 1 px solid rgba(255, 255, 255, 0.18);
   width: 20%;
   position: relative;
   top: -100 px;
   padding: 10 px;
   color: #5C5F68;
}
.modal-mcode{
   background: rgba(174, 182, 204);
   backdrop-filter: blur(13.5 px);
   -webkit-backdrop-filter: blur(13.5px);
   border-radius: 10 px;
   border: 1 px solid rgba(255, 255, 255, 0.18);
   width: 30%;
   position: relative;
   top: -100 px;
   padding: 10 px;
   color: #5C5F68;
}
.modal, .modal-window {
   background: rgba(174, 182, 204);
/*    box-shadow: 0 8px 32px 0 rgb(31 38 135/ 37%); */
   backdrop-filter: blur(13.5 px);
   -webkit-backdrop-filter: blur(13.5px);
   border-radius: 10 px;
   border: 1 px solid rgba(255, 255, 255, 0.18);
   width: 60%;
   position: relative;
   top: -100 px;
   padding: 10 px;
   color: #5C5F68;
   overflow: auto;
}

.modal{
   padding-left: 10px;
   display: inline;
/*    text-shadow: 1px 1px 2px gray; */
   color: #5C5F68;
}
.title{
   margin: 10px auto;
   padding-left: 20px;
   display: inline-block;
   color: #5C5F68;
   font-size: 20px;
   font-weight: bold;
/*    font-family: 'Noto Sans KR', sans-serif; */
}
.close-area {
   width : 20px;
   display: inline;
   float: right;
   padding-right: 10px;
   cursor: pointer;
/*    text-shadow: 1px 1px 2px gray; */
   color: #5C5F68;
}

.modal, .content {
/*    margin-top: 20px; */
   padding: 0px 10px;
/*    text-shadow: 1px 1px 2px gray; */
   color: #5C5F68;
}
.scodeAssistForm{
   margin: 0px -12px 20px 58px;
   width : 80%;
   color: #5C5F68;
   font-weight: bold;
   float: left;
}
.scodeAssistFormemp,  .scodeAssistFormwar, .scodeAssistFormdept{
    margin: 0px -31px 17px 33px;
    width: 80%;
    color: #5C5F68;
    font-weight: bold;
    float: left;
}
.mcodeAssistForm{
   margin: 0px auto 20px;
   width : 65%;
   color: #5C5F68;
   font-weight: bold;
}
.lcodeAssistForm{
   margin: 0px auto 20px;
   width : 90%;
   color: #5C5F68;
   font-weight: bold;
}
.historyContainer{
   background-color: white;
   margin : 15px 15px;
   overflow: auto; /*넘어가면 자동으로 스크롤 생깁니당!*/
   padding: 7px;
}
#abc{
   margin-bottom: 48px;
}
.chkNum{
   width: 100%;
    margin: 0px auto;
    display: inline-block;
    float: left;
    font-size: 1em;
    font-weight: 900;
    letter-spacing: 3px;
}
.chkDate{
   width : 100%;
   margin : 0px auto;
   display : inline-block   ;
   float: left;
   color: #FF5858;
    font-size: small;
}

/*검색해서 코드도움 띄우는 부분 css입니다.*/
input::-webkit-input-placeholder{
  background-size: contain;
  background-position:  1px center;
  background-repeat: no-repeat;
  text-align: center;
  text-indent: 0;
}
input[name="empName"], input[name="prodName"]
,input[name="warName"], input[name="buyerName"]
,input[name="deptName"], input[name="mdivName"]
,input[name="ldivName"], input[name="projName"]
,input[name="sdivName"], input[name="kcommName"]
,input[name="gcommName"], input[name="rawsName"]
,input[name="ucommName"], input[name="rawsCode"]
,#rawsName
{
   margin: 0 20px 0 0px;
}
</style>

<!-- ======================================================================================== -->
<!-- 2. 개별 코드도움 모달 HTML(div)  -->
<!-- 거래처 코드도움 -->
<div id="buyer-modal" class="modal-overlay modal">
   <div class="modal-mcode">
      <div class="title">거래처 코드도움</div>
      <div class="close-area">X</div>
      <div class="content">
         <div id="realgrid_buyer" class="hubWeb_standard"></div>
      <form:form id="buyerSearchForm" class="mcodeAssistForm">
         거래처명 <input type="text" name="buyerName" /> 
         <input type="button" id="buyerSearchFormBtn" class="btn btn-secondary btn-sm" value="검색">
      </form:form>
      </div>
   </div>
</div>

<!-- 부서 코드도움 -->
<div id="dept-modal" class="modal-overlay modal">
   <div class="modal-scode">
      <div class="title">부서 코드도움</div>
      <div class="close-area">X</div>
      <div class="content">
         <div id="realgrid_dept" class="hubWeb_standard"></div>
      </div>
      <form:form id="deptSearchForm" class="scodeAssistFormdept">
         부서명  <input type="text" name="deptName" /> 
      </form:form>
         <input type="button" id="deptSearchBtn" class="btn btn-secondary btn-sm" value="검색">
   </div>
</div>

<!-- 사원 코드도움 -->
<div id="emp-modal" class="modal-overlay modal">
   <div class="modal-scode">
      <div class="title">사원 코드도움</div>
      <div class="close-area">X</div>
      <div class="content">
         <div id="realgrid_emp" class="hubWeb_standard"></div>
      </div>
      <form:form id="empSearchForm" class="scodeAssistFormemp">
         사원명 <input type="text" name="empName" /> 
      </form:form>
         <input type="button" id="empSearchBtn" class="btn btn-secondary btn-sm" value="검색">
   </div>
</div>

<!-- 품목 코드도움 -->
<div id="item-modal" class="modal-overlay modal">
   <div class="modal-windows">
      <div class="title">품목 코드도움</div>
      <div class="close-area">X</div>
      <div class="content">
         <div id="realgrid_item" class="hubWeb_standard"></div>
      </div>
      <form:form id="itemSearchForm" class="scodeAssistForm">
         품목코드 <input type="text" name="rawsCode" /> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
         품명 <input type="text" name="rawsName" /> 
      </form:form>
         <input type="button" id="itemSearchBtn" class="btn btn-secondary btn-sm" value="검색">
   </div>
</div>
<!-- 품목 코드도움M -->
<div id="item-modalM" class="modal-overlay modal">
   <div class="modal-windows">
      <div class="title">품목 코드도움</div>
      <div class="close-area">X</div>
      <div class="content">
         <div id="realgrid_itemM" class="hubWeb_standard"></div>
      </div>
      <form:form id="itemSearchForm" class="scodeAssistForm">
         품목코드 <input type="text" name="rawsCode" />&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
          품명 <input type="text" name="rawsName" /> 
      </form:form>
          <input type="button" id="itemSearchBtn" class="btn btn-secondary btn-sm" value="검색">
   </div>
</div>

<!-- 창고 코드도움 master -->
<div id="war-modalM" class="modal-overlay modal">
   <div class="modal-scode">
      <div class="title">창고 코드도움</div>
      <div class="close-area">X</div>
      <div class="content">
         <div id="realgrid_warM" class="hubWeb_standard"></div>
      </div>
      <form:form id="warSearchForm" class="scodeAssistForm">
         창고명 <input type="text" name="warName" /> 
      </form:form>
      <input type="submit" class="btn btn-secondary btn-sm" value="검색">
   </div>
</div>
<!-- 창고 코드도움 detail -->
<div id="war-modal" class="modal-overlay modal">
   <div class="modal-scode">
      <div class="title">창고 코드도움</div>
      <div class="close-area">X</div>
      <div class="content">
         <div id="realgrid_war" class="hubWeb_standard"></div>
      </div>
      <form:form id="warSearchForm" class="scodeAssistFormwar">
         창고명 <input type="text" name="warName" /> 
      </form:form>
         <input type="button" id="warSearchBtn" class="btn btn-secondary btn-sm" value="검색">
   </div>
</div>

<!-- 프로젝트 코드도움 -->
<div id="proj-modal" class="modal-overlay modal">
   <div class="modal-scode">
      <div class="title">프로젝트 코드도움</div>
      <div class="close-area">X</div>
      <div class="content">
         <div id="realgrid_proj" class="hubWeb_standard"></div>
      </div>
      <form:form id="projSearchForm" class="lcodeAssistForm">
         프로젝트명 <input type="text" name="projName" /> 
         <input type="button" id="projSearchBtn" class="btn btn-secondary btn-sm" value="검색">
      </form:form>
   </div>
</div>

<!-- 공정 코드도움M -->
<div id="kcomm-modalM" class="modal-overlay modal">
   <div class="modal-scode">
      <div class="title">공정 코드도움</div>
      <div class="close-area">X</div>
      <div class="content">
         <div id="realgrid_kcommM" class="hubWeb_standard"></div>
      </div>
   </div>
</div>
<!-- 공정 코드도움 -->
<div id="kcomm-modal" class="modal-overlay modal">
   <div class="modal-scode">
      <div class="title">공정 코드도움</div>
      <div class="close-area">X</div>
      <div class="content">
         <div id="realgrid_kcomm" class="hubWeb_standard"></div>
      </div>
   </div>
</div>

<!-- master 규격 코드도움 -->
<div id="gcomm-modalM" class="modal-overlay modal">
   <div class="modal-scode">
      <div class="title">규격 코드도움</div>
      <div class="close-area">X</div>
      <div class="content">
         <div id="realgrid_gcommM" class="hubWeb_standard"></div>
      </div>
   </div>
</div>
<!-- detail 규격 코드도움 -->
<div id="gcomm-modalD" class="modal-overlay modal">
   <div class="modal-scode">
      <div class="title">규격 코드도움</div>
      <div class="close-area">X</div>
      <div class="content">
         <div id="realgrid_gcommD" class="hubWeb_standard"></div>
      </div>
   </div>
</div>

<!-- master 단위 코드도움 -->
<div id="ucomm-modalM" class="modal-overlay modal">
   <div class="modal-scode">
      <div class="title">단위 코드도움</div>
      <div class="close-area">X</div>
      <div class="content">
         <div id="realgrid_ucommM" class="hubWeb_standard"></div>
      </div>
   </div>
</div>
<!-- detail 단위 코드도움 -->
<div id="ucomm-modalD" class="modal-overlay modal">
   <div class="modal-scode">
      <div class="title">단위 코드도움</div>
      <div class="close-area">X</div>
      <div class="content">
         <div id="realgrid_ucommD" class="hubWeb_standard"></div>
      </div>
   </div>
</div>

<!-- detail 대분류 코드도움 -->
<div id="ldiv-modal" class="modal-overlay modal">
   <div class="modal-scode">
      <div class="title">대분류 코드도움</div>
      <div class="close-area">X</div>
      <div class="content">
         <div id="realgrid_ldiv" class="hubWeb_standard"></div>
      </div>
   </div>
</div>
<!-- detail 중분류 코드도움 -->
<div id="mdiv-modal" class="modal-overlay modal">
   <div class="modal-scode">
      <div class="title">중분류 코드도움</div>
      <div class="close-area">X</div>
      <div class="content">
         <div id="realgrid_mdiv" class="hubWeb_standard"></div>
      </div>
   </div>
</div>
<!-- detail 소분류 코드도움 -->
<div id="sdiv-modal" class="modal-overlay modal">
   <div class="modal-scode">
      <div class="title">소분류 코드도움</div>
      <div class="close-area">X</div>
      <div class="content">
         <div id="realgrid_sdiv" class="hubWeb_standard"></div>
      </div>
   </div>
</div>

<!-- ======================================================================================== -->
<!-- 3. 모달 HTML에 적용하는 script 로딩 -->
<script src="${cPath}/resources/js/custom/codeBuyer.js"></script>
<script src="${cPath}/resources/js/custom/codeDept.js"></script>
<script src="${cPath}/resources/js/custom/codeEmp.js"></script>
<script src="${cPath}/resources/js/custom/codeItem.js"></script>
<script src="${cPath}/resources/js/custom/codeItemM.js"></script>
<script src="${cPath}/resources/js/custom/codeWar.js"></script>
<script src="${cPath}/resources/js/custom/codeWarM.js"></script>
<script src="${cPath}/resources/js/custom/codeProj.js"></script>
<script src="${cPath}/resources/js/custom/codeKcommM.js"></script>
<script src="${cPath}/resources/js/custom/codeKcomm.js"></script>
<script src="${cPath}/resources/js/custom/codeGcommM.js"></script>
<script src="${cPath}/resources/js/custom/codeGcommD.js"></script>
<script src="${cPath}/resources/js/custom/codeUcommM.js"></script>
<script src="${cPath}/resources/js/custom/codeUcommD.js"></script>
<script src="${cPath}/resources/js/custom/codeLdiv.js"></script>
<script src="${cPath}/resources/js/custom/codeMdiv.js"></script>
<script src="${cPath}/resources/js/custom/codeSdiv.js"></script>

<!-- ======================================================================================== -->
<!-- 4. 모달 데이터 적용 시 클릭한 태그 안에 데이터를 넣기 위한 전역변수 선언 -->
<script>
   var empObj = "";
   var deptObj = "";
   var projObj = "";
   var warObj = "";
   var kcommObj = "";
   var gcommObj = "";
   var itemObj = "";
   var ucommObj = "";
   var ldivObj = "";
   var mdivObj = "";
   var sdivObj = "";
   var buyerObj = "";
</script>