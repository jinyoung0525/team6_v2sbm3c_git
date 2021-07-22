<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
System.out.println("-> logout.jsp");
%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>로그아웃</title>
<!-- /static 기준 -->
<link href="/css/style.css" rel="Stylesheet" type="text/css">
</head>
<body>
<jsp:include page="../menu/top.jsp" flush='false' />
<br>
<h1>로그아웃</h1>
<br>
<form name='frm' action="/admin/logout.do" method="post">
    <input type="hidden" name="${ _csrf.parameterName }" value="${ _csrf.token }">
    <script type="text/javascript">
      document.frm.submit();
    </script>
    <!-- 정말로 로그아웃 할 것인지등을 묻는 화면 구성 가능 -->
</form>
<jsp:include page="../menu/bottom.jsp" flush='false' />
</body>
</html>





