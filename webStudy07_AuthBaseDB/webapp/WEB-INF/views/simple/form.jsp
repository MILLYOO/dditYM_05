<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>    
	<form:form method="post" modelAttribute="simpleList">
		<form:input path="simpleList[0].prop1" placeholder="prop1"/>
		<form:errors path="simpleList[0].prop1"/>
		<form:input path="simpleList[0].prop2"  placeholder="prop2"/>
		<form:errors path="simpleList[0].prop2"/>
		<form:input path="simpleList[0].prop3"  placeholder="prop3"/>
		<form:errors path="simpleList[0].prop3"/>
		<form:input path="simpleList[1].prop1" placeholder="prop1"/>
		<form:errors path="simpleList[1].prop1"/>
		<form:input path="simpleList[1].prop2"  placeholder="prop2"/>
		<form:errors path="simpleList[1].prop2"/>
		<form:input path="simpleList[1].prop3"  placeholder="prop3"/>
		<form:errors path="simpleList[1].prop3"/>
		<form:input path="simpleList[2].prop1" placeholder="prop1"/>
		<form:errors path="simpleList[2].prop1"/>
		<form:input path="simpleList[2].prop2"  placeholder="prop2"/>
		<form:errors path="simpleList[2].prop2"/>
		<form:input path="simpleList[2].prop3"  placeholder="prop3"/>
		<form:errors path="simpleList[2].prop3"/>
		<form:button>전송</form:button>
	</form:form>
