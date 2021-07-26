<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
 
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
 
<DIV class='title_line'>전체 곡</DIV>

<DIV class='content_body'>
  <table class="table table-striped" style='width: 100%;'>
    <colgroup>
      <col style="width: 10%;"></col>
      <col style="width: 60%;"></col>
      <col style="width: 20%;"></col>
      <col style="width: 10%;"></col>
    </colgroup>
   
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

