/**
 * 
 */

currentPage = 1;


listPageServer = function(page) {

$.ajax({
	url		: '/Travel_Maker/myscrablist.do',
	method	: 'get',
	data	: {'page' : page},
	success	: function(res) {
		
			str = ""
		
		$.each(res.datas, function(i, v) {
			
			var imgName = v.img_name;

		str += '		<div class="col-lg-4 col-md-4 col-sm-6 scrabid">'
		str += '			<div class="fh5co-blog animate-box">'
		str += '				<div class="blog-text">'
		str += '					<div class="prod-title" idx="' + v.search_id +'">'
		str += '						<img class="maincity" src="../imageForSearch/' + imgName + '.jpg" alt=""'
		str += '						<span class="posted_by"></span>' + v.search_name
		str += '						<p>'+ v.search_addr +'</p>'
		str += '					</div>'
		str += '				</div>'
		str += '			</div>'
		str += '		</div>'
				
		})

			$('.myscrab').html(str)
			
			
			// 이전버튼
			$('#pageList').empty();
			if(res.startPage > 1){
				vpre = '<ul>';
                vpre += '<a class="page-link" href="#" id="prev">이전</a>';
                vpre += '</ul>';
				$('#pageList').append(vpre);
			}
			
			//페이징
			$('#pageList').empty();
			vpage  = '<ul class="pagination justify-content-end mb-0">'
			vpage += '<li class="page-item previous">'
			vpage += 	'<a class="page-link" href="#" tabindex="-1" aria-disabled="true">이전</a>'
			vpage += '</li>'
			
			for(i=res.startPage; i<=res.endPage; i++){
				if(i == currentPage){
					vpage += '<li class="page-item paging active"><a class="page-link" href="#">' + i +'</a></li>'
				}else{
					vpage += '<li><a class="page-item paging" href="#">' + i +'</a></li>'
				}
			}
        
			vpage += '<li class="page-item next"><a href="#">다음</a></li>'
			vpage += '</ul>'
				
				//ul 안붙으니까 스타일 주고 insert해
				
			$('#pageList').append(vpage)
	
		},
		error	: function(xhr) {
			console.log('서버 상태 : ' + xhr.status)
		},
		dataType: 'json'
	})
}


openModal = function(searchId) {
	
	$("#modalScrap").empty();
	$.ajax({
		url : '/Travel_Maker/searchInfo2.do',
		type : 'post',
		data : {"data" :searchId},
		dataType : 'json',
		success : function(res) {
			id = res.tm_search_id;
			var title = res.tm_search_name;
			var img = res.img_id;
			var addr = res.tm_search_addr;
			var tel = res.tm_search_tel;
			var cate = res.tm_search_cate;
			var con = res.tm_search_con;
			$(".modalId").text(id);
			$("#modalTitle").text(title);
			$("#modalContent").text(con);
			$("#exampleModalCenter").modal("show");
		},
		error : function(xhr){
			console.log("상태 : " + xhr.status);
		},
		async: false
	})
	
	//좋아요 숫자 확인용
	$.ajax({
		url : '/Travel_Maker/GetLikeCount.do',
		type : 'post',
		data : {"TM_SEARCH_ID" :  $(this).prop("id")},
		dataType : 'json',
		success : function(res) {
			likecount = res.cnt;
			$('#likecount').text(likecount);
		},
		error : function(xhr){
			console.log("상태: " + xhr.status);
		}
	})
 
 
 //좋아요 확인용
 searchNo = $(".modalId").text();
 $.ajax({
       url : '/Travel_Maker/SearchLikeCheck.do',
       async: false,
       type : 'post',
       data : {"tm_search_id" : searchNo},
       success : function(res){
          cnt = res.cnt;
       },
       error : function(xhr){
    	   console.log('좋아요 에러 : ' + xhr.status);
       },
       dataType : 'json'
    })
    if(cnt != 0){
       $('#likeon').show();
       $('#likeoff').hide();
    }
    else{
       $('#likeon').hide();
       $('#likeoff').show();
    }
 
 
 
 // 모달 좋아요 수 넣기
 $(document).on('click', '.icon-heart, .icon-heart-o', function(){
    
    console.log("좋아요 버튼 클릭");
    
    searchNo = $(".modalId").text(); // 검색ID 확인

    
    // 좋아요 여부 확인용
    $.ajax({
       url : '/Travel_Maker/SearchLikeCheck.do',
       async: false,
       type : 'post',
       data : {"tm_search_id" : searchNo},
       success : function(res){
          cnt = res.cnt;
       },
       error : function(xhr){
    	   console.log('좋아요 에러 : ' + xhr.status);
       },
       dataType : 'json'
          
    })
    
    
    
    if(cnt == 0){
       // 좋아요 적용

       $.ajax({
          url      : '/Travel_Maker/SearchLikeAdd.do',
          async: false,
          type   : 'post',
          data : {"tm_search_id" : searchNo},
          success   : function (res) {
        	  console.log("좋아요를 누르셨습니다.");
             $('#likeon').show();
             $('#likeoff').hide();
             
             cnt = parseInt($('#likecount').text());
				likecount = (cnt)+1;
				$('#likecount').text(likecount);
				

          },
          error   : function (xhr) {
        	  console.log('좋아요 적용애러 : ' + xhr.status);
          },
          dataType: 'json'
       })   
       
    }
    else{
       // 좋아요 취소(다시 누른 경우)

       $.ajax({
          url      : '/Travel_Maker/SearchLikeCancel.do',
          async: false,
          type   : 'post',
          data : {"tm_search_id" : searchNo},
          success   : function (res) {
        	  console.log("좋아요가 취소 되었습니다.")   
             $('#likeon').hide();
             $('#likeoff').show();
             
             cnt = parseInt($('#likecount').text());
				likecount = (cnt)-1;
				$('#likecount').text(likecount);

          },
          error   : function (xhr) {
        	  console.log('좋아요 취소 애러 : ' + xhr.status);
             
          },
          dataType: 'json'
       })
    }   
    

    $('.icon-heart').off("click"); 
 })
	
	
	
	
	
	
	
}
	
	

scrabForPlan = function() {
	//alert('들어옴');

$.ajax({
	url		: '/Travel_Maker/scrabplan.do',
	method	: 'get',
	success	: function(res) {
		
		str = ""
		//v.img_id
		
		$.each(res, function(i, v) { 

		   str += '<div class="scrabBowl">'
		   str += ' <div class="panel panel-info">'
		   str += '   <div class="panel-heading">'
		   str += '    <div class = "scrab-name">'+ v.search_name + '</div>'
		   str += '</div>'
		   str += '   <div class="panel-body">'
		   str += '   	<div class="scrab-img">'
	       str += '	    <div class="myimg"><img class="scimg" alt="대전.jpg" src="../page/images/대전.jpg"></div>'
	       str += '     </div>'
		   str += '    <div class = "scrab-addr">'+ v.search_addr + '</div>'
		   str += '     <div class="scrab-text"><p class="mycon">' + v.search_con + '</p></div>'
		   str += '   </div>'
		   str += ' </div>'
		   str += '</div>'
				
		})

			$('.myscrab').html(str)

		},
		error	: function(xhr) {
			console.log('서버 상태 : ' + xhr.status)
		},
		dataType: 'json'
	})
	
}