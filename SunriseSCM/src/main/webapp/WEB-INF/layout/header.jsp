<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/security/tags" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<nav class="navbar navbar-expand-sm bg-dark navbar-dark">
    <div class="container-fluid">
        <a class="navbar-brand" href="#">SunriseSCM Admin</a>
        <div class="collapse navbar-collapse" id="collapsibleNavbar">
            <ul class="navbar-nav">
                <li class="nav-item">
                    <a class="nav-link" href="<c:url value='/admin' />">Trang chủ</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="<c:url value='/admin/accounts' />">Danh sách người dùng</a>
                </li>
                <s:authorize access="!isAuthenticated()">
                    <li class="nav-item">
                        <a class="nav-link" href="<c:url value='/login' />">Đăng nhập</a>
                    </li>
                </s:authorize>
                <s:authorize access="isAuthenticated()">
                    <li class="nav-item">
                        <span class="nav-link">
                            Welcome <s:authentication property="principal.username" />!
                        </span>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="<c:url value='/logout' />">Đăng xuất</a>
                    </li>
                </s:authorize>
            </ul>
        </div>
    </div>
</nav>

