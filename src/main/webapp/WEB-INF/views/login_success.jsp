<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="true" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>Insert title here</title>
</head>
<body>
	<h2>로그인 성공</h2>
	이름 : ${sessionScope.userLoginInfo.username}
	<a href="logout">로그아웃</a>
	<a href="page1">페이지1</a>
</body>
</html>