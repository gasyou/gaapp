<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page pageEncoding="UTF-8" %>

<%@ page import="java.util.*,java.io.*" %>
<%
String contextPath = request.getContextPath();
%>
<c:set var="app" value="<%= contextPath %>" />

<script type="text/javascript">
<%-- JavaScriptのグローバル定数を用意 --%>
var GAM = {
        APP:"${app}"
};
</script>