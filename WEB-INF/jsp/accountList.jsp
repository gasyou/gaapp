<%@ include file="common.jsp"%>
<%@ page import="com.google.api.services.analytics.model.Account"%>
<%
	List<Account> accounts = (List<Account>) request.getAttribute("accounts");
	String id = (String) request.getAttribute("id") != null ? (String) request.getAttribute("id") : "";
%>
<html>
<head>
<title>Search Account</title>
<jsp:include page="headCommon.jsp" />
</head>
<body>
	<div class="container-fluid">
		<div class="row">
			<h1>Accounts</h1>
			<form action="${app}/AccountList.x" method="get">
				<input type="text" name="accountId" value="<%=id%>">
				<button type="submit">Send</button>
			</form>
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
</body>
</html>