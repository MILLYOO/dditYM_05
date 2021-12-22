<%@page import="kr.or.ddit.db.ConnectionFactory"%>
<%@page import="kr.or.ddit.vo.DataBasePropertyVO"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="java.sql.ResultSetMetaData"%>
<%@page import="java.sql.SQLException"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.Connection"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h4>JDBC(Java DataBase Connectivity)</h4>
<pre>
	1. 벤더가 제공하는 드라이버를 빌드패스에 추가
	2. 드라이버 로딩
	<%
// 	Object obj = new Object();	// 메모리가 3개가 잡힌다. 첫번쨰 Object 상속풀에 올라감 가비지컬렉션의 대상X. 두번째 bew Object() 힙메모리에 올라감 가비지컬렉션의 대상O
								// obj라는 변수를 잡힘.
// 	Class.forName("oracle.jdbc.driver.OracleDriver"); // 드라이버를 메모리에 적재하는 코드 이 코드가 실행되면 상속풀에 올라간다. 그래서 새로고침할때마다 연결하여 메모리를 잡아먹음
// 	3. Connection 생성
// 	String url = "jdbc:oracle:thin:@localhost:1521:xe";
// 	String user = "JWH97";
// 	String password = "java";
	List<DataBasePropertyVO> dataList = new ArrayList<>();
	String[] headers = null;
	try(
// 	Connection conn = DriverManager.getConnection(url, user, password);
	Connection conn = ConnectionFactory.getConnection();
	Statement stmt = conn.createStatement();
	){
	StringBuffer sql = new StringBuffer();
	sql.append(" SELECT PROPERTY_NAME, PROPERTY_VALUE, DESCRIPTION ");
	sql.append(" FROM DATABASE_PROPERTIES");
	ResultSet rs = stmt.executeQuery(sql.toString());
	ResultSetMetaData rsmd = rs.getMetaData();
	int colCnt = rsmd.getColumnCount();
	headers = new String[colCnt];
	for(int i = 1 ; i <= colCnt ; i++){
		headers[i-1] = rsmd.getColumnName(i);
	}
	while(rs.next()){
		DataBasePropertyVO propVO = new DataBasePropertyVO();
		propVO.setPropertyName(rs.getString("PROPERTY_NAME"));
		propVO.setPropertyValue(rs.getString("PROPERTY_VALUE"));
		propVO.setDescription(rs.getString("DESCRIPTION"));
		dataList.add(propVO);
	}
		}catch(SQLException e){
	
		}
%>
	4. 쿼리 객체 생성
		1) Statement : 동적 쿼리 생성에 활용.
		2) PreparedStatement(선컴파일된 쿼리 객체)
		3) CallableStatement(프로시저나 함수를 호출하는 쿼리 객체)
	5. 질의 수행(SQL)
		1) ResultSet excuteQuery : SELECT
		2) int(rowcnt) excuteUpdate : INSERT/UPDATE/DELETE1
	6. 질의 수행 결과 사용
	7. release(close) 
</pre>
<table>
	<thead>
		<tr>
			<%
				for(String header : headers){
					%>
					<th><%=header %></th>
					<%
				}
			%>
		</tr>
	</thead>
	<tbody>
	<%
		for(DataBasePropertyVO tmp : dataList){
			%>
				<tr>
					<td><%=tmp.getPropertyName()%></td>
					<td><%=tmp.getPropertyValue() %></td>
					<td><%=tmp.getDescription() %></td>
				</tr>
			<%
		}
	%>
	</tbody>
</table>
</body>
</html>