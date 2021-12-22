/**
 * 페이징 처리용 함수
 */
$.fn.paging=function(param){
	let searchForm = this;
	param = param ? param : {};
	let searchUI = param.searchUI ? $(param.searchUI) : $("#searchUI") ;
	let searchBtn = param.searchBtn ? param.searchBtn : "#searchUI";
	let pagingArea = param.pagingArea ? $(param.pagingArea) : $("#pagingArea");
	let pageKey = param.pageKey ? param.pageKey : "page"; 
	let pageTag = param.pageName ? parama.pageName : $("[name='page']");
	
	searchUI.on("click", searchBtn, function(){ // 디센던트구조
		searchUI.find(":input[name]").each(function(idx, input){
			let name = input.name;
			let value = $(input).val();
			let form = searchForm.get(0); // form태그 그 객체를 가지고옴
			form[name].value = value;
		});
		searchForm.submit();
	});
	
	pagingArea.on("click", "a" ,function(evnet){
		event.preventDefault();
		let page = $(this).data(pageKey);
		if(!page) return false;
		pageTag.val(page);
		searchForm.submit();
		return false;
	});
	
	return this; // 체인구조를 만들기위함
}
