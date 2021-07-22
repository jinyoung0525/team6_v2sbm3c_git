<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<c:set var="artistno" value="${artistVO.artistno }" />
<c:set var="name" value="${artistVO.name }" />
<c:set var="genre" value="${artistVO.genre }" />
<c:set var="nation" value="${artistVO.nation }" />
<c:set var="debut" value="${artistVO.debut }" />
<c:set var="type" value="${artistVO.type }" />
<c:set var="fan" value="${artistVO.fan }" />
<c:set var="fname" value="${artistVO.fname }" />
<c:set var="fupname" value="${artistVO.fupname }" />
<c:set var="thumb" value="${artistVO.thumb }" />
 
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
  <A href="../artist/read.do?artistno=${artistno }&now_page=" class='title_link'>${name }</A> >
  <A href="../artist/update_artist.do" class='title_link'>수정</A>
</DIV>

<DIV class='content_body'>
  <ASIDE class="aside_right">
    <A href="./create.do">등록</A>
    <span class='menu_divide' >│</span>
    <A href="javascript:location.reload();">새로고침</A>
    <%-- <span class='menu_divide' >│</span>
    <A href="./list_by_newsno_search_paging.do?word=&now_page=${param.now_page}">기본 목록형</A>  --%>   
    <span class='menu_divide' >│</span>
    <A href="./list_by_artistno_grid.do">목록</A>
    <span class='menu_divide' >│</span>
    <A href="./update_artist.do?artistno=${artistno }&now_page=${param.now_page }">수정</A>
    <%-- <span class='menu_divide' >│</span>
    <A href="./update_file.do?newsno=${newsno }&now_page=${param.now_page }">파일 수정</A> --%>
     <span class='menu_divide' >│</span>
    <A href="./delete.do?artistno=${artistno }&now_page=${param.now_page }">삭제</A>
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
                     onclick="location.href='./list_by_artistno_search_paging.do?word=&now_page=1'">검색 취소</button>  
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
          <%-- <span style='font-size: 1.5em;'>${name}</span> --%>
          <br>
          <FORM name='frm' method='POST' action='./update_artist.do' 
              enctype="multipart/form-data">
            <input type="hidden" name="artistno" value="${artistno }">
            <input type='hidden' name='now_page' value='${param.now_page }'>
            
            <div class="form-group">
               <label class="control-label col-md-4">아티스트 이름</label>
               <div class="col-md-8">
                 <input type='text' name='name' value='${name }' required="required" placeholder="아티스트 이름"
                            autofocus="autofocus" class="form-control" style='width: 30%;'>
               </div>
            </div>
            <div class="form-group">
               <label class="control-label col-md-4">출력 순서</label>
               <div class="col-md-8">
                 <input type='number' name='seqno' value='${artistVO.seqno }' required="required" 
                           placeholder="출력 순서" min="1" max="1000" step="1" 
                           style='width: 30%;' class="form-control" >
               </div>
            </div>  
            <div class="form-group">
               <label class="control-label col-md-4">데뷔 년도</label>
               <div class="col-md-8">
                 <input type='number' name='debut'  value='${debut }' required="required" 
                           placeholder="데뷔 년도" min="1900" max="2021" step="1" 
                           style='width: 30%;' class="form-control" >
               </div>
            </div>  
            <div class="form-group">
               <label class="control-label col-md-4">장르</label>
               <div class="col-md-8">
                  <select name='genre' class="form-control" style='width: 30%;'>
                    <option value='랩/힙합' selected="selected">랩/힙합</option>
                    <option value='발라드' >발라드</option>
                    <option value='댄스/팝' >댄스/팝</option>
                    <option value='포크/어쿠스틱' >포크/어쿠스틱</option>
                    <option value='아이돌' >아이돌</option>
                    <option value='알앤비/소울' >알앤비/소울</option>
                    <option value='일렉트로닉' >일렉트로닉</option>
                    <option value='락/메탈'>락/메탈</option>
                    <option value='재즈' >재즈</option>
                    <option value='인디' >인디</option>
                    <option value='성인가요' >성인가요</option>
                    <option value='팝' >팝</option>
                  </select>
               </div>
            </div>  
            
            <div class="form-group">
               <label class="control-label col-md-4">국가</label>
               <div class="col-md-8">
                 <input type='text' name='nation' value='${nation }' required="required" placeholder="국가"
                            autofocus="autofocus" class="form-control" style='width: 30%;'>
               </div>
            </div> 
            
            <div class="form-group">
               <label class="control-label col-md-4">활동 유형</label>
               <div class="col-md-8">
                  <select name='type' class="form-control" style='width: 30%;'>
                    <option value='그룹' selected="selected">그룹</option>
                    <option value='솔로' selected="selected">솔로</option>
                  </select>
               </div>
            </div>  
            
            <div class="form-group">
               <label class="control-label col-md-4">이미지</label>
               <div class="col-md-8">
                 <input type='file' class="form-control" name='file1MF' id='file1MF' 
                            value='' placeholder="파일 선택" style='width: 50%;'>
               </div>
            </div>   
            
            <div class="form-group">
               <label class="control-label col-md-4">검색어</label>
               <div class="col-md-8">
                 <input type='text' name='word' value='${artistVO.word }' required="required" placeholder="검색어"
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

