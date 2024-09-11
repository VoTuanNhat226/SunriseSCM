<%@page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>

<%@ taglib prefix="s" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<header class="p-3 bg-success text-white sticky-top">
    <div class="container-fluid">
        <div class="d-flex flex-wrap align-items-center justify-content-center justify-content-lg-start">
            <a class="navbar-custom__logo d-flex align-items-center" href="<c:url value="/" />">
                SUNRISE ADMIN
            </a>

            <ul class="nav col-12 col-lg-auto me-lg-auto mb-2 justify-content-center mb-md-0">
                <li class="nav-item navbar-custome__menu--item">
                    <a class="nav-link px-2 text-white" href="<c:url value="/" />">Trang chủ</a>
                </li>
                <s:authorize access="hasAnyRole('ADMIN')">
                    <li class="nav-item navbar-custome__menu--item">
                        <a class="nav-link px-2 text-white" href="<c:url value="/admin/statistics" />">Thống kê</a>
                    </li>
                    <li class="nav-item dropdown">
                        <a class="nav-link dropdown-toggle text-white" href="javascript:void(0)" id="navbarDarkDropdownMenuLink" role="button" data-bs-toggle="dropdown"
                           aria-expanded="false">
                            Quản lý
                        </a>
                        <ul style="padding: 0; max-height: 500px; overflow-y: auto" class="dropdown-menu dropdown-menu-dark"
                            aria-labelledby="navbarDarkDropdownMenuLink">
                            <c:forEach var="entity" items="${entities}">
                                <c:url value="/admin/${entity['key']}" var="url"/>
                                <li style="padding: 4px;"><a href="${url}" class="dropdown-item">${entity.value}</a></li>
                            </c:forEach>
                        </ul>
                    </li>
                </s:authorize>
            </ul>

            <ul style="margin-bottom: 0; padding-left: 0" class="text-end d-flex align-items-center">
                <s:authorize access="!isAuthenticated()">
                    <li class="nav-item ms-auto">
                        <a class="btn btn-outline-light me-2" href="<c:url value="/login" />">Đăng nhập</a>
                    </li>
                </s:authorize>
                <s:authorize access="isAuthenticated()">
                    <li class="nav-item me-2 ms-auto">
                        <a class="nav-link px-2 text-white" href="javascript:void(0)">
                            Chào <s:authentication property="principal.username"/>
                        </a>
                    </li>
                    <li class="nav-item">
                        <a class="btn btn-outline-light" href="<c:url value="/logout" />">Đăng xuất</a>
                    </li>
                </s:authorize>
            </ul>
        </div>
    </div>
</header>

<script src="<c:url value="/js/header.js"/>"></script>
<script>
    const items = [];
    <c:forEach items="${entities}" var="entity">
    items.push({
        key: '${entity.key}',
        value: '${entity.value}'
    })
    </c:forEach>
</script>