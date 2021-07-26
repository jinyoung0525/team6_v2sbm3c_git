
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
 
<!DOCTYPE html> 
<html lang="ko"> 
<head> 
<meta charset="UTF-8"> 
<meta name="viewport" content="user-scalable=yes, initial-scale=1.0, maximum-scale=3.0, width=device-width" /> 
<title>MusicBoot</title>
 
<link href="../css/style.css" rel="Stylesheet" type="text/css">
 
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
 
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
    
<script type="text/javascript">
 
  
</script>
 
</head> 
 
<body>
<jsp:include page="../menu/top.jsp" />
 
<DIV class='title_line'>공지사항</DIV>

<DIV class='content_body'>
  <ASIDE class="aside_right">
    <A href="./create.do">등록</A>
    <span class='menu_divide' >│</span>
    <A href="javascript:location.reload();">새로고침</A>
  </ASIDE> 

  <DIV class='menu_line'></DIV>
  
  <table class="table table-striped" style='width: 100%;'>
    <colgroup>
      <col style="width: 5%;"></col>
      <col style="width: 50%;"></col>
      <col style="width: 20%;"></col>
      <col style="width: 15%;"></col>
      <col style="width: 10%;"></col>
    </colgroup>
    
    <%-- table 컬럼 --%>
    <thead>
      <tr>
        <th style='text-align: center;'>글번호</th>
        <th style='text-align: center;'>글제목</th>
        <th style='text-align: center;'>등록일</th>
        <th style='text-align: center;'>작성자</th>
        <th style='text-align: center;'>조회수</th>
      </tr>
    
    </thead>
    
    <%-- table 내용 --%>
    <tbody>
      <c:forEach var="noticeVO" items="${list }">
        <c:set var="nnum" value="${noticeVO.nnum }" />
        
        <tr> 
          <td style='vertical-align: middle; text-align: center;'> ${noticeVO.nnum}</td>  
          <td style='vertical-align: middle;'>
            <a href="./read.do?nnum=${nnum}"> ${noticeVO.ntitle}</a> 
          </td> 
          <td style='vertical-align: middle; text-align: center;'>${noticeVO.rdate}</td>
          <td style='vertical-align: middle; text-align: center;'>관리자(추후구현)</td>
          <td style='vertical-align: middle; text-align: center;'>${noticeVO.cnt}</td>
          
        </tr>
      </c:forEach>
      
    </tbody>
  </table>
</DIV>

 
<jsp:include page="../menu/bottom.jsp" />
</body>
 
</html>

  