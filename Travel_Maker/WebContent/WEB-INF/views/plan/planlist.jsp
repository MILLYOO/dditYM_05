<%@page import="tm.plan.vo.planVO"%>
<%@page import="java.util.HashMap"%>
<%@page import="java.util.Map"%>
<%@page import="tm.board.service.FreeBoardService"%>
<%@page import="tm.board.service.UserQnaServiceImple"%>
<%@page import="tm.board.service.IFreeBoardService"%>
<%@page import="tm.comm.vo.ReplyVO"%>
<%@page import="java.util.List"%>
<%@page import="tm.member.vo.MemberVO"%>
<%@page import="tm.comm.vo.BoardVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
    <%  
    MemberVO memvo = (MemberVO)session.getAttribute("memVO");
    List<planVO> planList = (List<planVO>)request.getAttribute("list");
    
    %>
    
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css">
<link href="https://maxcdn.bootstrapcdn.com/font-awesome/4.3.0/css/font-awesome.min.css" rel="stylesheet">

<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com">
<link href="https://fonts.googleapis.com/css2?family=Do+Hyeon&display=swap" rel="stylesheet">

<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js"></script>
<script src="<%=request.getContextPath() %>/js/freeboard.js"></script>
<script src="<%=request.getContextPath() %>/js/LikeHate.js"></script>

<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/page/css/default.css">
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/page/css/style2.css">

<style type="text/css">
h1{
    margin: 20px 20px 20px 20px;
    text-align: center;
}
body{
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

#modiform{
   width : 50px;
}

.card{
      cursor: pointer;
   }
   .card-title{
      text-align: center;
   }
   .card-body {
       height: auto;
   }
   .col-4 {
         margin: 10px 0px;
   }
   .card-text{
      height: 80px;
      text-align: center;
   }
</style>




<script >

 $(document).ready(function() {
   $("#header").load("<%=request.getContextPath() %>/views/MainHeader.jsp") 
    $("#footers").load("<%=request.getContextPath() %>/views/footer.html")
    
    
    $(this).on('click', '.btn-primary',function(){
      idx = $(this).attr("idx");
      
         if (confirm("일정 삭제를 수행합니다. 계속합니까?")) {
               // 확인 버튼 클릭 시 동작
               alert("삭제하겠습니다.");
               
               $.ajax({
               url : '/Travel_Maker/DeletePlan.do',
               type : 'post',
               data : {"tm_plan_id" : idx},
               success : function(res){
                  alert("삭제 성공");
                  location.reload();
               },
               error : function(xhr){
                  alert('삭제 에러 : ' + xhr.status);
               },
               dataType : 'json'
                  
            })
           } else {
               // 취소 버튼 클릭 시 동작
               alert("취소했습니다.");
           }
    })
    })

</script>
</head> 




<body>
   <div id="header"></div>
<div class="container">
    <div class="row">
        <h1>일정 리스트</h1>

      </div>
    <!-- end row -->

    <div class="row">
        <div class="col-lg-12">
            <div class="card">
                <div class="card-body">
                            <tbody id="planlist">
                               <table class="table project-table table-centered table-nowrap">
                            <thead>
                           <%
                              if(planList.size() == 0) {
                           %>
                           <tr>
                              <td>일정이 없습니다.</td>
                           </tr>
                           <%
                           return;
                              }
                           %>
                           <tr>일정</tr>
                                <tr>
                                    <th class="re" scope="col">아이디</th>
                                    <th class="re" scope="col">일정번호</th>
                                    <th id="recont" scope="col">일정이름</th>
                                    <th id="recont" scope="col">생성일</th>
                                </tr>
                            </thead>

                            <tbody id="replylist">
                           
                           
                           
                           <%
                              for (int i = 0; i < planList.size(); i++) {
                           %>
                                     <tr>
                                         <th scope="row" id="pid"><%=planList.get(i).getTm_id()%></th>
                                       <td><%=planList.get(i).getTm_plan_id()%></td>
                                       <td><a href = "/Travel_Maker/PlanSelect.do?Tm_plan_id=<%=planList.get(i).getTm_plan_id() %>"><%=planList.get(i).getTm_plan_name()%></td>
                                       <td><%=planList.get(i).getTm_plan_wdate()%></td>
                                        
                                     

                                    <td>
                                      <input idx="<%=  planList.get(i).getTm_plan_id()%>" type="button" value="일정삭제"  class="btn btn-primary" >
                                      </td>
                               <%
                                     }
                               %>
             
                                       </tr>
                                       
                                       

                            </tbody>
    
                        </table>
    
                            </tbody>
                    <!-- end project-list -->

                </div>
            </div>
        </div>
    </div>




      <!-- end row -->
</div>             
             
             
             
             
      <div id="footers"></div>          

</body>
</html>   



















