<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html lang="ko">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>로그인</title>
<!-- /static 기준 -->
<link href="/css/style.css" rel="Stylesheet" type="text/css">
</head>
<body>
<jsp:include page="../menu/top.jsp" flush='false' />
<br>
<h1>로그인(login_form.jsp)</h1>
<br>
<form name='frm' action="/spring_security_check.do" method="post">
    <input type="hidden" name="${ _csrf.parameterName }" value="${ _csrf.token }">
    <c:if test="${param.error != null}">
      <p>
          로그인 에러! <br />
          ${error_message}
      </p>
    </c:if>
    <br>
    
    ID : <input type="text" name="id" value="${id}"> <br><br>
    
    PW : <input type="password" name="password"> <br><br>
    
    <input type="submit" value="LOGIN"> 
    <input type="button" value="사용자 테스트 계정" 
               onclick="frm.id.value='user1'; frm.password.value='1234'">
    <input type="button" value="관리자 테스트 계정" 
               onclick="frm.id.value='admin1'; frm.password.value='1234'"> <br>
</form>
<jsp:include page="../menu/bottom.jsp" flush='false' />
</body>
</html>
