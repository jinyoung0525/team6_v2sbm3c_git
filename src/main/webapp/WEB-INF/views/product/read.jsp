<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>

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
<title>MusicBoot</title>
 
<link href="/css/style.css" rel="Stylesheet" type="text/css">
 
<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>

<!-- Bootstrap -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
    
<script type="text/javascript">
function getParameterByName(name) {
  name = name.replace(/[\[]/, "\\[").replace(/[\]]/, "\\]");
  var regex = new RegExp("[\\?&]" + name + "=([^&#]*)"),
  results = regex.exec(location.search);
  return results == null ? "" : decodeURIComponent(results[1].replace(/\+/g, " "));
}
var product_no = getParameterByName('product_no');

function Kpay() {
  <c:choose>
  <c:when test="${sessionScope.id == null}"> <%-- 로그인 안 한 경 우 --%>
             alert("로그인 후 이용 가능합니다.")
  </c:when>
  <c:otherwise>
  var params ="";
  params="product_no=${productno}&product_price=${price}&Pay_count=${count}&Pay_day=${day}"
  $.ajax(
    {
      url: '/product/kpay.do',
      type: 'get',  // get, post
      cache: false, // 응답 결과 임시 저장 취소
      async: true,  // true: 비동기 통신
      dataType: 'json', // 응답 형식: json, html, xml...
      data: params,
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
    
  </c:otherwise>
</c:choose>     

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
  <sec:authorize access="isAuthenticated()">

  <ASIDE class="aside_right">
    <A href="./product_update.do?product_no=${productno}">상품 수정</A>
    <span class='menu_divide' >│</span>
    <A href="javascript:location.reload();">새로고침</A>
    <span class='menu_divide' >│</span>
    <A href="./product/delete.do">상품삭제</A>
  </ASIDE>
  </sec:authorize>
	
  
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
          

<br>
            <button onclick="javascript: Kpay()"><IMG src="/product/images/payment_icon_yellow_small.png" ></button>

          </DIV> 
        
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

