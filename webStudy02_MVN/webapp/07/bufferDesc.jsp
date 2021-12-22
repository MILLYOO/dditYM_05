<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" buffer="1kb" autoFlush="false"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>07/bufferDesc.jsp</title>
</head>
<body>
<h4>출력 버퍼</h4>
<pre>
전송효율
응답데이터가 커밋되기전에는 언제든지 메타데이터를 변경할 수 있다.
한번 커밋되면 그 이후는 변경 될 수 없다. ->이미 상태코드가 결정되어 나가버림.
이런 상황 방지하기 위해 버퍼 사용

JSP Buffer overflow
버퍼가 꽉차서 overflow가 되면 자동으로 처리 -> autoFlush

<%
	for(int i = 1; i <= 500 ; i++){
		out.print(i+"번째");
		// 버퍼제어 - 100byte가 남았다면 버퍼비우기
		if(out.getRemaining()<100){
			out.flush();
		}
		// 강제예외발생
		if(i == 490){
// 			throw new NullPointerException("강제 발생 예외");
			request.getRequestDispatcher("/07/destination.jsp").forward(request, response);
			//java.lang.IllegalStateException: Cannot forward after response has been committed
			// 응답이 커밋 된 이후 forward 될 수 없다. (버퍼가 flush가 되면 안됨)
		}
	}
%>

</pre>

</body>
</html>