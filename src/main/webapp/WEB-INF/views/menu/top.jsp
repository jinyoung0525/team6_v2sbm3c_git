<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec"  uri="http://www.springframework.org/security/tags" %>

<DIV class='container_main'> 
  <%-- 화면 상단 메뉴 --%>
  <DIV class='top_img'>
    <DIV class='top_menu_label'>Music</DIV>
    <NAV class='top_menu'>
      <span style='padding-left: 0.5%;'></span>
      <A class='menu_link'  href='/' >홈</A><span class='top_menu_sep'> </span> 
      <A class='menu_link'  href='/artist/list_by_artistno_grid.do'>아티스트</A><span class='top_menu_sep'> </span>
      
      <c:choose>
        <c:when test="${sessionScope.id == null}"> <%-- 로그인 안 한 경 우 --%>
          <A class='menu_link'  href='/member/login.do' >Login</A><span class='top_menu_sep'> </span>
        </c:when>
        <c:otherwise>
          ${sessionScope.id } <A class='menu_link'  href='/member/logout.do' >Logout</A><span class='top_menu_sep'> </span>
        </c:otherwise>
      </c:choose>     
                    
      관리자[
      <A class='menu_link'  href='/member/list.do'>회원</A><span class='top_menu_sep'> </span>
      <A href="/admin/admin.do" class="menu_link">관리자만 접속 가능</A>
      <sec:authorize access="isAuthenticated()">
        <span class='top_menu_sep'> </span>
        <a href="/admin/logout.do" class="menu_link">로그아웃</a>
      </sec:authorize>
      ]
    </NAV>
  </DIV>
  
  <%-- 내용 --%> 
  <DIV class='content'>