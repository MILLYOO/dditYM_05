/**
 * 페이징 처리용 함수
 * 페이징 모듈화
 * 함수체인구조
 */
$.fn.paging=function(param){
	
	// 파라미터 안넘어오면 기본 객체 생성
	param = param ? param : {};
	
	let searchForm = this;
	let searchUI = param.searchUI ? $(param.searchUI) : $("#searchUI");
	let searchBtn = param.searchBtn ? param.searchBtn : "#searchBtn";
	let pagingArea = param.pagingArea ? $(param.pagingArea) : $("#pagingArea");
	let pageKey = param.pageKey ? param.pageKey : "page";
	let pageTag = param.pageTag ? $(param.pageTag) : $("[name='page']");
	
	if(searchUI){
		searchUI.on("click",searchBtn,function(){
			searchUI.find(":input[name]").each(function(idx,input){
				let name = input.name;
				let value = $(input).val();
				// searchForm으로 찾았을 때 첫번째로 나오는 element (제이쿼리 내부에 배열로 저장됨)
				// searchForm 자체, HTML element객체, form.page 이렇게 접근하는게 가능
				let form = searchForm.get(0);		
				form[name].value = value;
			});
			searchForm.submit();
		});
	}

	pagingArea.on("click","a",function(event){
		event.preventDefault();
		let page = $(this).data(pageKey);
		if(!page) return false;
		pageTag.val(page);
		searchForm.submit();
		return false;
	});
	return this;	// 함수 체인구조 만들기 위함
}