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
  <A href="../categrp/list.do" class='title_link'>상품등록</A>
</DIV>


<DIV class='content_body'>

  <FORM name='frm' method='POST' action='./create.do' class="form-horizontal"
              enctype="multipart/form-data">
  <input type="hidden" name="${ _csrf.parameterName }" value="${ _csrf.token }">    
    <input type="hidden" name="adminno" value="1"> <%-- 관리자 개발후 변경 필요 --%>
    
    <div class="form-group">
       <label class="control-label col-md-2">이용권명</label>
       <div class="col-md-10">
         <input type='text' name='product_name' required="required" 
                   autofocus="autofocus" class="form-control" style='width: 100%;'>
       </div>
    </div>
    <div class="form-group">
       <label class="control-label col-md-2">가격</label>
       <div class="col-md-10">
         <input type='number' name='product_price' value='0' required="required" autofocus="autofocus"
                    min="0" max="10000000" step="10" 
                    class="form-control" style='width: 100%;'>
       </div>
    </div>   
    <div class="form-group">
       <label class="control-label col-md-2">듣기 횟수<br>(음수는 무제한)</label>
       <div class="col-md-10">
         <input type='number' name='product_count' value='10' required="required"
                    step="1" 
                    class="form-control" style='width: 100%;'>
       </div>
    </div> 
    <div class="form-group">
       <label class="control-label col-md-2">사용기간<br>(일단위로 입력)</label>
       <div class="col-md-10">
         <input type='number' name='product_day' value='0' required="required"
                    min="0" max="10000000" 
                    class="form-control" style='width: 100%;'>
       </div>
    </div>                 
    <div class="form-group">
       <label class="control-label col-md-2">이용권 설명</label>
       <div class="col-md-10">
         <textarea name='product_cont' required="required" class="form-control" rows="12" style='width: 100%;'></textarea>
       </div>
    </div>
    <div class="form-group">
       <label class="control-label col-md-2">이미지</label>
       <div class="col-md-10">
         <input type='file' class="form-control" name='file1MF' id='file1MF' 
                    value='' placeholder="파일 선택">
       </div>
    </div>   
    <div class="content_body_bottom">
      <button type="submit" class="btn btn-primary">등록</button>
      <button type="button" onclick="location.href='./list.do'" class="btn btn-primary">목록</button>
    </div>
  
  </FORM>
</DIV>
 
<jsp:include page="../menu/bottom.jsp" flush='false' />
</body>
 
</html>

