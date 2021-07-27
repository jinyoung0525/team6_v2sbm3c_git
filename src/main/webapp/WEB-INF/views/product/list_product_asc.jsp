<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>
 
<!DOCTYPE html> 
<html lang="ko"> 
<head> 
<meta charset="UTF-8"> 
<meta name="viewport" content="user-scalable=yes, initial-scale=1.0, maximum-scale=3.0, width=device-width" /> 
<title>MusicBoot - 이용권</title>
 
<link href="../css/style.css" rel="Stylesheet" type="text/css">
 
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
 
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
    
<script type="text/javascript">
/*
// 그리드형
function read_update_ajax(categrpno) {
  $('#panel_create').css("display","none"); // hide, 태그를 숨김 
  $('#panel_delete').css("display","none"); // hide, 태그를 숨김
  $('#panel_update').css("display",""); // show, 숨겨진 태그 출력 
  
  // console.log('-> categrpno:' + categrpno);
  var params = "";
  // params = $('#frm').serialize(); // 직렬화, 폼의 데이터를 키와 값의 구조로 조합
  params = 'categrpno=' + categrpno; // 공백이 값으로 있으면 안됨.
  $.ajax(
    {
      url: '/categrp/read_ajax.do',
      type: 'get',  // get, post
      cache: false, // 응답 결과 임시 저장 취소
      async: true,  // true: 비동기 통신
      dataType: 'json', // 응답 형식: json, html, xml...
      data: params,      // 데이터
      success: function(rdata) { // 응답이 온경우, Spring에서 하나의 객체를 전달한 경우 통문자열
        // {"categrpno":1,"visible":"Y","seqno":1,"rdate":"2021-04-08 17:01:28","name":"문화"}
        var categrpno = rdata.categrpno;
        var name = rdata.name;
        var seqno = rdata.seqno;
        var visible = rdata.visible;
        var rdate = rdata.rdate;

        var frm_update = $('#frm_update');
        $('#categrpno', frm_update).val(categrpno);
        $('#name', frm_update).val(name);
        $('#seqno', frm_update).val(seqno);
        $('#visible', frm_update).val(visible);
        $('#rdate', frm_update).val(rdate);
        
        // console.log('-> btn_recom: ' + $('#btn_recom').val());  // X
        // console.log('-> btn_recom: ' + $('#btn_recom').html());
        // $('#btn_recom').html('♥('+rdata.recom+')');
        // $('#span_animation').hide();
      },
      // Ajax 통신 에러, 응답 코드가 200이 아닌경우, dataType이 다른경우 
      error: function(request, status, error) { // callback 함수
        console.log(error);
      }
    }
  );  //  $.ajax END

  // $('#span_animation').css('text-align', 'center');
  // $('#span_animation').html("<img src='/contents/images/ani04.gif' style='width: 8%;'>");
  // $('#span_animation').show(); // 숨겨진 태그의 출력
} 
  */
</script>
 
</head> 
  
<body>
<jsp:include page="../menu/top.jsp" />



<tbody>
      <c:forEach var="payVO" items="${payVO}">
        <c:set var="payno" value="${payVO.pay_no }" />
        <c:set var="name2" value="${payVO.pay_name }" />
        <c:set var="count2" value="${payVO.pay_count }" />
        <c:set var="day2" value="${payVO.pay_day}" />
        
      </c:forEach>
      
    </tbody>
 
<DIV class='title_line'>
  <A href="../product/list.do" class='title_link'>이용권</A>
  
  
  <span style="padding-left : 60%">
  현재 사용중인 이용권 : 
  <c:choose>
    <c:when test="${payno > 0}">
        ${name2}  |  
        <c:choose>
        <c:when test="${count2<0}">
            무제한  |
        </c:when>
        <c:otherwise>
            ${count2 }  |
        </c:otherwise>
        </c:choose>
         ${day2}일 남음
     </c:when>
     <c:otherwise>
     이용권 없음
     </c:otherwise>
    </c:choose>
  </span>

<DIV class='content_body'>
<sec:authorize access="isAuthenticated()">
  <ASIDE class="aside_right">
    <A href="./create.do?">이용권 등록</A>
    <span class='menu_divide' >│</span>
    <A href="javascript:location.reload();">새로고침</A>
  </ASIDE> 
 </sec:authorize>
  <DIV class='menu_line'></DIV>
  
  <table class="table table-striped" style='width: 100%;'>
    <colgroup>
      <col style="width: 15%;"></col>
      <col style="width: 15%;"></col>
      <col style="width: 50%;"></col>
      <col style="width: 20%;"></col>
    </colgroup>

    <%-- table 내용 --%>
    <tbody>
      <c:forEach var="productVO" items="${list }">
        <c:set var="productno" value="${productVO.product_no }" />
        <c:set var="name" value="${productVO.product_name }" />
        <c:set var="count" value="${productVO.product_count }" />
        <c:set var="day" value="${productVO.product_day }" />
        <c:set var="content" value="${productVO.product_cont }" />
        <c:set var="price" value="${productVO.product_price}" />
        <c:set var="file1" value="${productVO.file1}" />
        <tr> 
          <td style='vertical-align: middle; text-align: center;'>
            <%-- /static/product/storage/ --%>
                <a href="./read.do?product_no=${productno}"><IMG src="/product/images/${file1}" style="width: 120px; height: 80px;"></a> 
          </td>  
          <td style='vertical-align: middle;'>
            <a href="./read.do?product_no=${productno}"><strong>${name}</strong></a> 
          </td> 
          <td style='vertical-align: middle;'>
            <a href="./read.do?product_no=${productno}">${content}</a> 
          </td> 
          <td style='vertical-align: middle; text-align: center;'>

            <strong><fmt:formatNumber value="${price}" pattern="#,###" />원</strong><br>
    
          </td>
        </tr>
      </c:forEach>
      
    </tbody>
  </table>
  <DIV class='bottom_menu'>${paging }</DIV>
</DIV>

 
<jsp:include page="../menu/bottom.jsp" />
</body>
 
</html>

