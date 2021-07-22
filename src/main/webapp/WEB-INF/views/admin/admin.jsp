<%@ page contentType="text/html; charset=UTF-8" %>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Admin</title>
<!-- /static 기준 -->
<link href="/css/style.css" rel="Stylesheet" type="text/css">
</head>
<body>
  <jsp:include page="../menu/top.jsp" flush='false' />
  <H1>관리자만 접속 가능한 페이지 입니다.</H1>
  
  <hr>
  
  <sec:authorize access="isAuthenticated()">
  <p> 관리자 로그인된 상태임.</p>
  </sec:authorize>
  
  <sec:authorize access="!isAuthenticated()">
  <p> 회원 로그아웃 상태</p>
  </sec:authorize>
  
  <%-- USER ID : ${pageContext.request.userPrincipal.name}<br/> --%>
  USER ID : <sec:authentication property="name"/><br/>
  
  USER ID(pageContext) : ${pageContext.request.userPrincipal.name}<br>
  USER ID(tag) : <sec:authentication property="name"/><br>
  
  <a href="/logout">Log Out</a> <br>
  
  <jsp:include page="../menu/bottom.jsp" flush='false' />
</body>
</html>

