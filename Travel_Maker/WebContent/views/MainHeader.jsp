<%@page import="tm.member.service.MemberService"%>
<%@page import="tm.member.service.IMemberService"%>
<%@page import="tm.member.vo.MemberVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	MemberVO memvo = (MemberVO)session.getAttribute("memVO");
	int tm_author = -1;
	String tm_id = "";
	
	IMemberService service = MemberService.getservice();
	if(memvo!=null){
		tm_id = memvo.getTm_id();
		tm_author = service.checkAuthor(tm_id);
	}
%>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.8.2/css/all.min.css" />
<style>
header > ul li {
    padding: 0 0 0 10px;
    line-height: 74px;
}
</style>

   <header id="header" class="index">
      <h1><a href="<%=request.getContextPath() %>/views/home.html">
         <img src="<%=request.getContextPath() %>/page/images/logo.jpg"></a>
      </h1>
      <div id="lnb">
         <nav>
            <ul>
               <%if(tm_author == 0){ %>
            	<li><a href="#">관리자 Mode</a>
                </li>
               <%}else{%>
                <li><a href="<%=request.getContextPath() %>/views/home.html">home</a>
                </li>
               <%} %>
               
               
               <%
		      	if(tm_author == -1 || tm_author == 1 || tm_author == 2){
		      	%>
               
               <li>
                  <a>게시판</a>
                  <ul class="fh5co-sub-menu">
					<li><a href="<%=request.getContextPath() %>/views/FreeBoard.jsp">자유게시판</a></li>
					<li><a href="<%=request.getContextPath() %>/views/TravelBoard.jsp">여행리뷰게시판</a></li>
					<li><a href="<%=request.getContextPath() %>/qboard/list.do">유저끼리 물어봐 게시판</a></li>
				 </ul>
               </li>
               <li>
                  <a>Ticket</a>
                  <ul class="fh5co-sub-menu">
					<li><a href="<%=request.getContextPath() %>/views/TicketSearch.jsp">티켓조회</a></li>
				 </ul>
               </li>
				<li><a>고객센터</a>
					<ul class="fh5co-sub-menu">
						<li><a href="/Travel_Maker/NoticeList.do">공지사항</a></li>

						<%
							if(tm_author == 1 || tm_author == 2) {
						%>
						<li><a href="/Travel_Maker/views/privQnA/privQnABoard.html">1:1문의</a></li>
						</ul></li>
									<li><a>일정</a>
					<ul class="fh5co-sub-menu">
						<li><a
							href="<%=request.getContextPath() %>/views/Planner.jsp">일정만들기</a></li>
						<li><a
							href="/Travel_Maker/PlanList.do">내 일정 확인</a></li>
					</ul></li>
						<%
							}
						%>
				<%
					}
				%>
               
               <%
                              	if (tm_author == 1 || tm_author == 2) {
                              %>
               <li>
                 <a>마이페이지</a>
                 <ul class="fh5co-sub-menu">
					<li><a href="/Travel_Maker/ListMyInfoServlet.do">회원정보</a></li>
					<li><a href="<%=request.getContextPath() %>/views/MyBoard.html">내 게시글 관리</a></li>
					<li><a href="<%=request.getContextPath() %>/views/scrabList.jsp">내 스크랩 목록</a></li>
					<li><a href="/Travel_Maker/MyCartList.do">장바구니</a></li>
					<li><a href="/Travel_Maker/buyList.do">결제내역</a></li>
				 </ul>
               </li>
				<%
		      	}else if(tm_author == 0){
		      	%>
               <li>		      	
		      	 <a>회원관리</a>
		      	 <ul class="fh5co-sub-menu">
					<li><a href="/Travel_Maker/MemManage.do">전체 회원관리</a></li>
					<li><a href="<%=request.getContextPath() %>/views/blacklist.html">블랙리스트 관리</a></li>
				 </ul>
               </li>				 
               <li>		      	
		      	 <a>고객센터관리</a>
		      	 <ul class="fh5co-sub-menu">
					<li><a href="/Travel_Maker/NoticeList.do">공지사항</a></li>
					<li><a href="/Travel_Maker/AdminQnaList.do">1:1 문의 관리</a></li>
				 </ul>
               </li>				 
               <li>		      	
		      	 <a>여행관리</a>
		      	 <ul class="fh5co-sub-menu">
					<li><a href="/Travel_Maker/views/Search/InsertSearch.html">여행지추가</a></li>
					<li><a href="/Travel_Maker/views/Search/modifySearch.html">여행지수정삭제</a></li>
				 </ul>
               </li>				 
               <li>		      	
		      	 <a>게시판관리</a>
		      	 <ul class="fh5co-sub-menu">
					<li><a href="<%=request.getContextPath() %>/views/FreeBoard.jsp">자유게시판</a></li>
					<li><a href="<%=request.getContextPath() %>/views/TravelBoard.jsp">여행리뷰게시판</a></li>
					<li><a href="<%=request.getContextPath() %>/qboard/list.do">유저끼리 물어봐 게시판</a></li>
					<li><a href="/Travel_Maker/spamList.do">불량게시글 관리</a></li>
				 </ul>
               </li>				 
				 
				 <!--  --><!-- 이제 이 자리에 회원들의 일정을 관리하는 페이지 연결하면 될 거 같아여! --><!--  -->
		      	<%	
		      	}
		      	%>
		      	
               
               
            </ul>
         </nav>
      </div>
      <ul>
      	<%
      	if(tm_author > -1){
      	%>	
      		<li id="logout "><a href="/Travel_Maker/logout.do"><i class="fas fa-unlock-alt"></i> Logout</a></li>
      	<%
      	}else{
      	%>
            <li><a href="<%=request.getContextPath() %>/views/login.html"><i class="fas fa-key"></i> Login</a></li>
          <li><a href="<%=request.getContextPath() %>/views/join.html"><i class="fas fa-user-plus"></i> 회원가입</a></li>
	    <%} %>
    	
      </ul>
   </header>
   

