<%@ include file="common.jsp"%>
<%
// Nothing to do.
%>
<html>
<head>
<title>User</title>
<meta http-equiv="content-type" content="text/html; charset=UTF-8"/>
<link rel="stylesheet" type="text/css" href="<%=contextPath%>/css/common.css" />
</head>
<body>
<h1>Login</h1>
<form action="${app}/Login.x" method="post">
	<%-- mode --%>
	<input type="hidden" name="mode" value="login">

	<%-- Return URL --%>
	<input type="hidden" name="rt" value="${rt}">

	<table>
		<tr>
			<th>Login ID</th>
			<td><input type="text" name="loginId" autocomplete="off"></td>
		</tr>
		<tr>
			<th>Password</th>
			<td><input type="password" name="password"></td>
		</tr>
	</table>

	<button type="submit">Login</button>
</form>
</body>
</html>