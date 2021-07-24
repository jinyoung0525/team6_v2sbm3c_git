<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
 
<!DOCTYPE html> 
<html lang="ko"> 
<head> 
<meta charset="UTF-8"> 
<meta name="viewport" content="user-scalable=yes, initial-scale=1.0, maximum-scale=3.0, width=device-width" /> 
<title>test</title>
 
<link href="../css/style.css" rel="Stylesheet" type="text/css">
 
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
 
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
    
<script type="text/javascript">

// 그리드형



function Kpay() {
 
  $.ajax(
    {
      url: '/product/kpay.do',
      type: 'get',  // get, post
      cache: false, // 응답 결과 임시 저장 취소
      async: true,  // true: 비동기 통신
      dataType: 'json', // 응답 형식: json, html, xml...
     
      success: function(rdata) { 
        var box = rdata.next_redirect_pc_url;
        var box2 = window.open(box,"new","width=500,height=500,top=100,left=100");


       


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
 
  
<form method="get" action="./kpay.do">
    <button id="api" >카카오페이로 결제하기</button>
</form>

<A href="javascript: Kpay()" title="수정">qqqq</A>
 
 
<h1> kakaoPay api 이용하기 </h1>
${Kpay[0]}

<input type="text" id="pInput" value=""> 



 
</body>
</html>
