/**
 * 
 */
var arr = new Array();
var arrCount = new Array();

let basket = {
    totalCount: 0, 
    totalPrice: 0,

    //재계산
    reCalc: function(){
        this.totalCount = 0;
        this.totalPrice = 0;
        document.querySelectorAll(".p_num").forEach(function (item) {
            if(item.parentElement.parentElement.parentElement.firstElementChild.firstElementChild.checked == true){
                var count = parseInt(item.getAttribute('value'));
                this.totalCount += count;
                var price = item.parentElement.parentElement.previousElementSibling.getAttribute('idx');
                this.totalPrice += count * price;
                
            }
        }, this); // forEach 2번째 파라메터로 객체를 넘겨서 this 가 객체리터럴을 가리키도록 함. - thisArg
    },
    //화면 업데이트
    updateUI: function () {
        document.querySelector('#sum_p_num').textContent = '상품개수: ' + this.totalCount.formatNumber() + '개';
        document.querySelector('#sum_p_price').textContent ='합계금액: ' + this.totalPrice.formatNumber() + '원';
    },
    //개별 수량 변경
    changePNum: function (pos) {
        var item = document.querySelector('input[name=p_num'+pos+']');
        var p_num = parseInt(item.getAttribute('value'));
        var newval = event.target.classList.contains('up') ? p_num+1 : event.target.classList.contains('down') ? p_num-1 : event.target.value;
        
        if (parseInt(newval) < 1 || parseInt(newval) > 99) { return false; }

        item.setAttribute('value', newval);
        item.value = newval;
        
        var price = item.parentElement.parentElement.previousElementSibling.getAttribute('idx');
        
        item.parentElement.parentElement.nextElementSibling.textContent = (newval * price).formatNumber()+"원";
        //AJAX 업데이트 전송

        //전송 처리 결과가 성공이면    
        this.reCalc();
        this.updateUI();
    },
    checkItem: function (a) {
    	var obj_length = document.getElementsByName("buy").length;
    	arr = new Array();
    	arrCount = new Array();
    	
    	for (var i=0; i<obj_length; i++) {
    		if (document.getElementsByName("buy")[i].checked == true) {
               arr.push(document.getElementsByName("buy")[i].value);
               arrCount.push(document.getElementsByClassName("p_num")[i].value);
            }
        }
        this.reCalc();
        this.updateUI();
    },
    delItem: function () {
        event.target.parentElement.parentElement.parentElement.remove();
        this.reCalc();
        this.updateUI();
    },
    
    shopping: function(){
    	var vid = "";
    	for(i = 0; i < arr.length; i++){
    		if(i > 0){
    			vid += "&";
    		}
    		vid += "arr=" + arr[i] + "&arrCount=" + arrCount[i];
    	}
    	$.ajax({
    		url : '/Travel_Maker/BasketInsert.do',
    		type : 'post',
    		data : vid,
    		success : function(res){
    			if(confirm("장바구니에 상품을 담았습니다. 장바구니로 이동하시겠습니까?") == true){
    				location.href = "/Travel_Maker/MyCartList.do";
    			}else{
    				location.href = "/Travel_Maker/views/TicketSearch.jsp";
    			}
    		},
    		error : function(xhr){
    			console.log("상태  " + xhr.status);
    		},
    		dataType : 'json'	
    	})

    },
    
    nonUser: function(){
    	if(confirm("회원만 이용 가능한 서비스입니다.") == true){
    		location.href ="/Travel_Maker/views/login.html";
		}else{
			location.href = "/Travel_Maker/views/TicketSearch.jsp";
		}
    }
    
    
}

// 숫자 3자리 콤마찍기
Number.prototype.formatNumber = function(){
    if(this==0) return 0;
    let regex = /(^[+-]?\d+)(\d{3})/;
    let nstr = (this + '');
    while (regex.test(nstr)) nstr = nstr.replace(regex, '$1' + ',' + '$2');
    return nstr;
};