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
  function likey_ajax(songno, status_count) {
    console.log("-> likey_" + status_count + ": " + $('#likey_' + status_count).html());  // A tag body      
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
          var str = '';
          if (rdata.cnt == 1) {
            // $('#span_animation_' + status_count).hide();   // SPAN 태그에 animation 출력
            $('#likey_' + status_count).html('♥('+rdata.likey+')');     // A 태그에 animation 출력
          } else {
            // $('#span_animation_' + status_count).html("X");
            $('#likey_' + status_count).html('♥(X)');
          }
        },
        // Ajax 통신 에러, 응답 코드가 200이 아닌경우, dataType이 다른경우 
        error: function(request, status, error) { // callback 함수
          console.log(error);
        }
      }
    );  //  $.ajax END
  
    $('#likey_' + status_count).html("<img src='/music/images/ani04.gif' style='width: 60%;'>");
    // $('#span_animation_' + status_count).css('text-align', 'center');
    // $('#span_animation_' + status_count).html("<img src='/contents/images/ani04.gif' style='width: 10%;'>");
    // $('#span_animation_' + status_count).show(); // 숨겨진 태그의 출력
      
  }  
  
</script>
 
</head> 
 
<body>
<jsp:include page="../menu/top.jsp" />
 
<DIV class='title_line'>
  <A href="../album/list_by_albumno_search_paging.do?word=${musicVO.word }&now_page=1" class='title_link'>곡</A>
</DIV>

<DIV class='content_body'>
  <ASIDE class="aside_right">
    <A href="./create_join.do">등록</A>
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
      <col style="width: 50%;"></col>
      <col style="width: 10%;"></col>
      <col style="width: 20%;"></col>
      <col style="width: 10%;"></col>
    </colgroup>
    <%-- table 컬럼 --%>

   <thead>  
    <TR>
      <TH class="th_bs"></TH>
      <TH class="th_bs_left">곡</TH>
      <TH class="th_bs">듣기</TH>
      <TH class="th_bs">앨범</TH>
      <TH class="th_bs">좋아요</TH>
    </TR>
   </thead>
    
    <%-- table 내용 --%>
    <tbody>
      <c:forEach var="musicVO" items="${list }" varStatus="status">
        <c:set var="r_albumno" value="${musicVO.r_albumno }" />
        <c:set var="r_title" value="${musicVO.r_title }" />
        <c:set var="songno" value="${musicVO.songno }" />
        <c:set var="mp3" value="${musicVO.mp3 }" />
        <c:set var="title" value="${musicVO.title }" />
        <c:set var="likey" value="${musicVO.likey }" />
        <c:set var="thumb" value="${albumVO.thumb }" />
        <c:set var="fsize" value="${albumVO.fsize }" />
        
        <tr> 
          <td style='vertical-align: middle; text-align: center;'>
           <c:choose>
            <c:when test="${fsize > 0}"> 
            <c:choose>
              <c:when test="${thumb.endsWith('jpg') || thumb.endsWith('png') || thumb.endsWith('gif')}">
                <%-- /static/contents/storage/ --%>
                <a href="./read.do?songno=${songno}"><IMG src="/album/storage/${thumb }" style="width: 120px; height: 80px;"></a> 
              </c:when>
              <c:otherwise> <!-- 이미지가 아닌 일반 파일 -->
                ${albumVO.fname}
              </c:otherwise>
             </c:choose>
            </c:when>
           </c:choose> 
          </td>  
          <td style='vertical-align: middle;'>
            <a href="./read.do?songno=${songno}"><strong>${title}</strong> </a> 
          </td> 
          <%-- <td style='vertical-align: middle; text-align: center;'>
            등록일 : ${newsVO.rdate.substring(0, 10) }
          </td> --%>
          <td style='vertical-align: middle; text-align: center;'><audio src="/music/storage/${mp3}" controls=""   loop=""  preload="auto"></audio> </td>
          <td style='vertical-align: middle; text-align: center;'><a href="../album/read.do?albumno=${albumno }">${r_title }</a></td>
          <td style='vertical-align: middle; text-align: center;'>
            <A id="likey_${status.count }" href="javascript:likey_ajax(${songno }, ${status.count })" class="likey_link">♥(${likey })</A>
          </td>
        </tr>
      </c:forEach>
      
    </tbody>
  </table>
</DIV>

 
<jsp:include page="../menu/bottom.jsp" />
</body>
 
</html>

