<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<c:set var="songno" value="${musicVO.songno }" />
<c:set var="title" value="${musicVO.title }" />
<c:set var="lyrics" value="${musicVO.lyrics }" />
<c:set var="likey" value="${musicVO.likey }" />

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
    $('#btn_likey').on("click", function() { update_likey_ajax(${songno}); });
  });
  
  function update_likey_ajax(songno) {
    // console.log('-> contentsno:' + contentsno);
    var params = "";
    // params = $('#frm').serialize(); // 직렬화, 폼의 데이터를 키와 값의 구조로 조합
    params = 'songno=' + songno; // 공백이 값으로 있으면 안됨.
    params += '&${ _csrf.parameterName }=${ _csrf.token }';
    $.ajax(
      {
        url: '/music/update_likey_ajax.do',
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
    $('#span_animation').html("<img src='/music/images/ani04.gif' style='width: 8%;'>");
    $('#span_animation').show(); // 숨겨진 태그의 출력
  }
</script>
 
</head> 
 
<body>
<jsp:include page="../menu/top.jsp" flush='false' />
 
<DIV class='title_line'>
  <A href="../artist/list_by_artistno_grid.do" class='title_link'>아티스트 목록</A> >
  <A href="../artist/read.do?artistno=${artistVO.artistno }&now_page=" class='title_link'>${artistVO.name }</A> >
  <A href="./list_by_artistno_grid.do?artistno=${artistVO.artistno }" class='title_link'>앨범 목록</A> >
  <A href="../album/read.do?albumno=${albumno}&now_page=" class='title_link'>${title }</A>
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
     <A href="./delete.do?songno=${songno }">삭제</A>
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
         <DIV style='text-align: center; width: 100%; float: left;'>
          <video src='/music/storage/${musicVO.mp4}' style='width: 70%;' controls="controls"></video><br>
          <DIV><span style="text-align: center; font-size: 2.2em; font-weight: bold;">${title }  <button style="font-size: 0.35em;" type='button' id="btn_likey" class="btn btn-info" >♥(${likey })</button></span>&nbsp;</DIV><br>
          <DIV>${lyrics } </DIV><br>
         </DIV> 
          <br>
          <%-- <DIV style="width: 47%; height: 260px; float: left; margin-right: 10px;">
            <span style="font-size: 1.5em; font-weight: bold;">${title }</span><button type='button' id="btn_likey" class="btn btn-info">♥(${likey })</button><br>
            <span style="font-size: 1.0em;">앨범 종류: ${kind }</span><br>
            <span style="font-size: 1.0em;">발매일: ${release }</span><br>
            <span style="font-size: 1.0em;">장르: ${genre }</span><br>
            <span style="font-size: 1.0em;">기획사: ${enter }</span><br>
            <span style="font-size: 1.8em;"><a href='../music/list_by_albumno_search_paging.do?albumno=${param.albumno }&word=&now_page=1'>수록곡 바로가기</a></span><br>
          </DIV>  --%>
       
      </li>
      
      <li class="li_none">
        <DIV>
          <c:if test="${musicVO.mp3.trim().length() > 0 }">
            MP3 음원 다운로드: <A href='/download?dir=/music/storage&filename=${musicVO.mp3}&downname=${musicVO.mp3}'>${musicVO.mp3}</A> (${musicVO.size3_label})  <br>
          </c:if><br>
          <c:if test="${musicVO.mp4.trim().length() > 0 }">
            뮤직비디오 다운로드: <A href='/download?dir=/music/storage&filename=${musicVO.mp4}&downname=${musicVO.mp4}'>${musicVO.mp4}</A> (${musicVO.size4_label})  
          </c:if>
        </DIV>
      </li>   
    </ul>
  </fieldset>

</DIV>
 
<jsp:include page="../menu/bottom.jsp" flush='false' />
</body>
 
</html>