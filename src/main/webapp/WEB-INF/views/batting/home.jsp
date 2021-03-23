<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<c:choose>
<c:when test="${empty sessionScope.SPRING_SECURITY_CONTEXT.authentication.principal.username}">
	location.href = '${pageContext.request.contextPath}/member/login';
</c:when>
<c:otherwise>
location.href = '${pageContext.request.contextPath}/match/list
</c:otherwise>
</c:choose>
</body>
</html>