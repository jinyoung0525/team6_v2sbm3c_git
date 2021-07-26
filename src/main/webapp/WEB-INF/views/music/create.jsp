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
 
<DIV class='title_line'>${albumVO.title }  > 곡 등록</DIV>
 
<DIV class='content_body'>
  <FORM name='frm' method='POST' action='./create.do' class="form-horizontal" 
              enctype="multipart/form-data">
    <input type="hidden" name="${ _csrf.parameterName }" value="${ _csrf.token }">
    <input type="hidden" name="artistno" value='${albumVO.artistno }'>
    <input type="hidden" name="albumno" value='${param.albumno }'>
    <input type="hidden" name="memberno" value=1>
    
    <div class="form-group">
       <label class="control-label col-md-4"> 곡 이름</label>
       <div class="col-md-8">
         <input type='text' name='title' value='' required="required" placeholder="곡 이름"
                    autofocus="autofocus" class="form-control" style='width: 30%;'>
       </div>
    </div>
   
    <div class="form-group">
       <label class="control-label col-md-2">가사</label>
       <div class="col-md-10">
         <textarea name='lyrics' required="required" class="form-control" rows="12" style='width: 100%;'>가사</textarea>
       </div>
    </div>
    
    <div class="form-group">
       <label class="control-label col-md-4">음악 파일</label>
       <div class="col-md-8">
         <input type='file' class="form-control" name='mp3MF' id='mp3MF' 
                    value='' placeholder="파일 선택" style='width: 50%;'>
       </div>
    </div>  
    
    <div class="form-group">
       <label class="control-label col-md-4">뮤비 파일</label>
       <div class="col-md-8">
         <input type='file' class="form-control" name='mp4MF' id='mp4MF' 
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
 
 
 