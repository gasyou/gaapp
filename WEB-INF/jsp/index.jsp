<%@ include file="common.jsp"%>
<%--
<jsp:include page="common.jsp"></jsp:include>
--%>
<%
String encoding = System.getProperty("file.encoding").toString();
%>
<html>
<head>
<link rel="stylesheet" type="text/css" href="<%=contextPath%>/css/common/sample.css" />
</head>
<body>
Hello!! JSP!!
<%= encoding %>
<div>
<a href="<%=contextPath %>/UserList.x">User List</a>
</div>
<div>
<a href="<%=contextPath %>/AccountList.x">Account List</a>
</div>
</body>
</html>