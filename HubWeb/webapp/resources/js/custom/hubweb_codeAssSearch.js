// 검색에서 클릭 시 코드도움 모달을 보여준다.
	buyerCode = function(obj) {
		$("#buyer-modal").css('display', 'flex')
		
		console.log(obj)
		buyerObj = obj;
		//데이터 가져오기
		$.ajax({
			   url : $.getContextPath() + "/info1/buyerRetrieve.do",
			   method : "post",
			   dataType : "json",
			   success : function(data){
				   console.log(data);
				   buyerProvider.fillJsonData(data, {fillMode: "set"});
			   },
			   error: function(xhr, errorResp, error){
				   console.log(xhr);
				   console.log(errorResp);
				   console.log(error);
			   }
		});
	}
	empCode = function(obj) {
		$("#emp-modal").css('display', 'flex')
		empObj = obj;
		//데이터 가져오기
		$.ajax({
			   url : $.getContextPath() + "/info1/employeeManageRetrieve.do",
			   method : "post",
			   dataType : "json",
			   success : function(data){
				   console.log(data);
				   empProvider.fillJsonData(data, {fillMode: "set"});
			   },
			   error: function(xhr, errorResp, error){
				   console.log(xhr);
				   console.log(errorResp);
				   console.log(error);
			   }
		});
	}
	deptCode = function(obj) {
		$("#dept-modal").css('display', 'flex')
		deptObj = obj;
		//데이터 가져오기
		$.ajax({
			   url : $.getContextPath() + "/info1/departmentManageRetrieve.do",
			   method : "post",
			   dataType : "json",
			   success : function(data){
				   console.log(data);
				   deptProvider.fillJsonData(data, {fillMode: "set"});
			   },
			   error: function(xhr, errorResp, error){
				   console.log(xhr);
				   console.log(errorResp);
				   console.log(error);
			   }
		});
	}
	projCode = function(obj) {
		$("#proj-modal").css('display', 'flex')
		projObj = obj;
		//데이터 가져오기
		$.ajax({
			   url : $.getContextPath() + "/info1/projectRetrieve.do",
			   method : "post",
			   dataType : "json",
			   success : function(data){
				   console.log(data);
				   projProvider.fillJsonData(data, {fillMode: "set"});
			   },
			   error: function(xhr, errorResp, error){
				   console.log(xhr);
				   console.log(errorResp);
				   console.log(error);
			   }
		});
	}
	warCode = function(obj) {
		$("#war-modal").css('display', 'flex')
		warObj = obj;
		//데이터 가져오기
		$.ajax({
			   url : $.getContextPath() + "/info2/warehouseRetrieve.do",
			   method : "post",
			   dataType : "json",
			   success : function(data){
				   console.log(data);
				   warProvider.fillJsonData(data, {fillMode: "set"});
			   },
			   error: function(xhr, errorResp, error){
				   console.log(xhr);
				   console.log(errorResp);
				   console.log(error);
			   }
		});
	}
	kcommCode = function(obj) {
		$("#kcomm-modalM").css('display', 'flex')
		kcommObj = obj;
		//데이터 가져오기
		$.ajax({
			   url : $.getContextPath() + "/info2/common/process.do",
			   dataType : "json",
			   success : function(data){
				   console.log(data);
				   kcommProviderM.fillJsonData(data, {fillMode: "set"});
			   },
			   error: function(xhr, errorResp, error){
				   console.log(xhr);
				   console.log(errorResp);
				   console.log(error);
			   }
		});
	}
	gcommCode = function(obj) {
		$("#gcomm-modalM").css('display', 'flex')
		gcommObj = obj;
		//데이터 가져오기
		$.ajax({
			   url : $.getContextPath() + "/info2/common/standard.do",
			   dataType : "json",
			   success : function(data){
				   console.log(data);
				   gcommMProvider.fillJsonData(data, {fillMode: "set"});
			   },
			   error: function(xhr, errorResp, error){
				   console.log(xhr);
				   console.log(errorResp);
				   console.log(error);
			   }
		});
	}
	itemCode = function(obj) {
		$("#item-modal").css('display', 'flex')
		itemCodeObj = obj;
		$("#itemSearchForm").ajaxForm({
			url : $.getContextPath() + "/info2/itemManageRetrieve.do",
			method : 'post',
			dataType:"json",
			success:function(data){
				console.log(data);
				itemProvider.fillJsonData(data, {fillMode: "set"});
			},
			error : function(xhr, errorResp, error){
				console.log(xhr);
				console.log(errorResp);
				console.log(error);
			}
		}).submit();
	}
	ucommCode = function(obj) {
		$("#ucomm-modalM").css('display', 'flex')
		ucommObj = obj;
		//데이터 가져오기
		$.ajax({
			   url : $.getContextPath() + "/info2/common/unit.do",
			   dataType : "json",
			   success : function(data){
				   console.log(data);
				   ucommMProvider.fillJsonData(data, {fillMode: "set"});
			   },
			   error: function(xhr, errorResp, error){
				   console.log(xhr);
				   console.log(errorResp);
				   console.log(error);
			   }
		});
	}
	ldivCode = function(obj) {
		$("#ldiv-modal").css('display', 'flex')
		ldivObj = obj;
		//데이터 가져오기
		$.ajax({
			   url : $.getContextPath() + "/info2/div/divList.do",
			   dataType : "json",
			   success : function(data){
				   console.log(data);
				   ldivProvider.fillJsonData(data, {fillMode: "set"});
			   },
			   error: function(xhr, errorResp, error){
				   console.log(xhr);
				   console.log(errorResp);
				   console.log(error);
			   }
		});
	}
	mdivCode = function(obj) {
		mdivObj = obj;
		var divCodeL = $("input[name=divCodeL]").val()
		console.log(divCodeL)
		if (divCodeL) {
			// 데이터 가져오기
			$.ajax({
				url : $.getContextPath() + "/info2/div/divList.do",
				data : {
					"divCategory" : "L",
					"divChild" : divCodeL
				},
				method : 'post',
				dataType : "json",
				success : function(data) {
					console.log(data);
					mdivProvider.fillJsonData(data, {
						fillMode : "set"
					});
				},
				error : function(xhr, errorResp, error) {
					console.log(xhr);
					console.log(errorResp);
					console.log(error);
				}
			});
		}

		$("#mdiv-modal").css('display', 'flex')
	}
	sdivCode = function(obj) {
		sdivObj = obj;
		var divCodeM = $("input[name=divCodeM]").val()
		console.log(divCodeM)
		if (divCodeM) {
			// 데이터 가져오기
			$.ajax({
				url : $.getContextPath() + "/info2/div/divList.do",
				data : {
					"divCategory" : "M",
					"divChild" : divCodeM
				},
				method : 'post',
				dataType : "json",
				success : function(data) {
					console.log(data);
					sdivProvider.fillJsonData(data, {
						fillMode : "set"
					});
				},
				error : function(xhr, errorResp, error) {
					console.log(xhr);
					console.log(errorResp);
					console.log(error);
				}
			});
		}

		$("#sdiv-modal").css('display', 'flex')
	}