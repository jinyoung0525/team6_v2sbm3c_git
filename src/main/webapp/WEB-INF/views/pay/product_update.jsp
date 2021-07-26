<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
 
<!DOCTYPE html> 
<html lang="ko"> 
<head> 
<meta charset="UTF-8"> 
<meta name="viewport" content="user-scalable=yes, initial-scale=1.0, maximum-scale=3.0, width=device-width" /> 
<title>Resort world</title>
 
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
 
<DIV class='title_line'>
  [${productVO.product_name }] 정보 수정
</DIV>

<DIV class='content_body'>
  <FORM name='frm' method='POST' action='./product_update.do' class="form-horizontal">
   
    <input type="hidden" name="product_no" value="${productVO.product_no}"> 
    
    <div class="form-group">
       <label class="control-label col-md-2">이용권명</label>
       <div class="col-md-10">
         <input type='text' name='product_name' value='${productVO.product_name}' required="required" autofocus="autofocus"
                    class="form-control" style='width: 100%;'>
       </div>
    </div>
    <div class="form-group">
       <label class="control-label col-md-2">가격</label>
       <div class="col-md-10">
         <input type='number' name='product_price' value='${productVO.product_price }' required="required"
                    min="0" max="10000000" step="100" 
                    class="form-control" style='width: 100%;'>
       </div>
    </div>   
    <div class="form-group">
       <label class="control-label col-md-2">듣기 횟수<br>(음수는 무제한)</label>
       <div class="col-md-10">
         <input type='number' name='product_count' value='${productVO.product_count }' required="required"
                    min="0" max="100" step="1" 
                    class="form-control" style='width: 100%;'>
       </div>
    </div> 
    <div class="form-group">
       <label class="control-label col-md-2">사용기간<br>(일단위로 입력)</label>
       <div class="col-md-10">
         <input type='number' name='product_day' value='${productVO.product_day }' required="required"
                    min="0" max="10000000" 
                    class="form-control" style='width: 100%;'>
       </div>
    </div>                 
    <div class="form-group">
       <label class="control-label col-md-2">이용권 설명</label>
       <div class="col-md-10">
         <textarea name='product_cont' required="required" class="form-control" rows="12" style='width: 100%;'>${productVO.product_cont } </textarea>
       </div>
    </div>
    <div class="form-group">
       <label class="control-label col-md-2">이미지</label>
       <div class="col-md-10">
         <input type='file' class="form-control" name='file1' id='file1' 
                    value='${productVO.file1}' placeholder="파일 선택">
                     현재이미지 : ${productVO.file1}
       </div>
      
    </div>   
    <div class="content_body_bottom">
      <button type="submit" class="btn btn-primary">저장</button>
      <button type="button" onclick="location.href='./read.do?cateno=${param.cateno}&contents=${param.contentsno }'" class="btn btn-primary">취소</button>
    </div>
  
  </FORM>
</DIV>
 
<jsp:include page="../menu/bottom.jsp" flush='false' />
</body>
 
</html>

