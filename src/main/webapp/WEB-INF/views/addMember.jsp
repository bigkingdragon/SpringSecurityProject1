<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<title>Insert title here</title>
</head>
<body>
<form method="post" action="addMember">
	<!-- <input type="hidden" name="${_csrf.parameterName}"	value="${_csrf.token}" /> -->
	아이디 : <input type="text" name="userid" /><br/><br/>
	패스워드 : <input type="password" name="userpw" /><br/><br/>
	이름 : <input type="text" name="username" /><br/><br/>
	이메일 : <input type="text" name="email" /><br/><br/>
	<input type="submit" value="회원가입" />
</form>
</body>
</html>