<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
 
 <c:set var="ntitle" value="${noticeVO.ntitle }" />
 <c:set var="ncontent" value="${noticeVO.ncontent }" />
 <c:set var="word" value="${noticeVO.word }" />
 <c:set var="nnum" value="${noticeVO.nnum }" />
 
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
  $(function(){
 
  });
</script>
 
</head> 
 
<body>
<jsp:include page="../menu/top.jsp" flush='false' />
 
<DIV class='title_line'>
  <A href="../notice/list.do" class='title_link'>공지사항</A> > 공지 수정
</DIV>

<DIV class='content_body'>  
  <DIV class='menu_line'></DIV>
  
  <FORM name='frm' method='POST' action='./update.do' class="form-horizontal"
              enctype="multipart/form-data">
    <input type="hidden" name="nnum" value= "${nnum }">
    <input type="hidden" name="adminno" value="1"> <%-- 추후 변경 필요 --%>
    <input type="hidden" name="${ _csrf.parameterName }" value="${ _csrf.token }">
    
    <div class="form-group">
       <label class="control-label col-md-2">제목</label>
       <div class="col-md-10">
         <input type='text' name='ntitle' value='${ntitle }' required="required" 
                   autofocus="autofocus" class="form-control" style='width: 100%;'>
       </div>
    </div>
    <div class="form-group">
       <label class="control-label col-md-2">내용</label>
       <div class="col-md-10">
         <textarea name='ncontent' required="required" class="form-control" rows="12" style='width: 100%;'> ${ncontent } </textarea>
       </div>
    </div>
    <div class="form-group">
       <label class="control-label col-md-2">검색어</label>
       <div class="col-md-10">
         <input type='text' name='word' value='${word } ' class="form-control" style='width: 100%;'>
       </div>
    </div>   
    <div class="form-group">
       <label class="control-label col-md-2">이미지</label>
       <div class="col-md-10">
            <input type='file' name='nimage1MF' id='nimage1MF' value='' placeholder="파일 선택"><br>
            <div style='margin-top: 20px; clear: both;'>  
              <button type="submit" class="btn btn-primary">파일 변경 처리</button>
              <button type="button" onclick="history.back();" class="btn btn-primary">취소</button>
            </div>  
          </div>
         </div>  
  </FORM>
 </DIV>   
    <div class="content_body_bottom">
      <button type="submit" class="btn btn-primary">등록</button>
      <button type="button" onclick="location.href='./list.do'" class="btn btn-primary">목록</button>
    </div>

 
<jsp:include page="../menu/bottom.jsp" flush='false' />
</body>
 
</html>
  