<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<c:set var="songno" value="${musicVO.songno }" />
<c:set var="title" value="${musicVO.title }" />
<c:set var="lyrics" value="${musicVO.lyrics }" />
<c:set var="likey" value="${musicVO.likey }" />
<c:set var="mp3" value="${musicVO.mp3 }" />
<c:set var="mp4" value="${musicVO.mp4 }" />
 
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
  <A href="../album/update_album.do" class='title_link'>수정</A>
</DIV>

<DIV class='content_body'>
  <ASIDE class="aside_right">
    <A href="./create.do">등록</A>
    <span class='menu_divide' >│</span>
    <A href="javascript:location.reload();">새로고침</A>
    <span class='menu_divide' >│</span>
    <A href="./list_by_albumno_search_paging.do?albumno=${albumVO.albumno }&word=&now_page=${param.now_page}">곡 목록</A>
    <span class='menu_divide' >│</span>
    <A href="./update_music.do?albumno=${albumVO.albumno }&songno=${songno}">곡 수정</A>
    <%-- <span class='menu_divide' >│</span>
    <A href="./update_file.do?newsno=${newsno }&now_page=${param.now_page }">파일 수정</A> --%>
     <span class='menu_divide' >│</span>
    <A href="./delete.do?albumno=${albumno }">삭제</A>
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

  <fieldset class="fieldset_basic">
    <ul>
      <li class="li_none">
        <%-- <DIV style='text-align: center; width: 40%; float: left;'>
          <c:set var="fupname" value="${artistVO.fupname.toLowerCase() }" />
          <c:set var="thumb" value="${artistVO.thumb }" />
          <c:choose>
            <c:when test="${thumb.endsWith('jpg') || thumb.endsWith('png') || thumb.endsWith('gif')}">
              <IMG src="/artist/storage/${fupname }" style='width: 70%;'> 
            </c:when>
            <c:otherwise> <!-- 이미지가 아닌 일반 파일 -->
              첨부 파일: ${fname}
            </c:otherwise>
          </c:choose>
        </DIV> --%>

        <DIV style='text-align: center; width: 100%; float: left;'>
         
          <FORM name='frm' method='POST' action='./update_music.do' 
              enctype="multipart/form-data">
            <input type="hidden" name="${ _csrf.parameterName }" value="${ _csrf.token }">  
            <input type="hidden" name="albumno" value='${albumVO.albumno }'>
            <input type="hidden" name="songno" value='${songno }'>
            <div class="form-group">
               <label class="control-label col-md-4">곡 이름</label>
               <div class="col-md-8">
                 <input type='text' name='title' value='${title }' required="required" placeholder="곡 이름"
                            autofocus="autofocus" class="form-control" style='width: 30%;'>
               </div>
            </div>
            
            <div class="form-group">
               <label class="control-label col-md-2">가사</label>
               <div class="col-md-10">
                 <textarea name='lyrics'  value='${lyrics }' required="required" class="form-control" rows="12" style='width: 100%;'>${lyrics }</textarea>
               </div>
            </div>
            
            <div class="form-group">
               <label class="control-label col-md-4">Mp3</label>
               <div class="col-md-8">
                 <input type='file' class="form-control" name='mp3MF' id='mp3MF' 
                            value='' placeholder="파일 선택" style='width: 50%;'>
               </div>
            </div>  
            
             <div class="form-group">
               <label class="control-label col-md-4">Mp4</label>
               <div class="col-md-8">
                 <input type='file' class="form-control" name='mp4MF' id='mp4MF' 
                            value='' placeholder="파일 선택" style='width: 50%;'>
               </div>
            </div>  
            
            <div class="form-group">
               <label class="control-label col-md-4">검색어</label>
               <div class="col-md-8">
                 <input type='text' name='word' value=''${word } required="required" placeholder=""
                            autofocus="autofocus" class="form-control" style='width: 30%;'>
               </div>
            </div>  
            <br><br>
            <div style='margin-top: 10px; clear: both;'>  
              <button type="submit" class="btn btn-primary">수정</button>
              <button type="button" onclick="history.back();" class="btn btn-primary">취소</button>
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

