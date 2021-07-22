<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<c:set var="ntitle" value="${noticeVO.ntitle }" />
<c:set var="ncontent" value="${noticeVO.ncontent }" />
 
<!DOCTYPE html> 
<html lang="ko"> 
<head> 
<meta charset="UTF-8"> 
<meta name="viewport" content="user-scalable=yes, initial-scale=1.0, maximum-scale=3.0, width=device-width" /> 
<title>(가제)음악감상사이트</title>
 
<link href="../css/style.css" rel="Stylesheet" type="text/css">
 
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
 
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
    
<script type="text/javascript">
 
  
</script>
 
</head> 
 
<body>
<jsp:include page="../menu/top.jsp" />
 
<DIV class='title_line'>공지사항</DIV>

<DIV class='content_body'>
  <ASIDE class="aside_right">
    <A href="./create.do">등록</A>
    <span class='menu_divide' >│</span>
    <A href="javascript:location.reload();">새로고침</A>
  </ASIDE> 
  
  <DIV class='menu_line'></DIV>

  <fieldset class="fieldset_basic">
    <ul>
      <li class="li_none">
      <c:set var="nimagesaved" value="${noticeVO.nimagesaved.toLowerCase() }" />
        <c:if test="${nimagesaved.endsWith('jpg') || nimagesaved.endsWith('png') || nimagesaved.endsWith('gif')}">
        
          <DIV style="width: 47%; height: 260px; float: left; margin-right: 10px;">
            <span style="font-size: 1.5em; font-weight: bold;">${ntitle }</span><br>
            
            <DIV style="width: 50%; float: left; margin-right: 10px;">
              <IMG src="/notice/storage/${noticeVO.nimagesaved }" style="width: 100%;">
            </DIV>
          </DIV> 
        </c:if> 
        <DIV>${noticeVO.ncontent }</DIV>
      </li>
      <li class="li_none">
        <DIV style='text-decoration: none;'>
          검색어(키워드): ${noticeVO.word }
        </DIV>
      </li>
      <li class="li_none">
        <DIV>
          <c:if test="${noticeVO.nimage.trim().length() > 0 }">
            첨부 파일: <A href='/download?dir=/notice/storage&filename=${noticeVO.nimagesaved}&downname=${noticeVO.nimage}'>${noticeVO.nimage}</A> (${noticeVO.size_label})  
          </c:if>
        </DIV>
      </li>   
    </ul>
  </fieldset>

</DIV>
 
<jsp:include page="../menu/bottom.jsp" flush='false' />
</body>
 
</html>