/**
 * 
 */


$(document).on("click", ".linkBtn",function(){	// document 전체 중 .linkBtn의 클릭 이벤트
	let gopage = $(this).data("gopage");
	if(!gopage) return false;
	location.href=gopage;
});