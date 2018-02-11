<%@ include file="common.jsp"%>
<html>
<head>
<title>Asset Account</title>
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
				<form action="${app}/AssetList.x" method="get">
					<input type="text" name="keyword" value="${keyword}">
					<button type="submit">Search</button>
				</form>
			</div>
		</div>
		<div class="row">
			<div class="col">
				<table class="table table-striped">
					<tr>
						<th>Type</th>
						<th>Primary Key</th>
						<th>Title</th>
					</tr>
					<c:forEach var="asset" items="${assets}">
						<tr>
							<td>${asset.type}</td>
							<td>${asset.primaryKey}</td>
							<td>${asset.title}</td>
						</tr>
					</c:forEach>
				</table>
			</div>
		</div>
	</div>
</body>
</html>