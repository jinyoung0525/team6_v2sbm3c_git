<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<c:set var="albumno" value="${albumVO.albumno }" />
<c:set var="title" value="${albumVO.title }" />
<c:set var="kind" value="${albumVO.kind }" />
<c:set var="release" value="${albumVO.release }" />
<c:set var="genre" value="${albumVO.genre }" />
<c:set var="enter" value="${albumVO.enter }" />
<c:set var="likey" value="${albumVO.likey }" />
<c:set var="intro" value="${albumVO.intro }" />
<c:set var="detail" value="${albumVO.detail }" />
<c:set var="fname" value="${albumVO.fname }" />
<c:set var="fupname" value="${albumVO.fupname }" />
<c:set var="thumb" value="${albumVO.thumb }" />
 
<!DOCTYPE html> 
<html lang="ko"> 
<head> 
<meta charset="UTF-8"> 
<meta name="viewport" content="user-scalable=yes, initial-scale=1.0, maximum-scale=3.0, width=device-width" /> 
<title>Team6</title>
 
<link href="/css/style.css" rel="Stylesheet" type="text/css">
 
<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>

<!-- Bootstrap -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
    
<script type="text/javascript">
  $(function(){
 
  });
</script>
 
</head> 
 
<body>
<jsp:include page="../menu/top.jsp" flush='false' />
 
<DIV class='title_line'>
  <A href="../artist/list_by_artistno_grid.do" class='title_link'>아티스트</A> >
  <A href="../artist/read.do?artistno=${artistVO.artistno }&now_page=" class='title_link'>${artistVO.name }</A> >
  <A href="./list_by_artistno_grid.do?artistno=${artistVO.artistno }" class='title_link'>앨범</A> >
  <A href="../album/read.do?albumno=${albumno}&now_page=" class='title_link'>${title }</A> >
  <A href="../artist/delete.do?artistno=${artistno }" class='title_link'>삭제</A>
</DIV>

<DIV class='content_body'>
  <ASIDE class="aside_right">
    <A href="./create.do">등록</A>
    <span class='menu_divide' >│</span>
    <A href="javascript:location.reload();">새로고침</A>
    <span class='menu_divide' >│</span>
    <A href="./list_by_artistno_grid.do?artistno=${artistVO.artistno }">앨범 목록</A>
    <span class='menu_divide' >│</span>
    <A href="./update_album.do?artistno=${parma.artistno }&now_page=${param.now_page }&albumno=${albumno}">앨범 수정</A>
    <%-- <span class='menu_divide' >│</span>
    <A href="./update_file.do?newsno=${newsno }&now_page=${param.now_page }">파일 수정</A> --%>
     <span class='menu_divide' >│</span>
    <A href="./delete.do?albumno=${albumno }">삭제</A>
  </ASIDE> 
  
    <DIV style="text-align: right; clear: both;">  
    <form name='frm' id='frm' method='get' action='./list_by_artistno_search.do'>
      <input type='hidden' name='artistno' value='${artistVO.artistno }'>
      <c:choose>
        <c:when test="${param.word != '' }"> <%-- 검색하는 경우 --%>
          <input type='text' name='word' id='word' value='${param.word }' style='width: 20%;'>
        </c:when>
        <c:otherwise> <%-- 검색하지 않는 경우 --%>
          <input type='text' name='word' id='word' value='' style='width: 20%;'>
        </c:otherwise>
      </c:choose>
      <button type='submit'>검색</button>
      <c:if test="${param.word.length() > 0 }">
        <button type='button' 
                     onclick="location.href='./list_by_artistno_grid.do'">검색 취소</button>  
      </c:if>    
    </form>
  </DIV>
  
  <DIV class='menu_line'></DIV>

  <fieldset class="fieldset_basic">
    <ul>
      <li class="li_none">
        <DIV style='text-align: center; width: 40%; float: left;'>
          <c:set var="fupname" value="${albumVO.fupname.toLowerCase() }" />
          <c:set var="thumb" value="${albumVO.thumb }" />
          <c:choose>
            <c:when test="${thumb.endsWith('jpg') || thumb.endsWith('png') || thumb.endsWith('gif')}">
              <IMG src="/album/storage/${fupname }" style='width: 70%;'> 
            </c:when>
            <c:otherwise> <!-- 이미지가 아닌 일반 파일 -->
              첨부 파일: ${fname}
            </c:otherwise>
          </c:choose>
        </DIV>

    
          
        <DIV style='text-align: left; width: 47%; float: left;'>
          <span style='font-size: 1.5em;'>${title}</span>
          <br>
          <FORM name='frm' method='POST' action='./delete.do'>
              <input type="hidden" name="albumno" value='${param.albumno }'> 
              <input type='hidden' name='artistno' value='${artistVO.artistno}'>
              
              <DIV id='panel1' style="width: 40%; text-align: center; margin: 10px auto;"></DIV>
                    
              <div class="form-group">   
                <div class="col-md-12" style='text-align: center; margin: 10px auto;'>
                  삭제 되는 아티스트: ${title }<br><br>
                  삭제하시겠습니까? 삭제하시면 복구 할 수 없습니다.<br><br>
                 
                  <button type = "submit" class="btn btn-info">삭제 진행</button>
                  <button type = "button" onclick = "history.back()" class="btn btn-info">취소</button>
                </div>
              </div>   
          </FORM>
        </DIV>
      </li>
      <li class="li_none">

      </li>   
    </ul>
  </fieldset>

</DIV>
 
<jsp:include page="../menu/bottom.jsp" flush='false' />
</body>
 
</html>

