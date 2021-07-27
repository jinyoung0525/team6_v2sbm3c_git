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
 
<!DOCTYPE html> 
<html lang="ko"> 
<head> 
<meta charset="UTF-8"> 
<meta name="viewport" content="user-scalable=yes, initial-scale=1.0, maximum-scale=3.0, width=device-width" /> 
<title>Team 6</title>
 
<link href="/css/style.css" rel="Stylesheet" type="text/css">
 
<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>

<!-- Bootstrap -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
    
<script type="text/javascript">
$(function(){
  $('#btn_likey').on("click", function() { update_likey_ajax(${albumno}); });
});

function update_likey_ajax(albumno) {
  // console.log('-> contentsno:' + contentsno);
  var params = "";
  // params = $('#frm').serialize(); // 직렬화, 폼의 데이터를 키와 값의 구조로 조합
  params = 'albumno=' + albumno; // 공백이 값으로 있으면 안됨.
  params += '&${ _csrf.parameterName }=${ _csrf.token }';
  $.ajax(
    {
      url: '/album/update_likey_ajax.do',
      type: 'post',  // get, post
      cache: false, // 응답 결과 임시 저장 취소
      async: true,  // true: 비동기 통신
      dataType: 'json', // 응답 형식: json, html, xml...
      data: params,      // 데이터
      success: function(rdata) { // 응답이 온경우
        // console.log('-> rdata: '+ rdata);
        var str = '';
        if (rdata.cnt == 1) {
          // console.log('-> btn_recom: ' + $('#btn_recom').val());  // X
          // console.log('-> btn_recom: ' + $('#btn_recom').html());
          $('#btn_likey').html('♥('+rdata.likey+')');
          $('#span_animation').hide();
        } else {
          $('#span_animation').html("지금은 추천을 할 수 없습니다.");
        }
      },
      // Ajax 통신 에러, 응답 코드가 200이 아닌경우, dataType이 다른경우 
      error: function(request, status, error) { // callback 함수
        console.log(error);
      }
    }
  );  //  $.ajax END

  // $('#span_animation').css('text-align', 'center');
  $('#span_animation').html("<img src='/album/images/ani04.gif' style='width: 8%;'>");
  $('#span_animation').show(); // 숨겨진 태그의 출력
}
</script>
 
</head> 
 
<body>
<jsp:include page="../menu/top.jsp" flush='false' />
 
<DIV class='title_line'>
  <A href="../artist/list_by_artistno_grid.do" class='title_link'>아티스트</A> >
  <A href="../artist/read.do?artistno=${artistVO.artistno }&now_page=" class='title_link'>${artistVO.name }</A> >
  <A href="./list_by_artistno_grid.do?artistno=${artistVO.artistno }" class='title_link'>앨범</A> >
  <A href="../album/read.do?albumno=${albumno}&now_page=" class='title_link'>${title }</A>
</DIV>

<DIV class='content_body'>
  <ASIDE class="aside_right">
    <A href="./create.do">등록</A>
    <span class='menu_divide' >│</span>
    <A href="javascript:location.reload();">새로고침</A>
    <span class='menu_divide' >│</span>
    <A href="./list_by_artistno_grid.do?artistno=${artistVO.artistno }">앨범 목록</A>
    <span class='menu_divide' >│</span>
    <A href="./update_album.do?artistno=${artistVO.artistno }&albumno=${albumno}">앨범 수정</A>
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
        <c:set var="fupname" value="${albumVO.fupname.toLowerCase() }" />
        <c:if test="${fupname.endsWith('jpg') || fupname.endsWith('png') || fupname.endsWith('gif')}">
          <DIV style="width: 50%; float: left; margin-right: 10px;">
            <IMG src="/album/storage/${albumVO.fupname }" style="width: 100%;">
          </DIV>
          <DIV style="width: 47%; height: 260px; float: left; margin-right: 10px;">
            <span style="font-size: 1.5em; font-weight: bold;">${title }</span><button type='button' id="btn_likey" class="btn btn-info">♥(${likey })</button><br>
            <span style="font-size: 1.0em;">앨범 종류: ${kind }</span><br>
            <span style="font-size: 1.0em;">발매일: ${release }</span><br>
            <span style="font-size: 1.0em;">장르: ${genre }</span><br>
            <span style="font-size: 1.0em;">기획사: ${enter }</span><br><br>
            <span style="font-size: 2.0em; font-weight: bold"><a href='../music/list_by_albumno_search_paging.do?albumno=${param.albumno }&word=&now_page=1'  style='font-size:1.0em;'>『수록곡 바로가기』</a></span><br>
          </DIV> 
        </c:if> 
        <DIV><strong>${intro }</strong></DIV><br>
        <DIV>${detail }</DIV>
      </li>
      <li class="li_none">
        <DIV style='text-decoration: none;'>
          검색어(키워드): ${albumVO.word }
        </DIV>
      </li>
      <li class="li_none">
        <DIV>
          <c:if test="${albumVO.fname.trim().length() > 0 }">
            첨부 파일: <A href='/download?dir=/album/storage&filename=${albumVO.fupname}&downname=${albumVO.fname}'>${albumVO.fname}</A> (${albumVO.fsize_label})  
          </c:if>
        </DIV>
      </li>   
    </ul>
  </fieldset>

</DIV>
 
<jsp:include page="../menu/bottom.jsp" flush='false' />
</body>
 
</html>