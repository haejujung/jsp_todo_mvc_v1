<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1> 싸인업 회원가입 페이지 입니다.</h1>
	
	<%
		String eMessage = (String)request.getAttribute("errorMessage");
		out.println(eMessage);
	%>
</body>
</html>