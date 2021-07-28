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
  <A href="../album/update_album.do" class='title_link'>수정</A>
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
          <%-- <span style='font-size: 1.5em;'>${name}</span> --%>
          <br>
          <FORM name='frm' method='POST' action='./update_album.do' 
              enctype="multipart/form-data">
            <input type="hidden" name="artistno" value='${artistVO.artistno }'>
            <input type="hidden" name="albumno" value='${albumno }'>
            <input type="hidden" name="${ _csrf.parameterName }" value="${ _csrf.token }">
            <div class="form-group">
               <label class="control-label col-md-4">앨범 이름</label>
               <div class="col-md-8">
                 <input type='text' name='title' value='${title }' required="required" placeholder="앨범 이름"
                            autofocus="autofocus" class="form-control" style='width: 30%;'>
               </div>
            </div>
            
            <div class="form-group">
               <label class="control-label col-md-4">앨범 종류</label>
               <div class="col-md-8">
                  <select name='kind' class="form-control" style='width: 30%;'>
                    <option value='정규' selected="selected">정규</option>
                    <option value='미니'>미니</option>
                    <option value='싱글'>싱글</option>
                    <option value='OST'>OST</option>
                    <option value='기타'>기타</option>
                  </select>
               </div>
            </div>  
            
             <div class="form-group">
               <label class="control-label col-md-4">발매일</label>
               <div class="col-md-8">
                 <input type='text' name='release' value=''${release } required="required" placeholder="0000.00.00"
                            autofocus="autofocus" class="form-control" style='width: 30%;'>
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
               <label class="control-label col-md-4">기획사</label>
               <div class="col-md-8">
                 <input type='text' name='enter' value='${enter }' required="required" placeholder="기획사"
                            autofocus="autofocus" class="form-control" style='width: 30%;'>
               </div>
            </div>
            
            <div class="form-group">
               <label class="control-label col-md-2">앨범 소개</label>
               <div class="col-md-10">
                 <textarea name='intro' required="required" class="form-control" rows="12" style='width: 100%;'>${intro }</textarea>
               </div>
            </div>
            
            <div class="form-group">
               <label class="control-label col-md-2">상세 소개</label>
               <div class="col-md-10">
                 <textarea name='detail' required="required" class="form-control" rows="12" style='width: 100%;'>${detail }</textarea>
               </div>
            </div>
            
            <div class="form-group">
               <label class="control-label col-md-4">이미지</label>
               <div class="col-md-8">
                 <input type='file' class="form-control" name='fnameMF' id='fnameMF' 
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

