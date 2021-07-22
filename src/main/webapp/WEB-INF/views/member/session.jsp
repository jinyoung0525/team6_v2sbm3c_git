<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
 
<!DOCTYPE html> 
<html lang="ko"> 
<head> 
<meta charset="UTF-8"> 
<meta name="viewport" content="user-scalable=yes, initial-scale=1.0, maximum-scale=3.0, width=device-width" /> 
<title>http://localhost:9091/member/session.do</title>
 
 
<link href="/css/style.css" rel="Stylesheet" type="text/css">
 
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
 
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
</head> 
 
<body>
<jsp:include page="../menu/top.jsp" flush='false' />
 
  <c:forEach var="name" items="${pageContext.session.attributeNames}">
      변수: ${name}
      값: ${sessionScope[name]}
      <br>
  </c:forEach> 
  <br>  
  로그인된 ID: ${sessionScope.id}
  <br>
  
  <%-- 등급(1 ~ 10: 관리자 / 11~20: 회원 / 30~39: 정지 회원 / 40~49: 탈퇴 회원 / 99: 비회원) --%>
  <c:set var="grade" value="${sessionScope.grade}" />
  로그인 등급: ${grade }<br>
  <c:if test="${sessionScope.id ne null }">
    로그인된 사용자 메뉴 출력 영역(특정 권한별 구분)<br>
    <c:choose>
      <c:when test="${grade >= 1 and grade <= 10}">관리자 영역</c:when>    
      <c:when test="${grade >= 11 and grade <= 20}">회원 영역</c:when>
      <c:when test="${grade >= 30 and grade <= 39}">정지 회원 영역</c:when>
      <c:when test="${grade >= 40 and grade <= 49}">탈퇴 회원 영역</c:when>
      <c:when test="${grade == 99}">비회원 영역 처리</c:when>
    </c:choose>  
    
    <c:choose>
      <c:when test="${grade <= 20}">관리자 + 회원 영역</c:when>    
      <c:when test="${grade >= 30}">비회원(정지, 탈퇴) 영역 처리</c:when>
    </c:choose>  
  </c:if><br> 
 
<jsp:include page="../menu/bottom.jsp" flush='false' />
</body>
 
</html>

