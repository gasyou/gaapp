<%@ page pageEncoding="UTF-8"%>
<%@ include file="common.jsp"%>
<%
%>
<html>
<head>
<title>Search Profile</title>
<meta http-equiv="content-type" content="text/html; charset=UTF-8" />
<jsp:include page="headCommon.jsp" />
</head>
<body>
	<div class="container-fluid">
		<div class="row">
			<div class="col">
				<h1>Views</h1>
			</div>
		</div>
		<div class="row">
			<div class="col">
				<form action="${app}/AccountList.x" method="get">
					<input type="text" name="accountId" value="">
					<button type="submit">Search</button>
				</form>
			</div>
		</div>
		<div class="row">
			<div class="col">
				<table class="table table-striped">
					<tr>
						<th>アカウントID</th>
						<th>アカウント名</th>
						<th>プロパティID</th>
						<th>プロパティ名</th>
						<th>ビューID</th>
						<th>ビュー名</th>
					</tr>
					<c:forEach var="profileW" items="${profiles}">
						<tr>
							<td>${profileW.account.id}</td>
							<td>${profileW.account.name}</td>
							<td>${profileW.webProperty.id}</td>
							<td>${profileW.webProperty.name}</td>
							<td>${profileW.profile.id}</td>
							<td>${profileW.profile.name}</td>
						</tr>
					</c:forEach>
				</table>
			</div>
		</div>
	</div>
</body>
</html>