<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<c:set var="btitle" value="${boardVO.btitle }" />
<c:set var="bcontent" value="${boardVO.bcontent }" />
 
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
 
<DIV class='title_line'>게시판</DIV>

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
      <c:set var="imagesaved" value="${boardVO.imagesaved.toLowerCase() }" />
        <c:if test="${imagesaved.endsWith('jpg') || imagesaved.endsWith('png') || imagesaved.endsWith('gif')}">
        
          <DIV style="width: 47%; height: 260px; float: left; margin-right: 10px;">
            <span style="font-size: 1.5em; font-weight: bold;">${btitle }</span><br>
            
            <DIV style="width: 50%; float: left; margin-right: 10px;">
              <IMG src="/board/storage/${boardVO.imagesaved }" style="width: 100%;">
            </DIV>
          </DIV> 
        </c:if> 
        <DIV>${boardVO.bcontent }</DIV>
      </li>
      <li class="li_none">
        <DIV style='text-decoration: none;'>
          검색어(키워드): ${boardVO.word }
        </DIV>
      </li>
      <li class="li_none">
        <DIV>
          <c:if test="${boardVO.image.trim().length() > 0 }">
            첨부 파일: <A href='/download?dir=/board/storage&filename=${boardVO.imagesaved}&downname=${boardVO.image}'>${boardVO.image}</A> (${boardVO.size1_label})  
          </c:if>
        </DIV>
      </li>   
    </ul>
  </fieldset>

</DIV>
 
<jsp:include page="../menu/bottom.jsp" flush='false' />
</body>
 
</html>