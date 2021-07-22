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
 
<DIV class='title_line'>${artistVO.name }앨범  > 등록</DIV>
 
<DIV class='content_body'>
  <FORM name='frm' method='POST' action='./create.do' class="form-horizontal" 
              enctype="multipart/form-data">
    <input type="hidden" name="artistno" value='${param.artistno }'>
    <div class="form-group">
       <label class="control-label col-md-4">앨범 이름</label>
       <div class="col-md-8">
         <input type='text' name='title' value='' required="required" placeholder="앨범 이름"
                    autofocus="autofocus" class="form-control" style='width: 30%;'>
       </div>
    </div>
    
    <div class="form-group">
       <label class="control-label col-md-4">앨범 종류</label>
       <div class="col-md-8">
          <select name='kind' class="form-control" style='width: 30%;'>
            <option value='정규' selected="selected">정규</option>
            <option value='미니'>미니</option>
            <option value='싱글'>싱글</option>
            <option value='OST'>OST</option>
            <option value='기타'>기타</option>
          </select>
       </div>
    </div>  
    
     <div class="form-group">
       <label class="control-label col-md-4">발매일</label>
       <div class="col-md-8">
         <input type='text' name='release' value='' required="required" placeholder="0000.00.00"
                    autofocus="autofocus" class="form-control" style='width: 30%;'>
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
       <label class="control-label col-md-4">기획사</label>
       <div class="col-md-8">
         <input type='text' name='enter' value='' required="required" placeholder=""
                    autofocus="autofocus" class="form-control" style='width: 30%;'>
       </div>
    </div>
    
    <div class="form-group">
       <label class="control-label col-md-2">앨범 소개</label>
       <div class="col-md-10">
         <textarea name='intro' required="required" class="form-control" rows="12" style='width: 100%;'>앨범 소개</textarea>
       </div>
    </div>
    
    <div class="form-group">
       <label class="control-label col-md-2">상세 소개</label>
       <div class="col-md-10">
         <textarea name='detail' required="required" class="form-control" rows="12" style='width: 100%;'>상세 소개</textarea>
       </div>
    </div>
    
    <div class="form-group">
       <label class="control-label col-md-4">이미지</label>
       <div class="col-md-8">
         <input type='file' class="form-control" name='fnameMF' id='fnameMF' 
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
 
 
 