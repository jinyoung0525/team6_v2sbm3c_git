<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
 
<!DOCTYPE html> 
<html lang="ko"> 
<head> 
<meta charset="UTF-8"> 
<meta name="viewport" content="user-scalable=yes, initial-scale=1.0, maximum-scale=3.0, width=device-width" /> 
<title>Team6</title>
 
<link href="../css/style.css" rel="Stylesheet" type="text/css">
 
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
 
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
    
<script type="text/javascript">
 
  
</script>
 
</head> 
 
<body>
<jsp:include page="../menu/top.jsp" />
 
<DIV class='title_line'>
  <A href="../album/list_by_albumno_search_paging.do?word=${musicVO.word }&now_page=1" class='title_link'>곡</A>
</DIV>

<DIV class='content_body'>
  <ASIDE class="aside_right">
    <A href="./create.do">등록</A>
    <span class='menu_divide' >│</span>
    <A href="javascript:location.reload();">새로고침</A>
    <span class='menu_divide' >│</span>
    <A href="./list_by_artistno_grid.do?artistno=${artistVO.artistno }">갤러리형</A>
  </ASIDE> 


  <DIV style="text-align: right; clear: both;">  
    <form name='frm' id='frm' method='get' action='./list_by_albumno_search.do'>
      <input type="hidden" name="${ _csrf.parameterName }" value="${ _csrf.token }">
      <input type='hidden' name='albumno' value='${albumVO.albumno }'>
      <c:choose>
        <c:when test="${param.word != '' } }"> <%-- 검색하는 경우 --%>
          <input type='text' name='word' id='word' value='${param.word }' style='width: 20%;'>
        </c:when>
        <c:otherwise> <%-- 검색하지 않는 경우 --%>
          <input type='text' name='word' id='word' value='' style='width: 20%;'>
        </c:otherwise>
      </c:choose>
      <button type='submit'>검색</button>
      <c:if test="${param.word.length() > 0 }">
        <button type='button' 
                     onclick="location.href='/music/list_by_albumno_search.do?'">검색 취소</button>  
      </c:if>    
    </form>
  </DIV>
  
  <DIV class='menu_line'></DIV>
  
  <table class="table table-striped" style='width: 100%;'>
    <colgroup>
      <col style="width: 10%;"></col>
      <col style="width: 60%;"></col>
      <col style="width: 20%;"></col>
      <col style="width: 10%;"></col>
    </colgroup>
    <%-- table 컬럼 --%>

   <thead>  
    <TR>
      <TH class="th_bs"></TH>
      <TH class="th_bs_left">곡</TH>
      <TH class="th_bs">듣기</TH>
      <TH class="th_bs">좋아요</TH>
    </TR>
   </thead>
    
    <%-- table 내용 --%>
    <tbody>
      <c:forEach var="musicVO" items="${list }">
        <c:set var="songno" value="${musicVO.songno }" />
        <c:set var="mp3" value="${musicVO.mp3 }" />
        <c:set var="title" value="${musicVO.title }" />
        <c:set var="thumb" value="${albumVO.thumb }" />
        
        <tr> 
          <td style='vertical-align: middle; text-align: center;'>
            <c:choose>
              <c:when test="${thumb.endsWith('jpg') || thumb.endsWith('png') || thumb.endsWith('gif')}">
                <%-- /static/contents/storage/ --%>
                <a href="./read.do?songno=${songno}"><IMG src="/album/storage/${thumb }" style="width: 120px; height: 80px;"></a> 
              </c:when>
              <c:otherwise> <!-- 이미지가 아닌 일반 파일 -->
                ${albumVO.fname}
              </c:otherwise>
            </c:choose>
          </td>  
          <td style='vertical-align: middle;'>
            <a href="./read.do?songno=${songno}"><strong>${title}</strong> </a> 
          </td> 
          <%-- <td style='vertical-align: middle; text-align: center;'>
            등록일 : ${newsVO.rdate.substring(0, 10) }
          </td> --%>
          <td style='vertical-align: middle; text-align: center;'><audio src="/music/storage/${title }.mp3" controls=""   loop=""  preload="auto"></audio> </td>
          <td style='vertical-align: middle; text-align: center;'>수정/삭제</td>
        </tr>
      </c:forEach>
      
    </tbody>
  </table>
</DIV>

 
<jsp:include page="../menu/bottom.jsp" />
</body>
 
</html>

