<%@ page pageEncoding="UTF-8"%>
<%@ page contentType="application/json;charset=UTF-8" %>
<%-- loginId はサニタイズしないと危険 --%>
{"loginId":"${loginId}","duplicate":${user != null}}