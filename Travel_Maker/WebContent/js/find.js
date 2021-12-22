/**
 * id/pw 찾기 js
 */

// 아이디 찾기

findIdServer = function(){
	$.ajax({
		url : '/Travel_Maker/FindId.do',
		data : {"name" : inputName,
				"tel" : inputTel},
		method : 'post',
		dataType : 'json',
		success : function(res){
//			location.href = "이동할페이지주소...";
			alert(res.sw);
			window.close();
		},
		error : function(xhr){
			alert('상태 : ' + xhr.status);
		}
	})
}

findPwServer = function(){
	$.ajax({
		url : '/Travel_Maker/FindPw.do',
		data : {"id" : findPwId,
				"tel" : findPwTel,
				"email" : findPwEmail},
		method : 'post',
		dataType : 'json',
		success : function(res){
//			location.href = "이동할페이지주소...";
			vres = res.sw;
			vres = vres.substr(0,2);
			if(vres == "회원"){
				alert(res.sw);
			}else{
				alert('비밀번호 메일 전송 완료');
				window.close();
			}
		},
		error : function(xhr){
			alert('상태 : ' + xhr.status);
		}
	})
}