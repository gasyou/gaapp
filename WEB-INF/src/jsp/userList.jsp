<%@ include file="common.jsp"%>
<%= System.getProperty("file.encoding") %>
<html>
<head>
<title>Search User</title>
<meta http-equiv="content-type" content="text/html; charset=UTF-8"/>
<link rel="stylesheet" type="text/css" href="<%=contextPath%>/css/common.css" />
</head>
<body>
<h1>Users</h1>
<form action="<%=contextPath %>/UserList.x" method="get">
	<input type="text" name="userName" value="${userName}">
	<button type="submit">Search</button>
</form>
<table>
	<tr>
		<th>ID</th>
		<th>Name</th>
	</tr>
	<c:forEach var="user" items="${userList}">
		<tr>
			<td><a href="<%=contextPath %>/User.x?userId=${user.id}">${user.id}</a></td>
			<td><a href="<%=contextPath %>/User.x?userId=${user.id}">${user.name}</a></td>
		</tr>
	</c:forEach>
</table>
<a href="<%=contextPath %>/User.x?mode=add">Add User</a>
</body>
</html>