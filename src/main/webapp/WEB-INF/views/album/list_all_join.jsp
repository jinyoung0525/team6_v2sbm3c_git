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
  function likey_ajax(albumno, status_count) {
    console.log("-> likey_" + status_count + ": " + $('#likey_' + status_count).html());  // A tag body      
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
  
    $('#likey_' + status_count).html("<img src='/album/images/ani04.gif' style='width: 10%;'>");
    // $('#span_animation_' + status_count).css('text-align', 'center');
    // $('#span_animation_' + status_count).html("<img src='/contents/images/ani04.gif' style='width: 10%;'>");
    // $('#span_animation_' + status_count).show(); // 숨겨진 태그의 출력
      
  }  
  
</script>
 
</head> 
 
<body>
<jsp:include page="../menu/top.jsp" />
 
<DIV class='title_line'>
  <A href="./list_all_join.do" class='title_link'>앨범</A>
</DIV>

<DIV class='content_body'>
  <ASIDE class="aside_right">
    <A href="./create_join.do">등록</A>
    <span class='menu_divide' >│</span>
    <A href="javascript:location.reload();">새로고침</A>
    <span class='menu_divide' >│</span>
    <A href="./list_by_artistno_search_paging.do?word=${artistVO.word }&now_page=1">기본 목록형</A>
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
                     onclick="location.href='./list_all_join.do'">검색 취소</button>  
      </c:if>    
    </form>
  </DIV>
  

  <DIV class='menu_line'></DIV>
  
  <div style='width: 100%;'> <%-- 갤러리 Layout 시작 --%>
    <c:forEach var="albumVO" items="${list }" varStatus="status">
      <c:set var="r_artistno" value="${albumVO.r_artistno }" />
      <c:set var="r_name" value="${albumVO.r_name }" />
      <c:set var="albumno" value="${albumVO.albumno }" />
      <c:set var="title" value="${albumVO.title }" />
      <c:set var="kind" value="${albumVO.kind }" />
      <c:set var="release" value="${albumVO.release }" />
      <c:set var="genre" value="${albumVO.genre }" />
      <c:set var="enter" value="${albumVO.enter }" />
      <c:set var="intro" value="${albumVO.intro }" />
      <c:set var="detail" value="${albumVO.detail }" />
      <c:set var="fname" value="${albumVO.fname }" />
      <c:set var="fupname" value="${albumVO.fupname }" />
      <c:set var="fsize" value="${albumVO.fsize }" />
      <c:set var="thumb" value="${albumVO.thumb }" />
      <c:set var="likey" value="${albumVO.likey }" />

      <%-- 하나의 행에 이미지를 4개씩 출력후 행 변경 --%>
      <c:if test="${status.index % 4 == 0 && status.index != 0 }"> 
        <HR class='menu_line'>
      </c:if>
      
      <!-- 하나의 이미지, 24 * 4 = 96% -->
      <DIV style='width: 24%; 
              float: left; 
              margin: 0.5%; padding: 0.5%; background-color: #EEEFFF; text-align: center;'>
        <c:choose>
          <c:when test="${fsize > 0}"> <!-- 파일이 존재하면 -->
            <c:choose> 
              <c:when test="${thumb.endsWith('jpg') || thumb.endsWith('png') || thumb.endsWith('gif')}"> <!-- 이미지 인경우 -->
                <a href="./read.do?albumno=${albumno}&now_page=${param.now_page}">               
                  <IMG src="/album/storage/${thumb }" style='width: 100%; height: 150px;'>
                </a><br>
                ${title} <br>
                ${r_name }<br>
                ${release } | ${kind }<br>
                <A id="likey_${status.count }" href="javascript:likey_ajax(${albumno }, ${status.count })" class="likey_link">♥(${likey })</A>
                
          
              </c:when>
              <c:otherwise> <!-- 이미지가 아닌 일반 파일 -->
                <DIV style='width: 100%; height: 150px; display: table; border: solid 1px #CCCCCC;'>
                  <DIV style='display: table-cell; vertical-align: middle; text-align: center;'> <!-- 수직 가운데 정렬 -->
                    <a href="./read.do?albumno=${albumno}&now_page=${param.now_page}">${fname}</a><br>
                  </DIV>
                </DIV>
                ${title} (${albumVO.likey})              
              </c:otherwise>
            </c:choose>
          </c:when>
          <c:otherwise> <%-- 파일이 없는 경우 기본 이미지 출력 --%>
            <a href="./read.do?albumno=${albumno}">
              <img src='/images/none1.png' style='width: 100%; height: 150px;'>
            </a><br>
            이미지를 등록해주세요.
          </c:otherwise>
        </c:choose>         
      </DIV>  
    </c:forEach>
    <!-- 갤러리 Layout 종료 -->
    <br><br>
  </div>

</DIV>

 
<jsp:include page="../menu/bottom.jsp" />
</body>
 
</html>

