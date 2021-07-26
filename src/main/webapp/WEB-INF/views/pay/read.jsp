<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<c:set var="productno" value="${productVO.product_no }" />
<c:set var="name" value="${productVO.product_name }" />
<c:set var="count" value="${productVO.product_count }" />
<c:set var="day" value="${productVO.product_day }" />
<c:set var="content" value="${productVO.product_cont }" />
<c:set var="price" value="${productVO.product_price}" />
 
<!DOCTYPE html> 
<html lang="ko"> 
<head> 
<meta charset="UTF-8"> 
<meta name="viewport" content="user-scalable=yes, initial-scale=1.0, maximum-scale=3.0, width=device-width" /> 
<title>팀6</title>
 
<link href="/css/style.css" rel="Stylesheet" type="text/css">
 
<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>

<!-- Bootstrap -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
    
<script type="text/javascript">
function Kpay() {
  $.ajax(
    {
      url: '/product/kpay.do',
      type: 'get',  // get, post
      cache: false, // 응답 결과 임시 저장 취소
      async: true,  // true: 비동기 통신
      dataType: 'json', // 응답 형식: json, html, xml...
     
      success: function(rdata) {
        var tid = rdata.tid; 
        var pcurl = rdata.next_redirect_pc_url;
        var mobileurl=rdata.next_redirect_mobile_url;
        var box = window.open(pcurl,"new","width=500,height=500,top=100,left=100");
      },
      // Ajax 통신 에러, 응답 코드가 200이 아닌경우, dataType이 다른경우 
      error: function(request, status, error) { // callback 함수
        console.log(error);
      }
    });  //  $.ajax END
}



</script>

</head> 
 
<body>
<jsp:include page="../menu/top.jsp" flush='false' />
 
<DIV class='title_line'>
  <A href="../product/list.do" class='title_link'>이용권</A> > 
  <A href="../product/read.do?productno=${productno }" class='title_link'>${name }</A>
</DIV>

<DIV class='content_body'>
  <ASIDE class="aside_right">
    <A href="./create.do?">상품등록</A>
    <span class='menu_divide' >│</span>
    <A href="javascript:location.reload();">새로고침</A>
    <span class='menu_divide' >│</span>
    <A href="./product_update.do?product_no=${productno}">수정</A>
  </ASIDE>
  
	<DIV style="text-align: right; clear: both;">  
    <form name='frm' id='frm' method='get' action='./list.do?v=g'>
      <c:choose>
        <c:when test="${param.word != '' }"> <%-- 검색하는 경우 --%>
          <input type='text' name='word' id='word' value='${param.word}' style='width: 20%;'>
          <input type="hidden" name='v' id='v' value='${param.v}' style='width: 20%;'>
        </c:when>
        <c:otherwise> <%-- 검색하지 않는 경우 --%>
          <input type='text' name='word' id='word' value='' style='width: 20%;'>
        </c:otherwise>
      </c:choose>
      <button type='submit'>검색</button>
      <c:if test="${param.word.length() > 0 }">
        <button type='button' 
                     onclick="location.href='./list.do?word='">검색 취소</button>  
      </c:if>    
    </form>
  </DIV>
  
  <DIV class='menu_line'></DIV>

  <fieldset class="fieldset_basic">
    <ul>
      <li class="li_none">
          <DIV style="width: 50%; float: left; margin-right: 10px;">
            <IMG src="/product/images/${productVO.file1 }" style="width: 100%;">
          </DIV>
          
          <DIV style="width: 47%; height: 260px; float: left; margin-right: 10px;">
            <span style="font-size: 1.5em; font-weight: bold;">${name}<br><br></span>
            <span style="font-size: 1.3em;">${content}<br><br></span>
            <span style="font-size: 1.2em; font-weight: bold;">
            <fmt:formatNumber value="${price}" pattern="#,###" /> 원<br></span>
          
            <form>
            <button type='button' onclick="javascript: Kpay()" class="btn btn-info">구매 하기</button>
            </form>
          </DIV> 
         
          
        <DIV>${productcont }</DIV>
      </li>
      <li class="li_none">
        <DIV style='text-decoration: none;'>
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

