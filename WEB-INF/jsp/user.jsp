<%@ include file="common.jsp"%>
<!DOCTYPE html>
<html>
<head>
<title>User</title>
<jsp:include page="headCommon.jsp"></jsp:include>
</head>
<body>
	<div class="container-fluid">
		<div class="row-fluid">
			<h1>User</h1>
		</div>
		<div class="row-fluid">
			<form action="${app}/UserUpdate.x" method="post"
				class="form-horizontal">
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

				<div class="form-group">
					<label for="id" class="col-sm-2 control-label">ID</label>
					<div class="col-sm-10">
						<c:choose>
							<%-- 入力と表示を切り替え --%>
							<c:when test="${mode=='add'}">
								<input type="text" id="id" name="id" class="form-control"
									placeholder="ID" autocomplete="off">
							</c:when>
							<c:otherwise>
								<span id="id">${id}</span>
								<input type="hidden" name="id" value="${id}">
							</c:otherwise>
						</c:choose>
					</div>
				</div>
				<table>
					<tr>
						<th>Login ID</th>
						<c:choose>
							<%-- 入力と表示を切り替え --%>
							<c:when test="${mode=='add' || mode=='update'}">
								<td><input type="text" name="loginId" autocomplete="off"
									<c:if test="${mode=='update'}"> value="${loginId}" </c:if>></td>
							</c:when>
							<c:otherwise>
								<td>${loginId}<input type="hidden" name="loginId"
									value="${loginId}"></td>
							</c:otherwise>
						</c:choose>
					</tr>
					<c:if
						test="${mode=='add' || mode=='update' || mode=='update.confirm'}">
						<tr>
							<th>Password</th>
							<c:choose>
								<c:when test="${mode=='update.confirm'}">
									<td>***<input type="hidden" name="password1"
										value="${password1}"><input type="hidden"
										name="password2" value="${password2}"></td>
								</c:when>
								<c:otherwise>
									<td><input type="password" name="password1"><br>
										<input type="password" name="password2"></td>
								</c:otherwise>
							</c:choose>
						</tr>
					</c:if>
					<tr>

						<th>Name</th>
						<c:choose>
							<%-- 入力と表示を切り替え --%>
							<c:when test="${mode=='add' || mode=='update'}">
								<td><input type="text" autocomplete="off" name="name"
									<c:if test="${mode=='update'}"> value="${user.name}" </c:if>></td>
							</c:when>
							<c:otherwise>
								<td>${name}<input type="hidden" name="name" value="${name}"></td>
							</c:otherwise>
						</c:choose>
					</tr>
				</table>

				<c:choose>
					<c:when test="${mode==null}">
						<button type="button" class="btn btn-primary"
							onClick="location.href='${app}/User.x?mode=update&userId=${user.id}'">Update
							User</button>
					</c:when>
					<c:when test="${mode=='update'}">
						<button type="submit" class="btn btn-primary">Confirm</button>
					</c:when>
					<c:when test="${mode=='update.confirm'}">
						<button type="submit" class="btn btn-primary">Update</button>
					</c:when>
					<c:when test="${mode=='add'}">
						<button type="submit" class="btn btn-primary">Add User</button>
					</c:when>
				</c:choose>
			</form>
		</div>
	</div>
</body>
</html>