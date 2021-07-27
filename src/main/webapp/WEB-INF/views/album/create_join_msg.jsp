<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
  
<!DOCTYPE html> 
<html lang="ko"> 
<head> 
<meta charset="UTF-8"> 
<meta name="viewport" content="user-scalable=yes, initial-scale=1.0, maximum-scale=3.0, width=device-width" /> 
<title>Team6</title>
<%-- /static/css/style.css --%> 
<link href="/css/style.css" rel="Stylesheet" type="text/css">

<script type="text/JavaScript" src="http://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>

<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">

</head> 
<body>
<jsp:include page="../menu/top.jsp" flush='false' />

<DIV class='title_line'>앨범 > 알림</DIV>

<DIV class='message'>
  <fieldset class='fieldset_basic'>
    <UL>
      <c:choose>
        <c:when test="${param.cnt == 1}"> <%-- Java if --%>
          <LI class='li_none'>
            <span class="span_success">앨범 '${param.title }'을 등록했습니다.</span>
          </LI>                                                                      
        </c:when>
        <c:otherwise>
          <LI class='li_none_left'>
            <span class="span_fail">앨범 '${param.title }'등록에 실패했습니다.</span>
          </LI>
          <LI class='li_none_left'>
            <span class="span_fail">다시 시도해주세요.</span>
          </LI>
        </c:otherwise>
      </c:choose>
      <LI class='li_none'>
        <br>
        <c:choose>
          <c:when test="${param.cnt == 1 }">
            <%-- <button type='button' 
                         onclick="location.href='./artist_update.do?artistno=${param.artistno }'"
                         class="btn btn-primary">관련 아티스트 등록</button> --%>
            <button type='button' 
                         onclick="location.href='./create_join.do'"
                         class="btn btn-primary">새로운 컨텐츠 등록</button>
          </c:when>
          <c:otherwise>
            <button type='button' onclick="history.back();" class="btn btn-primary">다시 시도</button>
          </c:otherwise>
        </c:choose>
        
        <%-- <button type='button' onclick="location.href='./list_by_cateno.do?cateno=${param.cateno}'" class="btn btn-primary">목록</button> --%>
        <%-- <button type='button' onclick="location.href='./list_by_cateno_search.do?cateno=${param.cateno}'" class="btn btn-primary">목록</button> --%>
        <button type='button' onclick="location.href='./list_all_join.do'" class="btn btn-primary">목록</button>
      </LI>
    </UL>
  </fieldset>

</DIV>

<jsp:include page="../menu/bottom.jsp" flush='false' />
</body>

</html>



