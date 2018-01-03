<%@ include file="common.jsp"%>
<!DOCTYPE html>
<html>
<head>
<title>Search User</title>
<meta http-equiv="content-type" content="text/html; charset=UTF-8" />
<jsp:include page="headCommon.jsp" />
</head>
<body>
	<div class="container-fluid">
		<div class="row-fluid">
			<h1>Users</h1>
			<form action="<%=contextPath%>/UserList.x" method="get">
				<input type="text" name="userName" value="${userName}">
				<button type="submit" class="btn btn-default">Search</button>
			</form>
			<table class="table table-striped">
				<thead>
					<tr>
						<th>#</th>
						<th>Login ID</th>
						<th>Name</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="user" items="${userList}">
						<tr>
							<th><a href="${app}/User.x?userId=${user.id}"
								class="btn link">${user.id}</a></th>
							<td><a href="${app}/User.x?userId=${user.id}"
								class="btn link">${user.loginId}</a></td>
							<td><a href="${app}/User.x?userId=${user.id}"
								class="btn link">${user.name}</a></td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
			<a href="<%=contextPath%>/User.x?mode=add" class="btn btn-default">Add
				User</a>
		</div>
	</div>
</body>
</html>