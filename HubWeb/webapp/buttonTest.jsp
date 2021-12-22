<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
input {
    background-repeat: no-repeat;
    border: 1px solid #ccc;
    padding: 5px 5px;
    width: 120px;
}

input::-webkit-input-placeholder{
  background-image: url(https://cdn1.iconfinder.com/data/icons/hawcons/32/698627-icon-111-search-256.png) ;
  background-size: contain;
  background-position:  1px center;
  background-repeat: no-repeat;
  text-align: center;
  text-indent: 0;
}
</style>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
</head>
	<input type="search" id="buyerSearch" placeholder="거래처명" onclick="test()" />
<script>
	test = function(){
		var buyerName = '현대'
		$("#buyerSearch").val(buyerName)
	}
</script>
</html>