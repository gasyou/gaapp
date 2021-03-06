<%@ include file="common.jsp"%>
<%@ page import="com.google.api.services.analytics.model.Account"%>
<%
	List<Account> accounts = (List<Account>) request.getAttribute("accounts");
	String id = (String) request.getAttribute("id") != null ? (String) request.getAttribute("id") : "";
%>
<html>
<head>
<title>Search Account</title>
<meta http-equiv="content-type" content="text/html; charset=UTF-8" />
<jsp:include page="headCommon.jsp" />
</head>
<body>
	<div class="container-fluid">
		<div class="row">
			<div class="col">
				<h1>Accounts</h1>
			</div>
		</div>
		<div class="row">
			<div class="col">
				<form action="${app}/AccountList.x" method="get">
					<input type="text" name="accountId" value="<%=id%>">
					<button type="submit">Search</button>
				</form>
			</div>
		</div>
		<div class="row">
			<div class="col">
				<table class="table table-striped">
					<tr>
						<th>ID</th>
						<th>Name</th>
					</tr>
					<c:forEach var="acct" items="<%=accounts%>">
						<tr>
							<td>${acct.id}</td>
							<td>${acct.name}</td>
						</tr>
					</c:forEach>
				</table>
			</div>
		</div>
	</div>
</body>
</html>