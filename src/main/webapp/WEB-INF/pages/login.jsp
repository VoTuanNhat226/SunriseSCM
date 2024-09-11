<%@page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Đăng nhập</title>

    <link href="<c:url value="/vendor/bootstrap/css/bootstrap.min.css"/>" rel="stylesheet">
    <link href="<c:url value="/fonts/font-awesome-4.7.0/css/font-awesome.min.css"/>" rel="stylesheet">
    <link href="<c:url value="/fonts/Linearicons-Free-v1.0.0/icon-font.min.css"/>" rel="stylesheet">
    <link href="<c:url value="/vendor/animate/animate.css"/>" rel="stylesheet">
    <link href="<c:url value="/vendor/css-hamburgers/hamburgers.min.css"/>" rel="stylesheet">
    <link href="<c:url value="/vendor/animsition/css/animsition.min.css"/>" rel="stylesheet">
    <link href="<c:url value="/vendor/select2/select2.min.css"/>" rel="stylesheet">
    <link href="<c:url value="/vendor/daterangepicker/daterangepicker.css"/>" rel="stylesheet">
    <link href="<c:url value="/css/util.css"/>" rel="stylesheet">
</head>
<body style="background-color: #b9bbbe">
    <div class="limiter">
        <div class="container">
            <div class="row justify-content-center">
                <div class="col-md-6 col-lg-4" style="margin-top: 250px; padding: 20px 10px 20px 10px;">
                    <c:url value="/login" var="loginUrl"/>
                    <form method="post" class="validate-form form-group" action="${loginUrl}">
                        <h2 class="text-center mb-4">ĐĂNG NHẬP QUẢN TRỊ</h2>

                        <c:if test="${param.error}">
                            <p class="text-danger text-center" style="font-size: 16px; margin-bottom: 20px;">
                                Tên tài khoản hoặc mật khẩu không đúng
                            </p>
                        </c:if>
                        <c:if test="${param.accessDenied}">
                            <p class="text-danger text-center" style="font-size: 16px; margin-bottom: 20px;">
                                Tài khoản không có quyền truy cập
                            </p>
                        </c:if>

                        <div class="form-group" data-validate="Valid email is required: ex@abc.xyz">
                            <label for="username" class="">Tên tài khoản</label>
                            <input id="username" autofocus required class="form-control" type="text" name="username" placeholder="Nhập tên tài khoản"/>
                        </div>

                        <div class="form-group" data-validate="Password is required">
                            <label for="password" class="">Mật khẩu</label>
                            <input id="password" required class="form-control" type="password" name="password" placeholder="Nhập mật khẩu"/>
                        </div>

                        <div class="form-group text-center">
                            <button class="btn btn-success btn-block">Đăng nhập</button>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
    <script src="<c:url value='/vendor/jquery/jquery-3.2.1.min.js'/>"></script>
    <script src="<c:url value='/vendor/animsition/js/animsition.min.js'/>"></script>
    <script src="<c:url value='/vendor/bootstrap/js/popper.js'/>"></script>
    <script src="<c:url value='/vendor/bootstrap/js/bootstrap.min.js'/>"></script>
    <script src="<c:url value='/vendor/select2/select2.min.js'/>"></script>
    <script src="<c:url value='/vendor/daterangepicker/moment.min.js'/>"></script>
    <script src="<c:url value='/vendor/daterangepicker/daterangepicker.js'/>"></script>
    <script src="<c:url value='/vendor/countdowntime/countdowntime.js'/>"></script>
    <script src="<c:url value='/js/login.js'/>"></script>
</body>

</html>