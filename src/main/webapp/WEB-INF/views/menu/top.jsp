<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec"
  uri="http://www.springframework.org/security/tags"%>

<DIV class='container_main'>
<NAV class='top_menu'>
  <A href='/index.do'><img src='/images/top_logo.png' style='width: 30%;'></A>
    
      <span style='padding-left: 10%;'></span> 
      <A href='/index.do'>Home</A><span class='top_menu_sep'> </span>
      <A href='/artist/list_by_artistno_grid.do'>Artist</A><span class='top_menu_sep'> </span>
      <A href='/album/list_all_join.do'>Album</A><span class='top_menu_sep'> </span>
      <A href='/music/list_all_join.do'>Songs</A><span class='top_menu_sep'> </span>
      <A href='/notice/list.do'>Notice</A><span class='top_menu_sep'> </span>
      <A href='/board/list.do'>Board</A><span class='top_menu_sep'> </span>
      <A href='/product/list.do'>Product</A><span class='top_menu_sep'> </span>
      Member [ <c:choose>
          <c:when test="${sessionScope.id == null}">
            <%-- 로그인 안 한 경 우   --%>
            <A href='/member/login.do'>Login</A>
          </c:when>
          <c:otherwise>
          ${sessionScope.id } <A class='menu_link'
              href='/member/logout.do'>Logout </A>
          </c:otherwise>
        </c:choose> ]
        <span class='top_menu_sep'></span>
      Admin [ <A href="/admin/admin.do">Login</A>
      <A href='/member/list.do'>Member List</A>
      <sec:authorize access="isAuthenticated()">
        <span class='top_menu_sep'> </span>
        <a href="/admin/logout.do">Logout</a>
      </sec:authorize>
      ] 
    </NAV>
  </div>


  <%-- 내용 --%>
  <DIV class='content'>