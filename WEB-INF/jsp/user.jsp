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
<h1>User</h1>
<form action="${app}/UserUpdate.x" method="post">
	<%-- mode を hidden で仕込んでおく --%>
	<c:choose>
		<c:when test="${mode=='update.confirm'}">
			<input type="hidden" name="mode" value="update.update">
		</c:when>
		<c:otherwise>
			<input type="hidden" name="mode" value="${mode}">
		</c:otherwise>
	</c:choose>

	<%-- 更新の場合、更新回数を忍ばせておく --%>
	<c:choose>
		<c:when test="${mode=='update' || mode=='update.confirm'}">
			<input type="hidden" name="updCnt" value="${updCnt}">
		</c:when>
	</c:choose>

	<table>
		<tr>
			<th>ID</th>
			<c:choose>
				<%-- 入力と表示を切り替え --%>
				<c:when test="${mode=='add'}">
					<td><input type="text" name="id" autocomplete="off"></td>
				</c:when>
				<c:otherwise>
					<td>${id}</td>
					<input type="hidden" name="id" value="${id}">
				</c:otherwise>
			</c:choose>
		</tr>
		<tr>
			<th>Login ID</th>
			<c:choose>
				<%-- 入力と表示を切り替え --%>
				<c:when test="${mode=='add' || mode=='update'}">
					<td><input type="text" name="loginId"  autocomplete="off" <c:if test="${mode=='update'}"> value="${loginId}" </c:if> ></td>
				</c:when>
				<c:otherwise>
					<td>${loginId}<input type="hidden" name="loginId" value="${loginId}"></td>
				</c:otherwise>
			</c:choose>
		</tr>
		<c:if test="${mode=='add' || mode=='update' || mode=='update.confirm'}">
			<tr>
				<th>Password</th>
				<c:choose>
					<c:when test="${mode=='update.confirm'}">
						<td>***<input type="hidden" name="password1" value="${password1}"><input type="hidden" name="password2" value="${password2}"></td>
					</c:when>
					<c:otherwise>
						<td><input type="password" name="password1"><br><input type="password" name="password2"></td>
					</c:otherwise>
				</c:choose>
			</tr>
		</c:if>
		<tr>
		
			<th>Name</th>
			<c:choose>
				<%-- 入力と表示を切り替え --%>
				<c:when test="${mode=='add' || mode=='update'}">
					<td><input type="text"  autocomplete="off" name="name" <c:if test="${mode=='update'}"> value="${user.name}" </c:if>></td>
				</c:when>
				<c:otherwise>
					<td>${name}<input type="hidden" name="name" value="${name}"></td>
				</c:otherwise>
			</c:choose>
		</tr>
	</table>

	<c:choose>
		<c:when test="${mode==null}">
			<button type="button" onClick="location.href='${app}/User.x?mode=update&userId=${user.id}'">Update User</button>
		</c:when>
		<c:when test="${mode=='update'}">
			<button type="submit">Confirm</button>
		</c:when>
		<c:when test="${mode=='update.confirm'}">
			<button type="submit">Update</button>
		</c:when>
		<c:when test="${mode=='add'}">
			<button type="submit">Add User</button>
		</c:when>
	</c:choose>
</form>
</body>
</html>