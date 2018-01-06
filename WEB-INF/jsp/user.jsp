<%@ page pageEncoding="UTF-8"%>
<%@include file="common.jsp"%>
<!doctype html>
<html>
<head>
<title>User</title>
<jsp:include page="headCommon.jsp"></jsp:include>
</head>
<body>
	<div class="container-fluid">
		<div class="row">
			<div class="col">
				<h1>User</h1>
			</div>
		</div>
		<div class="row">
			<div class="col">
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

					<c:if test="${mode!='add'}">
						<div class="form-group">
							<label for="id" class="control-label">ID</label><span id="id">${id}</span>
							<input type="hidden" name="id" value="${id}">
						</div>
					</c:if>

					<div class="form-group">
						<label for="loginId" class="control-label">Login ID</label>
						<c:choose>
							<%-- 入力と表示を切り替え --%>
							<c:when test="${mode=='add' || mode=='update'}">
								<input type="text" id="loginId" name="loginId"
									autocomplete="off" class="form-control"
									<c:if test="${mode=='update'}"> value="${loginId}" </c:if>>
								<div class="valid-feedback">This ID is available.</div>
								<div class="invalid-feedback">Duplicate login ID.</div>
							</c:when>
							<c:otherwise>
								<span id="loginId">${loginId}</span>
								<input type="hidden" name="loginId" value="${loginId}">
							</c:otherwise>
						</c:choose>
					</div>

					<c:if
						test="${mode=='add' || mode=='update' || mode=='update.confirm'}">
						<div class="form-group">
							<label for="password1" class="control-label">Password</label>
							<c:choose>
								<c:when test="${mode=='update.confirm'}">
									<span id="password1">***</span>
									<input type="hidden" name="password1" value="${password1}">
									<input type="hidden" name="password2" value="${password2}">
								</c:when>
								<c:otherwise>
									<input type="password" id="password1" name="password1"
										class="form-control">
									<label for="password2" class="control-label">Password
										(Confirm)</label>
									<input type="password" id="password2" name="password2"
										class="form-control" aria-describedby="password2-help">
									<small id="password2-help" class="form-text text-muted">確認のためパスワードをもう一度入力してください。</small>
								</c:otherwise>
							</c:choose>
						</div>
					</c:if>

					<div class="form-group">
						<label for="name" class="control-label">Name</label>
						<c:choose>
							<%-- 入力と表示を切り替え --%>
							<c:when test="${mode=='add' || mode=='update'}">
								<input type="text" id="name" autocomplete="off" name="name"
									<c:if test="${mode=='update'}"> value="${user.name}" </c:if>
									class="form-control">
							</c:when>
							<c:otherwise>
								<span id="name">${name}</span>
								<input type="hidden" name="name" value="${name}">
							</c:otherwise>
						</c:choose>

					</div>

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
	</div>
	<script src="${app}/js/user.js"></script>
</body>
</html>