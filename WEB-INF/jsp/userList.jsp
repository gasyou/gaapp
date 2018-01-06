<%@ include file="common.jsp"%>
<!doctype html>
<html>
<head>
<title>User List</title>
<meta http-equiv="content-type" content="text/html; charset=UTF-8" />
<jsp:include page="headCommon.jsp" />
</head>
<body>
	<div class="container-fluid">
		<div class="row">
			<div class="col-sm-12">
				<h1>User List</h1>
			</div>
		</div>
		<div class="row">
			<div class="col-sm-12">
				<form action="<%=contextPath%>/UserList.x" method="get">
					<input type="text" name="userName" value="${userName}">
					<button type="submit" class="btn btn-default">Search</button>
				</form>
			</div>
		</div>
		<div class="row">
			<div class="col-sm-12">
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
	</div>
</body>
</html>