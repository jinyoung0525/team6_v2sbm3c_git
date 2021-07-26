<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<c:set var="bnum" value="${boardVO.bnum }"/>
<c:set var="btitle" value="${boardVO.btitle }" />
<c:set var="bcontent" value="${boardVO.bcontent }" />
 
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
 
<DIV class='title_line'><A href="./board/list.do">게시판</A></DIV>

<DIV class='content_body'>
  <ASIDE class="aside_right">
    <A href="./create.do">등록</A>
    <span class='menu_divide' >│</span>
    <A href="./update.do?bnum=${bnum }">수정</A>
    <span class='menu_divide' >│</span>
    <A href="./delete.do?bnum=${bnum }">삭제</A>
    <span class='menu_divide' >│</span>
    <A href="./list.do">목록</A>
    <span class='menu_divide' >│</span>
    <A href="javascript:location.reload();">새로고침</A>
  </ASIDE> 
  
  <DIV class='menu_line'></DIV>

  <fieldset class="fieldset_basic">
    <ul>
      <li class="li_none">
      <c:set var="imagesaved" value="${boardVO.imagesaved.toLowerCase() }" />
        <c:if test="${imagesaved.endsWith('jpg') || imagesaved.endsWith('png') || imagesaved.endsWith('gif')}">
        
          <DIV style="width: 47%; height: 260px; float: left; margin-right: 10px;">
            <span style="font-size: 1.5em; font-weight: bold;">${btitle }</span><br>
            
            <DIV style="width: 50%; float: left; margin-right: 10px;">
              <IMG src="/board/storage/${boardVO.imagesaved }" style="width: 100%;">
            </DIV>
          </DIV> 
        </c:if> 
        <DIV style='text-align: left; width: 47%; float: left;'>
          <span style='font-size: 1.5em;'>${btitle}</span>
          <br>
          <FORM name='frm' method='POST' action='./delete.do'>
              <input type='hidden' name='bnum' value='${param.bnum}'>
              <input type="hidden" name="${ _csrf.parameterName }" value="${ _csrf.token }">
              <DIV id='panel1' style="width: 40%; text-align: center; margin: 10px auto;"></DIV>
                    
              <div class="form-group">   
                <div class="col-md-12" style='text-align: center; margin: 10px auto;'>
                  게시글 삭제 : ${boardVO.btitle }<br><br>
                  삭제하시겠습니까? 삭제하시면 복구 할 수 없습니다.<br><br>
                 
                  <button type = "submit" class="btn btn-info">삭제 진행</button>
                  <button type = "button" onclick = "history.back()" class="btn btn-info">취소</button>
                </div>
              </div>   
          </FORM>
        </DIV>
        
      </li>
    </ul>
  </fieldset>

</DIV>
 
<jsp:include page="../menu/bottom.jsp" flush='false' />
</body>
 
</html>