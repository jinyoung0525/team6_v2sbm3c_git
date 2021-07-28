<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
 
<!DOCTYPE html> 
<html lang="ko"> 
<head> 
<meta charset="UTF-8"> 
<meta name="viewport" content="user-scalable=yes, initial-scale=1.0, maximum-scale=3.0, width=device-width" /> 
<title>team6</title>
 
<link href="/css/style.css" rel="Stylesheet" type="text/css">

<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>

<!-- Bootstrap -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">

    
<script type="text/javascript">
  $(function(){
 
  });
</script>
 
</head> 
 
<body>
<jsp:include page="../menu/top.jsp" flush='false' />
 
<DIV class='title_line'>아티스트  > 등록</DIV>
 
<DIV class='content_body'>
  <FORM name='frm' method='POST' action='./create.do' class="form-horizontal" 
              enctype="multipart/form-data">
    <%-- <input type='hidden' name='artistno' value='${artistVO.artistno }'> --%>
    <input type="hidden" name="${ _csrf.parameterName }" value="${ _csrf.token }">
    <div class="form-group">
       <label class="control-label col-md-4">아티스트 이름</label>
       <div class="col-md-8">
         <input type='text' name='name' value='' required="required" placeholder="아티스트 이름"
                    autofocus="autofocus" class="form-control" style='width: 30%;'>
       </div>
    </div>
    <div class="form-group">
       <label class="control-label col-md-4">출력 순서</label>
       <div class="col-md-8">
         <input type='number' name='seqno' value='1' required="required" 
                   placeholder="출력 순서" min="1" max="1000" step="1" 
                   style='width: 30%;' class="form-control" >
       </div>
    </div>  
    <div class="form-group">
       <label class="control-label col-md-4">데뷔 년도</label>
       <div class="col-md-8">
         <input type='number' name='debut'  value='1970' required="required" 
                   placeholder="데뷔 년도" min="1900" max="2021" step="1" 
                   style='width: 30%;' class="form-control" >
       </div>
    </div>  
    <div class="form-group">
       <label class="control-label col-md-4">장르</label>
       <div class="col-md-8">
          <select name='genre' class="form-control" style='width: 30%;'>
            <option value='랩/힙합' selected="selected">랩/힙합</option>
            <option value='발라드' >발라드</option>
            <option value='댄스/팝' >댄스/팝</option>
            <option value='포크/어쿠스틱' >포크/어쿠스틱</option>
            <option value='아이돌' >아이돌</option>
            <option value='알앤비/소울' >알앤비/소울</option>
            <option value='일렉트로닉' >일렉트로닉</option>
            <option value='락/메탈'>락/메탈</option>
            <option value='재즈' >재즈</option>
            <option value='인디' >인디</option>
            <option value='성인가요' >성인가요</option>
            <option value='팝' >팝</option>
          </select>
       </div>
    </div>  
    
    <div class="form-group">
       <label class="control-label col-md-4">국가</label>
       <div class="col-md-8">
         <input type='text' name='nation' value='' required="required" placeholder="국가 명"
                    autofocus="autofocus" class="form-control" style='width: 30%;'>
       </div>
    </div> 
    
    <div class="form-group">
       <label class="control-label col-md-4">활동 유형</label>
       <div class="col-md-8">
          <select name='type' class="form-control" style='width: 30%;'>
            <option value='그룹' selected="selected">그룹</option>
            <option value='솔로' selected="selected">솔로</option>
          </select>
       </div>
    </div>  
    
    <div class="form-group">
       <label class="control-label col-md-4">이미지</label>
       <div class="col-md-8">
         <input type='file' class="form-control" name='file1MF' id='file1MF' 
                    value='' placeholder="파일 선택" style='width: 50%;'>
       </div>
    </div>   
    
    <div class="form-group">
       <label class="control-label col-md-4">검색어</label>
       <div class="col-md-8">
         <input type='text' name='word' value='' required="required" placeholder=""
                    autofocus="autofocus" class="form-control" style='width: 30%;'>
       </div>
    </div> 
  
    <div class="content_body_bottom" style="padding-right: 20%;">
      <button type="submit" class="btn">등록</button>
      <button type="button" onclick="location.href='./list.do'" class="btn">목록</button>
    </div>
  
  </FORM>
  
</DIV>

 
<jsp:include page="../menu/bottom.jsp" flush='false' />
</body>
 
</html>
 
 
 